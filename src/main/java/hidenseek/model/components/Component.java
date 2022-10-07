package hidenseek.model.components;

import java.util.Optional;

import hidenseek.model.entities.Entity;

public interface Component {
    
    void attach(Entity e);
    
    void detach();
    
    Optional<Entity> getOwner();
    
    void setOwner(Entity e);
}
