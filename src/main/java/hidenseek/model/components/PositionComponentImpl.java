package hidenseek.model.components;

import javafx.geometry.Point2D;

final public class PositionComponentImpl extends AbstractObservableComponent implements PositionComponent {

    private Point2D position;

    @Override
    public Point2D getPosition() {
        return position;
    }

    @Override
    public void setPosition(Point2D position) {
        this.position = position;
    }


    
}
