package hidenseek.components;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import hidenseek.model.entities.Entity;
import hidenseek.model.components.physics.CollisionComponent;
import hidenseek.model.components.physics.CollisionComponentImpl;
import hidenseek.model.components.physics.LinearMovementComponentImpl;
import hidenseek.model.components.physics.MaterialComponent;
import hidenseek.model.components.physics.MaterialComponentImpl;
import hidenseek.model.components.physics.MoveComponent;
import hidenseek.model.components.physics.PositionComponent;
import hidenseek.model.components.physics.PositionComponentImpl;
import hidenseek.model.entities.AbstractEntity;
import javafx.geometry.Point2D;

public class CollisionTest {
    
    
    @Test public void testCollisionComponent() {

        Point2D offset = new Point2D(0, 0);
        
        //CREATE ENTITY1
        final Entity entity1 = new AbstractEntity(){};        
        //Position component
        final PositionComponent positionComponent1 = new PositionComponentImpl();
        positionComponent1.setPosition(new Point2D(100, 100));
        entity1.attach(positionComponent1);        
        //Move component
        final MoveComponent moveComponent1 = new LinearMovementComponentImpl(1);
        entity1.attach(moveComponent1);        
        //Material component
        final MaterialComponent materialComponent1 = new MaterialComponentImpl();
        entity1.attach(materialComponent1);        
        //Collision component
        final CollisionComponent collisionComponent1 = new CollisionComponentImpl();
        collisionComponent1.getHitbox().addPoint(new Point2D(0, 0));
        collisionComponent1.getHitbox().addPoint(new Point2D(0, 30));
        collisionComponent1.getHitbox().addPoint(new Point2D(30, 30));
        collisionComponent1.getHitbox().addPoint(new Point2D(30, 0));
        entity1.attach(collisionComponent1);

        //CREATE ENTITY2
        final Entity entity2 = new AbstractEntity(){};        
        //Position component
        final PositionComponent positionComponent2 = new PositionComponentImpl();
        entity2.attach(positionComponent2);        
        //Move component
        final MoveComponent moveComponent2 = new LinearMovementComponentImpl(1);
        entity2.attach(moveComponent2);        
        //Material component
        final MaterialComponent materialComponent2 = new MaterialComponentImpl();
        entity2.attach(materialComponent2);        
        //Collision component
        final CollisionComponent collisionComponent2 = new CollisionComponentImpl();
        collisionComponent2.getHitbox().addPoint(new Point2D(0, 0));
        collisionComponent2.getHitbox().addPoint(new Point2D(0, 30));
        collisionComponent2.getHitbox().addPoint(new Point2D(30, 30));
        collisionComponent2.getHitbox().addPoint(new Point2D(30, 0));
        entity2.attach(collisionComponent2);
        
        
        //intersection
        positionComponent2.setPosition(new Point2D(110, 110));
        assertTrue(collisionComponent1.collisionTo(entity2, offset) && collisionComponent2.collisionTo(entity1, offset));
        assertTrue(collisionComponent1.collisionTo(entity2, offset) && collisionComponent2.collisionTo(entity1, offset));
        
        //one side overlapped
        positionComponent2.setPosition(new Point2D(110, 70));
        assertFalse(collisionComponent1.collisionTo(entity2, offset)); // || collisionComponent2.collisionTo(entity1, offset)
        assertTrue(collisionComponent1.nearTo(entity2, offset) && collisionComponent2.nearTo(entity1, offset));
        positionComponent2.setPosition(new Point2D(130, 110));
        assertFalse(collisionComponent1.collisionTo(entity2, offset) || collisionComponent2.collisionTo(entity1, offset));
        assertTrue(collisionComponent1.nearTo(entity2, offset) && collisionComponent2.nearTo(entity1, offset));
        positionComponent2.setPosition(new Point2D(90, 130));
        assertFalse(collisionComponent1.collisionTo(entity2, offset) || collisionComponent2.collisionTo(entity1, offset));
        assertTrue(collisionComponent1.nearTo(entity2, offset) && collisionComponent2.nearTo(entity1, offset));
        positionComponent2.setPosition(new Point2D(70, 90));
        assertFalse(collisionComponent1.collisionTo(entity2, offset) || collisionComponent2.collisionTo(entity1, offset));
        assertTrue(collisionComponent1.nearTo(entity2, offset) && collisionComponent2.nearTo(entity1, offset));
        
        //one point overlapped
        positionComponent2.setPosition(new Point2D(70, 70));
        assertFalse(collisionComponent1.collisionTo(entity2, offset) || collisionComponent2.collisionTo(entity1, offset));
        assertTrue(collisionComponent1.nearTo(entity2, offset) && collisionComponent2.nearTo(entity1, offset));
        positionComponent2.setPosition(new Point2D(130, 70));
        assertFalse(collisionComponent1.collisionTo(entity2, offset) || collisionComponent2.collisionTo(entity1, offset));
        assertTrue(collisionComponent1.nearTo(entity2, offset) && collisionComponent2.nearTo(entity1, offset));
        positionComponent2.setPosition(new Point2D(130, 130));
        assertFalse(collisionComponent1.collisionTo(entity2, offset) || collisionComponent2.collisionTo(entity1, offset));
        assertTrue(collisionComponent1.nearTo(entity2, offset) && collisionComponent2.nearTo(entity1, offset));
        positionComponent2.setPosition(new Point2D(70, 130));
        assertFalse(collisionComponent1.collisionTo(entity2, offset) || collisionComponent2.collisionTo(entity1, offset));
        assertTrue(collisionComponent1.nearTo(entity2, offset) && collisionComponent2.nearTo(entity1, offset));

    }
    
    @Test
    void testIntersection() {
        
    }
}
