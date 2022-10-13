package hidenseek.view;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class MonsterViewImpl implements PlayerView{

    @Override
    public void draw(final GraphicsDevice device, final Point2D position) {
        device.drawCircle(100, position.add(25, 25), Color.GREEN);
        device.drawRect(50, 50, position, Color.BLACK);
        device.drawRect(40, 40, position.add(5, 5), Color.BEIGE);
    }

}