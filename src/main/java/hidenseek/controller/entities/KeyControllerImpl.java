package hidenseek.controller.entities;

import hidenseek.model.entities.Entity;
import hidenseek.view.entities.KeyView;
import hidenseek.view.entities.KeyViewImpl;

public class KeyControllerImpl extends EntityControllerImpl<KeyView>{

    public KeyControllerImpl(final Entity model) {
        super(model, new KeyViewImpl());
    }
}
