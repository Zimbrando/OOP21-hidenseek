package hidenseek.model.components;

import java.util.Optional;

import hidenseek.model.entities.Entity;

/**
 * Basic component
 */
public interface Component {
    
    /**
     * Attach to a specified Entity
     * If the component has already an owner the method throws an exception
     * @param e
     *          The entity
     */
    void attach(Entity e);
    
    /**
     * Detach from the current Entity
     */
    void detach();
    
    /**
     * @return The current owner: the Entity that this component is attached to 
     */
    Optional<Entity> getOwner();
    
    /**
     * Sets the owner of this component to an Entity
     * @param e
     */
    void setOwner(Entity e);
}
