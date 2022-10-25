package hidenseek.controller;

/**
 * Handles and provides a loop at a fixed frame rate
 */
public interface Gameloop {

    /**
     * Starts or resumes the loop
     */
    void start();
    
    /**
     * Stops the loop
     */
    void stop();
    
    /**
     * @return The approximate current frame rate
     */
    int getCurrentFramerate();
}
