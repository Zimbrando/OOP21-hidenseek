package hidenseek.model;

import java.util.Set;
import hidenseek.controller.HudController;
import hidenseek.controller.entities.EntityController;
import hidenseek.model.statistics.StatisticsManager;

public interface GameLevel {
    
    int getLevelID();
    
    String getLevelName();
    
    double getLevelGravity();
    
    int getLevelMaximumTime();
    
    Set<EntityController> getEntities();
    
    Set<HudController> getHuds();
    
    int getKeysNumber();
    
}
