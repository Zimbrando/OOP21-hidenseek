package hidenseek.view.entities;

import hidenseek.view.GraphicsDevice;
import javafx.geometry.Point2D;

/**
 * Base class for {@link EntityView}
 */
abstract class AbstractEntityView implements EntityView {

    private Point2D position;
    
    public AbstractEntityView() {
        this.position = new Point2D(0, 0);
    }
    
    public void setPosition(final Point2D position) {
        this.position = position;
    }
    
    protected Point2D getPosition() {
        return this.position;
    }
    
    public abstract void draw(GraphicsDevice device);
}
