package hidenseek.model.entities;

import java.util.Optional;
import java.util.function.Consumer;

import hidenseek.model.components.CollisionComponent;
import hidenseek.model.components.CollisionComponentImpl;
import hidenseek.model.components.Force;
import hidenseek.model.components.InputHandlerComponent;
import hidenseek.model.components.InputHandlerComponentImpl;
import hidenseek.model.components.LifeComponentImpl;
import hidenseek.model.components.LinearMovementComponentImpl;
import hidenseek.model.components.MaterialComponent;
import hidenseek.model.components.MaterialComponentImpl;
import hidenseek.model.components.MoveComponent;
import hidenseek.model.components.PositionComponent;
import hidenseek.model.components.PositionComponentImpl;
import hidenseek.model.components.brains.BrainComponent;
import hidenseek.model.components.brains.ExpertBrainComponentImpl;
import hidenseek.model.components.brains.NaiveBrainComponentImpl;
import hidenseek.model.components.hearts.EvilHeartComponentImpl;
import hidenseek.model.components.hearts.HeartComponent;
import hidenseek.model.components.senses.SenseComponent;
import hidenseek.model.components.senses.SightSenseComponentImpl;
import hidenseek.model.enums.Direction;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;

public class Monster extends AbstractEntity {
    
    public Monster() {
        super();
        
        this.attach(new LifeComponentImpl(1));  
        
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
        collisionComponent.addHitboxPoint(new Point2D(0, 0));
        collisionComponent.addHitboxPoint(new Point2D(0, 50));
        collisionComponent.addHitboxPoint(new Point2D(50, 50));
        collisionComponent.addHitboxPoint(new Point2D(50, 0));
        this.attach(collisionComponent);        
        
        // heart
        final HeartComponent heart = new EvilHeartComponentImpl();
        this.attach(heart);

        // sight sense
        final SenseComponent sight = new SightSenseComponentImpl(200);
        this.attach(sight);
        
        // brain sense
        final BrainComponent brain = new ExpertBrainComponentImpl();
        this.attach(brain);
        

        
        //InputHandler component
        final InputHandlerComponent inputHandlerComponent = new InputHandlerComponentImpl();
        mapKeyToAction(inputHandlerComponent, KeyCode.J, Direction.LEFT);
        mapKeyToAction(inputHandlerComponent, KeyCode.I, Direction.UP);
        mapKeyToAction(inputHandlerComponent, KeyCode.K, Direction.DOWN);
        mapKeyToAction(inputHandlerComponent, KeyCode.L, Direction.RIGHT);
        this.attach(inputHandlerComponent);
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
