package hidenseek.controller;

import javafx.geometry.Point2D;

import java.util.HashSet;
import java.util.Set;

import hidenseek.view.GraphicsDevice;
import hidenseek.view.HudView;
import hidenseek.view.entities.EntityView;

public class RendererImpl implements Renderer {

    final private GraphicsDevice gd;
    final private Set<HudView> huds;
    
    public RendererImpl(final GraphicsDevice gd) {
        this.gd = gd;
        this.huds = new HashSet<>();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(final EntityView view, final Point2D position) {
        view.draw(gd,  position);
    }

    @Override
    public void refresh() {
        gd.repaint();
    }

    @Override
    public GraphicsDevice getGraphicsDevice() {
        return this.gd;
    }

    @Override
    public <H extends HudView> void attachHudView(H hud) {
        this.huds.add(hud);
    }

    @Override
    public void drawHud() {
        this.huds.forEach(hud -> hud.draw(gd));
    }

}
