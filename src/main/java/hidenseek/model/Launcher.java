package hidenseek.model;

import hidenseek.controller.GameWorldController;
import hidenseek.controller.GameWorldControllerImpl;
import hidenseek.controller.RendererCanvasImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.*;

public class Launcher extends Application {

    @Override
    public final void start(final Stage primaryStage) throws Exception {
        final Scene root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/GameGui.fxml"));
        
        Canvas gameCanvas = (Canvas)root.lookup("#mainCanvas");
        
        GameWorldController gameController = new GameWorldControllerImpl(new RendererCanvasImpl(gameCanvas.getGraphicsContext2D()));
        
       
        primaryStage.setTitle("Hide'n Seek");
        primaryStage.setHeight(860);
        primaryStage.setWidth(1024);
        primaryStage.setScene(root);
        primaryStage.show();
    }
}
