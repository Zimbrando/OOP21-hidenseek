package hidenseek.controller;

import hidenseek.model.statistics.StatisticsManager;
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
    
    void goToStatistics();
    
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
     *          Name of the scene
     * @return the current scene's root object
     */
    Pane getSceneRoot(final String name);
    
//    /**
//     * 
//     * @param controller
//     *          The gui controller
//     */
//    void resumeGame(final GameGuiController controller);
    
    /**
     * 
     * @param controller
     *          The gui controller
     */
    void pauseGame(final GameGuiController controller);
    
    StatisticsManager getStatisticsManager();
    
}
