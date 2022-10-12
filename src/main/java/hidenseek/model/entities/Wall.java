package hidenseek.model.entities;

import java.util.Set;

import hidenseek.model.components.CollisionComponent;
import hidenseek.model.components.CollisionComponentImpl;
import hidenseek.model.components.MaterialComponent;
import hidenseek.model.components.MaterialComponentImpl;
import hidenseek.model.components.PositionComponent;
import hidenseek.model.components.PositionComponentImpl;
import javafx.geometry.Point2D;

public class Wall extends AbstractEntity {
    
    public Wall(Point2D position, Set<Point2D> shape) {
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
        shape.stream().forEach(point -> collisionComponent.addHitboxPoint(point));
        this.attach(collisionComponent);
       
    }
}
