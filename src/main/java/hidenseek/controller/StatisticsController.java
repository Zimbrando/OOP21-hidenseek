package hidenseek.controller;

import hidenseek.model.statistics.Statistic;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class StatisticsController implements MenuController{

    @FXML
    private double interfaceHeight;
    
    @FXML
    private double interfaceWidth;
    
    @FXML
    private AnchorPane gameStatsMainPane;
    
    @FXML
    private VBox statisticsContainer;
    
    @FXML
    private Button exitButton;
    
    private GameSceneController gameController;
       
    @FXML
    public void handleStatisticsExit() {
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
    public void setSceneController(final GameSceneController gameController) {
        this.gameController = gameController;
        
        this.gameStatsMainPane.setPrefHeight(this.getHeight());
        this.gameStatsMainPane.setPrefWidth(this.getWidth());
        
        for(final Statistic<?> statistic : gameController.getStatisticsManager().getStatistics()) {
            final HBox statisticBox = new HBox();
            statisticBox.setAlignment(Pos.TOP_CENTER);
            
            final Label statisticTitleLbl = new Label(statistic.getTitle());
            statisticTitleLbl.setMinWidth(1000);
            //statisticTitleLbl.setFont(new Font("impact", 14));
            statisticTitleLbl.setTextFill(Color.WHITE);
            
            final Label statisticValueLbl = new Label("0");
            statisticTitleLbl.setMinWidth(200);
            //statisticValueLbl.setFont(new Font("impact", 14));
            statisticValueLbl.setTextFill(Color.YELLOW);

            statisticBox.getChildren().add(statisticTitleLbl);
            statisticBox.getChildren().add(statisticValueLbl);
            statisticsContainer.getChildren().add(statisticBox);
        }
    }

    
}
