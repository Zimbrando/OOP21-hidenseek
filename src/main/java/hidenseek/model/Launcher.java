package hidenseek.model;

import hidenseek.controller.GameWorldController;
import hidenseek.controller.GameWorldControllerImpl;
import hidenseek.controller.RendererImpl;
import hidenseek.controller.Renderer;
import hidenseek.view.CanvasDeviceImpl;
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
        
        Renderer renderer = new RendererImpl(new CanvasDeviceImpl(gameCanvas.getGraphicsContext2D()));
        GameWorldController gameController = new GameWorldControllerImpl(renderer);
        
       
        primaryStage.setTitle("Hide'n Seek");
        primaryStage.setHeight(860);
        primaryStage.setWidth(1024);
        primaryStage.setScene(root);
        primaryStage.show();
    }
}
