package hidenseek.components;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import hidenseek.model.components.MapComponent;
import hidenseek.model.components.GPSMapComponentImpl;
import hidenseek.model.components.brains.BrainComponent;
import hidenseek.model.components.brains.ExpertBrainComponentImpl;
import hidenseek.model.components.brains.NaiveBrainComponentImpl;
import hidenseek.model.components.hearts.EvilHeartComponentImpl;
import hidenseek.model.components.hearts.GoodHeartComponentImpl;
import hidenseek.model.components.physics.CollisionComponent;
import hidenseek.model.components.physics.CollisionComponentImpl;
import hidenseek.model.components.physics.Force;
import hidenseek.model.components.physics.LinearMovementComponentImpl;
import hidenseek.model.components.physics.MaterialComponent;
import hidenseek.model.components.physics.MaterialComponentImpl;
import hidenseek.model.components.physics.MoveComponent;
import hidenseek.model.components.physics.PositionComponent;
import hidenseek.model.components.physics.PositionComponentImpl;
import hidenseek.model.components.senses.SenseComponent;
import hidenseek.model.components.senses.SightSenseComponentImpl;
import hidenseek.model.entities.AbstractEntity;
import hidenseek.model.entities.Entity;
import javafx.geometry.Point2D;

public class BrainTest {

    private Entity getSenseMovableEntity() {
        final Entity entity = new AbstractEntity() {};
        //Move component
        final MoveComponent moveComponent = new LinearMovementComponentImpl(120);
        entity.attach(moveComponent);
        // sight sense
        final SenseComponent sight = new SightSenseComponentImpl(Integer.MAX_VALUE);
        entity.attach(sight);
        // brain
        final BrainComponent brain = new NaiveBrainComponentImpl();
        entity.attach(brain);
        // map
        final MapComponent map = new GPSMapComponentImpl();
        entity.attach(map);
        // material
        final MaterialComponent material = new MaterialComponentImpl();
        entity.attach(material);
        //Collision component
        final CollisionComponent collisionComponent = new CollisionComponentImpl();
        collisionComponent.getHitbox().addPoint(new Point2D(0, 0));
        collisionComponent.getHitbox().addPoint(new Point2D(0, 40));
        collisionComponent.getHitbox().addPoint(new Point2D(40, 40));
        collisionComponent.getHitbox().addPoint(new Point2D(40, 0));
        entity.attach(collisionComponent);  
        
        return entity;
    }

    private Entity naiveEntity() {
        final Entity entity = getSenseMovableEntity();
        // naive brain
        final BrainComponent brain = new NaiveBrainComponentImpl();
        entity.attach(brain);
        return entity;
    }
    
    private Entity expertEntity() {
        final Entity entity = getSenseMovableEntity();
        // naive brain
        final BrainComponent brain = new ExpertBrainComponentImpl();
        entity.attach(brain);
        return entity;
    }

    @Test
    void naiveEvilFollowGood() {
        // EVIL entity
        final Entity evil = naiveEntity();
        // attach evil heart
        evil.attach(new EvilHeartComponentImpl());
        // attach position
        final PositionComponent evilPos = new PositionComponentImpl();
        evilPos.setPosition(new Point2D(0, 0));
        evil.attach(evilPos);
        
        // good entity
        final Entity good = getSenseMovableEntity();
        // attach good heart
        good.attach(new GoodHeartComponentImpl());
        // attach position
        final PositionComponent goodPos = new PositionComponentImpl();
        goodPos.setPosition(new Point2D(100, 100));
        good.attach(goodPos);
        
        // evil sense good
        evil.getComponent(SenseComponent.class).get().feel(good);
        // make 30 moves
        for(int i = 0; i <30;i++) {
            // save current distance
            final double distance = evilPos.getPosition().distance(goodPos.getPosition());
            // follow good entity
            evil.getComponent(BrainComponent.class).get().neuroImpulse();
            Force force = evil.getComponent(MoveComponent.class).get().getResultantForce();
            // apply force
            evilPos.setPosition(evilPos.getPosition().add(new Point2D(force.getXComponent(), force.getYComponent())));
            // check if is gettin near
            assertTrue(distance > evilPos.getPosition().distance(goodPos.getPosition()));
        }
    }
    

    @Test
    void expertEvilFollowGood() {
        // EVIL entity
        final Entity evil = expertEntity();
        // attach evil heart
        evil.attach(new EvilHeartComponentImpl());
        // attach position
        final PositionComponent evilPos = new PositionComponentImpl();
        evilPos.setPosition(new Point2D(0, 0));
        evil.attach(evilPos);
        
        // good entity
        final Entity good = getSenseMovableEntity();
        // attach good heart
        good.attach(new GoodHeartComponentImpl());
        // attach position
        final PositionComponent goodPos = new PositionComponentImpl();
        goodPos.setPosition(new Point2D(100, 100));
        good.attach(goodPos);
        
        // evil sense good
        evil.getComponent(SenseComponent.class).get().feel(good);
        // make 30 moves
        for(int i = 0; i <30;i++) {
            // save current distance
            final double distance = evilPos.getPosition().distance(goodPos.getPosition());
            // follow good entity
            evil.getComponent(BrainComponent.class).get().neuroImpulse();
            Force force = evil.getComponent(MoveComponent.class).get().getResultantForce();
            // check is following - if force is change towards target
            evilPos.setPosition(evilPos.getPosition().add(new Point2D(force.getXComponent(), force.getYComponent())));
            assertTrue(distance > evilPos.getPosition().distance(goodPos.getPosition()));
        }
        
    }
    

}
