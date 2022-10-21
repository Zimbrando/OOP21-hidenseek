package hidenseek.controller.entities;

import hidenseek.model.entities.Entity;
import hidenseek.view.entities.PlayerView;
import hidenseek.view.entities.PlayerViewImpl;

public class PlayerControllerImpl extends MovableEntityControllerImpl<PlayerView>{
    
    public PlayerControllerImpl(final Entity model) {
        super(model, new PlayerViewImpl());
    }
    
}
