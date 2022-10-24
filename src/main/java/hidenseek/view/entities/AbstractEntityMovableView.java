package hidenseek.view.entities;

import hidenseek.model.enums.Direction;

/**
 * Base class for {@link EntityMovableView}
 */
abstract class AbstractEntityMovableView extends AbstractEntityView implements EntityMovableView{

    private Direction spriteDirection;

    public AbstractEntityMovableView() {
        this.spriteDirection = Direction.RIGHT;
    }

    @Override
    public void setDirection(final Direction direction) {
        this.spriteDirection = direction;
    }
    
    @Override
    public Direction getDirection() {
        return this.spriteDirection;
    }

}
