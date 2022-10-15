package hidenseek.model.components;

import hidenseek.model.events.DamageEvent;

public class OneTimeLifeComponentImpl extends AbstractObservableComponent implements LifeComponent {

    static final int MAX_HEALTH = 1;
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

}
