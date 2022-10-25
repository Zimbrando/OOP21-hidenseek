package hidenseek.view.entities;

import hidenseek.model.enums.LightRadius;
import hidenseek.utils.ViewUtils;
import hidenseek.view.GraphicsDevice;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;


public class PlayerViewImpl extends AbstractEntityMovableView implements PlayerView{

    private static final Image LIGHTMASK_SMALL = ViewUtils.loadImage("/assets/lightmask_small.png");
    private static final Image LIGHTMASK_LARGE = ViewUtils.loadImage("/assets/lightmask_large.png");
    private static final Image PLAYER_TOP = ViewUtils.loadImage("/assets/player-top.png");
    private static final Image PLAYER_RIGHT = ViewUtils.loadImage("/assets/player-right.png");
    private static final Image PLAYER_BOTTOM = ViewUtils.loadImage("/assets/player-bottom.png");
    private static final Image PLAYER_LEFT= ViewUtils.loadImage("/assets/player-left.png");
    private static final int SPRITE_WIDTH = 40;
    private static final int SPRITE_HEIGHT = 40;
    private LightRadius lightRadius = LightRadius.SMALL;
    
    
    @Override
    public void draw(final GraphicsDevice device) {

        //draw light radius
        Image lightMask = LIGHTMASK_SMALL;
        switch (this.getLightRadius()) {
        case SMALL:
            lightMask = LIGHTMASK_SMALL;
            break;
        case LARGE:
            lightMask = LIGHTMASK_LARGE;
            break;
        }
        
        device.drawImage(lightMask, (int)device.getWidth() * 2, (int)device.getHeight() * 2, this.getPosition().add(new Point2D(-device.getWidth(), -device.getHeight())));
        
        //draw player sprite
        Image playerSprite = PLAYER_TOP;
        switch (this.getDirection()) {
        case UP:
            playerSprite = PLAYER_TOP;
            break;
        case DOWN:
            playerSprite = PLAYER_BOTTOM;
            break;
        case RIGHT:
            playerSprite = PLAYER_RIGHT;
            break;
        case LEFT:
            playerSprite = PLAYER_LEFT;
            break;
        }
        device.drawImage(playerSprite, SPRITE_WIDTH, SPRITE_HEIGHT, this.getPosition().add(new Point2D(-5, -5)));
    }


    @Override
    public void setLightRadius(LightRadius lightRadius) {
        this.lightRadius = lightRadius;
    }


    @Override
    public LightRadius getLightRadius() {
        return this.lightRadius;
    }
}
