package hidenseek.model.events;

/**
 * Event thrown by entities that get damaged
 */
public class LevelUpEvent extends AbstractEvent {

    private final int currentLevel;
    
    public LevelUpEvent(final Object sender, final int currentLevel) {
        super(sender);
        this.currentLevel = currentLevel;
    }

    public int getCurrentLevel() {
        return this.currentLevel;
    }

}
