package hidenseek.controller;

import hidenseek.view.GraphicsDevice;
import hidenseek.view.HudView;
import hidenseek.view.entities.EntityView;
import javafx.geometry.Point2D;

/** 
 * Components that handles the general game graphics
 */
public interface Renderer {

    /**
     * Tells the {@link GraphicsDevice} to draw the entity
     * @param view 
     *          The view of the Entity that has to be drawn
     * @param position
     *          The position of the Entity that has to be drawn
     */
    void drawEntity(EntityView view, Point2D position);
    
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
