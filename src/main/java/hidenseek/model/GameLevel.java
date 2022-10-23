package hidenseek.model;

import java.util.Set;
import hidenseek.controller.HudController;
import hidenseek.controller.entities.EntityController;

public interface GameLevel {
    
    /**
     * 
     * @return the game level identificator.
     */
    
    int getLevelID();
    
    /**
     * 
     * @return the level name.
     */
    
    String getLevelName();
    
    /**
     * 
     * @return the level gravity.
     */
    
    double getLevelGravity();
    
    /**
     * 
     * @return the level maximum time of completion.
     */
    
    int getLevelMaximumTime();
    
    /**
     * 
     * @return the parsed entities from file.
     */
    
    Set<EntityController> getEntities();
    
    /**
     * 
     * @return the HUDs from file.
     */
    Set<HudController> getHuds();
    
    /**
     * 
     * @return the parsed number of keys.
     */
    
    int getKeysNumber();
    
}
