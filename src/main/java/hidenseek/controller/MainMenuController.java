package hidenseek.controller;

import hidenseek.model.entities.Player;
import hidenseek.view.CanvasDeviceImpl;
import hidenseek.view.PlayerView;
import hidenseek.view.PlayerViewImpl;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;

public class MainMenuController implements MenuController{

    @FXML
    private final double interfaceHeight;
    
    @FXML
    private final double interfaceWidth;
    
    @FXML
    private Pane mainMenuRoot;

    @FXML
    private AnchorPane mainPane;
    
    @FXML
    private Button startButton;
    
    @FXML
    private Button goToSettingsButton;
    
    @FXML
    private Button exitButton;
    
    private GameSceneController gameController;
   
    public MainMenuController() {
        
        this.interfaceHeight = 1080;
        this.interfaceWidth = 1920;

    } 
   
    @FXML
    public void handleStartClicked() {
        this.gameController.goToGame();  
    }
    
    @FXML
    public void handleGoToSettingsClicked() {
        this.gameController.goToSettings();  
    }
    
    @FXML
    public void handleExitClicked() {
        this.gameController.goToExit();
    }
    
    @FXML
    public double getHeight() {
        return this.interfaceHeight;
    }
    
    @FXML
    public double getWidth() {
        return this.interfaceWidth;
    }

    @Override
    public void setSceneController(final  GameSceneController gameController) {
        this.gameController = gameController; 
    }
    
}
