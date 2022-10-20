package hidenseek.model.components;

import java.util.Set;
import java.util.function.Consumer;

import hidenseek.model.entities.Entity;
import javafx.scene.input.KeyCode;

/*
 * Component that handles the behaviour of an Entity 
 */
public interface InputHandlerComponent extends Component {

    /**
     * Maps a keyboard key to a callback.
     * @param key
     *           The keycode of the key
     * @param action
     *           The callback
     */
    void mapKeyToAction(KeyCode key, Consumer<Entity> action);

    /**
     * Maps a keyboard key to a callback following an 'onTyped' type of behaviour.
     * The action callback is executed once, even if the key has not been released yet.
     * The releaseAction is executed after the key is released
     * @param key
     *          The keycode of the key
     * @param action
     *          On pressed callback
     * @param releaseAction
     *          On released callback
     */
    void mapKeyToOneTimeAction(KeyCode key, Consumer<Entity> action, Consumer<Entity> releaseAction);

    /**
     * Maps a keyboard key to a callback following an 'onTyped' type of behaviour.
     * The action callback is executed once, even if the key has not been released yet.
     * @param key
     *          The keycode of the key
     * @param action
     *          On pressed callback
     */
    void mapKeyToOneTimeAction(KeyCode key, Consumer<Entity> action);

    /**
     * Executes the callbacks mapped to the relatives keys
     * @param keysPressed
     *          Set of keys currently pressed
     * @param delta
     *          Delta time between current and last frame                  
     */
    void computeScheme(Set<KeyCode> keysPressed);
}
