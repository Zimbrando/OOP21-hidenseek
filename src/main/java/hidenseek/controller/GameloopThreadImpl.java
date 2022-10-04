package hidenseek.controller;

public abstract class GameloopThreadImpl implements Gameloop, Runnable {
    
    private Thread thread;
    private boolean isRunning = false;
    
    @Override
    public void start() {
        thread = new Thread(this);
        thread.start();
        this.isRunning = true;
    }

    @Override
    public void stop() {
        this.isRunning = false;
    }
    
    @Override
    public void run() {
        System.out.println("IM ON A THREAD");
        while(this.isRunning) {
            this.tick();        
        }
    }
    
    public abstract void tick();
    

    @Override
    public int getCurrentFramerate() {
        // TODO Auto-generated method stub
        return 0;
    }

}
