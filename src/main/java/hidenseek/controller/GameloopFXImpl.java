package hidenseek.controller;

import javafx.animation.AnimationTimer;

public abstract class GameloopFXImpl extends AnimationTimer implements Gameloop {

    private long pastTick;
    private double currentFrameRate;
    
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
        double delta =  (now - this.pastTick) / 1e9;
        this.currentFrameRate = 1 / delta;
        this.tick();
        this.pastTick = now;
    }
    
    public abstract void tick();
    
    public int getCurrentFramerate() {
        return (int)this.currentFrameRate;
    }
}
