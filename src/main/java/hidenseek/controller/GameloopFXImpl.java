package hidenseek.controller;

import javafx.animation.AnimationTimer;

public abstract class GameloopFXImpl extends AnimationTimer implements Gameloop {

    private long pastTick;
    private double currentFrameRate;
    
    @Override
    public void handle(final long now) {
        final double delta =  (now - this.pastTick) / 1e9;
        this.currentFrameRate = 1 / delta;
        this.tick();
        this.pastTick = now;
    }
    
    public abstract void tick();
    
    @Override
    public int getCurrentFramerate() {
        return (int)this.currentFrameRate;
    }
}
