package hidenseek.controller.entities;

import hidenseek.model.components.MoveComponent;
import hidenseek.model.entities.Player;
import hidenseek.model.enums.Direction;
import hidenseek.view.entities.PlayerView;
import hidenseek.view.entities.PlayerViewImpl;

public class PlayerControllerImpl extends MovableEntityControllerImpl<PlayerView>{

    public PlayerControllerImpl() {
        super(new Player(), new PlayerViewImpl());
    }
    
    @Override
    public void update() {
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
