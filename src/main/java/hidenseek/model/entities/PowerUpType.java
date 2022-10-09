package hidenseek.model.entities;

import java.util.function.BiConsumer;

import hidenseek.model.components.MoveComponent;
import hidenseek.model.events.Event;

public enum PowerUpType {
    INCREASE_SPEED((event, entity) -> entity.getComponent(MoveComponent.class).ifPresent(c -> c.setSpeed(c.getSpeed() + 5))),
    INCREASE_VISIBILITY((event, entity) -> System.out.println("LightComponent missing"));
        
    BiConsumer<Event, Entity> effect;
    
    PowerUpType(BiConsumer<Event, Entity> effect) {
        this.effect = effect;
    }
    
    public static PowerUpType generateRandomType() {
        return values()[(int)(Math.random() * values().length)];
    }
}
