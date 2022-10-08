package hidenseek.model.components;

public interface MoveComponent extends PositionComponent{

    int getSpeed();
    
    void setSpeed(int speed);

    void move(Direction dir);
}
