package hidenseek.controller;

import hidenseek.controller.entities.EntityController;
import hidenseek.controller.huds.HudController;

/**
 * The GameWorld controller that manages the {@link hidenseek.model.worlds.GameWorld} model and view
 */
public interface GameWorldController {

    /**
     * Step in the game  
     * @param delta
     *          Seconds since last frame
     */
    void update(double delta);
  
    /**
     * Pause the game
     */
    void pause();
    
    /**
     * Resume the game
     */
    void resume();
    
    /**
     * Adds an entity
     * @param entityController
     *          The entity controller
     */
    void addEntity(EntityController entityController);
    
    /**
     * Adds an hud
     * @param hudController
     *          The hud controller
     */
    void addHud(HudController hudController);
}
