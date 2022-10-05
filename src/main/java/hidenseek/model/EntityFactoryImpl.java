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
        Entity player = new EntityImpl();
        
        player.attach(new LifeComponentImpl(1));
        player.attach(new LinearMovementComponentImpl(position, playerSpeed));
        
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
        
        player.attach(input);
        
        return player;
    }

    @Override
    public Entity createWall(Point2D position) {
        Entity wall = new EntityImpl();
        return wall;
    }

    @Override
    public Entity createEnemy(Point2D position) {
        Entity enemy = new EntityImpl();
        return enemy;
    }

    @Override
    public Entity createPowerup(Point2D position) {
        Entity powerup = new EntityImpl();
        return powerup;
    }

}
