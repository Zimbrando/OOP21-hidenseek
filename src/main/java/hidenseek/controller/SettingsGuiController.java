package hidenseek.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class SettingsGuiController implements MenuController {

    @FXML
    private Button exitButton;
    
//    @FXML
//    private CheckBox fullScreenToggle;
    
    private GameSceneController gameController;
   
    @FXML
    public void handleSaveAndExitClicked() {
        this.gameController.goToMenu();  
    }
    
//    @FXML
//    public void handleFullScreenClicked() {
//        this.gameController.getMainStage().setFullScreen(this.fullScreenToggle.selectedProperty().getValue());  
//    }
    
    @Override
    public void setSceneController(final  GameSceneController gameController) {
        this.gameController = gameController; 
    }

}
