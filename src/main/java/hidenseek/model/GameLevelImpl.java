package hidenseek.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
import hidenseek.view.KeyHudView;
import hidenseek.view.KeyHudViewImpl;
import javafx.geometry.Point2D;

public class GameLevelImpl implements GameLevel {

    private final Set<Wall> walls = new LinkedHashSet<>();
    private final Set<Player> players = new LinkedHashSet<>();
    private final Set<Monster> monsters = new LinkedHashSet<>();
    private final Map<PowerUpType, List<Entity>> powerUps = new HashMap<>();
    private final Set<Key> keys = new LinkedHashSet<Key>();
    
    public GameLevelImpl(final int levelID) {
        try {
            
           final File inputFile = new File(getClass().getResource("/levels/level" + levelID + ".xml").getFile());
           final Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputFile);
           doc.getDocumentElement().normalize();
           
           final NodeList xmlWalls = doc.getElementsByTagName("wall");
           final NodeList xmlPlayers = doc.getElementsByTagName("player");
           final NodeList xmlMonsters = doc.getElementsByTagName("monster");
           final NodeList xmlPowerUps = doc.getElementsByTagName("powerup");
           final NodeList xmlKeys = doc.getElementsByTagName("key");
           
           //parse Walls
           for(int i=0; i<xmlWalls.getLength(); i++){
               final Set<Point2D> wallVertices = new LinkedHashSet <Point2D>();               
               final Node xmlWall = xmlWalls.item(i);
               final Point2D wallPosition = deserializePoint(xmlWall.getAttributes().getNamedItem("position").getNodeValue());
               final NodeList xmlWallVertices = xmlWall.getChildNodes();
               for(int y=0; y<xmlWallVertices.getLength(); y++){
                   if(xmlWallVertices.item(y).getNodeName() != "vertex") {
                       continue;
                   }
                   final Node xmlWallVertex = xmlWallVertices.item(y);
                   final Point2D wallVertexPosition = deserializePoint(xmlWallVertex.getAttributes().getNamedItem("position").getNodeValue());
                   wallVertices.add(wallVertexPosition);
               }
               walls.add(new Wall(wallPosition, wallVertices));               
           }
           
           //parse Players
           for(int i=0; i<xmlPlayers.getLength(); i++){              
               final Node xmlPlayer = xmlPlayers.item(i);
               final Point2D playerPosition = deserializePoint(xmlPlayer.getAttributes().getNamedItem("position").getNodeValue());
               final Player player = new Player(playerPosition);
               players.add(player);               
           }
           
           //parse Monsters
           for(int i=0; i<xmlMonsters.getLength(); i++){              
               final Node xmlMonster = xmlMonsters.item(i);
               final Point2D monsterPosition = deserializePoint(xmlMonster.getAttributes().getNamedItem("position").getNodeValue());
               final Monster monster = new Monster(monsterPosition);
               monsters.add(monster);               
           }
           
           //parse PowerUps
           for(int i=0; i<xmlPowerUps.getLength(); i++){              
               final Node xmlPowerUp = xmlPowerUps.item(i);
               final Point2D powerUpPosition = deserializePoint(xmlPowerUp.getAttributes().getNamedItem("position").getNodeValue());
               final PowerUpType type = PowerUpType.generateRandomType();
               this.addPowerup(powerUpPosition, type);              
           }
           
           //parse Keys
           for(int i=0; i<xmlKeys.getLength(); i++){              
               final Node xmlKey = xmlKeys.item(i);
               Point2D keyPosition = deserializePoint(xmlKey.getAttributes().getNamedItem("position").getNodeValue());
               Key key = new Key(keyPosition);
               keys.add(key);               
           }
           
           
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
    
    private Point2D deserializePoint(String xmlValue) {
        String[] coordinates = xmlValue.split(",");
        return new Point2D(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
    }
    
    private void addPowerup(final Point2D pos, final PowerUpType type) {
        if (this.powerUps.containsKey(type)) {
            List<Entity> typeEntity = powerUps.get(type);
            typeEntity.add(new PowerUp(type, pos));
            powerUps.put(type, typeEntity);
        } else {
            List<Entity> typeEntity = new ArrayList<>(List.of(new PowerUp(type, pos)));
            powerUps.put(type, typeEntity);    
        }
    }

    @Override
    public Set<EntityController> getEntities() {
        final Set<EntityController> controllers = new LinkedHashSet<>();

        this.walls.forEach(wall -> {
            controllers.add(new WallControllerImpl(wall));
        });

        this.powerUps.forEach((type, powerups) -> {
            powerups.forEach(powerup -> {
                controllers.add(new PowerUpControllerImpl(type, powerup));        
            });
        });

        this.keys.forEach(key -> {
            controllers.add(new KeyControllerImpl(key));
        });

        this.players.forEach(player -> {
            final EntityController playerController = new PlayerControllerImpl(player);
            controllers.add(playerController);   
        });
       
        this.monsters.forEach(monster -> {
            final EntityController monsterController = new MonsterControllerImpl(monster);
            controllers.add(monsterController);            
        });

        return controllers;
    }

    @Override
    public Set<HudController> getHuds() {
        final Set<HudController> huds = new LinkedHashSet<>();
        final KeyHudView keyHudView = new KeyHudViewImpl(new Point2D(1400, 30));
        keyHudView.setMaxKeys(this.getKeysNumber());
        final HudController keyHud = new KeyHudControllerImpl(this.players.stream().findAny().get(), keyHudView);
        huds.add(keyHud);
        return huds;
    }

    @Override
    public int getKeysNumber() {
        return this.keys.size();
    }

}

