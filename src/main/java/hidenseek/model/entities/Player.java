package hidenseek.model.entities;

import java.util.Optional;
import java.util.function.Consumer;

import hidenseek.model.components.InputHandlerComponent;
import hidenseek.model.components.InputHandlerComponentImpl;
import hidenseek.model.components.InventoryComponent;
import hidenseek.model.components.InventoryComponentImpl;
import hidenseek.model.components.LifeComponent;
import hidenseek.model.components.LifeComponentImpl;
import hidenseek.model.components.hearts.GoodHeartComponentImpl;
import hidenseek.model.components.hearts.HeartComponent;
import hidenseek.model.components.physics.CollisionComponent;
import hidenseek.model.components.physics.CollisionComponentImpl;
import hidenseek.model.components.physics.Force;
import hidenseek.model.components.physics.LinearMovementComponentImpl;
import hidenseek.model.components.physics.MaterialComponent;
import hidenseek.model.components.physics.MaterialComponentImpl;
import hidenseek.model.components.physics.MoveComponent;
import hidenseek.model.components.physics.PositionComponent;
import hidenseek.model.components.physics.PositionComponentImpl;
import hidenseek.model.enums.Direction;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;

public final class Player extends AbstractEntity {
    
    private static final double PLAYER_SPEED = 150;
    
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
        final MoveComponent moveComponent = new LinearMovementComponentImpl(PLAYER_SPEED);
        this.attach(moveComponent);
        
        //Material component
        final MaterialComponent materialComponent = new MaterialComponentImpl();
        this.attach(materialComponent);
        
        //Collision component
        final CollisionComponent collisionComponent = new CollisionComponentImpl();
        collisionComponent.addHitboxPoint(new Point2D(0, 0));
        collisionComponent.addHitboxPoint(new Point2D(0, 30));
        collisionComponent.addHitboxPoint(new Point2D(30, 30));
        collisionComponent.addHitboxPoint(new Point2D(30, 0));
        this.attach(collisionComponent);
        
        //InputHandler component
        final InputHandlerComponent inputHandlerComponent = new InputHandlerComponentImpl();
        mapKeyToAction(inputHandlerComponent, KeyCode.A, Direction.LEFT);
        mapKeyToAction(inputHandlerComponent, KeyCode.W, Direction.UP);
        mapKeyToAction(inputHandlerComponent, KeyCode.S, Direction.DOWN);
        mapKeyToAction(inputHandlerComponent, KeyCode.D, Direction.RIGHT);
        this.attach(inputHandlerComponent);
        
        //Heart
        HeartComponent heart = new GoodHeartComponentImpl();
        this.attach(heart);
        
        //Inventory
        InventoryComponent inventory = new InventoryComponentImpl();
        this.attach(inventory);
    }
    
    private void mapKeyToAction(final InputHandlerComponent inputHandlerComponent, final KeyCode keyCode, Direction direction) {

        Consumer<Entity> pressAction = (entity) -> {
            Optional<MoveComponent> moveComponent = entity.getComponent(MoveComponent.class);
            if(!moveComponent.isPresent()) {
                return;
            }
            moveComponent.get().removeForce(force -> force.getDirection() == direction.getValue() && force.getIdentifier() == "key");
            moveComponent.get().addForce(new Force("key", 1, direction.getValue()));
        };
        
        Consumer<Entity> releaseAction = (entity) -> {
            Optional<MoveComponent> moveComponent = entity.getComponent(MoveComponent.class);
            if(!moveComponent.isPresent()) {
                return;
            }
            moveComponent.get().removeForce(force -> force.getDirection() == direction.getValue() && force.getIdentifier() == "key");
        };
        
        inputHandlerComponent.mapKeyToOneTimeAction(keyCode, pressAction, releaseAction);        
    }
}
