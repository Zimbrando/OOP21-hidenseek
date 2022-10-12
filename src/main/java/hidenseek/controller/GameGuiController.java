package hidenseek.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameGuiController implements MenuController {
    
    private GameSceneController gameController;
    
    
    @FXML
    private Button backButton;
    
    @FXML
    public void handleBackClicked() {
        this.gameController.goToMenu();
        setPauseMode();
    }
    
    public void setPauseMode(){
        backButton.setVisible(!backButton.isVisible());
    }
    
    @Override
    public void setSceneController(final GameSceneController gameController) {
        this.gameController = gameController; 
    }

}
