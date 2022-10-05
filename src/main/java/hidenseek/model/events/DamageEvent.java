package hidenseek.model.events;

import hidenseek.model.Entity;

public class DamageEvent extends AbstractEvent {

    private int damage;
    
    public DamageEvent(Entity sender, int damage) {
        super(sender);
        this.damage = damage;
    }

    public int getDamage() {
        return this.damage;
    }

}
