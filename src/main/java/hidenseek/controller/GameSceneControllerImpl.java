package hidenseek.controller;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Optional;

import hidenseek.model.SceneManagerImpl;
import hidenseek.model.enums.GuiPanes;
import hidenseek.model.statistics.StatisticsManager;
import hidenseek.model.statistics.StatisticsManagerImpl;
import hidenseek.model.statistics.numeric.NumericStatistic;
import hidenseek.model.statistics.time.TimeStatistic;
import hidenseek.view.CanvasDeviceImpl;
import hidenseek.view.Renderer;
import hidenseek.view.RendererImpl;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameSceneControllerImpl implements GameSceneController {

    private final Stage mainStage;
    private Optional<Scene> currentScene = Optional.empty(); 
    private final SceneManagerImpl sceneManager = new SceneManagerImpl();
    private final static String RESOURCE_LOCATION = "/layouts/";
    private final static String STYLING_LOCATION = "/stylesheets/";
    private final StatisticsManager statisticsManager = new StatisticsManagerImpl();
    private final static double INTERFACE_WIDTH = 1600.0;
    private final static double INTERFACE_HEIGHT = 900.0;

    public GameSceneControllerImpl(final Stage stage) throws IOException, URISyntaxException {  

        this.statisticsManager.addStatistic(new NumericStatistic("curr_level", "", "Current level"));
        this.statisticsManager.addStatistic(new TimeStatistic("total_play_time", "", "Play time"));
        this.statisticsManager.addStatistic(new NumericStatistic("total_win", "", "Victories"));
        this.statisticsManager.addStatistic(new NumericStatistic("total_loose", "", "Losses"));
        this.statisticsManager.addStatistic(new NumericStatistic("win_percentage", "", "Win percentage", "%"));
        this.statisticsManager.addStatistic(new NumericStatistic("collected_keys", "", "Collected keys"));        
        
        new LevelHandlerImpl(this.statisticsManager);
        
        stage.setResizable(false);
        
        this.mainStage = stage;
       
        this.init();
        
        mainStage.setTitle("Hide'n Seek");
        mainStage.setWidth(INTERFACE_WIDTH); 
        mainStage.setHeight(INTERFACE_HEIGHT);
        mainStage.setScene(currentScene.get());
        mainStage.show();
        
    }
    
    private void init() {
        Arrays.asList(GuiPanes.values()).stream().forEach(e-> this.loadInterface(RESOURCE_LOCATION+e.getFileName(), STYLING_LOCATION + "MenuStyle.css"));
    }
   
    
    private void loadInterface(final String pathToInterface, final String cssStyle) {
    
        try {
            
            final FXMLLoader loader = new FXMLLoader();   
            
            loader.setLocation(getClass().getResource(pathToInterface));

            final Parent root = loader.load();
            
            if(this.currentScene.isEmpty()) {
                this.currentScene = Optional.of(new Scene(root));
                this.sceneManager.setMainScene(this.currentScene.get());
            }
            
            this.currentScene.ifPresent(c-> c.getStylesheets().add(getClass().getResource(cssStyle).toExternalForm()));
            
            sceneManager.addScreen(pathToInterface, (Pane)root);
            
            final Optional<MenuController> sceneController = Optional.ofNullable((MenuController)loader.getController());
            sceneController.ifPresent(s-> s.setWidth(INTERFACE_WIDTH));
            sceneController.ifPresent(s-> s.setHeight(INTERFACE_HEIGHT));
            sceneController.ifPresent(s-> s.setSceneController(this));
            sceneController.ifPresent(s -> sceneManager.addScreenController(pathToInterface, s));
            
        } catch (IOException e) {
            System.out.println("[ERROR] - Error while trying to open file"+pathToInterface);
            e.printStackTrace();
        }
    };
    
    @Override
    public Pane getSceneRoot(final String name) {
        return this.sceneManager.getSceneRootByScreen(name);
    }
    
    @Override
    public Stage getMainStage() {
        return this.mainStage;
    }
    
    @Override
    public void pauseGame(final GameGuiController controller) {
        if(!controller.isPaused()) {
            controller.setPauseMode();
        }
    }
        
    @Override
    public void goToMenu() {
        sceneManager.activate(RESOURCE_LOCATION+GuiPanes.MAIN_MENU.getFileName());
    }

    @Override
    public void goToGame() {
        final String gameGuiPath = RESOURCE_LOCATION+GuiPanes.GAME_GUI.getFileName();
        
        sceneManager.activate(gameGuiPath);
         
        final Pane gamePane = (Pane)getSceneRoot(gameGuiPath).lookup("#gameRoot");

        final Canvas gameCanvas = (Canvas)getSceneRoot(gameGuiPath).lookup("#gameMainCanvas");
        gameCanvas.setFocusTraversable(true);
        
        final InputScheme input = new InputSchemeImpl();
        input.assignInputNode(gamePane);
        
        final Renderer renderer = new RendererImpl(new CanvasDeviceImpl(gameCanvas.getGraphicsContext2D()));

        final LevelHandler levelhandler = new LevelHandlerImpl(statisticsManager);
       
        final GameWorldController gameController = new GameWorldControllerImpl(this, renderer, input, levelhandler, statisticsManager);
        
        final GameGuiController temp = (GameGuiController) sceneManager.getSceneControllerByName(gameGuiPath);
        temp.setGameController(gameController);
        gameCanvas.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                gameController.pause();
                this.pauseGame(temp);
            }
       });
    }

    
    @Override
    public void goToStats() {
        final String statsGuiPath = RESOURCE_LOCATION+GuiPanes.STATS_MENU.getFileName();
        
        sceneManager.activate(statsGuiPath);    
    }
    
    @Override
    public void goToGameWin() {
        final String gameWinGuiPath = RESOURCE_LOCATION+GuiPanes.GAMEWIN_MENU.getFileName();
        
        sceneManager.activate(gameWinGuiPath);
    }
    
    @Override
    public void goToGameOver() {
        final String gameOverGuiPath = RESOURCE_LOCATION+GuiPanes.GAMEOVER_MENU.getFileName();
        
        sceneManager.activate(gameOverGuiPath);    
    }


    @Override
    public void goToExit() {
       Platform.exit();
    }

    @Override
    public StatisticsManager getStatisticsManager() {
        return this.statisticsManager;
    }
}
