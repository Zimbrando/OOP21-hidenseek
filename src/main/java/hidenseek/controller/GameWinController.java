package hidenseek.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class GameWinController implements MenuController{
    
    @FXML
    private double interfaceHeight;
    
    @FXML
    private double interfaceWidth;
    
    @FXML
    private AnchorPane gameWinMainPane;
        
    @FXML
    private Button goToMenu;
    
    private GameSceneController gameController;
   
    /**
     * The "Go to main menu" button action handler 
     */
    
    @FXML
    public void handleGoToMenuClicked() {
        this.gameController.goToMenu();  
    }
        
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

    @Override
    public void setSceneController(final  GameSceneController gameController) {
        this.gameController = gameController;
        this.gameWinMainPane.setPrefHeight(this.getHeight());
        this.gameWinMainPane.setPrefWidth(this.getWidth());
    }   
}
