package hidenseek.model;

import hidenseek.model.components.Direction;
import hidenseek.model.components.InputHandlerComponent;
import hidenseek.model.components.InputHandlerComponentImpl;
import hidenseek.model.components.LifeComponentImpl;
import hidenseek.model.components.LinearMovementComponentImpl;
import hidenseek.model.components.MoveComponent;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;

public class EntityFactoryImpl implements EntityFactory {

    @Override
    public Entity createPlayer(final Point2D position, final int playerSpeed) {
        Entity e = new EntityImpl();
        e.attach(new LifeComponentImpl(1));
        e.attach(new LinearMovementComponentImpl(position, playerSpeed));
        
        //Input
        InputHandlerComponent input = new InputHandlerComponentImpl();
        input.mapKeyToAction(KeyCode.A, entity -> entity.getComponent(MoveComponent.class)
                .ifPresent(c -> c.move(Direction.LEFT)));
        input.mapKeyToAction(KeyCode.D, entity -> entity.getComponent(MoveComponent.class)
                .ifPresent(c -> c.move(Direction.RIGHT)));
        input.mapKeyToAction(KeyCode.W, entity -> entity.getComponent(MoveComponent.class)
                .ifPresent(c -> c.move(Direction.UP)));
        input.mapKeyToAction(KeyCode.S, entity -> entity.getComponent(MoveComponent.class)
                .ifPresent(c -> c.move(Direction.DOWN)));
        
        e.attach(input);
        
        return e;
    }

    @Override
    public Entity createWall(Point2D position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Entity createEnemy(Point2D position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Entity createPowerup(Point2D position) {
        // TODO Auto-generated method stub
        return null;
    }

}
