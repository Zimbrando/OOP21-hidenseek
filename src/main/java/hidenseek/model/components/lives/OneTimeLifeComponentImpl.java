package hidenseek.model.components.lives;

import hidenseek.model.components.AbstractObservableComponent;
import hidenseek.model.events.DamageEvent;

/**
 * Life for Collectibles and Powerups
 */
public class OneTimeLifeComponentImpl extends AbstractObservableComponent implements LifeComponent {

    private static final int MAX_HEALTH = 1;
    private boolean alive = true;
    
    @Override
    public int getHealth() {
        return this.alive ? 1 : 0;
    }

    @Override
    public int getMaxHealth() {
        return MAX_HEALTH;
    }

    @Override
    public void hurt(final int damage) {
        this.alive = false;
        this.getOwner().ifPresent(owner -> this.notifyListener(
                new DamageEvent(this.getOwner().get(), damage),
                DamageEvent.class));
    }

    @Override
    public boolean isAlive() {
        return this.alive;
    }

}
