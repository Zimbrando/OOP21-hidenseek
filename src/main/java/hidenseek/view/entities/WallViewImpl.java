package hidenseek.view.entities;

import hidenseek.model.components.CollisionComponent;
import hidenseek.model.entities.Enemy;
import hidenseek.model.entities.Player;
import hidenseek.model.entities.Wall;
import hidenseek.view.GraphicsDevice;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class WallViewImpl implements WallView{

    @Override
    public void draw(final GraphicsDevice device, final Point2D position) {
        
        //TODO: remove this, only used for test
        //Draw Hitbox
        Point2D[] hitbox = model.getComponent(CollisionComponent.class).get().getHitbox().toArray(new Point2D[0]);       

        //Point2D position = model.getComponent(PositionComponent.class).get().getPosition();
        
        for(int i=0; i<hitbox.length; i++) {
            Point2D prevEntityPoint = (i == 0 ? hitbox[hitbox.length-1] : hitbox[i-1]).add(position);
            Point2D currEntityPoint = hitbox[i].add(position);
            
            device.drawLine(prevEntityPoint, currEntityPoint, Color.MAGENTA);
        }
    }

    //TODO: remove this, only used for test
    private final Wall model;
    public WallViewImpl(Wall wall) {
        this.model = wall;
    }

}
