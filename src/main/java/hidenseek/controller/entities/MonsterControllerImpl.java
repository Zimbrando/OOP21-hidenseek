package hidenseek.controller.entities;

import hidenseek.model.entities.Entity;
import hidenseek.view.entities.MonsterView;
import hidenseek.view.entities.MonsterViewImpl;

public class MonsterControllerImpl extends MovableEntityControllerImpl<MonsterView>{
    
    public MonsterControllerImpl(final Entity model) {
        super(model, new MonsterViewImpl());
    }
    
}
