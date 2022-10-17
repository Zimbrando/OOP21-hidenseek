package hidenseek.view.entities;

import hidenseek.view.GraphicsDevice;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;


public class MonsterViewImpl extends AbstractEntityMovableView implements MonsterView {

    private String spriteUrl;
    
    public MonsterViewImpl() {
        this.spriteUrl = "sprites/player-top.png";
    }
    

    @Override
    public void draw(final GraphicsDevice device) {

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
        
        device.drawImage(new Image(spriteUrl), 40, 40, this.getPosition().add(new Point2D(-5, -5)));
    }

}