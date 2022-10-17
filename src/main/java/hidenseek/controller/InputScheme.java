package hidenseek.controller;

import java.util.Set;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;

/**
 * Provides the input currently activated
 */
public interface InputScheme {

    /**
     * @return The set of keys that are currently pressed by {@link KeyCode}
     */
    Set<KeyCode> getCurrentPressedKeys();
    
    /**
     * Assign the JavaFx node that will be binded with this scheme
     * @param n
     *          The node
     */
    void assignInputNode(Node n);
}
