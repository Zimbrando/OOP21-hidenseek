package hidenseek.view.entities;

import hidenseek.model.enums.PowerUpType;
import hidenseek.view.GraphicsDevice;
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
            device.drawCircle(30, position, Color.GREEN);        
        } else if (this.type.equals(PowerUpType.INCREASE_VISIBILITY)) {
            device.drawCircle(30, position, Color.YELLOW);
        } else {
            device.drawCircle(30, position, Color.ALICEBLUE);
        }
    }

    @Override
    public PowerUpType getPowerUpType() {
        return this.type;
    }

}
