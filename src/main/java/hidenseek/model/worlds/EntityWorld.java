package hidenseek.model.worlds;

import hidenseek.model.entities.Entity;

public interface EntityWorld extends World<Entity>{
    
    /**
     * 
     * @param e Entity to be added
     */
    void addEntity(Entity e);
    
    /**
     * 
     * @param e Entity to be removed
     */
    void removeEntity(Entity e);
}
