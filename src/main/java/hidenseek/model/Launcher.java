package hidenseek.model;

import hidenseek.controller.GameWorldController;
import hidenseek.controller.GameWorldControllerImpl;
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
        
        GameWorldController gameController = new GameWorldControllerImpl();
        
        primaryStage.setTitle("Hide'n Seek");
        primaryStage.setHeight(800);
        primaryStage.setWidth(600);
        primaryStage.setScene(root);
        primaryStage.show();
    }
}
