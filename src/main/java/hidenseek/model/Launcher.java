package hidenseek.model;

import hidenseek.controller.GameWorldController;
import hidenseek.controller.GameWorldControllerImpl;
import hidenseek.controller.InputScheme;
import hidenseek.controller.InputSchemeImpl;
import hidenseek.controller.RendererImpl;
import hidenseek.controller.Renderer;
import hidenseek.view.CanvasDeviceImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.*;
import javafx.scene.input.KeyCode;

public class Launcher extends Application {

    @Override
    public final void start(final Stage primaryStage) throws Exception {
        final Scene root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/GameGui.fxml"));
        
        Canvas gameCanvas = (Canvas)root.lookup("#mainCanvas");
        gameCanvas.setFocusTraversable(true);
        
        InputScheme input = new InputSchemeImpl();
        input.assignInputNode(gameCanvas);
        
        Renderer renderer = new RendererImpl(new CanvasDeviceImpl(gameCanvas.getGraphicsContext2D()));
        GameWorldController gameController = new GameWorldControllerImpl(renderer, input);
        
        root.setOnKeyPressed(e -> {
          if (e.getCode() == KeyCode.ESCAPE) {
              gameController.pause();
          }
          
          if (e.getCode() == KeyCode.R) {
              gameController.resume();
          }
         });
        
        primaryStage.setTitle("Hide'n Seek");
        primaryStage.setHeight(860);
        primaryStage.setWidth(1024);
        primaryStage.setScene(root);
        primaryStage.show();
    }
}
