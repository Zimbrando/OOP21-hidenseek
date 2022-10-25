package hidenseek.model.components.physics;

import javafx.geometry.Point2D;

import hidenseek.model.components.Component;
import hidenseek.model.entities.Entity;

public interface CollisionComponent extends Component {

    /**
     * @return The component's Hitbox
     */
    Hitbox getHitbox();

    /**
     * Must be called by the physics engine when owner collides with an Entity 
     * @param entity that collides with owner
     */
    void onCollision(Entity entity);
    
    /**
     * Must be called by the physics engine when owner is near another Entity 
     * @param entity near the owner
     */
    void onNear(Entity entity);

    /** 
     * @param entity
     *          target entity
     * @param ownOffset
     *          owner offset
     * @return if owner will collide with specified entity if owner moves by specified offset  
     */
    Boolean collisionTo(Entity entity, Point2D ownOffset);

    /**
     * @param entity
     *          target entity
     * @param ownOffset
     *          owner offset
     * @return if owner will be near to specified entity if owner moves by specified offset 
     */
    Boolean nearTo(Entity entity, Point2D ownOffset);

    /**
     * @param entity
     *          target entity
     * @param ownOffset
     *          owner offset
     * @return if owner will be near or in collision with specified entity if owner moves by specified offset 
     */
    Boolean collisionOrNearTo(Entity entity, Point2D ownOffset);
    
}
