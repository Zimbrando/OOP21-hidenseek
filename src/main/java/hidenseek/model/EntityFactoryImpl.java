package hidenseek.model;

import hidenseek.model.components.LifeComponentImpl;
import hidenseek.model.components.LinearMovementComponentImpl;
import javafx.geometry.Point2D;

public class EntityFactoryImpl implements EntityFactory {

    @Override
    public Entity createPlayer(Point2D position) {
        Entity e = new EntityImpl();
        e.attach(new LifeComponentImpl(1));
        e.attach(new LinearMovementComponentImpl(position));
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
