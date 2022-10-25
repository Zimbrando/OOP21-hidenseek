package hidenseek.view;

import hidenseek.view.entities.EntityView;
import hidenseek.view.huds.HudView;

/** 
 * Components that handles the general game graphics
 */
public interface Renderer {

    /**
     * Tells the {@link GraphicsDevice} to draw the entity
     * @param view 
     *          The view of the Entity that has to be drawn
     */
    void drawEntity(EntityView view);
    
    /**
     * Tells the {@link GraphicsDevice} to draw the hud
     * @param view
     *          The view of the hud
     */
    void drawHud(HudView view);
    
    /**
     * Refresh the current {@link GraphicsDevice} to prepare it to draw
     */
    void refresh();
    
}
