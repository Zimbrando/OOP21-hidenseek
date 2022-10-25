package hidenseek.controller.fxml;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import hidenseek.controller.GameSceneController;
import hidenseek.model.statistics.Statistic;
import hidenseek.model.statistics.numeric.NumericStatistic;
import hidenseek.model.statistics.score.ScoreStatistic;
import hidenseek.model.statistics.time.TimeStatistic;
import hidenseek.view.statistics.ScoreStatisticViewImpl;
import hidenseek.view.statistics.TextStatisticViewImpl;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ScrollPane;
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
    private ScrollPane mainScroll;
    
    @FXML
    private Button exitButton;
    
    private GameSceneController gameController;
       
    @FXML
    public void initialize() {
        mainScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mainScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mainScroll.setFitToWidth(true);
        mainScroll.setPannable(true);
    }
    
    
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
        this.loadStatistics();
    }

    @Override
    public void onActivate() {
        this.loadStatistics();
    }
    
    private void loadStatistics() {
        statisticsContainer.getChildren().clear();

        //group statistics by Tag
        final Map<String, List<Statistic<?>>> groupedStatistics = 
                gameController.getStatisticsManager().getStatistics().stream().collect(Collectors.groupingBy(Statistic::getTag));
        
        //sort statistics: at first the ones with empty tag (general statistics), then levels statistics.
        final List<String> sortedStatisticsGround = groupedStatistics.keySet().stream().sorted((x, y) -> {
            return x.equals("") ? -1 : 1;
        }).collect(Collectors.toList());
        
        //create a statistic category into view for each level
        for(final String statisticTag : sortedStatisticsGround) {
            
            final Label statisticGroupLbl = new Label(statisticTag == "" ? "" : "LIVELLO " + statisticTag);
            statisticGroupLbl.getStyleClass().add("statsCategory");
            statisticGroupLbl.setTextFill(Color.WHITESMOKE);
            statisticGroupLbl.setMinWidth(450);
            VBox.setMargin(statisticGroupLbl, new Insets(20, 0, 0, 0));
            statisticsContainer.getChildren().add(statisticGroupLbl);

            //populate each category with respective statistics
            for(final Statistic<?> statistic : groupedStatistics.get(statisticTag)) { 
                
                final AnchorPane statisticBox = new AnchorPane();
                statisticBox.setMaxWidth(450);
                statisticBox.setMinHeight(30);
                
                final Label statisticTitleLbl = new Label(statistic.getTitle());
                statisticTitleLbl.getStyleClass().add("statsValue");
                statisticTitleLbl.setTextFill(Color.WHITE);
                
                javafx.scene.Node statisticValueLbl = null;
                
                if(NumericStatistic.class.isAssignableFrom(statistic.getClass())) {
                    final TextStatisticViewImpl statisticValue = new TextStatisticViewImpl();
                    statisticValue.updateText(((NumericStatistic)statistic).getValue() + ((NumericStatistic)statistic).getUnits());
                    statisticValueLbl = statisticValue;
                    AnchorPane.setTopAnchor(statisticValueLbl, 6.0);
                }
                
                if(TimeStatistic.class.isAssignableFrom(statistic.getClass())) {
                    final TextStatisticViewImpl statisticValue = new TextStatisticViewImpl();
                    statisticValue.updateText(((TimeStatistic)statistic).getValue());
                    statisticValueLbl = statisticValue;
                    AnchorPane.setTopAnchor(statisticValueLbl, 6.0);
                }
                
                if(ScoreStatistic.class.isAssignableFrom(statistic.getClass())) {
                    final ScoreStatisticViewImpl statisticValue = new ScoreStatisticViewImpl();
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
