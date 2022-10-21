package hidenseek.controller.entities;

import hidenseek.model.components.CollisionComponent;
import hidenseek.model.entities.Entity;
import hidenseek.model.enums.PowerUpType;
import hidenseek.view.entities.PowerUpView;
import hidenseek.view.entities.PowerUpViewImpl;
import hidenseek.view.entities.WallView;
import hidenseek.view.entities.WallViewImpl;

public class PowerUpControllerImpl extends EntityControllerImpl<PowerUpView>{

    public PowerUpControllerImpl(final PowerUpType type, final Entity model) {
        super(model, new PowerUpViewImpl(type));
    }
}
