package hidenseek.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MainMenuController implements MenuController{

    @FXML
    private double interfaceHeight;
    
    @FXML
    private double interfaceWidth;
    
    @FXML
    private AnchorPane mainPane;
    
    @FXML
    private Button startButton;
    
    @FXML
    private Button goToSettingsButton;
    
    @FXML
    private Button exitButton;
    
    private GameSceneController gameController;
   
    
    @Override
    public void setWidth(final double width) {
       this.interfaceWidth = width;
        
    }

    @Override
    public void setHeight(final double height) {
        this.interfaceHeight = height;
    }
    
    @Override
    public double getHeight() {
        return this.interfaceHeight;
    }
    
    @Override
    public double getWidth() {
        return this.interfaceWidth;
    }
    
    @FXML
    public void handleStartClicked() {
        this.gameController.goToGame();  
    }
    
    @FXML
    public void handleGoToStatisticsClicked() {
        this.gameController.goToStats();  
    }
    
    @FXML
    public void handleExitClicked() {
        this.gameController.goToExit();
    }

    @Override
    public void setSceneController(final  GameSceneController gameController) {
        this.gameController = gameController; 
        this.mainPane.setPrefHeight(this.getHeight());
        this.mainPane.setPrefWidth(this.getWidth());
    }
    
}
