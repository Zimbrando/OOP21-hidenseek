package hidenseek.view.huds;

import hidenseek.view.GraphicsDevice;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class KeyHudViewImpl implements KeyHudView {

    private static final Image KEY_SPRITE_BLANK = new Image("assets/key_01_transparent.png");
    private static final Image KEY_SPRITE = new Image("assets/key_01.png");
    private static final int KEY_WIDTH = 50;
    private static final int KEY_HEIGHT = 50;
    private int numKeys;
    private int maxKeys;
    private final Point2D position;
    
    public KeyHudViewImpl(final Point2D position) {
        this.position = position;
    }
    
    @Override
    public void draw(final GraphicsDevice device) {
        for (int i = 0; i < maxKeys; i++) {
            final Point2D newPosition = this.position.add(new Point2D(KEY_WIDTH * i, 0));
            if (i < numKeys) {
                device.drawImage(KEY_SPRITE, KEY_WIDTH, KEY_HEIGHT, newPosition);            
            }
            device.drawImage(KEY_SPRITE_BLANK, KEY_WIDTH, KEY_HEIGHT, this.position.add(new Point2D(KEY_WIDTH * i, 0)));
        }
    }

    @Override
    public void updateKeys(final int nKeys) {
        this.numKeys = nKeys;
    }

    @Override
    public void setMaxKeys(final int maxKeys) {
        this.maxKeys = maxKeys;
    }

}
