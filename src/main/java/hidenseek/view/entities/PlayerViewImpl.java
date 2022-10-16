package hidenseek.view.entities;

import hidenseek.model.components.CollisionComponent;
import hidenseek.model.components.MoveComponent;
import hidenseek.model.entities.Enemy;
import hidenseek.model.entities.Player;
import hidenseek.model.enums.Direction;
import hidenseek.view.GraphicsDevice;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class PlayerViewImpl extends AbstractEntityMovableView implements  PlayerView{

    private String spriteUrl;

    public PlayerViewImpl() {
        this.spriteUrl = "sprites/player-right.png";
    }

    
    @Override
    public void draw(final GraphicsDevice device, final Point2D position) {

        switch (this.getDirection()) {
        case UP:
            this.spriteUrl = "sprites/player-top.png";
            break;
        case DOWN:
            this.spriteUrl = "sprites/player-bottom.png";
            break;
        case RIGHT:
            this.spriteUrl = "sprites/player-right.png";
            break;
        case LEFT:
            this.spriteUrl = "sprites/player-left.png";
            break;
        }
        
        device.drawImage(new Image(spriteUrl), 40, 40, position.add(new Point2D(-5, -5)));
        //device.drawCircle(100, new Point2D(positionX+50, positionY+50), Color.GRAY);
        //device.drawRect(50, 50, new Point2D(positionX+25, positionY+25), Color.BLACK);
        //device.drawRect(40, 40, new Point2D(positionX+30, positionY+30), Color.GREEN);

        //TODO: remove this, only used for test
        //Draw Hitbox
        //Point2D[] hitbox = model.getComponent(CollisionComponent.class).get().getHitbox().toArray(new Point2D[0]);       

        //Point2D position = model.getComponent(PositionComponent.class).get().getPosition();
        
//        for(int i=0; i<hitbox.length; i++) {
//            Point2D prevEntityPoint = (i == 0 ? hitbox[hitbox.length-1] : hitbox[i-1]).add(position);
//            Point2D currEntityPoint = hitbox[i].add(position);
//            
//            //device.drawLine(prevEntityPoint, currEntityPoint, Color.MAGENTA);
//        }
    }
    
}
