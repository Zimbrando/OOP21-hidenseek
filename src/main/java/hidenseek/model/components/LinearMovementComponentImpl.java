package hidenseek.model.components;

import javafx.geometry.Point2D;

public class LinearMovementComponentImpl extends AbstractComponent implements MoveComponent {

    private Point2D position;
    private int speed;
    
    @Override
    public Point2D getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Point2D pos) {
        this.position = pos;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void move(Direction dir) {
        Point2D delta = dir.point.multiply(speed);
        this.position = this.position.add(delta);
    }

}
