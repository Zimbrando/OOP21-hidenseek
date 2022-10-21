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
            // get intensity
            final double intensity = c.getResultantForce().getIntensity();
            // get direction
            final double direction = c.getResultantForce().getDirection();
            // set view direciton
            if(intensity > 0) {
                this.getView().setDirection(Direction.UP);
            }
            if(intensity > 0 && direction < 270) {
                this.getView().setDirection(Direction.LEFT);
            }
            if(intensity > 0 && direction < 180) {
                this.getView().setDirection(Direction.DOWN);
            }
            if(intensity > 0 && direction < 90) {
                this.getView().setDirection(Direction.RIGHT);
            }
        });
        
    }
}
