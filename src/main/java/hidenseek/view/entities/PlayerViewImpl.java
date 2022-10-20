package hidenseek.view.entities;

import hidenseek.view.GraphicsDevice;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;


public class PlayerViewImpl extends AbstractEntityMovableView implements  PlayerView{

    private static final Image PLAYER_TOP = new Image("assets/player-top.png");
    private static final Image PLAYER_RIGHT = new Image("assets/player-right.png");
    private static final Image PLAYER_BOTTOM = new Image("assets/player-bottom.png");
    private static final Image PLAYER_LEFT= new Image("assets/player-left.png");
    private static final int SPRITE_WIDTH = 40;
    private static final int SPRITE_HEIGHT = 40;
    
    
    @Override
    public void draw(final GraphicsDevice device) {

        switch (this.getDirection()) {
        case UP:
            device.drawImage(PLAYER_TOP, SPRITE_WIDTH, SPRITE_HEIGHT, this.getPosition().add(new Point2D(-5, -5)));
            break;
        case DOWN:
            device.drawImage(PLAYER_BOTTOM, SPRITE_WIDTH, SPRITE_HEIGHT, this.getPosition().add(new Point2D(-5, -5)));
            break;
        case RIGHT:
            device.drawImage(PLAYER_RIGHT, SPRITE_WIDTH, SPRITE_HEIGHT, this.getPosition().add(new Point2D(-5, -5)));
            break;
        case LEFT:
            device.drawImage(PLAYER_LEFT, SPRITE_WIDTH, SPRITE_HEIGHT, this.getPosition().add(new Point2D(-5, -5)));
            break;
        }

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
