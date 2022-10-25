package hidenseek.view.entities;

import hidenseek.model.enums.Direction;

public interface EntityMovableView extends EntityView{
    
    /**
     * @param direction
     *          The Entity direction
     */
    void setDirection(Direction direction);
    
    /**
     * @return The current direction
     */
    Direction getDirection();
}
