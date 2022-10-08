package hidenseek.view;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ConsoleDeviceImpl implements GraphicsDevice{
     
    @Override
    public void drawImage(final Image sprite, final Point2D position) {
        System.out.println("Disegno sprite at " + position);
    }

    @Override
    public void drawRect(final int w, final int h, final Point2D position, final Color color) {
        System.out.println("Rect (" + w + "," + h + ") at " + position + " color:" + color);
    }

    @Override
    public void drawCircle(final int radius, final Point2D position, final Color color) {
        System.out.println("Circle radius:" + radius + " at " + position + " color:" + color);
    }

    @Override
    public void repaint() {
        System.out.println("Repaint");
    }

    @Override
    public void fill(final Color c) {
        System.out.println("Fill");
    }

}
