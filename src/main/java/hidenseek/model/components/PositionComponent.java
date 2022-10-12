package hidenseek.model.components;

import javafx.geometry.Point2D;

public interface PositionComponent extends Component {

    Point2D getPosition();    
    void setPosition(Point2D position);
    
}
