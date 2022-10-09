package hidenseek.model.components;

import javafx.geometry.Point2D;

public class PositionComponentImpl extends AbstractComponent implements PositionComponent {

    private Point2D position;
    
    public PositionComponentImpl(final Point2D position) {
        this.position = position;
    }
    
    @Override
    public Point2D getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(final Point2D pos) {
        this.position = pos;
    }

}
