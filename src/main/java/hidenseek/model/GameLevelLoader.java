package hidenseek.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

public class GameLevelLoader{
    private final Set<Wall> walls = new LinkedHashSet<>();
    private final Set<Player> players = new LinkedHashSet<>();
    private final Set<Monster> monsters = new LinkedHashSet<>();
    private final Map<PowerUpType, List<Entity>> powerUps = new HashMap<>();
    private final Set<Key> keys = new LinkedHashSet<>();
    private Document document;
    
    public enum LevelElements{
        
        LEVEL_NAME("name"),
        LEVEL_GRAVITY("gravity"),
        LEVEL_MAX_TIME("maximumTime"),
        LEVEL_POSITION("position"),
        LEVEL_WALL("wall"),
        LEVEL_PLAYER("player"),
        LEVEL_MONSTER("monster"),
        LEVEL_POWERUP("powerup"),
        LEVEL_KEY("key");

        private final String text;
        
        LevelElements(final String text) {
            this.text = text;
        }
        
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
        parseMonsters();
        parsePlayers();
        parsePowerUps();
        parseKeys();
    }
    
    public String getLevelName() {
        return this.document.getDocumentElement().getAttribute(LevelElements.LEVEL_NAME.getName());
    }
    
    public double getLevelGravity() {
        return Double.parseDouble(this.document.getDocumentElement().getAttribute(LevelElements.LEVEL_GRAVITY.getName()));                                            
    }
    
    public int getLevelMaxTime () {
        return Integer.parseInt(this.document.getDocumentElement().getAttribute(LevelElements.LEVEL_MAX_TIME.getName()));
    }
    
    private void parseWalls() {
        final NodeList xmlWalls = this.document.getElementsByTagName(LevelElements.LEVEL_WALL.getName());      
        
        for(int i=0; i<xmlWalls.getLength(); i++){
            final Set<Point2D> wallVertices = new LinkedHashSet <>();
            final Node xmlWall = xmlWalls.item(i);
            final Point2D wallPosition = deserializePoint(xmlWall.getAttributes().getNamedItem(LevelElements.LEVEL_POSITION.getName()).getNodeValue());
            final NodeList xmlWallVertices = xmlWall.getChildNodes();
            for(int y=0; y<xmlWallVertices.getLength(); y++){
                if(!"vertex".equals(xmlWallVertices.item(y).getNodeName())) {
                    continue;
                }
                final Node xmlWallVertex = xmlWallVertices.item(y);
                final Point2D wallVertexPosition = deserializePoint(xmlWallVertex.getAttributes().getNamedItem(LevelElements.LEVEL_POSITION.getName()).getNodeValue());
                wallVertices.add(wallVertexPosition);
            }
            walls.add(new Wall(wallPosition, wallVertices));
        }
                
    }
    
    private void parsePlayers() {
        final NodeList xmlPlayers = this.document.getElementsByTagName(LevelElements.LEVEL_PLAYER.getName());
        final Stream<Node> xmlPlayersStream = IntStream.range(0, xmlPlayers.getLength()).mapToObj(xmlPlayers::item);
        xmlPlayersStream.forEach(e -> this.players.add(new Player(deserializePoint(e.getAttributes().getNamedItem(LevelElements.LEVEL_POSITION.getName()).getNodeValue()))));
       
    }
    
    private void parseMonsters() {
        final NodeList xmlMonsters = this.document.getElementsByTagName(LevelElements.LEVEL_MONSTER.getName());

        final Stream<Node> xmlMonstersStream = IntStream.range(0, xmlMonsters.getLength()).mapToObj(xmlMonsters::item);
        xmlMonstersStream.forEach(e -> this.monsters.add(new Monster(deserializePoint(e.getAttributes().getNamedItem(LevelElements.LEVEL_POSITION.getName()).getNodeValue()))));
        
    }
    
    private void parsePowerUps() {
        final NodeList xmlPowerUps = this.document.getElementsByTagName(LevelElements.LEVEL_POWERUP.getName());

        final Stream<Node> xmlPowerUpsStream = IntStream.range(0, xmlPowerUps.getLength()).mapToObj(xmlPowerUps::item);
        xmlPowerUpsStream.forEach(e -> this.addPowerup(deserializePoint(e.getAttributes().getNamedItem(LevelElements.LEVEL_POSITION.getName()).getNodeValue()),PowerUpType.generateRandomType()));
                
    }
    
    private void parseKeys() {
        final NodeList xmlKeys = this.document.getElementsByTagName(LevelElements.LEVEL_KEY.getName());
        
        final Stream<Node> xmlKeysStream = IntStream.range(0, xmlKeys.getLength()).mapToObj(xmlKeys::item);
        xmlKeysStream.forEach(e -> this.keys.add(new Key(deserializePoint(e.getAttributes().getNamedItem(LevelElements.LEVEL_POSITION.getName()).getNodeValue()))));
        
    }

    public Set<EntityController> getEntities() {
        final Set<EntityController> controllers = new LinkedHashSet<>();

        this.walls.forEach(wall ->controllers.add(new WallControllerImpl(wall)));
        
        this.powerUps.forEach((type, powerups) -> 
            powerups.forEach(powerup -> controllers.add(new PowerUpControllerImpl(type, powerup))));

        this.keys.forEach(key -> controllers.add(new KeyControllerImpl(key)));
       
        this.monsters.forEach(monster -> controllers.add(new MonsterControllerImpl(monster)));

        this.players.forEach(player ->controllers.add(new PlayerControllerImpl(player)));

        return controllers;
    }
    
    public Set<HudController> getHuds() {
        final Set<HudController> huds = new LinkedHashSet<>();
        final KeyHudView keyHudView = new KeyHudViewImpl(new Point2D(1400, 30));
        keyHudView.setMaxKeys(this.getKeysNumber());
        final HudController keyHud = new KeyHudControllerImpl(Set.copyOf(this.players), keyHudView);
        huds.add(keyHud);
        return huds;
    }
    
    public int getKeysNumber() {
        return this.keys.size();
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
    
}
