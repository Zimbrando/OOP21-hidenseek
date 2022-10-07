package hidenseek.model.components;

public interface MoveComponent extends PositionComponent{

    public int getSpeed();
    
    public void setSpeed(int speed);

    void move(Direction dir);
}
