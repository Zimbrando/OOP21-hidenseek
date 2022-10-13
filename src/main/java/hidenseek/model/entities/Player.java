package hidenseek.model.entities;

import hidenseek.model.components.InputHandlerComponent;
import hidenseek.model.components.InputHandlerComponentImpl;
import hidenseek.model.components.LifeComponentImpl;
import hidenseek.model.components.LinearMovementComponentImpl;
import hidenseek.model.components.MoveComponent;
import hidenseek.model.components.hearts.GoodHeartComponentImpl;
import hidenseek.model.components.hearts.HeartComponent;
import hidenseek.model.enums.Direction;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;

public class Player extends AbstractEntity {
    
    public Player() {
        super();
        
        this.attach(new LifeComponentImpl(1));
        final MoveComponent m = new LinearMovementComponentImpl();
        m.setPosition(new Point2D(0, 0));
        m.setSpeed(5);
        this.attach(m);
       
        //Input
        final InputHandlerComponent input = new InputHandlerComponentImpl();
        input.mapKeyToAction(KeyCode.A, entity -> entity.getComponent(MoveComponent.class)
                .ifPresent(c -> c.move(Direction.LEFT)));
        input.mapKeyToAction(KeyCode.D, entity -> entity.getComponent(MoveComponent.class)
                .ifPresent(c -> c.move(Direction.RIGHT)));
        input.mapKeyToAction(KeyCode.W, entity -> entity.getComponent(MoveComponent.class)
                .ifPresent(c -> c.move(Direction.UP)));
        input.mapKeyToAction(KeyCode.S, entity -> entity.getComponent(MoveComponent.class)
                .ifPresent(c -> c.move(Direction.DOWN)));
        
        this.attach(input);

        // heart
        final HeartComponent heart = new GoodHeartComponentImpl();
        this.attach(heart);

        // sight sense
//        final SenseComponent sight = new SightSenseComponentImpl(100);
//        this.attach(sight);
        
        // brain sense
//        final BrainComponent brain = new NaiveBrainComponentImpl();
//        this.attach(brain);
    }
}
