package hidenseek.model.entities;

import javafx.geometry.Point2D;

public class EntityFactoryImpl implements EntityFactory {

    @Override
    public Entity createPlayer(final Point2D position, final int playerSpeed) {
        Entity player = new Player();
        
        return player;
    }

    @Override
    public Entity createWall(Point2D position) {
        //TODO
        return new Player();
    }

    @Override
    public Entity createEnemy(Point2D position) {
        //TODO
        return new Player();
    }

    @Override
    public Entity createPowerup(Point2D position) {
        //TODO
        return new Player();
    }

}
