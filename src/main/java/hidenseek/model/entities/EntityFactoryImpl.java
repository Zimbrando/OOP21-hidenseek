package hidenseek.model.entities;

import javafx.geometry.Point2D;

public class EntityFactoryImpl implements EntityFactory {

    @Override
    public Entity createPlayer(final Point2D position, final int playerSpeed) {
        return new Player();
    }

    @Override
    public Entity createWall(final Point2D position) {
        //TODO
        return new Player();
    }

    @Override
    public Entity createEnemy(final Point2D position) {
        //TODO
        return new Player();
    }

    @Override
    public Entity createPowerup(final Point2D position) {
        //TODO
        return new Player();
    }

}
