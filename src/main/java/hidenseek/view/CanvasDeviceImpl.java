package hidenseek.view;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public final class CanvasDeviceImpl implements GraphicsDevice {

    private final GraphicsContext graphics;
    private final double width, height;
    
    public CanvasDeviceImpl(final GraphicsContext graphics) {
        this.graphics = graphics;
        this.width = graphics.getCanvas().getWidth();
        this.height = graphics.getCanvas().getHeight();
    }

    @Override
    public void repaint() {
        this.graphics.clearRect(0, 0, this.width, this.height);
    }

    @Override
    public void fill(final Color c) {
        graphics.setFill(c);
        graphics.fillRect(0, 0, this.width, this.height);
    }

    @Override
    public void drawImage(final Image sprite, final Point2D position) {
        graphics.drawImage(sprite, position.getX(), position.getY());
    }

    @Override
    public void drawRect(final int w, final int h, final Point2D position, final Color color) {
        graphics.setFill(color);
        graphics.fillRect(position.getX(), position.getY(), w, h);
    }

    @Override
    public void drawCircle(final int radius, final Point2D position, final Color color) {
        graphics.setFill(color);
        graphics.fillOval(position.getX() - radius / 2, position.getY() - radius / 2, radius, radius);
    }
}
