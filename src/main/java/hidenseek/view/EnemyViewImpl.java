package hidenseek.view;

import hidenseek.model.components.CollisionComponent;
import hidenseek.model.entities.Enemy;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class EnemyViewImpl implements EnemyView{

    @Override
    public void draw(final GraphicsDevice device, final Point2D position) {
        device.drawCircle(100, position.add(50, 50), Color.YELLOW);
        device.drawRect(50, 50, position.add(25, 25), Color.BLACK);
        device.drawRect(40, 40, position.add(30, 30), Color.RED);
        

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
    private final Enemy model;
    public EnemyViewImpl(Enemy enemy) {
        this.model = enemy;
    }

}
