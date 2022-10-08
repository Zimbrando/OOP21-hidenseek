package hidenseek.controller.entities;

import java.util.Optional;

import hidenseek.model.components.PositionComponent;
import hidenseek.model.entities.Entity;
import hidenseek.view.EntityView;
import javafx.geometry.Point2D;

public class EntityControllerImpl<V extends EntityView> implements EntityController {
    private final Entity model;
    private final V view;
    
    public EntityControllerImpl(final Entity model, final V view) {
        this.model = model;
        this.view = view;
    }
    
    @Override
    public Optional<Point2D> getPosition() {
        return this.model.getComponent(PositionComponent.class).map(c -> c.getPosition());
     }

    @Override
    public V getView() {
        return this.view;
    }

    @Override
    public Entity getModel() {
        return this.model;
    }

}
