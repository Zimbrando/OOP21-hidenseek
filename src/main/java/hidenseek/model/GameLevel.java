package hidenseek.model;

import java.util.Set;

import hidenseek.model.entities.Entity;
import javafx.scene.input.KeyCode;

public interface GameLevel {

    Set<Entity> getWalls();
}
