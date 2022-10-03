package hidenseek.model.components;

import java.util.Optional;

import hidenseek.model.Entity;

public interface Component {
    
    void attach(Entity e);
    
    void detach();
    
    Optional<Entity> getOwner();
    
    void setOwner(Entity e);
}
