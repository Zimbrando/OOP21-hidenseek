package hidenseek.view;

import hidenseek.model.enums.PowerUpType;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class PowerUpViewImpl implements PowerUpView {
    
    private final PowerUpType type;
    
    public PowerUpViewImpl(PowerUpType type) {
        this.type = type;
    }
    
    @Override
    public void draw(GraphicsDevice device, Point2D position) {
        if (this.type.equals(PowerUpType.INCREASE_SPEED)) {
            device.drawCircle(50, position, Color.GREEN);        
        } else if (this.type.equals(PowerUpType.INCREASE_VISIBILITY)) {
            device.drawCircle(50, position, Color.YELLOW);
        }
    }

    @Override
    public PowerUpType getPowerUpType() {
        return this.type;
    }

}
