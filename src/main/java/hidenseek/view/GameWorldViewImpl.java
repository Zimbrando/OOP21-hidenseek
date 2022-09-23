package hidenseek.view;

import javafx.scene.canvas.GraphicsContext;

public class GameWorldViewImpl implements GameWorldView {
    
    private final GraphicsContext context;

    public GameWorldViewImpl(final GraphicsContext context) {
        this.context = context;
    }
    
    @Override
    public void update() {
        context.fill();
    }

}
