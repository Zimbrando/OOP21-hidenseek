package hidenseek.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import hidenseek.controller.HudController;
import hidenseek.controller.KeyHudControllerImpl;
import hidenseek.controller.entities.EntityController;
import hidenseek.controller.entities.EntityControllerImpl;
import hidenseek.controller.entities.MovableEntityControllerImpl;
import hidenseek.controller.entities.PlayerControllerImpl;
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
import hidenseek.view.entities.KeyView;
import hidenseek.view.entities.KeyViewImpl;
import hidenseek.view.entities.MonsterViewImpl;
import hidenseek.view.entities.PowerUpView;
import hidenseek.view.entities.PowerUpViewImpl;
import hidenseek.view.entities.WallView;
import hidenseek.view.entities.WallViewImpl;
import javafx.geometry.Point2D;

public class GameLevelImpl implements GameLevel {

    private final Set<Wall> walls = new LinkedHashSet<Wall>();
    private final Set<Player> players = new LinkedHashSet<Player>();
    private final Set<Monster> monsters = new LinkedHashSet<Monster>();
    private final Set<PowerUp> powerUps = new LinkedHashSet<PowerUp>();
    private final Set<Key> keys = new LinkedHashSet<Key>();
    
    public GameLevelImpl(final int levelID) {

        try {
           File inputFile = new File("src\\main\\resources\\levels\\level" + levelID + ".xml");
           Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputFile);
           doc.getDocumentElement().normalize();
           
           NodeList xmlWalls = doc.getElementsByTagName("wall");
           NodeList xmlPlayers = doc.getElementsByTagName("player");
           NodeList xmlMonsters = doc.getElementsByTagName("monster");
           NodeList xmlPowerUps = doc.getElementsByTagName("powerup");
           NodeList xmlKeys = doc.getElementsByTagName("key");
           
           //parse Walls
           for(int i=0; i<xmlWalls.getLength(); i++){
               Set<Point2D> wallVertices = new LinkedHashSet <Point2D>();               
               Node xmlWall = xmlWalls.item(i);
               Point2D wallPosition = deserializePoint(xmlWall.getAttributes().getNamedItem("position").getNodeValue());
               NodeList xmlWallVertices = xmlWall.getChildNodes();
               for(int y=0; y<xmlWallVertices.getLength(); y++){
                   if(xmlWallVertices.item(y).getNodeName() != "vertex") {
                       continue;
                   }
                   Node xmlWallVertex = xmlWallVertices.item(y);
                   Point2D wallVertexPosition = deserializePoint(xmlWallVertex.getAttributes().getNamedItem("position").getNodeValue());
                   wallVertices.add(wallVertexPosition);
               }
               walls.add(new Wall(wallPosition, wallVertices));               
           }
           
           //parse Players
           for(int i=0; i<xmlPlayers.getLength(); i++){              
               Node xmlPlayer = xmlPlayers.item(i);
               Point2D playerPosition = deserializePoint(xmlPlayer.getAttributes().getNamedItem("position").getNodeValue());
               Player player = new Player(playerPosition);
               players.add(player);               
           }
           
           //parse Monsters
           for(int i=0; i<xmlMonsters.getLength(); i++){              
               Node xmlMonster = xmlMonsters.item(i);
               Point2D monsterPosition = deserializePoint(xmlMonster.getAttributes().getNamedItem("position").getNodeValue());
               Monster monster = new Monster(monsterPosition);
               monsters.add(monster);               
           }
           
           //parse PowerUps
           for(int i=0; i<xmlPowerUps.getLength(); i++){              
               Node xmlPowerUp = xmlPowerUps.item(i);
               int powerUpType = Integer.parseInt(xmlPowerUp.getAttributes().getNamedItem("type").getNodeValue());
               Point2D powerUpPosition = deserializePoint(xmlPowerUp.getAttributes().getNamedItem("position").getNodeValue());
               PowerUp powerUp = new PowerUp(PowerUpType.getValue(powerUpType), powerUpPosition);
               powerUps.add(powerUp);               
           }
           
           //parse Keys
           for(int i=0; i<xmlKeys.getLength(); i++){              
               Node xmlKey = xmlKeys.item(i);
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

    @Override
    public Set<Wall> getWalls() {
        return walls;
    }

    @Override
    public Set<Player> getPlayers() {
        return players;
    }

    @Override
    public Set<Monster> getMonsters() {
        return monsters;
    }

    @Override
    public Set<PowerUp> getPowerUps() {
        return powerUps;
    }

    @Override
    public Set<Key> getKeys() {
        return keys;
    }

}

