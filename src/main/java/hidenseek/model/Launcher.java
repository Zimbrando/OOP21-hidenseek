package hidenseek.model;

import hidenseek.controller.GameSceneController;
import hidenseek.controller.GameSceneControllerImpl;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {

    @Override
    public final void start(final Stage primaryStage) throws Exception {
        
        final GameSceneController gameSceneController = new GameSceneControllerImpl(primaryStage);
        
    }
}
