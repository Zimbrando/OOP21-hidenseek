package hidenseek.model.enums;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import hidenseek.model.components.MoveComponent;
import hidenseek.model.components.UpgradableComponent;
import hidenseek.model.entities.Entity;
import javafx.application.Platform;

/**
 * Types of PowerUps
 */
public enum PowerUpType {

    INCREASE_SPEED(entity -> entity.getComponent(MoveComponent.class).ifPresent(component -> {
        if (!component.isUpgraded()) {
            component.setSpeed(component.getSpeed() * 1.5);
            resetAfter(component, 7);
        }
    })),

    INCREASE_VISIBILITY(entity -> System.out.println("INCREASE LIGHT RANGE"));

    public static PowerUpType getValue(int value) {
        return new PowerUpType[]{INCREASE_SPEED, INCREASE_VISIBILITY}[value];
    }
    
    /**
     * The effect applied to the Entity using the PowerUp
     */
    public Consumer<Entity> effect;

    private PowerUpType(Consumer<Entity> effect) {
        this.effect = effect;
    }

    /**
     * @return A random type
     */
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
