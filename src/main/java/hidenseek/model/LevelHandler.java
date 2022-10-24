package hidenseek.model;

import java.util.Optional;

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
     * 
     * @return The current level.
     */
    Optional<GameLevel> getCurrentLevel();
}
