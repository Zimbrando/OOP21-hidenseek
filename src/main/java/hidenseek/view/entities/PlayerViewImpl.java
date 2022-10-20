package hidenseek.view.entities;

import java.util.HashSet;

import hidenseek.view.GraphicsDevice;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;


public class PlayerViewImpl extends AbstractEntityMovableView implements  PlayerView{

    private String spriteUrl;

    public PlayerViewImpl() {
        this.spriteUrl = "assets/player-right.png";
    }
    
    @Override
    public void draw(final GraphicsDevice device) {

        switch (this.getDirection()) {
        case UP:
            this.spriteUrl = "assets/player-top.png";
            break;
        case DOWN:
            this.spriteUrl = "assets/player-bottom.png";
            break;
        case RIGHT:
            this.spriteUrl = "assets/player-right.png";
            break;
        case LEFT:
            this.spriteUrl = "assets/player-left.png";
            break;
        }
        
        device.drawImage(new Image(spriteUrl), 40, 40, this.getPosition().add(new Point2D(-5, -5)));
        
        //DEBUG: hitbox draw
        Point2D[] hitbox = new Point2D[] {
                new Point2D(0, 0),
                new Point2D(0, 30),
                new Point2D(30, 30),
                new Point2D(30, 0)
        };
        
        for(int i=0; i<hitbox.length; i++) {
            Point2D prevEntityPoint = (i == 0 ? hitbox[hitbox.length-1] : hitbox[i-1]).add(this.getPosition());
            Point2D currEntityPoint = hitbox[i].add(this.getPosition());
            device.drawLine(prevEntityPoint, currEntityPoint, javafx.scene.paint.Color.MAGENTA);
        }
    }
    
}
