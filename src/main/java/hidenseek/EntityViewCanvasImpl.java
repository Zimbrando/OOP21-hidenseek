package hidenseek;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class EntityViewCanvasImpl implements EntityView {
    
    private final Canvas canvas;
    private Color color;
    
    EntityViewCanvasImpl(final Canvas canvas, final Color color) {
        this.canvas = canvas;
        this.color = color;
    }
    
    @Override
    public void update() {
        GraphicsContext g = this.canvas.getGraphicsContext2D();
        g.setFill(this.color);
        g.fillRect(0, 0, 100, 100);
    }

}
