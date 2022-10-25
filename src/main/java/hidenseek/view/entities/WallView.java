package hidenseek.view.entities;

import java.util.Set;

import javafx.geometry.Point2D;

public interface WallView extends EntityView {

    /**
     * Sets the polygon bounds
     * @param hitbox
     *          The wall hitbox
     */
    void setHitbox(Set<Point2D> hitbox);
}
