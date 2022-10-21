package hidenseek.controller.entities;

import hidenseek.model.entities.Entity;
import hidenseek.model.entities.Player;
import hidenseek.view.entities.MonsterView;
import hidenseek.view.entities.MonsterViewImpl;
import hidenseek.view.entities.PlayerView;
import hidenseek.view.entities.PlayerViewImpl;
import hidenseek.view.entities.WallView;

public class MonsterControllerImpl extends MovableEntityControllerImpl<MonsterView>{
    
    public MonsterControllerImpl(final Entity model) {
        super(model, new MonsterViewImpl());
    }
    
}
