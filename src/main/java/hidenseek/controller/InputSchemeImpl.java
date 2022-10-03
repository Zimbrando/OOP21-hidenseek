package hidenseek.controller;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;

public class InputSchemeImpl implements InputScheme {

    private Optional<Node> inputNode;
    private Set<KeyCode> currentPressedKeys;
    
    public InputSchemeImpl() {
        this.inputNode = Optional.empty();
        this.currentPressedKeys = new LinkedHashSet<KeyCode>();
    }
    
    @Override
    public Set<KeyCode> getCurrentPressedKeys() {
        return new LinkedHashSet<KeyCode>(this.currentPressedKeys);
    }

    @Override
    public void assignInputNode(Node n) {
        this.inputNode = Optional.ofNullable(n);
        this.mapEvents();
    }
    
    private void mapEvents() {
        if (this.inputNode.isEmpty()) return;
        Node input = this.inputNode.get();
        input.setOnKeyPressed((e) -> this.currentPressedKeys.add(e.getCode()));
        input.setOnKeyReleased((e) -> this.currentPressedKeys.remove(e.getCode()));
    }
}
