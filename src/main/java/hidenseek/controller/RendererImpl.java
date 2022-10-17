package hidenseek.controller;

import javafx.geometry.Point2D;

import hidenseek.view.GraphicsDevice;
import hidenseek.view.HudView;
import hidenseek.view.entities.EntityView;

/**
 * Basic Renderer
 */
public class RendererImpl implements Renderer {

    final private GraphicsDevice gd;
    
    public RendererImpl(final GraphicsDevice gd) {
        this.gd = gd;
    }
    
    @Override
    public void drawEntity(final EntityView view, final Point2D position) {
        view.draw(gd,  position);
    }
    
    @Override
    public void drawHud(final HudView view) {
        view.draw(gd);
    }

    @Override
    public void refresh() {
        gd.repaint();
    }

}
