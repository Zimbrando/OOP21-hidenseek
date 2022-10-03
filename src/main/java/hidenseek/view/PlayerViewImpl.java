package hidenseek.view;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class PlayerViewImpl implements PlayerView{

    @Override
    public void draw(GraphicsDevice device, Point2D position) {
        device.drawRect(50, 50, position, Color.BLACK);
        device.drawRect(40, 40, position.add(5, 5), Color.RED);
    }

}
