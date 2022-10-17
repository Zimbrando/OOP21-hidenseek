package hidenseek.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import hidenseek.model.entities.Entity;
import hidenseek.model.enums.PowerUpType;

public interface GameLevel {

    Set<Entity> getWalls();
    
    Map<PowerUpType, List<Entity>> getPowerUps();
    
    Set<Entity> getKeys();
}
