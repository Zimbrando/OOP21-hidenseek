package hidenseek.controller.entities;

import hidenseek.model.entities.Player;
import hidenseek.view.entities.PlayerView;
import hidenseek.view.entities.PlayerViewImpl;

public class PlayerControllerImpl extends MovableEntityControllerImpl<PlayerView>{

    public PlayerControllerImpl() {
        super(new Player(), new PlayerViewImpl());
    }
    
}
