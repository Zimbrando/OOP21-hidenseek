package hidenseek.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import hidenseek.controller.HudController;
import hidenseek.controller.KeyHudControllerImpl;
import hidenseek.controller.entities.EntityController;
import hidenseek.controller.entities.KeyControllerImpl;
import hidenseek.controller.entities.MonsterControllerImpl;
import hidenseek.controller.entities.PlayerControllerImpl;
import hidenseek.controller.entities.PowerUpControllerImpl;
import hidenseek.controller.entities.WallControllerImpl;
import hidenseek.model.entities.Entity;
import hidenseek.model.entities.Key;
import hidenseek.model.entities.Monster;
import hidenseek.model.entities.Player;
import hidenseek.model.entities.PowerUp;
import hidenseek.model.entities.Wall;
import hidenseek.model.enums.PowerUpType;
import hidenseek.view.huds.KeyHudView;
import hidenseek.view.huds.KeyHudViewImpl;
import javafx.geometry.Point2D;

public class GameLevelLoader {
    private final Set<Wall> walls = new LinkedHashSet<>();
    private final Set<Player> players = new LinkedHashSet<>();
    private final Set<Monster> monsters = new LinkedHashSet<>();
    private final Map<PowerUpType, List<Entity>> powerUps = new HashMap<>();
    private final Set<Key> keys = new LinkedHashSet<>();
    private final Set<Point2D> hudsPositions = new LinkedHashSet<>();
    private Document document;
    
    /**
     * The names of the possible elements in the xml level files.
     */
    
    public enum LevelElements{
        
        LEVEL_NAME("name"),
        LEVEL_MAX_TIME("maximumTime"),
        LEVEL_POSITION("position"),
        LEVEL_WALL("wall"),
        LEVEL_PLAYER("player"),
        LEVEL_MONSTER("monster"),
        LEVEL_POWERUP("powerup"),
        LEVEL_KEY("key"),
        LEVEL_HUD("hud");

        private final String text;
        
        LevelElements(final String text) {
            this.text = text;
        }
        
        /**
         * 
         * @return The name of the enum value.
         */
        
        public String getName() {
            return text;
        }
        
    }
    
        
    public GameLevelLoader(final int levelID){

        final InputStream inputFile = getClass().getResourceAsStream("/levels/level" + levelID + ".xml");
        
        try {
            this.document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputFile);
            this.document.getDocumentElement().normalize();
                       
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        
        try {
            inputFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        parseWalls();
        parseEntityByType(LevelElements.LEVEL_MONSTER, e-> this.monsters.add(new Monster(deserializePoint(getNodePosition(e)))));
        parseEntityByType(LevelElements.LEVEL_PLAYER, e -> this.players.add(new Player(deserializePoint(getNodePosition(e)))));
        parseEntityByType(LevelElements.LEVEL_POWERUP, e -> this.addPowerup(deserializePoint(getNodePosition(e)), PowerUpType.generateRandomType()));
        parseEntityByType(LevelElements.LEVEL_KEY, e -> this.keys.add(new Key(deserializePoint(getNodePosition(e)))));
        parseEntityByType(LevelElements.LEVEL_HUD, e -> this.hudsPositions.add(deserializePoint(getNodePosition(e))));
        
    }
   
    private void parseWalls() {
        final NodeList xmlWalls = this.document.getElementsByTagName(LevelElements.LEVEL_WALL.getName());      
        
        for (int i = 0; i < xmlWalls.getLength(); i++) {
            final Set<Point2D> wallVertices = new LinkedHashSet <>();
            final Node xmlWall = xmlWalls.item(i);
            final Point2D wallPosition = deserializePoint(getNodePosition(xmlWall));
            final NodeList xmlWallVertices = xmlWall.getChildNodes();
            for (int y = 0; y < xmlWallVertices.getLength(); y++) {
                if (!"vertex".equals(xmlWallVertices.item(y).getNodeName())) {
                    continue;
                }
                final Node xmlWallVertex = xmlWallVertices.item(y);
                final Point2D wallVertexPosition = deserializePoint(getNodePosition(xmlWallVertex));
                wallVertices.add(wallVertexPosition);
            }
            this.walls.add(new Wall(wallPosition, wallVertices));
        }
                
    }
    
    private void parseEntityByType(final LevelElements type, final Consumer<Node> action) {
        final NodeList nodeList = this.document.getElementsByTagName(type.getName());
        final Stream<Node> nodeListStream = IntStream.range(0, nodeList.getLength()).mapToObj(nodeList::item);
        
        nodeListStream.forEach(e -> action.accept(e));
        
    }
    
    private String getNodePosition(final Node node) {
        return node.getAttributes().getNamedItem(LevelElements.LEVEL_POSITION.getName()).getNodeValue();
    }
    
    private Point2D deserializePoint(final String xmlValue) {
        final String[] coordinates = xmlValue.split(",");
        return new Point2D(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
    }
    
    private void addPowerup(final Point2D pos, final PowerUpType type) {
        if (this.powerUps.containsKey(type)) {
            final List<Entity> typeEntity = powerUps.get(type);
            typeEntity.add(new PowerUp(type, pos));
            powerUps.put(type, typeEntity);
        } else {
            final List<Entity> typeEntity = new ArrayList<>(List.of(new PowerUp(type, pos)));
            powerUps.put(type, typeEntity);    
        }
    }
    
    /**
     * 
     * @return The level name parsed from the file.
     */
    
    public String getLevelName() {
        return this.document.getDocumentElement().getAttribute(LevelElements.LEVEL_NAME.getName());
    }
    
    /**
     * 
     * @return The maximum amount of time to get the best score. This information is parsed from the file.
     */
    
    public int getLevelMaxTime () {
        return Integer.parseInt(this.document.getDocumentElement().getAttribute(LevelElements.LEVEL_MAX_TIME.getName()));
    }

    /**
     * 
     * @return The complete set of entities in the game.
     */
    
    public Set<EntityController> getEntities() {
        final Set<EntityController> controllers = new LinkedHashSet<>();

        this.walls.forEach(wall -> controllers.add(new WallControllerImpl(wall)));
        
        this.powerUps.forEach((type, powerups) -> powerups.forEach(powerup -> controllers.add(new PowerUpControllerImpl(type, powerup))));

        this.keys.forEach(key -> controllers.add(new KeyControllerImpl(key)));
       
        this.monsters.forEach(monster -> controllers.add(new MonsterControllerImpl(monster)));

        this.players.forEach(player -> controllers.add(new PlayerControllerImpl(player)));

        return controllers;
    }
    
    /**
     * 
     * @return The keys HUDs.
     */
    
    public Set<HudController> getHuds() {
        final Set<HudController> huds = new LinkedHashSet<>();
        final KeyHudView keyHudView = new KeyHudViewImpl(this.hudsPositions.stream().findAny().get());
        keyHudView.setMaxKeys(this.getKeysNumber());
        final HudController keyHud = new KeyHudControllerImpl(Set.copyOf(this.players), keyHudView);
        huds.add(keyHud);
        return huds;
    }
    
    /**
     * 
     * @return The numbers of keys of the level.
     */
    
    public int getKeysNumber() {
        return this.keys.size();
    }
    
}
