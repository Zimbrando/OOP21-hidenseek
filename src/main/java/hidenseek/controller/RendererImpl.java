package hidenseek.controller;

import javafx.scene.paint.Color;
import hidenseek.view.GraphicsDevice;

public class RendererImpl implements Renderer {

    private GraphicsDevice gd;
    
    public RendererImpl(GraphicsDevice gd) {
        this.gd = gd;
    }
    
    @Override
    public void update(EntityController ec) {
        ec.getView().draw(gd,  ec.getPosition());
    }

    @Override
    public void refresh() {
        gd.repaint();
    }

    @Override
    public void createFog() {
        gd.fill(Color.BLACK);
    }

    @Override
    public GraphicsDevice getGraphicsDevice() {
        return this.gd;
    }

}
