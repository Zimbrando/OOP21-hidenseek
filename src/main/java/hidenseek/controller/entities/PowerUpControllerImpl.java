package hidenseek.controller.entities;

import hidenseek.model.entities.Entity;
import hidenseek.model.enums.PowerUpType;
import hidenseek.view.entities.PowerUpView;
import hidenseek.view.entities.PowerUpViewImpl;

public class PowerUpControllerImpl extends EntityControllerImpl<PowerUpView>{

    public PowerUpControllerImpl(final PowerUpType type, final Entity model) {
        super(model, new PowerUpViewImpl(type));
    }
}
