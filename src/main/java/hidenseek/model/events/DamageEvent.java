package hidenseek.model.events;

import hidenseek.model.entities.Entity;

public class DamageEvent extends AbstractEvent {

    private final int damage;
    
    public DamageEvent(final Entity sender, final int damage) {
        super(sender);
        this.damage = damage;
    }

    public int getDamage() {
        return this.damage;
    }

}
