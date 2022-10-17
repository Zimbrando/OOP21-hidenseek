package hidenseek.view.entities;

import hidenseek.model.enums.PowerUpType;
import hidenseek.view.GraphicsDevice;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class PowerUpViewImpl implements PowerUpView {
    
    private static final String DECREASE_SPEED_SPRITE = "sprites/potion_01.png";
    private static final String INCREASE_SPEED_SPRITE = "sprites/potion_02.png";
    private static final String INCREASE_VISIBILITY_SPRITE = "sprites/potion_03.png";
    private static final int WIDTH = 40;
    private static final int HEIGHT = 40;
    private final PowerUpType type;

    public PowerUpViewImpl(PowerUpType type) {
        this.type = type;
    }
    
    @Override
    public void draw(GraphicsDevice device, Point2D position) {
        if (this.type.equals(PowerUpType.INCREASE_SPEED)) {
            device.drawImage(new Image(INCREASE_SPEED_SPRITE), WIDTH, HEIGHT, position);        
        } else if (this.type.equals(PowerUpType.INCREASE_VISIBILITY)) {
            device.drawImage(new Image(INCREASE_VISIBILITY_SPRITE), WIDTH, HEIGHT, position);   
        } else if (this.type.equals(PowerUpType.DECREASE_SPEED)) {
            device.drawImage(new Image(DECREASE_SPEED_SPRITE), WIDTH, HEIGHT, position);   
        }
    }

    @Override
    public PowerUpType getPowerUpType() {
        return this.type;
    }

}
