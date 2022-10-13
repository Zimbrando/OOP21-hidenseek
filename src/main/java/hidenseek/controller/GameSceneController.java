package hidenseek.controller;

import javafx.scene.layout.Pane;

public interface GameSceneController {
    
    
    /**
     * Goes to general game menu. 
     */
  
    void goToMenu();
    
    /**
     * Enters the game. 
     */
    
    void goToGame();
    
    /**
     * Goes to pause menu.
     */
    
    void goToPause();
    
    /**
     * Goes to the statistics page.
     */
    
    void goToSettings();
    
    /**
     * Goes to the statistics page.
     */
    
    void goToStats();
    
    /**
     * Exits the program. 
     */
    
    void goToExit(); 
    
    /**
     * 
     * @param name 
     * @return the current scene's root object
     */
    Pane getSceneRoot(final String name);
    
    
    void resumeGame(final GameGuiController controller);
    
    void pauseGame(final GameGuiController controller);
    
}
