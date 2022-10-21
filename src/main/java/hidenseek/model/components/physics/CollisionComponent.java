package hidenseek.model.components.physics;

import java.util.Set;

import hidenseek.model.components.Component;
import hidenseek.model.entities.Entity;
import javafx.geometry.Point2D;

public interface CollisionComponent extends Component {

    Set<Point2D> getHitbox();
    
    void addHitboxPoint(Point2D point);
    void removeHitboxPoint(Point2D point);

    void onCollision(Entity entity);
    void onNear(Entity entity);
    
    Boolean collisionTo(Entity entity, Point2D ownOffset);
    Boolean nearTo(Entity entity, Point2D ownOffset);
    Boolean collisionOrNearTo(Entity entity, Point2D ownOffset);
    
    Point2D getBounds();
}
