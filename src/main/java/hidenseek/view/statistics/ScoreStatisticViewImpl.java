package hidenseek.view.statistics;

import hidenseek.utils.ViewUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ScoreStatisticViewImpl extends Canvas implements ScoreStatisticView {

    private final static Image scoreOpacityMask = ViewUtils.loadImage("/assets/score_mask.png");
    
    public ScoreStatisticViewImpl() {
        this(0);
    }

    public ScoreStatisticViewImpl(double score) {
        super();
        setWidth(110);
        setHeight(30);
        this.updateScore(score);
    }

    @Override
    public void updateScore(double score) {
        final GraphicsContext context = getGraphicsContext2D();
        context.clearRect(score, score, score, score);
        
        for (int y = 0; y < scoreOpacityMask.getHeight(); y++) {
            for (int x = 0; x < scoreOpacityMask.getWidth(); x++) {
                final int maskOpacity = (scoreOpacityMask.getPixelReader().getArgb(x, y) >> 24) & 0xFF;
                
                Color fillColor = (score * scoreOpacityMask.getWidth() / 5 > x) ? Color.ORANGE : Color.GRAY;
                fillColor = Color.color(fillColor.getRed(), fillColor.getGreen(), fillColor.getBlue(), 1.0 - maskOpacity/255.0);
                
                context.setFill(fillColor);
                context.fillRect(x, y, 1, 1);
            }
        }
    }
    
}
