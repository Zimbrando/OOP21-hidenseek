package hidenseek.controller.entities;

import hidenseek.model.components.physics.CollisionComponent;
import hidenseek.model.entities.Entity;
import hidenseek.view.entities.WallView;
import hidenseek.view.entities.WallViewImpl;

public class WallControllerImpl extends EntityControllerImpl<WallView>{

    public WallControllerImpl(final Entity model) {
        super(model, new WallViewImpl());
    }

    @Override
    public void update() {
        super.update();
        
        if (this.getModel().getComponent(CollisionComponent.class).isPresent()) {
            this.getView().setHitbox(this.getModel().getComponent(CollisionComponent.class).get().getHitbox());        
        }
    }
}
