package hidenseek.model.components;

import java.util.function.Consumer;

import hidenseek.model.entities.Entity;

public class TemporaryEffectImpl extends AbstractTemporaryComponent {
    
    private final Consumer<Entity> effect;
    
    TemporaryEffectImpl(final Consumer<Entity> effect, final long timeToLive) {
        super(timeToLive);
        this.effect = effect;
    }

    @Override
    void onStart() {
        this.getOwner().ifPresent(owner -> this.effect.accept(owner));
    }

    @Override
    void onDestroy() {
        //Reset to default the stats
    }
}
