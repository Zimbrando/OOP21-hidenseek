package hidenseek.view;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * Interface for a generic graphics device
 */
public interface GraphicsDevice {

    /**
     * Draws an image in the 'canvas'
     * 
     * @param sprite, the image that has to be drawn
     * @param position, the position in the 'canvas'
     */
    void drawImage(Image sprite, Point2D position);
    
    /**
     * Draws a filled rect in the 'canvas'
     * @param w, width
     * @param h, height
     * @param position, the position in the 'canvas'
     * @param color, the fill color
     */
    void drawRect(int w, int h, Point2D position, Color color);
    
    void drawCircle(int radius, Point2D position, Color color);
    
    /**
     * Repaints the entire 'canvas'
     */
    void repaint();
    
    /**
     * Fills the entire canvas with a Color
     * @param c, the color
     */
    void fill(Color c);
}