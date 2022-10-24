package hidenseek.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class GameGuiController implements MenuController {
    
    private GameSceneController gameSceneController;
    private GameWorldController gameController;
    private boolean paused;
    
    @FXML
    private Pane pausePane;
    
    @FXML
    private Text pauseTitle;
    
    @FXML
    private Button resumeButton;
    
    @FXML
    private Button goToMainMenuButton;
    
    
    
    @FXML
    public void handleResumeClicked() {
        this.gameController.resume();
        setPauseMode();
        //this.gameController.resumeGame(null);
    }
    
    @FXML
    public void handlegoToMainMenuClicked() {
        this.gameSceneController.goToMenu();
        setPauseMode();
    }
    
    public void setPauseMode(){
        pausePane.setVisible(!pausePane.isVisible());
        paused = !paused;
    }
    
    
    public boolean isPaused() {
        return this.paused;
    }
    
    public void setGameController(final GameWorldController gameController) {
        this.gameController = gameController; 
    }

    
    @Override
    public void setSceneController(final GameSceneController gameSceneController) {
        this.gameSceneController = gameSceneController; 
    }

    @Override
    public void onActivate() {
        
    }

}
