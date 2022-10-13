package hidenseek.model.worlds;

import java.util.Set;
import javafx.scene.input.KeyCode;

public interface GameWorld extends EntityWorld{

    void handleInput(final Set<KeyCode> keysPressed);
}
