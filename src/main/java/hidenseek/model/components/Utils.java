package hidenseek.model.components;

import hidenseek.model.entities.Entity;

final public class Utils {
    
    static public double distanceBetween(final Entity e1, final Entity e2) {
        // check position component
        if(e1.getComponent(PositionComponent.class).isEmpty()
        || e2.getComponent(PositionComponent.class).isEmpty()) {
            return -1;
        }
        // get position components
        final PositionComponent pos1 = e1.getComponent(PositionComponent.class).get();
        final PositionComponent pos2 = e2.getComponent(PositionComponent.class).get();
        return pos1.getPosition().distance(pos2.getPosition());
    }
}
