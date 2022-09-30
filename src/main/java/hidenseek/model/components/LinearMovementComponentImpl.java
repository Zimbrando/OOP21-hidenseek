package hidenseek.model.components;

import javafx.geometry.Point2D;

public class LinearMovementComponentImpl extends ComponentImpl implements MoveComponent {

    private Point2D position;
    
    public LinearMovementComponentImpl(final Point2D initPos) {
        this.position = initPos;
    }
    
    @Override
    public Point2D getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Point2D pos) {
        this.position = pos;
    }

    @Override
    public void move() {
        this.setPosition(position.add(new Point2D(0, 10)));
    }

}
