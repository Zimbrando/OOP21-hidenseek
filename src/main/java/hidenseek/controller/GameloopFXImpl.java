package hidenseek.controller;

import javafx.animation.AnimationTimer;

public abstract class GameloopFXImpl extends AnimationTimer implements Gameloop {

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
        this.tick();
    }
}
