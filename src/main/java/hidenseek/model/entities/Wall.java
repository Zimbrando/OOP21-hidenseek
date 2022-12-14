package hidenseek.model.entities;

import java.util.Set;

import hidenseek.model.components.physics.CollisionComponent;
import hidenseek.model.components.physics.CollisionComponentImpl;
import hidenseek.model.components.physics.MaterialComponent;
import hidenseek.model.components.physics.MaterialComponentImpl;
import hidenseek.model.components.physics.PositionComponent;
import hidenseek.model.components.physics.PositionComponentImpl;
import javafx.geometry.Point2D;

public final class Wall extends AbstractEntity {
    
    public Wall(final Point2D position, final Set<Point2D> shape) {
        super();
        
        //Position component
        final PositionComponent positionComponent = new PositionComponentImpl();
        positionComponent.setPosition(position);
        this.attach(positionComponent);
        
        //Material component
        final MaterialComponent materialComponent = new MaterialComponentImpl();
        this.attach(materialComponent);
        
        //Collision component
        final CollisionComponent collisionComponent = new CollisionComponentImpl();
        shape.stream().forEach(point -> collisionComponent.getHitbox().addPoint(point));
        this.attach(collisionComponent);
       
    }
}
