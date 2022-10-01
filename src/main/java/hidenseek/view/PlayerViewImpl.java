package hidenseek.view;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class PlayerViewImpl implements PlayerView{

    @Override
    public void draw(GraphicsDevice device, Point2D position) {
        device.drawRect(100, 100, position, Color.BLACK);
        device.drawRect(50, 50, position.add(new Point2D(50, 50)), Color.RED);
        device.drawRect(50, 50, position, Color.RED);   
    }

}
