package hidenseek.model;

import javafx.geometry.Rectangle2D;

abstract class EntityBodyAbstr implements EntityBody {

    @Override
    abstract public Rectangle2D getHitbox();

    @Override
    public boolean intersects(EntityBody body) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
