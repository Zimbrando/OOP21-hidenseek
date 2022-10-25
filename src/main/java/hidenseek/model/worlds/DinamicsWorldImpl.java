package hidenseek.model.worlds;

import java.util.Optional;

import hidenseek.model.components.physics.CollisionComponent;
import hidenseek.model.components.physics.Force;
import hidenseek.model.components.physics.MaterialComponent;
import hidenseek.model.components.physics.MoveComponent;
import hidenseek.model.components.physics.PositionComponent;
import javafx.geometry.Point2D;

public class DinamicsWorldImpl extends AbstractEntityWorldImpl {

    @Override
    public void update(final double delta) {
        this.handleCollisions(delta);
    }
    

    private void handleCollisions(final double delta) {
        
        //handle movement of entities
        this.world().stream().forEach(entity -> {

            final Optional<PositionComponent> positionComponent = entity.getComponent(PositionComponent.class);
            
            if (!positionComponent.isPresent()) {
                return;
            }
            final Optional<MoveComponent> moveComponent = entity.getComponent(MoveComponent.class);
            
            if (!moveComponent.isPresent()) {
                return;
            }
            final Optional<CollisionComponent> collisionComponent = entity.getComponent(CollisionComponent.class);
            final Optional<MaterialComponent> materialComponent = entity.getComponent(MaterialComponent.class);


            Point2D resultantOffset = new Point2D(0, 0);
            for (final Force force : moveComponent.get().getForces().toArray(new Force[0])) {
                if (!collisionComponent.isPresent()) {
                    return;
                }
                
                final double speedMultiplier = moveComponent.get().getSpeed();
                
                int forceX = (int)Math.abs(force.getXComponent() * speedMultiplier * delta);
                final int forceXSign = force.getXComponent() < 0 ? -1 : 1;
                Boolean forceXAccepted = false;
                
                int forceY = (int)Math.abs(force.getYComponent() * speedMultiplier * delta);
                final int forceYSign = force.getYComponent() < 0 ? -1 : 1;
                Boolean forceYAccepted = false;
                
                if (!materialComponent.isPresent()) {
                    resultantOffset = resultantOffset.add(new Point2D(forceX * forceXSign, forceY * forceYSign));
                    continue;
                }
                
                //try to move horizontally entity with an offset as the force intensity.
                //if movement is not allowed for a collision, decrease the offset until it gets accepted by the collision component. 
                while (forceX > 0 && !forceXAccepted) {
                    final int finalForceX = forceX * forceXSign;
                    if (allowMovement(collisionComponent.get(), new Point2D(finalForceX, 0))) {                       
                        resultantOffset = resultantOffset.add(new Point2D(finalForceX, 0));
                        forceXAccepted = true;
                        break;
                    }
                    forceX--;
                }

                //try to move vertically entity with an offset as the force intensity.
                //if movement is not allowed for a collision, decrease the offset until it gets accepted by the collision component. 
                while (forceY > 0 && !forceYAccepted) {
                    final int finalForceY = forceY * forceYSign;
                    if (allowMovement(collisionComponent.get(), new Point2D(0, finalForceY))) {                        
                        resultantOffset = resultantOffset.add(new Point2D(0, finalForceY));
                        forceYAccepted = true;
                        break;
                    }
                    forceY--;
                }
            }
           
            positionComponent.get().setPosition(positionComponent.get().getPosition().add(resultantOffset));
            
        });
        
        //handle the collisions between entities
        this.world().stream().forEach(entity -> {
            final Optional<CollisionComponent> collisionComponent = entity.getComponent(CollisionComponent.class);
            if(!collisionComponent.isPresent()) {
                return;
            }
            
            this.world().stream().forEach(entity1 -> {
                final Optional<CollisionComponent> entityCollisionComponent = entity1.getComponent(CollisionComponent.class);
                if(!entityCollisionComponent.isPresent()) {
                    return;
                }
                
                if(collisionComponent.get().collisionTo(entity1, new Point2D(0, 0))){
                    collisionComponent.get().onCollision(entity1);
                    entityCollisionComponent.get().onCollision(entity);
                }
                
                if(collisionComponent.get().nearTo(entity1, new Point2D(0, 0))){
                    collisionComponent.get().onNear(entity);
                    entityCollisionComponent.get().onNear(entity);
                }
            });
        });
        
    }
    
    private Boolean allowMovement(CollisionComponent collisionComponent, Point2D offset) {
        return (this.world().stream().noneMatch(entity1 -> {
            return entity1.hasComponent(MaterialComponent.class) && collisionComponent.collisionTo(entity1, offset);
        }));
    }

}
