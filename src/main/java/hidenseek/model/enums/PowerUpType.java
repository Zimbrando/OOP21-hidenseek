package hidenseek.model.enums;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import hidenseek.model.components.UpgradableComponent;
import hidenseek.model.entities.Entity;
import javafx.application.Platform;

public enum PowerUpType {
    INCREASE_SPEED(entity -> System.out.println("INCREASE SPEED")),
    INCREASE_VISIBILITY(entity -> System.out.println("INCREASE LIGHT RANGE")),
    DECREASE_SPEED(entity -> System.out.println("DECREASE SPEED"));
        
    public Consumer<Entity> effect;
    
    private PowerUpType(Consumer<Entity> effect) {
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
