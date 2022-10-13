package hidenseek.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SettingsGuiController implements MenuController {

    @FXML
    private Button exitButton;
    
    private GameSceneController gameController;
   
    @FXML
    public void handleSaveAndExitClicked() {
        this.gameController.goToMenu();  
    }
    
    @Override
    public void setSceneController(final  GameSceneController gameController) {
        this.gameController = gameController; 
    }

}
