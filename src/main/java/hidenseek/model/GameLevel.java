package hidenseek.model;

import java.util.Set;
import hidenseek.controller.HudController;
import hidenseek.controller.entities.EntityController;

public interface GameLevel {
    
    Set<EntityController> getEntities();
    
    Set<HudController> getHuds();
    
    int getKeysNumber();
}
