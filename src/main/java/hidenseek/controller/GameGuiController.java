package hidenseek.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class GameGuiController implements MenuController {
    
    private GameSceneController gameSceneController;
    private GameWorldController gameController;
    private boolean paused;
    
    @FXML
    private double interfaceHeight;
    
    @FXML
    private double interfaceWidth;
    
    @FXML
    private AnchorPane gameGuiMainPane;
    
    @FXML
    private Pane pausePane;
    
    @FXML
    private Text pauseTitle;
    
    @FXML
    private Button resumeButton;
    
    @FXML
    private Button goToMainMenuButton;
    
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
    public void handleResumeClicked() {
        this.gameController.resume();
        setPauseMode();
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
        this.gameGuiMainPane.setPrefHeight(this.getHeight());
        this.gameGuiMainPane.setPrefWidth(this.getWidth());
    }

}
