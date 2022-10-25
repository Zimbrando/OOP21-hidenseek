package hidenseek.controller.entities;

import java.util.Optional;

import hidenseek.model.components.physics.PositionComponent;
import hidenseek.model.entities.Entity;
import hidenseek.view.entities.EntityView;
import javafx.geometry.Point2D;

/**
 * Base class for all {@link EntityController}
 * 
 * @param <V>
 *       The view
 */
public abstract class AbstractEntityController<V extends EntityView> implements EntityController {
    private final Entity model;
    private final V view;
    
    public AbstractEntityController(final Entity model, final V view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public Optional<Point2D> getPosition() {
        return this.model.getComponent(PositionComponent.class).map(c -> c.getPosition());
     }

    @Override
    public void setPosition(Point2D position) {
        this.model.getComponent(PositionComponent.class).ifPresent(c -> c.setPosition(position));
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
