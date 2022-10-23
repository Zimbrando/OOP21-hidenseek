package hidenseek.view;

import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

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
    public void drawImage(final Image sprite, final int w, final int h, final Point2D position) {
        graphics.drawImage(sprite, position.getX(), position.getY(), w, h);
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

    @Override
    public void drawLine(final Point2D positionStart, final Point2D positionEnd, final Color color) {
        graphics.setStroke(color);
        graphics.strokeLine(positionStart.getX(), positionStart.getY(), positionEnd.getX(), positionEnd.getY());
    }
    
    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public double getHeight() {
        return this.height;
    }

    @Override
    public void drawPolygon(final List<Point2D> points, final ImagePattern pattern) {
        this.graphics.setFill(pattern);;
        double[] xPoints = points.stream().mapToDouble(point -> point.getX()).toArray();
        double[] yPoints = points.stream().mapToDouble(point -> point.getY()).toArray();
        this.graphics.fillPolygon(xPoints, yPoints, points.size());
    }

    @Override
    public void drawPolygon(final List<Point2D> points, final Color color) {
        this.graphics.setFill(color);
        double[] xPoints = points.stream().mapToDouble(point -> point.getX()).toArray();
        double[] yPoints = points.stream().mapToDouble(point -> point.getY()).toArray();
        this.graphics.fillPolygon(xPoints, yPoints, points.size());
    }

}
