package hidenseek.view.entities;

import hidenseek.view.GraphicsDevice;
import javafx.scene.image.Image;

public class KeyViewImpl extends AbstractEntityView implements KeyView {

    private static final String KEY_SPRITE = "sprites/key_01.png";
    private static final int WIDTH = 40;
    private static final int HEIGHT = 40;
    
    
    @Override
    public void draw(GraphicsDevice device) {
        device.drawImage(new Image(KEY_SPRITE), WIDTH, HEIGHT, this.getPosition());
    }

}