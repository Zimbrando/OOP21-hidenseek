package hidenseek.controller;

import java.util.Optional;

import hidenseek.model.GameLevel;

public interface LevelHandler {
    
    /**
     * Goes to the next game level.
     */
    
    void next();
    
    /**
     * 
     * @return if a next level exist.
     */
    
    boolean hasNext();
    
    /**
     * 
     * @return the current level.
     */
    Optional<GameLevel> getCurrentLevel();
}
