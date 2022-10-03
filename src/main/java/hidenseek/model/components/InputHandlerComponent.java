package hidenseek.model.components;

import java.util.Set;
import java.util.function.Consumer;
import hidenseek.model.Entity;
import javafx.scene.input.KeyCode;

public interface InputHandlerComponent extends Component {
    
    void mapKeyToAction(KeyCode key, Consumer<Entity> action);
   
    void computeScheme(Set<KeyCode> keysPressed);
}
