package hidenseek.model;

import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class GameWorldImpl extends AnimationTimer implements GameWorld {

    
    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public void handle(long now) {
        System.out.println("Game Loop " + now);
        execute();
    }
    
    public abstract void execute();
    
}
