package hidenseek.controller;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
    //FXMLLoader loader = new FXMLLoader();
    private final String resourceLocation = "./layouts/"; 
    private final List<String> interfacesPaths;//= new ArrayList<>();
    
    //List.of("MainMenuGui.fxml", "GameGui.fxml", "GameSettingsGui.fxml", "MainMenuGui.fxml", "MainMenuGui.fxml", "MainMenuGui.fxml");

    public GameSceneControllerImpl(final Stage stage) throws IOException, URISyntaxException {
        
        final URL url = getClass().getResource("/layouts/");
        final Path path = Paths.get(url.toURI());
        //path.forEach(p -> System.out.printf("- %s%n", p.toString()));
        this.interfacesPaths = Files.walk(path, 1).skip(1).map(e -> e.toString()).collect(Collectors.toList());
        
        //this.interfacesPaths.stream().map(e -> e.split("\\\\").);
        
        //System.out.println(this.interfacesPaths);
        
        this.mainStage = stage;
        
        this.loadInterface("./layouts/MainMenuGui.fxml", "./stylesheets/MainMenuStyle.css");
       
        mainStage.setTitle("Hide'n Seek");
        mainStage.setHeight(768);
        mainStage.setWidth(1024);
        //mainStage.setFullScreen(true);
        mainStage.setScene(currentScene.get());
        mainStage.show();
        
        this.init();
        
    }
    
    private void init() {        
        //this.loadInterface("./layouts/MainMenuGui.fxml", "./stylesheets/MainMenuStyle.css"); 
        this.loadInterface("./layouts/GameGui.fxml" ,"./stylesheets/MainMenuStyle.css");
        this.loadInterface("./layouts/ResumeMenuGui.fxml","./stylesheets/MainMenuStyle.css");
        this.loadInterface("./layouts/GameSettingsGui.fxml","./stylesheets/MainMenuStyle.css");
        this.loadInterface("./layouts/GameStatsGui.fxml","./stylesheets/MainMenuStyle.css");
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
        
    @Override
    public void goToMenu() {
                
        sceneManager.activate("./layouts/MainMenuGui.fxml");
        //this.currentScene.ifPresent(c-> c.getStylesheets().add(ClassLoader.getSystemResource("./stylesheets/MainMenuStyle.css").toExternalForm()));
    }

    @Override
    public void goToGame() {
        
        sceneManager.activate("./layouts/GameGui.fxml");
        
        final Pane gamePane = (Pane)getSceneRoot("./layouts/GameGui.fxml").lookup("#gameMain");
        
        final Canvas gameCanvas = (Canvas)getSceneRoot("./layouts/GameGui.fxml").lookup("#mainCanvas");
        gameCanvas.setFocusTraversable(true);
        
        final InputScheme input = new InputSchemeImpl();
        input.assignInputNode(gamePane);
        
        final Renderer renderer = new RendererImpl(new CanvasDeviceImpl(gameCanvas.getGraphicsContext2D()));
        final GameWorldController gameController = new GameWorldControllerImpl(renderer, input);       
      
        gameCanvas.setOnKeyPressed(e -> {
          if (e.getCode() == KeyCode.ESCAPE) {
              final GameGuiController temp = (GameGuiController) sceneManager.getSceneControllerByName("./layouts/GameGui.fxml");
              temp.setPauseMode();
              gameController.pause();
          }
          
          if (e.getCode() == KeyCode.R) {
              gameController.resume();
          }
         });
        
        gameController.addEntity(new EntityControllerImpl<PlayerView>(new Player(), new PlayerViewImpl()));
        
        //this.currentScene.ifPresent(c-> c.getStylesheets().add(ClassLoader.getSystemResource("./stylesheets/MainMenuStyle.css").toExternalForm()));
       
    }

    @Override
    public void goToResume() {
        // TODO Auto-generated method stub
       
    }

    @Override
    public void goToSettings() {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void goToStats() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void goToExit() {
       System.exit(0);
    }

}
