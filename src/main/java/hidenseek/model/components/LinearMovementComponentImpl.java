package hidenseek.model.components;

import javafx.geometry.Point2D;

public class LinearMovementComponentImpl extends ComponentImpl implements MoveComponent {

    private Point2D position;
    private int speed;
    
    public LinearMovementComponentImpl(final Point2D initPos, final int speed) {
        this.position = initPos;
        this.speed = speed;
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
    public void move(Direction dir) {
        Point2D delta = dir.point.multiply(speed);
        this.position = this.position.add(delta);
    }

}
