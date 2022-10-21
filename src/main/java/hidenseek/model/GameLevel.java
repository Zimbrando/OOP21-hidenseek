package hidenseek.model;

import java.util.LinkedHashSet;
import java.util.Set;

import hidenseek.controller.HudController;
import hidenseek.controller.entities.EntityController;
import hidenseek.model.entities.Key;
import hidenseek.model.entities.Monster;
import hidenseek.model.entities.Player;
import hidenseek.model.entities.PowerUp;
import hidenseek.model.entities.Wall;

public interface GameLevel {
    
    Set<Wall> getWalls();
    Set<Player> getPlayers();
    Set<Monster> getMonsters();
    Set<PowerUp> getPowerUps();
    Set<Key> getKeys();
    
    
    //Set<EntityController> getEntities();
    
    //Set<HudController> getHuds();
    
    //int keysInLevel();
}
