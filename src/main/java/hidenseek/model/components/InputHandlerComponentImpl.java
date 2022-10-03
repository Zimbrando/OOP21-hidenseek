package hidenseek.model.components;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import hidenseek.model.Entity;
import javafx.scene.input.KeyCode;

public class InputHandlerComponentImpl extends ComponentImpl implements InputHandlerComponent {

    Map<KeyCode, Consumer<Entity>> actions;
    
    public InputHandlerComponentImpl() {
        this.actions = new LinkedHashMap<KeyCode, Consumer<Entity>>();
    }
    
    @Override
    public void mapKeyToAction(KeyCode key, Consumer<Entity> action) {
        this.actions.put(key, action);
    }

    @Override
    public void computeScheme(Set<KeyCode> keysPressed) {
        keysPressed.forEach(key -> {
            if (this.actions.containsKey(key)) {
                this.actions.get(key).accept(this.getOwner().get());
            }
        });
    }

}
