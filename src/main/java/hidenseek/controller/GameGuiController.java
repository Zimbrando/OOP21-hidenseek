package hidenseek.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class GameGuiController implements MenuController {
    
    private GameSceneController gameController;
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
        setPauseMode();
        //this.gameController.resumeGame(null);
    }
    
    @FXML
    public void handlegoToMainMenuClicked() {
        this.gameController.goToMenu();
        setPauseMode();
    }
    
    public void setPauseMode(){
        pausePane.setVisible(!pausePane.isVisible());
        paused = !paused;
    }
    
    
    public boolean isPaused() {
        return this.paused;
    }
    
    @Override
    public void setSceneController(final GameSceneController gameController) {
        this.gameController = gameController; 
    }

}
