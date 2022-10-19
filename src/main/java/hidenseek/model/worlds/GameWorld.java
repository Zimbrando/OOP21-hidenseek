package hidenseek.model.worlds;

import java.util.Set;

import hidenseek.model.entities.Entity;
import hidenseek.model.enums.GameState;
import javafx.scene.input.KeyCode;

public interface GameWorld extends EntityWorld{

    void updateInput(final Set<KeyCode> keysPressed);
    
    Set<Entity> getDeadEntities();
    
    GameState getState();
    
    void setKeys(int keys);
}
