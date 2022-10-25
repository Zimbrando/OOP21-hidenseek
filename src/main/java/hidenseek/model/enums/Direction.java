package hidenseek.model.enums;

/**
 * Possible directions to move
 */
public enum Direction {
    RIGHT(0),
    LEFT(180), 
    UP(270), 
    DOWN(90);

    int direction;
    
    Direction(final int direction) {
        this.direction = direction;
    }
    
    /**
     * @return The integer value
     */
    public int getValue() {
        return direction;
    }
}