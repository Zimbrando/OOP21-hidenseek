package hidenseek.model.entities;

import java.util.Optional;
import java.util.function.Consumer;

import hidenseek.model.components.CollisionComponent;
import hidenseek.model.components.CollisionComponentImpl;
import hidenseek.model.components.Direction;
import hidenseek.model.components.Force;
import hidenseek.model.components.InputHandlerComponent;
import hidenseek.model.components.InputHandlerComponentImpl;
import hidenseek.model.components.LifeComponent;
import hidenseek.model.components.LifeComponentImpl;
import hidenseek.model.components.LinearMovementComponentImpl;
import hidenseek.model.components.MoveComponent;
import hidenseek.model.components.PositionComponent;
import hidenseek.model.components.PositionComponentImpl;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;

public class Player extends AbstractEntity {
    
    public Player() {
        super();
        
        //Life component
        final LifeComponent lifeComponent = new LifeComponentImpl(1);
        this.attach(lifeComponent);
        
        //Position component
        final PositionComponent positionComponent = new PositionComponentImpl();
        positionComponent.setPosition(new Point2D(0, 0));
        this.attach(positionComponent);
        
        //Move component
        final MoveComponent moveComponent = new LinearMovementComponentImpl();
        this.attach(moveComponent);
        
        //Collision component
        final CollisionComponent collisionComponent = new CollisionComponentImpl();
        collisionComponent.addHitboxPoint(new Point2D(100, 50));
        collisionComponent.addHitboxPoint(new Point2D(92, 77));
        collisionComponent.addHitboxPoint(new Point2D(71, 96));
        collisionComponent.addHitboxPoint(new Point2D(43, 100));
        collisionComponent.addHitboxPoint(new Point2D(17, 90));
        collisionComponent.addHitboxPoint(new Point2D(2, 64));
        collisionComponent.addHitboxPoint(new Point2D(2, 36));
        collisionComponent.addHitboxPoint(new Point2D(17, 12));
        collisionComponent.addHitboxPoint(new Point2D(43, 1));
        collisionComponent.addHitboxPoint(new Point2D(71, 5));
        collisionComponent.addHitboxPoint(new Point2D(92, 23));
        this.attach(collisionComponent);
       
        //InputHandler component
        final InputHandlerComponent inputHandlerComponent = new InputHandlerComponentImpl();
        mapKeyToAction(inputHandlerComponent, KeyCode.A, Direction.LEFT);
        mapKeyToAction(inputHandlerComponent, KeyCode.W, Direction.UP);
        mapKeyToAction(inputHandlerComponent, KeyCode.S, Direction.DOWN);
        mapKeyToAction(inputHandlerComponent, KeyCode.D, Direction.RIGHT);
        this.attach(inputHandlerComponent);
    }
    
    private void mapKeyToAction(InputHandlerComponent inputHandlerComponent, KeyCode keyCode, Direction direction) {

        Consumer<Entity> pressAction = (Entity entity) -> {
            Optional<MoveComponent> moveComponent = entity.getComponent(MoveComponent.class);
            if(!moveComponent.isPresent()) {
                return;
            }
            moveComponent.get().removeForce(force -> force.getDirection() == direction.getValue() && force.getIdentifier() == "key");
            moveComponent.get().addForce(new Force("key", 1, direction.getValue()));
        };
        
        Consumer<Entity> releaseAction = (Entity entity) -> {
            Optional<MoveComponent> moveComponent = entity.getComponent(MoveComponent.class);
            if(!moveComponent.isPresent()) {
                return;
            }
            moveComponent.get().removeForce(force -> force.getDirection() == direction.getValue() && force.getIdentifier() == "key");
        };
        
        inputHandlerComponent.mapKeyToOneTimeAction(keyCode, pressAction, releaseAction);
        
    }
}
