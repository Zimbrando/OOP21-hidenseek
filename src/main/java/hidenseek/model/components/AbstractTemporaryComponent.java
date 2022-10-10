package hidenseek.model.components;

import java.util.Timer;
import java.util.TimerTask;


abstract class AbstractTemporaryComponent extends AbstractComponent implements TemporaryComponent {

    private final long timeToLive; //ms
    
    AbstractTemporaryComponent(final long timeToLive) {
        this.timeToLive = timeToLive;
    }
    
    protected void start() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                onDestroy(); 
                detach();
            }
        }, this.timeToLive);
    }
    
    abstract void onStart();
    
    abstract void onDestroy();
    
    @Override
    public double getTimeToLive() {
        return this.timeToLive;
    }
}
