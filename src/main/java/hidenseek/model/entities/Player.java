package hidenseek.model.entities;

import java.util.Optional;
import java.util.function.Consumer;

import hidenseek.model.components.CollisionComponent;
import hidenseek.model.components.CollisionComponentImpl;
import hidenseek.model.components.Force;
import hidenseek.model.components.InputHandlerComponent;
import hidenseek.model.components.InputHandlerComponentImpl;
import hidenseek.model.components.LifeComponent;
import hidenseek.model.components.LifeComponentImpl;
import hidenseek.model.components.LinearMovementComponentImpl;
import hidenseek.model.components.MaterialComponent;
import hidenseek.model.components.MaterialComponentImpl;
import hidenseek.model.components.MoveComponent;
import hidenseek.model.components.hearts.GoodHeartComponentImpl;
import hidenseek.model.components.hearts.HeartComponent;
import hidenseek.model.enums.Direction;
import hidenseek.model.components.PositionComponent;
import hidenseek.model.components.PositionComponentImpl;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;

public final class Player extends AbstractEntity {
    
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
        
        //Material component
        final MaterialComponent materialComponent = new MaterialComponentImpl();
        this.attach(materialComponent);
        
        //Collision component
        final CollisionComponent collisionComponent = new CollisionComponentImpl();
        collisionComponent.addHitboxPoint(new Point2D(-15, -15));
        collisionComponent.addHitboxPoint(new Point2D(15, -15));
        collisionComponent.addHitboxPoint(new Point2D(15, 15));
        collisionComponent.addHitboxPoint(new Point2D(-15, 15));
        this.attach(collisionComponent);
       
        //InputHandler component
        final InputHandlerComponent inputHandlerComponent = new InputHandlerComponentImpl();
        mapKeyToAction(inputHandlerComponent, KeyCode.A, Direction.LEFT);
        mapKeyToAction(inputHandlerComponent, KeyCode.W, Direction.UP);
        mapKeyToAction(inputHandlerComponent, KeyCode.S, Direction.DOWN);
        mapKeyToAction(inputHandlerComponent, KeyCode.D, Direction.RIGHT);
        this.attach(inputHandlerComponent);
        
        // heart
        HeartComponent heart = new GoodHeartComponentImpl();
        this.attach(heart);

    }
    
    private void mapKeyToAction(InputHandlerComponent inputHandlerComponent, KeyCode keyCode, Direction direction) {

        Consumer<Entity> pressAction = (Entity entity) -> {
            Optional<MoveComponent> moveComponent = entity.getComponent(MoveComponent.class);
            if(!moveComponent.isPresent()) {
                return;
            }
            moveComponent.get().removeForce(force -> force.getDirection() == direction.getValue() && force.getIdentifier() == "key");
            moveComponent.get().addForce(new Force("key", 5, direction.getValue()));
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
