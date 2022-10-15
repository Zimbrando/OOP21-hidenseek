package hidenseek.model.enums;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import hidenseek.model.components.MoveComponent;
import hidenseek.model.components.UpgradableComponent;
import hidenseek.model.entities.Entity;
import javafx.application.Platform;

public enum PowerUpType {
    INCREASE_SPEED(entity -> {
        //entity.getComponent(MoveComponent.class).ifPresent(c -> { c.setSpeed(c.getSpeed() + 5); resetAfter(c, 10); });
    }),
    INCREASE_VISIBILITY(entity -> System.out.println("LightComponent missing"));
        
    public Consumer<Entity> effect;
    
    PowerUpType(Consumer<Entity> effect) {
        this.effect = effect;
    }
    
    public static PowerUpType generateRandomType() {
        return values()[(int)(Math.random() * values().length)];
    }
    
    private static void resetAfter(final UpgradableComponent component, final int seconds) {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.schedule(() -> {
            Platform.runLater(() -> component.reset());
        }, seconds, TimeUnit.SECONDS);
    }
}
