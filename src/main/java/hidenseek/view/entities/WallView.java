package hidenseek.view.entities;

import java.util.Set;

import javafx.geometry.Point2D;

public interface WallView extends EntityView {

    void setHitbox(Set<Point2D> hitbox);
}
