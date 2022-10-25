package hidenseek.controller;

import javafx.animation.AnimationTimer;

/**
 * Implementation with JavaFX {@link AnimationTimer}
 */
abstract class GameloopFXImpl extends AnimationTimer implements Gameloop {

    private static double MINIMUM_FPS = 15.0;
    private long pastTick;
    private double currentFrameRate;
    private boolean restarting;
    private boolean paused;
    
    @Override
    public void start() {
        if (this.paused) {
            this.restarting = true;
        }
        this.paused = false;
        super.start();
    }

    @Override
    public void stop() {
        this.paused = true;
        super.stop();
    }
       
    @Override
    public void handle(final long now) {
        final double delta =  Math.min(1.0 / MINIMUM_FPS,(now - this.pastTick) / 1e9);
        if (!restarting) {
            this.currentFrameRate = 1 / delta;
            this.tick(delta);
        } else {
            this.restarting = false;
        }
        this.pastTick = now;
    }
    
    /**
     * Method executed at a fixed rate
     */
    protected abstract void tick(double delta);
    
    
    public int getCurrentFramerate() {
        return (int)this.currentFrameRate;
    }
}
