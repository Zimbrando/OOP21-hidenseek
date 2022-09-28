package hidenseek.controller;

public interface Gameloop {

    public void start();
    
    public void stop();
    
    abstract void tick();
}
