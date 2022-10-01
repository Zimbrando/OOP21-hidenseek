package hidenseek.view;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public final class CanvasDeviceImpl implements GraphicsDevice {

    private final GraphicsContext graphics;
    
    public CanvasDeviceImpl(GraphicsContext graphics) {
        this.graphics = graphics;
    }

    @Override
    public void repaint() {
        this.fill(Color.WHITE);
    }

    @Override
    public void fill(Color c) {
        graphics.setFill(c);
        graphics.fillRect(0, 0, 1024, 860);
    }

    @Override
    public void drawImage(Image sprite, Point2D position) {
        graphics.drawImage(sprite, 0, 0);
    }

    @Override
    public void drawRect(int w, int h, Point2D position, Color color) {
        graphics.setFill(color);
        graphics.fillRect(position.getX(), position.getY(), w, h);
    }

}
