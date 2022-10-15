package hidenseek;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import hidenseek.controller.InputScheme;
import hidenseek.controller.InputSchemeImpl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;


public class InputSchemeTest {
    
    @Test
    public void testInputSchemeOneKey() {
        final InputScheme s = new InputSchemeImpl();
        final Pane node = new Pane();
        s.assignInputNode(node);
        node.fireEvent(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.A, true, true, true, true));
        assertEquals(1, s.getCurrentPressedKeys().size());
        assertTrue(s.getCurrentPressedKeys().contains(KeyCode.A));
    }
    
    @Test
    public void testInputSchemeMultipleKeys() {
        final InputScheme s = new InputSchemeImpl();
        final Pane node = new Pane();
        s.assignInputNode(node);
        node.fireEvent(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.A, true, true, true, true));
        node.fireEvent(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.B, true, true, true, true));
        node.fireEvent(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.C, true, true, true, true));
        assertEquals(3, s.getCurrentPressedKeys().size());
        assertTrue(s.getCurrentPressedKeys().contains(KeyCode.A));
        assertTrue(s.getCurrentPressedKeys().contains(KeyCode.B));
        assertTrue(s.getCurrentPressedKeys().contains(KeyCode.C));
    }
    
    @Test
    public void testInputSchemeReleaseKeys() {
        final InputScheme s = new InputSchemeImpl();
        final Pane node = new Pane();
        s.assignInputNode(node);
        node.fireEvent(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.A, true, true, true, true));
        node.fireEvent(new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.B, true, true, true, true));
        assertEquals(2, s.getCurrentPressedKeys().size());
        node.fireEvent(new KeyEvent(KeyEvent.KEY_RELEASED, "", "", KeyCode.B, true, true, true, true));
        assertEquals(1, s.getCurrentPressedKeys().size());
        assertFalse(s.getCurrentPressedKeys().contains(KeyCode.B));
    }
}
