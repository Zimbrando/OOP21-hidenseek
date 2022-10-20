package hidenseek.view.entities;

import hidenseek.model.enums.PowerUpType;
import hidenseek.view.GraphicsDevice;
import javafx.scene.image.Image;

public class PowerUpViewImpl extends AbstractEntityView implements PowerUpView {
    
    private static final Image INCREASE_SPEED = new Image("assets/potion_02.png");
    private static final Image INCREASE_VISIBILITY = new Image("assets/potion_03.png");
    private static final int SPRITE_WIDTH = 40;
    private static final int SPRITE_HEIGHT = 40;
    private final PowerUpType type;

    public PowerUpViewImpl(PowerUpType type) {
        this.type = type;
    }
    
    @Override
    public void draw(GraphicsDevice device) {
        if (this.type.equals(PowerUpType.INCREASE_SPEED)) {
            device.drawImage(INCREASE_SPEED, SPRITE_WIDTH, SPRITE_HEIGHT, this.getPosition());        
        } else if (this.type.equals(PowerUpType.INCREASE_VISIBILITY)) {
            device.drawImage(INCREASE_VISIBILITY, SPRITE_WIDTH, SPRITE_HEIGHT, this.getPosition());   
        }
    }

    @Override
    public PowerUpType getPowerUpType() {
        return this.type;
    }

}
