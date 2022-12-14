package hidenseek.controller.huds;

import hidenseek.view.huds.HudView;

/**
 * Models a generic hud interface controller
 *
 */
public interface HudController {

    /**
     * Updates the view based on model data
     */
    void update();
    
    /**
     * @return The relative HudView
     */
    HudView getView();
}
