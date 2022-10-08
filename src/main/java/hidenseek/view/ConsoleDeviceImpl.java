package hidenseek.view;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ConsoleDeviceImpl implements GraphicsDevice{

    public ConsoleDeviceImpl() {
        // TODO Auto-generated constructor stub
    }
     
    @Override
    public void drawImage(Image sprite, Point2D position) {
        System.out.println("Disegno sprite at " + position);
    }

    @Override
    public void drawRect(int w, int h, Point2D position, Color color) {
        System.out.println("Rect (" + w + "," + h + ") at " + position + " color:" + color);
    }

    @Override
    public void drawCircle(int radius, Point2D position, Color color) {
        System.out.println("Circle radius:" + radius + " at " + position + " color:" + color);
    }

    @Override
    public void repaint() {
        System.out.println("Repaint");
    }

    @Override
    public void fill(Color c) {
        System.out.println("Fill");
    }

}
