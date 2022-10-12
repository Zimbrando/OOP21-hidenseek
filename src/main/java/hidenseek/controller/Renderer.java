package hidenseek.controller;

import hidenseek.view.GraphicsDevice;

/**
 * 
 * Components that handles the general game graphics
 */
public interface Renderer {

    /**
     * Tells the @link{GraphicsDevice} to draw the entity
     * @param ec, the controller of the Entity that has to be drawn
     */
    void update(EntityController ec);
    
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
