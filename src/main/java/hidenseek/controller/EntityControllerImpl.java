package hidenseek.controller;

import hidenseek.model.Entity;
import hidenseek.model.components.MoveComponent;
import hidenseek.model.components.PositionComponent;
import hidenseek.view.EntityView;
import javafx.geometry.Point2D;

public class EntityControllerImpl implements EntityController {
    private final Entity model;
    private final EntityView view;
    
    public EntityControllerImpl(Entity model, EntityView view) {
        this.model = model;
        this.view = view;
    }
    
    @Override
    public Point2D getPosition() {
        return this.model.getComponent(PositionComponent.class).get().getPosition();
    }

    @Override
    public EntityView getView() {
        return this.view;
    }

    @Override
    public Entity getModel() {
        return this.model;
    }

    @Override
    public void move() {
        this.model.getComponent(MoveComponent.class).get().move();
    }

}
