package hidenseek.model.entities;

import hidenseek.model.components.LifeComponentImpl;
import hidenseek.model.components.LinearMovementComponentImpl;
import hidenseek.model.components.MoveComponent;
import hidenseek.model.components.brains.BrainComponent;
import hidenseek.model.components.brains.NaiveBrainComponentImpl;
import hidenseek.model.components.hearts.EvilHeartComponentImpl;
import hidenseek.model.components.hearts.HeartComponent;
import hidenseek.model.components.senses.SenseComponent;
import hidenseek.model.components.senses.SightSenseComponentImpl;
import javafx.geometry.Point2D;

public class Monster extends AbstractEntity {
    
    public Monster() {
        super();
        
        this.attach(new LifeComponentImpl(1));
        final MoveComponent m = new LinearMovementComponentImpl();
        m.setPosition(new Point2D(200, 200));
        m.setSpeed(2);
        this.attach(m);
       
        
        // heart
        final HeartComponent heart = new EvilHeartComponentImpl();
        this.attach(heart);

        // sight sense
        final SenseComponent sight = new SightSenseComponentImpl(200);
        this.attach(sight);
        
        // brain sense
        final BrainComponent brain = new NaiveBrainComponentImpl();
        this.attach(brain);
    }
}
