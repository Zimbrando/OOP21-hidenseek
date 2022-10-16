package hidenseek.controller;

import hidenseek.controller.entities.EntityController;
import hidenseek.view.GraphicsDevice;
import hidenseek.view.entities.EntityView;
import javafx.geometry.Point2D;

/**
 * 
 * Components that handles the general game graphics
 */
public interface Renderer {

    /**
     * Tells the @link{GraphicsDevice} to draw the entity
     * @param view, the controller of the Entity that has to be drawn
     * @param position, the position of the Entity that has to be drawn
     */
    void draw(EntityView view, Point2D position);
    
    /**
     * Refresh the current @link{GraphicsDevice}, called each frame to correctly render the game
     */
    void refresh();
    
    /**
     * Creates the game's fog. Black screen where light sources can see through
     */
    void createFog();
    
    /**
     * 
     * @return the current @link{GraphicsDevice} in use
     */
    GraphicsDevice getGraphicsDevice();
}
