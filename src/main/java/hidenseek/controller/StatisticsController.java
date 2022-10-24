package hidenseek.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import hidenseek.model.statistics.Statistic;
import hidenseek.model.statistics.numeric.NumericStatistic;
import hidenseek.model.statistics.score.ScoreStatistic;
import hidenseek.model.statistics.time.TimeStatistic;
import hidenseek.view.statistics.ScoreStatisticViewImpl;
import hidenseek.view.statistics.TextStatisticViewImpl;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
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
    private ScrollPane mainScroll;
    
    @FXML
    private Button exitButton;
    
    private GameSceneController gameController;
   
    public StatisticsController() {
        this.interfaceHeight = 1080;
        this.interfaceWidth = 1920;
    }
    
    @FXML
    public void initialize() {
        mainScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // never show a vertical ScrollBar
        mainScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // never show a vertical ScrollBar
        mainScroll.setFitToWidth(true); // set content width to viewport width
        mainScroll.setPannable(true); // allow scrolling via mouse dragging
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
    }

    @Override
    public void onActivate() {
        statisticsContainer.getChildren().clear();

        Map<String, List<Statistic<?>>> groupedStatistics = 
                gameController.getStatisticsManager().getStatistics().stream().collect(Collectors.groupingBy(Statistic::getTag));
        
        List<String> sortedStatisticsGround = groupedStatistics.keySet().stream().sorted((x, y) -> {
            return x.equals("") ? -1 : 1;
        }).toList();
        
        for(String statisticTag : sortedStatisticsGround) {
            
            Label statisticGroupLbl = new Label(statisticTag == "" ? "" : "LIVELLO " + statisticTag);
            statisticGroupLbl.setFont(new Font("impact", 22));
            statisticGroupLbl.setTextFill(Color.WHITESMOKE);
            statisticGroupLbl.setMinWidth(450);
            VBox.setMargin(statisticGroupLbl, new Insets(20, 0, 0, 0));
            statisticsContainer.getChildren().add(statisticGroupLbl);
            
            for(Statistic<?> statistic : groupedStatistics.get(statisticTag)) { 
                
                AnchorPane statisticBox = new AnchorPane();
                statisticBox.setMaxWidth(450);
                statisticBox.setMinHeight(30);
                
                Label statisticTitleLbl = new Label(statistic.getTitle());
                statisticTitleLbl.setFont(new Font("impact", 14));
                statisticTitleLbl.setTextFill(Color.WHITE);
                
                javafx.scene.Node statisticValueLbl = null;
                
                if(NumericStatistic.class.isAssignableFrom(statistic.getClass())) {
                    TextStatisticViewImpl statisticValue = new TextStatisticViewImpl();
                    statisticValue.updateText(((NumericStatistic)statistic).getValue() + ((NumericStatistic)statistic).getUnits());
                    statisticValueLbl = statisticValue;
                    AnchorPane.setTopAnchor(statisticValueLbl, 6.0);
                }
                
                if(TimeStatistic.class.isAssignableFrom(statistic.getClass())) {
                    TextStatisticViewImpl statisticValue = new TextStatisticViewImpl();
                    statisticValue.updateText(((TimeStatistic)statistic).getValue());
                    statisticValueLbl = statisticValue;
                    AnchorPane.setTopAnchor(statisticValueLbl, 6.0);
                }
                
                if(ScoreStatistic.class.isAssignableFrom(statistic.getClass())) {
                    ScoreStatisticViewImpl statisticValue = new ScoreStatisticViewImpl();
                    statisticValue.updateScore(((ScoreStatistic)statistic).getValue());
                    statisticValueLbl = statisticValue;
                }

                AnchorPane.setTopAnchor(statisticTitleLbl, 6.0);
                AnchorPane.setLeftAnchor(statisticTitleLbl, statisticTag == "" ? 0.0 : 15.0);
                AnchorPane.setRightAnchor(statisticValueLbl, 0.0);

                statisticBox.getChildren().add(statisticTitleLbl);
                statisticBox.getChildren().add(statisticValueLbl);

                statisticsContainer.getChildren().add(statisticBox);
            }
        }
    }
    
}
