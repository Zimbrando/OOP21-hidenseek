package hidenseek.model;

import java.util.Set;

import hidenseek.model.entities.Entity;
import javafx.scene.input.KeyCode;

public interface GameWorld {

    void update();
    
    void handleInput(Set<KeyCode> keysPressed);
    
    void addEntity(Entity entity);
}
