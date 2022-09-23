package hidenseek.model;

import hidenseek.controller.GameWorldController;
import hidenseek.controller.GameWorldControllerImpl;
import hidenseek.view.EntityViewCanvasImpl;
import hidenseek.view.EntityViewConsoleImpl;
import hidenseek.view.GameWorldViewImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.canvas.*;

public class Launcher extends Application {

    @Override
    public final void start(final Stage primaryStage) throws Exception {
        final Scene root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/GameGui.fxml"));
        
        Canvas gameCanvas = (Canvas)root.lookup("#mainCanvas");

        GameWorldController controller = new GameWorldControllerImpl(new GameWorldViewImpl(gameCanvas.getGraphicsContext2D()));
        
        primaryStage.setTitle("Hide'n Seek");
        primaryStage.setHeight(800);
        primaryStage.setWidth(600);
        primaryStage.setScene(root);
        primaryStage.show();
    }
}
