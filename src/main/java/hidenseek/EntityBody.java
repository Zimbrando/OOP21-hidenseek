package hidenseek;

import javafx.geometry.Rectangle2D;

interface EntityBody {

    Rectangle2D getHitbox();
    
    boolean intersects(EntityBody body);
}
