package hidenseek.view.entities;

import hidenseek.view.GraphicsDevice;
import javafx.geometry.Point2D;

public interface EntityView {

    /**
     * Draw in the device this view
     * @param device
     */
    void draw(GraphicsDevice device);
    
    /**
     * Sets the view position
     * @param position
     */
    void setPosition(Point2D position);
}