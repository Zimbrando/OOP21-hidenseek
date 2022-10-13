package hidenseek.model.components;

import java.util.Set;

import hidenseek.model.entities.Entity;
import javafx.geometry.Point2D;

public interface CollisionComponent extends Component {

    Set<Point2D> getHitbox();
    
    void addHitboxPoint(Point2D point);
    void removeHitboxPoint(Point2D point);

    Boolean collisionWith(Entity entity);
    Boolean willCollisionWith(Entity entity, Point2D offset);
    
    Point2D getBounds();
}
