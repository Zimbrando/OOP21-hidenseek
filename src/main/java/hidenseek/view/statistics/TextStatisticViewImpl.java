package hidenseek.view.statistics;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class TextStatisticViewImpl extends Label implements TextStatisticView {

    public TextStatisticViewImpl() {
        this("");
    }
    
    public TextStatisticViewImpl(String text) {
        super();
        setAlignment(Pos.TOP_RIGHT);
        setFont(new Font("impact", 14));
        setTextFill(Color.ORANGE);
        updateText("");
    }
    
    @Override
    public void updateText(String text) {
        setText(text);
    }

}
