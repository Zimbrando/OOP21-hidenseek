package hidenseek.view.entities;

import hidenseek.view.GraphicsDevice;
import javafx.geometry.Point2D;

abstract class AbstractEntityView implements EntityView {

    private Point2D position;
    
    public void setPosition(final Point2D position) {
        this.position = position;
    }
    
    protected Point2D getPosition() {
        return this.position;
    }
    
    public abstract void draw(GraphicsDevice device);
}
