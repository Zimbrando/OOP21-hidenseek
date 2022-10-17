package hidenseek.view.entities;

import hidenseek.view.GraphicsDevice;
import javafx.geometry.Point2D;

public interface EntityView {

    void draw(GraphicsDevice device);
    
    void setPosition(Point2D position);
}