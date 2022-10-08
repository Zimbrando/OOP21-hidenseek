package hidenseek.model.entities;

import hidenseek.model.components.Direction;
import hidenseek.model.components.InputHandlerComponent;
import hidenseek.model.components.InputHandlerComponentImpl;
import hidenseek.model.components.LifeComponentImpl;
import hidenseek.model.components.LinearMovementComponentImpl;
import hidenseek.model.components.MoveComponent;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;

public class Player extends AbstractEntity {
    
    public Player() {
        super();
        
        this.attach(new LifeComponentImpl(1));
        MoveComponent m = new LinearMovementComponentImpl();
        m.setPosition(new Point2D(0, 0));
        m.setSpeed(2);
        this.attach(m);
        
        
        //Input
        InputHandlerComponent input = new InputHandlerComponentImpl();
        input.mapKeyToAction(KeyCode.A, entity -> entity.getComponent(MoveComponent.class)
                .ifPresent(c -> c.move(Direction.LEFT)));
        input.mapKeyToAction(KeyCode.D, entity -> entity.getComponent(MoveComponent.class)
                .ifPresent(c -> c.move(Direction.RIGHT)));
        input.mapKeyToAction(KeyCode.W, entity -> entity.getComponent(MoveComponent.class)
                .ifPresent(c -> c.move(Direction.UP)));
        input.mapKeyToAction(KeyCode.S, entity -> entity.getComponent(MoveComponent.class)
                .ifPresent(c -> c.move(Direction.DOWN)));
        
        this.attach(input);
        
    }
}
