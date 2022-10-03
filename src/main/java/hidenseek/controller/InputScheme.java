package hidenseek.controller;

import java.util.Set;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;

public interface InputScheme {

    Set<KeyCode> getCurrentPressedKeys();
    
    void assignInputNode(Node n);
}
