package hidenseek.controller;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import hidenseek.controller.entities.EntityController;
import hidenseek.controller.entities.EntityControllerImpl;
import hidenseek.controller.entities.MovableEntityControllerImpl;
import hidenseek.controller.entities.PlayerControllerImpl;
import hidenseek.controller.entities.WallControllerImpl;
import hidenseek.model.GameLevel;
import hidenseek.model.GameLevelImpl;
import hidenseek.model.SceneManagerImpl;
import hidenseek.model.components.physics.PositionComponent;
import hidenseek.model.entities.Entity;
import hidenseek.model.entities.Monster;
import hidenseek.model.entities.Player;
import hidenseek.model.entities.PowerUp;
import hidenseek.model.entities.Wall;
import hidenseek.view.CanvasDeviceImpl;
import hidenseek.view.Renderer;
import hidenseek.view.RendererImpl;
import hidenseek.view.entities.KeyView;
import hidenseek.view.entities.KeyViewImpl;
import hidenseek.view.entities.MonsterView;
import hidenseek.view.entities.MonsterViewImpl;
import hidenseek.view.entities.PowerUpView;
import hidenseek.view.entities.PowerUpViewImpl;
import hidenseek.view.entities.WallView;
import hidenseek.view.entities.WallViewImpl;
import hidenseek.view.huds.KeyHudView;
import hidenseek.view.huds.KeyHudViewImpl;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        
        stage.setResizable(false);
        
        this.interfacesPaths = Files.walk(path, 1)
        .skip(1)
        .map(e -> e.toString())
        .collect(Collectors.toList())
        .stream()
        .map(e -> e.substring(e.lastIndexOf(File.separator)).substring(1))
        .sorted()
        .collect(Collectors.toList());
        
        this.mainStage = stage;
        
        this.loadInterface(RESOURCE_LOCATION+this.interfacesPaths.get(3), STYLING_LOCATION + "MainMenuStyle.css");
       
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
        //TODO BUG: if you go back in the GameMenu, you can't create a new game
        final String gameGuiPath = RESOURCE_LOCATION+this.interfacesPaths.get(0);
        
        sceneManager.activate(gameGuiPath);
         
        final Pane gamePane = (Pane)getSceneRoot(gameGuiPath).lookup("#gameRoot");

        final Canvas gameCanvas = (Canvas)getSceneRoot(gameGuiPath).lookup("#gameMainCanvas");
        gameCanvas.setFocusTraversable(true);
        
        final InputScheme input = new InputSchemeImpl();
        input.assignInputNode(gameCanvas);
        
        final Renderer renderer = new RendererImpl(new CanvasDeviceImpl(gameCanvas.getGraphicsContext2D()));
        final GameWorldController gameController = new GameWorldControllerImpl(this, renderer, input, new LevelHandlerImpl());
        
        gamePane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                gameController.pause();
            }
          
            if (e.getCode() == KeyCode.R) {
                gameController.resume();
            }
        });
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
       Platform.exit();
    }

}
