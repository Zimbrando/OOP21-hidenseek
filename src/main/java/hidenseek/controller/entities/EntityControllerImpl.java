package hidenseek.controller.entities;

import hidenseek.model.entities.Entity;
import hidenseek.view.entities.EntityView;

public class EntityControllerImpl<V extends EntityView> extends AbstractEntityController<V>{

    public EntityControllerImpl(final Entity model, final V view) {
        super(model, view);
    }
    
    @Override
    public void update() {
    
    }
    
}
