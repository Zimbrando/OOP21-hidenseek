package hidenseek.view.entities;

import hidenseek.view.GraphicsDevice;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;


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
    }
    
}
