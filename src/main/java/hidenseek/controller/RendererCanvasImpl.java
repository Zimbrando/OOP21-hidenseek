package hidenseek.controller;

import hidenseek.view.EntityView;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class RendererCanvasImpl implements Renderer {

    private GraphicsContext g;
    
    public RendererCanvasImpl(final GraphicsContext graphics) {
        this.g = graphics;
    }

    @Override
    public <V extends EntityView> void draw(V view, Point2D position) {
        g.fillRect(position.getX(), position.getY(), 50, 50);
    }

}
