package hidenseek.controller;

import java.io.IOException;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public interface GameSceneController {
    
    
    /***
     * Goes to general game menu.
     * @throws IOException 
     */
  
    void goToMenu();
    
    /****
     * Enters the game. 
     */
    
    void goToGame();
    
    /***
     * Goes to pause menu.
     */
    
    void goToResume();
    
    /***
     * Goes to the statistics page.
     */
    
    void goToSettings();
    
    /***
     * Goes to the statistics page.
     */
    
    void goToStats();
    
    /****
     * Exits the program. 
     */
    
    void goToExit(); 
    
    
    Pane getSceneRoot(final String name);
    
}
