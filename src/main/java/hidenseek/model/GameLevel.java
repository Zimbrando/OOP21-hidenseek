package hidenseek.model;

import java.util.Set;

import hidenseek.controller.entities.EntityController;
import hidenseek.controller.huds.HudController;

public interface GameLevel {
    
    /**
     * 
     * @return The game level identifier.
     */
    
    int getLevelID();
    
    /**
     * 
     * @return The level name.
     */
    
    String getLevelName();
        
    /**
     * 
     * @return The maximum amount of time to get the best score.
     */
    
    int getLevelMaximumTime();
    
    /**
     * 
     * @return The game entities.
     */
    
    Set<EntityController> getEntities();
    
    /**
     * 
     * @return The game HUDs.
     */
    
    Set<HudController> getHuds();
    
    /**
     * 
     * @return The level's number of keys.
     */
    
    int getKeysNumber();
    
}
