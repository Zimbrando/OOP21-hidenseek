package hidenseek.controller;

import java.util.Set;

import hidenseek.model.components.InputHandlerComponent;
import hidenseek.model.components.PositionComponent;
import hidenseek.model.entities.Entity;
import hidenseek.view.EntityView;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;

public class EntityControllerImpl<V extends EntityView> implements EntityController {
    private final Entity model;
    private final V view;
    
    public EntityControllerImpl(Entity model, V view) {
        this.model = model;
        this.view = view;
    }
    
    @Override
    public Point2D getPosition() {
        return this.model.getComponent(PositionComponent.class).get().getPosition();
    }

    @Override
    public V getView() {
        return this.view;
    }

    @Override
    public Entity getModel() {
        return this.model;
    }

    @Override
    public void handleInput(Set<KeyCode> keysPressed) {
        this.model.getComponent(InputHandlerComponent.class).ifPresent(c -> c.computeScheme(keysPressed));
    }

}
