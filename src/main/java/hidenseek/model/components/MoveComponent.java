package hidenseek.model.components;

import hidenseek.model.enums.Direction;

public interface MoveComponent extends PositionComponent{

    int getSpeed();
    
    void setSpeed(int speed);

    void move(Direction dir);
}
