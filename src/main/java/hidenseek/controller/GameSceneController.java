package hidenseek.controller;

import hidenseek.controller.fxml.GameGuiController;
import hidenseek.model.statistics.StatisticsManager;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
     * Goes to the statistics page.
     */
    
    void goToStats();
    
    /**
     * Goes to the "You Won" page.
     */
    
    void goToGameWin();
    
    /**
     * Goes to the "Game Over" page.
     */
    
    void goToGameOver();
    
    /**
     * Exits the program. 
     */
    
    void goToExit(); 
    
    /**
     * 
     * @param name of the scene.
     *  
     * @return the current scene's root object.
     */
    
    Pane getSceneRoot(String name);
        
    /**
     * Toggles the pause mode in the game canvas gui.
     * @param controller of the GameGui graphical interface.
     */
    
    void pauseGame(GameGuiController controller);
    
    /**
     * 
     * @return the main Stage object for displaying UIs.
     */
    
    Stage getMainStage();
    
    /**
     * 
     * @return the statistics manager object.
     */
    
    StatisticsManager getStatisticsManager();
    
}
