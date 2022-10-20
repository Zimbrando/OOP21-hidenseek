package hidenseek.model.components;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import hidenseek.model.entities.Entity;
import javafx.scene.input.KeyCode;

public class InputHandlerComponentImpl extends AbstractComponent implements InputHandlerComponent {

    private final Map<KeyCode, BiConsumer<Entity, Double>> actions;
    private final Map<KeyCode, BiConsumer<Entity, Double>> oneTimeActions;
    private final Map<KeyCode, Consumer<Entity>> releaseActions;
    private final Set<KeyCode> disabledKeys;
    
    public InputHandlerComponentImpl() {
        this.actions = new LinkedHashMap<>();
        this.oneTimeActions = new LinkedHashMap<>();
        this.releaseActions = new LinkedHashMap<>();
        this.disabledKeys = new HashSet<>();
    }
    
    @Override
    public void mapKeyToAction(final KeyCode key, final BiConsumer<Entity, Double> action) {
        this.actions.put(key, action);
    }
    
    @Override
    public void mapKeyToOneTimeAction(final KeyCode key, final BiConsumer<Entity, Double> action, final Consumer<Entity> releaseAction) {
        this.oneTimeActions.put(key, action);
        this.releaseActions.put(key, releaseAction);
    }
    
    @Override
    public void mapKeyToOneTimeAction(final KeyCode key, final BiConsumer<Entity, Double> action) {
        this.oneTimeActions.put(key, action);
    }

    @Override
    public void computeScheme(final Set<KeyCode> keysPressed, final double delta) {
        this.disabledKeys.stream()
                    .filter(key -> !keysPressed.contains(key) && this.releaseActions.containsKey(key))
                    .forEach(key -> this.releaseActions.get(key).accept(this.getOwner().get()));
        this.disabledKeys.removeIf(key -> !keysPressed.contains(key));
      
        keysPressed.forEach(key -> {
            if (this.actions.containsKey(key) && !disabledKeys.contains(key)) {
                this.actions.get(key).accept(this.getOwner().get(), delta);
            } else if (this.oneTimeActions.containsKey(key) && !disabledKeys.contains(key)) {
                this.oneTimeActions.get(key).accept(this.getOwner().get(), delta);
                this.disabledKeys.add(key);
            }
        });
    }

}
