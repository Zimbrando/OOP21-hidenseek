package hidenseek.controller.entities;

import hidenseek.model.components.physics.CollisionComponent;
import hidenseek.model.entities.Entity;
import hidenseek.view.entities.WallView;

public class WallControllerImpl extends EntityControllerImpl<WallView>{

    public WallControllerImpl(final Entity model, final WallView view) {
        super(model, view);
    }

    @Override
    public void update() {
        super.update();
        
        if (this.getModel().getComponent(CollisionComponent.class).isPresent()) {
            this.getView().setHitbox(this.getModel().getComponent(CollisionComponent.class).get().getHitbox());        
        }
    }
}
