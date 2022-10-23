package hidenseek.controller;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import hidenseek.model.SceneManager;
import hidenseek.model.SceneManagerImpl;
import hidenseek.model.statistics.StatisticsManager;
import hidenseek.model.statistics.StatisticsManagerImpl;
import hidenseek.model.statistics.numeric.NumericStatistic;
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
    private final SceneManager sceneManager = new SceneManagerImpl();
    private final static String RESOURCE_LOCATION = "./layouts/";
    private final static String STYLING_LOCATION = "./stylesheets/";
    private final List<String> interfacesPaths = List.of("MainMenuGui.fxml","GameStatisticsGui.fxml","GameStatsGui.fxml","GameOverGui.fxml","GameGui.fxml");
    private final StatisticsManager statisticsManager = new StatisticsManagerImpl();
    

    public GameSceneControllerImpl(final Stage stage) throws IOException, URISyntaxException {

        statisticsManager.addStatistic(new NumericStatistic("curr_level", "root", "Livello attuale"));
        statisticsManager.addStatistic(new NumericStatistic("total_play_time", "root", "Tempo totale di gioco"));
        statisticsManager.addStatistic(new NumericStatistic("total_win", "root", "Vittorie totali"));
        statisticsManager.addStatistic(new NumericStatistic("total_loose", "root", "Perdite totali"));
        statisticsManager.addStatistic(new NumericStatistic("win_percentage", "root", "Percentuale vittoria", "%"));
        statisticsManager.addStatistic(new NumericStatistic("collected_keys", "root", "Chiavi raccolte"));
        
        stage.setResizable(false);
        
        this.mainStage = stage;
        
        this.loadInterface(RESOURCE_LOCATION+this.interfacesPaths.get(0), STYLING_LOCATION + "MainMenuStyle.css");
       
        this.init();
        
        mainStage.setTitle("Hide'n Seek");
        mainStage.setWidth(1600); //Screen.getPrimary().getBounds().getWidth()
        mainStage.setHeight(900); //Screen.getPrimary().getBounds().getHeight()
        mainStage.setScene(currentScene.get());
        mainStage.show();
        
    }
    
    private void init() {        
        this.interfacesPaths.stream().forEach(e-> this.loadInterface(RESOURCE_LOCATION+e, STYLING_LOCATION + "MainMenuStyle.css"));
    }
    
    private void loadInterface(final String pathToInterface, final String cssStyle) {
    
        try {
            
            final FXMLLoader loader = new FXMLLoader();   
            
            loader.setLocation(ClassLoader.getSystemResource(pathToInterface));
            
            final Parent root = loader.load();
            
            if(this.currentScene.isEmpty()) {
               
                this.currentScene = Optional.of(new Scene(root));
                this.sceneManager.setMainScene(this.currentScene.get());
            }
            
            this.currentScene.ifPresent(c-> c.getStylesheets().add(ClassLoader.getSystemResource(cssStyle).toExternalForm()));
            
            sceneManager.addScreen(pathToInterface, (Pane)root);
            
            final Optional<MenuController> sceneController = Optional.ofNullable((MenuController)loader.getController());
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
    
    public void toggleResumeGame(final GameGuiController controller) {
        
    }
    
    public void pauseGame(final GameGuiController controller) {
        if(!controller.isPaused()) {
            controller.setPauseMode();
        }
    }
        
    @Override
    public void goToMenu() {
        sceneManager.activate(RESOURCE_LOCATION+this.interfacesPaths.get(0));
    }

    @Override
    public void goToGame() {
        final String gameGuiPath = RESOURCE_LOCATION+this.interfacesPaths.get(4);
        
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
    public void goToPause() {
        final String pauseGuiPath = RESOURCE_LOCATION+this.interfacesPaths.get(3);
        
        sceneManager.activate(pauseGuiPath);       
    }

    @Override
    public void goToStatistics() {
        final String statisticsGuiPath = RESOURCE_LOCATION+this.interfacesPaths.get(1);
        
        sceneManager.activate(statisticsGuiPath);        
    }
    
    @Override
    public void goToStats() {
        final String statsGuiPath = RESOURCE_LOCATION+this.interfacesPaths.get(2);
        
        sceneManager.activate(statsGuiPath);    
    }

    @Override
    public void goToExit() {
       Platform.exit();
    }

    @Override
    public StatisticsManager getStatisticsManager() {
        return statisticsManager;
    }

}
