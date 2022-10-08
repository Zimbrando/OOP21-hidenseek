package hidenseek.model.components;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import hidenseek.model.entities.Entity;
import javafx.scene.input.KeyCode;

public class InputHandlerComponentImpl extends AbstractComponent implements InputHandlerComponent {

    private final Map<KeyCode, Consumer<Entity>> actions;
    private final Map<KeyCode, Consumer<Entity>> oneTimeActions;
    private final Set<KeyCode> disabledKeys;
    
    public InputHandlerComponentImpl() {
        this.actions = new LinkedHashMap<>();
        this.oneTimeActions = new LinkedHashMap<>();
        this.disabledKeys = new HashSet<>();
    }
    
    @Override
    public void mapKeyToAction(final KeyCode key, final Consumer<Entity> action) {
        this.actions.put(key, action);
    }
    
    @Override
    public void mapKeyToOneTimeAction(final KeyCode key, final Consumer<Entity> action) {
        this.oneTimeActions.put(key, action);
    }

    @Override
    public void computeScheme(final Set<KeyCode> keysPressed) {  
        this.disabledKeys.removeIf(key -> !keysPressed.contains(key));
      
        keysPressed.forEach(key -> {
            if (this.actions.containsKey(key) && !disabledKeys.contains(key)) {
                this.actions.get(key).accept(this.getOwner().get());
            } else if (this.oneTimeActions.containsKey(key) && !disabledKeys.contains(key)) {
                this.oneTimeActions.get(key).accept(this.getOwner().get());
                this.disabledKeys.add(key);
            }
        });
    }

}
