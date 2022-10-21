package hidenseek.controller.entities;

import hidenseek.model.components.CollisionComponent;
import hidenseek.model.entities.Entity;
import hidenseek.view.entities.KeyView;
import hidenseek.view.entities.KeyViewImpl;
import hidenseek.view.entities.WallView;
import hidenseek.view.entities.WallViewImpl;

public class KeyControllerImpl extends EntityControllerImpl<KeyView>{

    public KeyControllerImpl(final Entity model) {
        super(model, new KeyViewImpl());
    }
}
