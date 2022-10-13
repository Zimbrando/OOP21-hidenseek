package hidenseek.controller;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import hidenseek.model.SceneManagerImpl;
import hidenseek.model.entities.Player;
import hidenseek.view.CanvasDeviceImpl;
import hidenseek.view.PlayerView;
import hidenseek.view.PlayerViewImpl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GameSceneControllerImpl implements GameSceneController {

    
    private final Stage mainStage;
    private Optional<Scene> currentScene = Optional.empty(); 
    private final SceneManagerImpl sceneManager = new SceneManagerImpl();
    private final static String RESOURCE_LOCATION = "./layouts/";
    private final static String STYLING_LOCATION = "./stylesheets/";
    private final List<String> interfacesPaths;

    public GameSceneControllerImpl(final Stage stage) throws IOException, URISyntaxException {
        
        final URL url = getClass().getResource("/layouts/");
        final Path path = Paths.get(url.toURI());
        
        this.interfacesPaths = Files.walk(path, 1)
        .skip(1)
        .map(e -> e.toString())
        .collect(Collectors.toList())
        .stream()
        .map(e -> e.substring(e.lastIndexOf("\\")).substring(1))
        .collect(Collectors.toList());
                
        this.mainStage = stage;
        
        this.loadInterface(RESOURCE_LOCATION+this.interfacesPaths.get(3), STYLING_LOCATION + "MainMenuStyle.css");
       
        this.init();
        
        mainStage.setTitle("Hide'n Seek");
        mainStage.setHeight(Screen.getPrimary().getBounds().getHeight());
        mainStage.setWidth(Screen.getPrimary().getBounds().getWidth());
        //mainStage.setFullScreen(true);
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
    
    public void resumeGame(final GameGuiController controller) {
        controller.setPauseMode();
    }
    
    public void pauseGame(final GameGuiController controller) {
        if(!controller.isPaused()) {
            controller.setPauseMode();
        }
    }
        
    @Override
    public void goToMenu() {
        sceneManager.activate(RESOURCE_LOCATION+this.interfacesPaths.get(3));
    }

    @Override
    public void goToGame() {
        
        final String gameGuiPath = RESOURCE_LOCATION+this.interfacesPaths.get(0);
        
        sceneManager.activate(gameGuiPath);
        
        final Pane gamePane = (Pane)getSceneRoot(gameGuiPath).lookup("#gameRoot");
        
        final Canvas gameCanvas = (Canvas)getSceneRoot(gameGuiPath).lookup("#gameMainCanvas");
        gameCanvas.setFocusTraversable(true);
        
        final InputScheme input = new InputSchemeImpl();
        input.assignInputNode(gamePane);
        
        final Renderer renderer = new RendererImpl(new CanvasDeviceImpl(gameCanvas.getGraphicsContext2D()));
        final GameWorldController gameController = new GameWorldControllerImpl(renderer, input);       
      
        gameCanvas.setOnKeyPressed(e -> {
              final GameGuiController temp = (GameGuiController) sceneManager.getSceneControllerByName(gameGuiPath);
              if (e.getCode() == KeyCode.ESCAPE) {
                  gameController.pause();
                  this.pauseGame(temp);
              }
              
              if (e.getCode() == KeyCode.R) {
                  gameController.resume();
                  this.resumeGame(temp);
              }
             });
        
        gameController.addEntity(new EntityControllerImpl<PlayerView>(new Player(), new PlayerViewImpl()));
        
        //this.currentScene.ifPresent(c-> c.getStylesheets().add(ClassLoader.getSystemResource("./stylesheets/MainMenuStyle.css").toExternalForm()));
       
    }

    @Override
    public void goToPause() {
        final String pauseGuiPath = RESOURCE_LOCATION+this.interfacesPaths.get(4);
        
        sceneManager.activate(pauseGuiPath);       
    }

    @Override
    public void goToSettings() {
        final String settingsGuiPath = RESOURCE_LOCATION+this.interfacesPaths.get(1);
        
        sceneManager.activate(settingsGuiPath);        
    }
    
    @Override
    public void goToStats() {
        final String statsGuiPath = RESOURCE_LOCATION+this.interfacesPaths.get(2);
        
        sceneManager.activate(statsGuiPath);    
    }

    @Override
    public void goToExit() {
       System.exit(0);
    }

}
