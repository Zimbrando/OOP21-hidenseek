package hidenseek.view.statistics;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ScoreStatisticViewImpl extends Canvas implements ScoreStatisticView {

    public ScoreStatisticViewImpl() {
        this(0);
    }

    public ScoreStatisticViewImpl(double score) {
        super();
        setWidth(110);
        setHeight(30);
        updateScore(score);
    }

    @Override
    public void updateScore(double score) {

        GraphicsContext context = getGraphicsContext2D();
        context.clearRect(score, score, score, score);
        
        Image scoreOpacityMask = new Image("/assets/score_mask.png");
        for (int y = 0; y < scoreOpacityMask.getHeight(); y++) {
            for (int x = 0; x < scoreOpacityMask.getWidth(); x++) {
                int maskOpacity = (scoreOpacityMask.getPixelReader().getArgb(x, y) >> 24) & 0xFF;
                
                Color fillColor = (score * scoreOpacityMask.getWidth() / 5 > x) ? Color.ORANGE : Color.GRAY;
                fillColor = Color.color(fillColor.getRed(), fillColor.getGreen(), fillColor.getBlue(), 1.0 - maskOpacity/255.0);
                
                context.setFill(fillColor);
                context.fillRect(x, y, 1, 1);
            }
        }
    }
    
}
