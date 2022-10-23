package hidenseek.controller.entities;

import hidenseek.model.components.physics.LightComponent;
import hidenseek.model.components.physics.MoveComponent;
import hidenseek.model.entities.Entity;
import hidenseek.model.enums.Direction;
import hidenseek.model.enums.LightRadius;
import hidenseek.view.entities.PlayerView;
import hidenseek.view.entities.PlayerViewImpl;

public class PlayerControllerImpl extends MovableEntityControllerImpl<PlayerView>{
    
    public PlayerControllerImpl(final Entity model) {
        super(model, new PlayerViewImpl());
    }

    @Override
    public void update() {
        super.update();      
        
        // update direction
        this.getModel().getComponent(LightComponent.class).ifPresent(c -> {
            // get light radius
            final LightRadius lightRadius = c.getRadius();
            // set view light radius
            this.getView().setLightRadius(lightRadius);
        });
        
    }
    
}
