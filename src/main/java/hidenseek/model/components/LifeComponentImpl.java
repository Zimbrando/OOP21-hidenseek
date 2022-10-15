package hidenseek.model.components;

import hidenseek.model.events.DamageEvent;

final public class LifeComponentImpl extends AbstractObservableComponent implements LifeComponent {

    private final int maxHealth;
    private int health;
    
    public LifeComponentImpl(final int maxHealth) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }
    
    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public int getMaxHealth() {
        return this.maxHealth;
    }

    @Override
    public void hurt(final int damage) {
        this.health -= damage;
        this.health = this.health < 0 ? 0 : this.health;
        this.getOwner().ifPresent(owner -> this.notifyListener(
                new DamageEvent(this.getOwner().get(), damage),
                DamageEvent.class));
    }

    @Override
    public boolean isAlive() {
        return this.health > 0;
    }
    
}
