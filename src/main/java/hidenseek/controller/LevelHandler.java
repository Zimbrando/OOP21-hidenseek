package hidenseek.controller;

import java.util.Optional;

import hidenseek.model.GameLevel;

public interface LevelHandler {

    void next();
    
    boolean hasNext();
    
    Optional<GameLevel> getCurrentLevel();
}
