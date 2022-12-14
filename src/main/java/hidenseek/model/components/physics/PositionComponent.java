package hidenseek.model.components.physics;

import hidenseek.model.components.Component;
import javafx.geometry.Point2D;

public interface PositionComponent extends Component {

    Point2D getPosition();    
    
    /**
     * Set the position
     * @param position 
     */void setPosition(Point2D position);
    
}
