package hidenseek.model.worlds;

import java.util.Optional;
import hidenseek.model.components.CollisionComponent;
import hidenseek.model.components.Force;
import hidenseek.model.components.MaterialComponent;
import hidenseek.model.components.MoveComponent;
import hidenseek.model.components.PositionComponent;
import javafx.geometry.Point2D;

public class DinamicsWorldImpl extends AbstractEntityWorldImpl {

    @Override
    public void update() {
        //TODO move entities - apply Forces

        // check collisions
        this.handleCollisions();
    }
    

    private void handleCollisions() {
        this.world().stream().forEach(entity -> {

            Optional<PositionComponent> positionComponent = entity.getComponent(PositionComponent.class);
            Optional<MoveComponent> moveComponent = entity.getComponent(MoveComponent.class);
            Optional<CollisionComponent> collisionComponent = entity.getComponent(CollisionComponent.class);
            Optional<MaterialComponent> materialComponent = entity.getComponent(MaterialComponent.class);

            if (!positionComponent.isPresent()) {
                return;
            }
            
            if (!moveComponent.isPresent()) {
                return;
            }

            //COLLISION DETECT METHOD 2
            //TODO: write it better with no code repetition

            Point2D resultantOffset = new Point2D(0, 0);
            for (Force force : moveComponent.get().getForces().toArray(new Force[0])) {
                if (!collisionComponent.isPresent()) {
                    return;
                }

                int forceX = (int)Math.abs(force.getXComponent());
                int forceXSign = force.getXComponent() < 0 ? -1 : 1;
                Boolean forceXAccepted = false;
                int forceY = (int)Math.abs(force.getYComponent());
                int forceYSign = force.getYComponent() < 0 ? -1 : 1;
                Boolean forceYAccepted = false;
                
                if (!materialComponent.isPresent()) {
                    resultantOffset = resultantOffset.add(new Point2D(forceX * forceXSign, forceY * forceYSign));
                    continue;
                }
                
                while (forceX > 0 && !forceXAccepted) {
                    final int finalForceX = forceX * forceXSign;
                    if (!this.world().stream().anyMatch(entity1 -> 
                                            entity1.getComponent(MaterialComponent.class).isPresent() && 
                                            collisionComponent.get().willCollisionWith(entity1, new Point2D(finalForceX + forceXSign, 0)))) {
                        
                        resultantOffset = resultantOffset.add(new Point2D(finalForceX, 0));
                        forceXAccepted = true;
                        break;
                    }
                    forceX--;
                }

                while (forceY > 0 && !forceYAccepted) {
                    final int finalForceY = forceY * forceYSign;
                    if (!this.world().stream().anyMatch(entity1 -> 
                                            entity1.getComponent(MaterialComponent.class).isPresent() && 
                                            collisionComponent.get().willCollisionWith(entity1, new Point2D(0, finalForceY + forceYSign)))) {
                        
                        resultantOffset = resultantOffset.add(new Point2D(0, finalForceY));
                        forceYAccepted = true;
                        break;
                    }
                    forceY--;
                }
            }
           
            positionComponent.get().setPosition(positionComponent.get().getPosition().add(resultantOffset));
            
            //Calls 'collisionWith' and so it throws the CollisionEvent
            // A --> B
            // B --> A
            this.world().stream().forEach(entity1 -> collisionComponent.get().collisionWith(entity1));
            this.world().stream().forEach(entity1 -> entity1.getComponent(CollisionComponent.class).get().collisionWith(entity));
            
        });
    }

}
