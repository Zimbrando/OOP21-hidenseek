package hidenseek.controller;

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
    public void drawEntity(final EntityView view) {
        view.draw(gd);
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
