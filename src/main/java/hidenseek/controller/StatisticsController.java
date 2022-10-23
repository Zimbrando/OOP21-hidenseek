package hidenseek.controller;

import hidenseek.model.statistics.Statistic;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class StatisticsController implements MenuController{

    @FXML
    private final double interfaceHeight;
    
    @FXML
    private final double interfaceWidth;
    
    @FXML
    private Pane statisticsRoot;
    
    @FXML
    private VBox statisticsContainer;
    
    @FXML
    private Button exitButton;
    
    private GameSceneController gameController;
   
    public StatisticsController() {
        this.interfaceHeight = 1080;
        this.interfaceWidth = 1920;
    }
    
    @FXML
    public void handleStatisticsExit() {
        this.gameController.goToMenu();  
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
    public void setSceneController(final GameSceneController gameController) {
        this.gameController = gameController;
        for(Statistic<?> statistic : gameController.getStatisticsManager().getStatistics()) {
            HBox statisticBox = new HBox();
            statisticBox.setAlignment(Pos.TOP_CENTER);
            
            Label statisticTitleLbl = new Label(statistic.getTitle());
            statisticTitleLbl.setMinWidth(1000);
            statisticTitleLbl.setFont(new Font("impact", 14));
            statisticTitleLbl.setTextFill(Color.WHITE);
            
            Label statisticValueLbl = new Label("0");
            statisticTitleLbl.setMinWidth(200);
            statisticValueLbl.setFont(new Font("impact", 14));
            statisticValueLbl.setTextFill(Color.YELLOW);

            statisticBox.getChildren().add(statisticTitleLbl);
            statisticBox.getChildren().add(statisticValueLbl);
            statisticsContainer.getChildren().add(statisticBox);
        }
    }
    
}
