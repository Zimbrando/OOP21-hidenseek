package hidenseek.model;

import hidenseek.controller.EntityControllerImpl;
import hidenseek.controller.GameSceneController;
import hidenseek.controller.GameSceneControllerImpl;
import hidenseek.controller.GameWorldController;
import hidenseek.controller.GameWorldControllerImpl;
import hidenseek.controller.InputScheme;
import hidenseek.controller.InputSchemeImpl;
import hidenseek.controller.RendererImpl;
import hidenseek.model.entities.Player;
import hidenseek.controller.Renderer;
import hidenseek.view.CanvasDeviceImpl;
import hidenseek.view.PlayerView;
import hidenseek.view.PlayerViewImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.*;
import javafx.stage.Screen;
import javafx.scene.input.KeyCode;

public class Launcher extends Application {

    @Override
    public final void start(final Stage primaryStage) throws Exception {
        
        final GameSceneController gameSceneController = new GameSceneControllerImpl(primaryStage);
       
        //gameController.addLevel(1,map);
        
    }
}
