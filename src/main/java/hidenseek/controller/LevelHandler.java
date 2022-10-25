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
     * @return If a next level exist.
     */
    
    boolean hasNext();
    
    /**
     * Resets the current level counter.
     */
    
    void reset();
    
    /**
     * 
     * @return The current level.
     */
    
    Optional<GameLevel> getCurrentLevel();
}
