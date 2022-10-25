package hidenseek.view.statistics;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;


public class TextStatisticViewImpl extends Label implements TextStatisticView {

    private static final String STYLE_CLASS = "statsValue";
    
    public TextStatisticViewImpl() {
        this("");
    }
    
    public TextStatisticViewImpl(String text) {
        super();
        setAlignment(Pos.TOP_RIGHT);
        getStyleClass().add(STYLE_CLASS);
        setTextFill(Color.ORANGE);
        this.updateText("");
    }
    
    @Override
    public void updateText(String text) {
        setText(text);
    }

}
