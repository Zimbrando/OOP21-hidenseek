package hidenseek.model;

import hidenseek.controller.GameSceneController;
import hidenseek.controller.GameSceneControllerImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Launcher extends Application {

    @Override
    public final void start(final Stage primaryStage) throws Exception {
        final GameSceneController gameSceneController = new GameSceneControllerImpl(primaryStage);
             
        //Prevents the program from running even if close
        primaryStage.setOnCloseRequest(new EventHandler<>() {
            public void handle(final WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
    }
}
