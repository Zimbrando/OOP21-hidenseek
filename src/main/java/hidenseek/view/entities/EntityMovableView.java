package hidenseek.view.entities;

import hidenseek.model.enums.Direction;

public interface EntityMovableView extends EntityView{
    
    void setDirection(Direction direction);
    
    Direction getDirection();
}
