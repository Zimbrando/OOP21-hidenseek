package hidenseek.model.components;

import java.util.Optional;

import hidenseek.model.Entity;

public interface Component {
    
    void detach();
    
    Optional<Entity> getOwner();
}
