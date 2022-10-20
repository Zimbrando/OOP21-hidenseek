package hidenseek.components;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import hidenseek.model.entities.Entity;
import javafx.scene.input.KeyCode;
import hidenseek.model.components.InputHandlerComponent;
import hidenseek.model.components.InputHandlerComponentImpl;
import hidenseek.model.components.LinearMovementComponentImpl;
import hidenseek.model.components.MoveComponent;
import hidenseek.model.entities.AbstractEntity;

public class InputHandlerTest {

    private int count = 0;
    
    @Test
    public void InputHandlerTestAction() {
        final Entity e = new AbstractEntity(){};
        e.attach(new LinearMovementComponentImpl(1));
        InputHandlerComponent inputComponent = new InputHandlerComponentImpl();
        inputComponent.mapKeyToAction(KeyCode.A, entity -> entity.detach(MoveComponent.class));
        e.attach(inputComponent);
        inputComponent.computeScheme(Set.of(KeyCode.A));
        assertEquals(1, e.getComponents().size());
        
        inputComponent.mapKeyToAction(KeyCode.A, entity -> count += 1);
        inputComponent.computeScheme(Set.of(KeyCode.A));
        inputComponent.computeScheme(Set.of(KeyCode.A));
        inputComponent.computeScheme(Set.of(KeyCode.A));
        assertEquals(3, count);
    }
    
    @Test
    public void InputHandlerTestOneTimeActions() {
        final Entity e = new AbstractEntity(){};
        InputHandlerComponent inputComponent = new InputHandlerComponentImpl();
        inputComponent.mapKeyToOneTimeAction(KeyCode.A, entity -> count += 1);
        e.attach(inputComponent);
        inputComponent.computeScheme(Set.of(KeyCode.A));
        inputComponent.computeScheme(Set.of(KeyCode.A));
        assertEquals(1, count);
        inputComponent.computeScheme(Set.of(KeyCode.B));
        inputComponent.computeScheme(Set.of(KeyCode.A, KeyCode.B));
        assertEquals(2, count);
    }
}
