package hidenseek.view;

import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

/**
 * Interface for a generic graphics device
 */
public interface GraphicsDevice {

    /**
     * Draws an image 
     * @param sprite
     *          The image that has to be drawn
     * @param position
     *          The position in the game area
     * @param h
     *          Height of the sprite
     * @param w 
     *          Width of the sprite
     */
    void drawImage(Image sprite, int w, int h, Point2D position);
    

    /**
     * Draws a filled rect
     * @param w
     * @param h
     * @param position
     *          The position in the game area
     * @param color
     *          The fill color
     */
    void drawRect(int w, int h, Point2D position, Color color);
    
    /**
     * Draws a filled circle
     * @param radius
     *          Size of radius
     * @param position
     *          The center position
     * @param color
     *          The fill color
     */
    void drawCircle(int radius, Point2D position, Color color);
    
    /**
     * Repaints the drawing area, deleting all the currently drawn stuff
     */
    void repaint();
    
    /**
     * Fills the entire canvas with a Color
     * @param c
     *          The color
     */
    void fill(Color c);

    /**
     * Draws a line
     * @param positionStart
     *          First point
     * @param positionEnd
     *          End point
     * @param color
     *          Line color
     */
    void drawLine(Point2D positionStart, Point2D positionEnd, Color color);

    /**
     * Draws a filled polygon
     * @param points
     * @param pattern
     */
    void drawPolygon(List<Point2D> points, ImagePattern pattern);
    
    /**
     * Draws a filled polygon
     * @param points
     *          The vertices
     * @param color
     *          Fill color
     */
    void drawPolygon(List<Point2D> points, Color color);
    
    /**
     * @return The area width
     */
    double getWidth();
    
    /**
     * @return The area height
     */
    double getHeight();
}