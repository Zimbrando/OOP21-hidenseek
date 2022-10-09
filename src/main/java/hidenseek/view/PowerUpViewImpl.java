package hidenseek.view;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class PowerUpViewImpl implements PowerUpView {
    
    @Override
    public void draw(GraphicsDevice device, Point2D position) {
        device.drawCircle(50, position, Color.RED);
    }

}
