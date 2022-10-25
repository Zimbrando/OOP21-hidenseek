package hidenseek.controller.entities;

import hidenseek.model.components.physics.MoveComponent;
import hidenseek.model.entities.Entity;
import hidenseek.model.enums.Direction;
import hidenseek.view.entities.EntityMovableView;

public class MovableEntityControllerImpl<V extends EntityMovableView>  extends EntityControllerImpl<V>{
    
    public MovableEntityControllerImpl(final Entity model, final V view) {
        super(model, view);
    }

    @Override
    public void update() {
        super.update();      
        
        // update direction
        this.getModel().getComponent(MoveComponent.class).ifPresent(c -> {
            final double intensity = c.getResultantForce().getIntensity();
            final double direction = c.getResultantForce().getDirection();
            // set view direction
            if(intensity > 0) {
                this.getView().setDirection(Direction.UP);
            }
            if(intensity > 0 && direction < Direction.UP.getValue()) {
                this.getView().setDirection(Direction.LEFT);
            }
            if(intensity > 0 && direction < Direction.LEFT.getValue()) {
                this.getView().setDirection(Direction.DOWN);
            }
            if(intensity > 0 && direction < Direction.DOWN.getValue()) {
                this.getView().setDirection(Direction.RIGHT);
            }
        });
        
    }
}
