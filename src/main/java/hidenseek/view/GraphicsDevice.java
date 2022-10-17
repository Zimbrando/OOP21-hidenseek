package hidenseek.view;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Interface for a generic graphics device
 */
public interface GraphicsDevice {

    /**
     * Draws an image 
     * @param sprite
     *          The image that has to be drawn
     * @param position
     *          The position in the 'canvas'
     */
    void drawImage(Image sprite, int w, int h, Point2D position);
    
    /**
     * Draws a filled rect
     * @param w 
     *          Width
     * @param h
     *          Height
     * @param position
     *          The position in the 'canvas'
     * @param color
     *          The fill color
     */
    void drawRect(int w, int h, Point2D position, Color color);
    
    /**
     * Draws a filled circle
     * @param radius
     * @param position
     *          The center position
     * @param color
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
     * @param positionEnd
     * @param color
     */
    void drawLine(Point2D positionStart, Point2D positionEnd, Color color);
    
    /**
     * @return The area width
     */
    double getWidth();
    
    /**
     * @return The area height
     */
    double getHeight();
}