package hidenseek.utils;

import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

final public class ViewUtils {

    private ViewUtils() {

    }

    /**
     * Loads a JavaFX image from the class path
     * @param path
     *          Path to the image
     * @return The Image object, null if the image does not exist
     */
    static public Image loadImage(final String path) {
        final InputStream stream = ViewUtils.class.getResourceAsStream(path);
        try {
            final Image image = SwingFXUtils.toFXImage(ImageIO.read(stream), null);
            return image;
        } catch (IOException e) {
            return null;
        }
    }
}