package hidenseek.controller.entities;

import hidenseek.model.components.physics.PositionComponent;
import hidenseek.model.entities.Entity;
import hidenseek.view.entities.EntityView;

public class EntityControllerImpl<V extends EntityView> extends AbstractEntityController<V>{

    public EntityControllerImpl(final Entity model, final V view) {
        super(model, view);
    }
    
    @Override
    public void update() {
        this.getModel().getComponent(PositionComponent.class)
                .ifPresent(component -> this.getView().setPosition(component.getPosition()));
    }
    
}
