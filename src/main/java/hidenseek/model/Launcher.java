package hidenseek.model;

import hidenseek.controller.entities.EntityControllerImpl;
import hidenseek.controller.GameWorldController;
import hidenseek.controller.GameWorldControllerImpl;
import hidenseek.controller.InputScheme;
import hidenseek.controller.InputSchemeImpl;
import hidenseek.controller.RendererImpl;
import hidenseek.model.entities.Monster;
import hidenseek.model.entities.Player;
import hidenseek.controller.Renderer;
import hidenseek.view.CanvasDeviceImpl;
import hidenseek.view.MonsterViewImpl;
import hidenseek.view.PlayerView;
import hidenseek.view.PlayerViewImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;

public class Launcher extends Application {

    @Override
    public final void start(final Stage primaryStage) throws Exception {
        final Scene root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/GameGui.fxml"));
        
        final Canvas gameCanvas = (Canvas)root.lookup("#mainCanvas");
        gameCanvas.setFocusTraversable(true);
        
        final InputScheme input = new InputSchemeImpl();
        input.assignInputNode(gameCanvas);
        
        final Renderer renderer = new RendererImpl(new CanvasDeviceImpl(gameCanvas.getGraphicsContext2D()));
        final GameWorldController gameController = new GameWorldControllerImpl(renderer, input);
        
        root.setOnKeyPressed(e -> {
          if (e.getCode() == KeyCode.ESCAPE) {
              gameController.pause();
          }
          
          if (e.getCode() == KeyCode.R) {
              gameController.resume();
          }
         });

        gameController.addEntity(new EntityControllerImpl<PlayerView>(new Player(), new PlayerViewImpl()));
        gameController.addEntity(new EntityControllerImpl<PlayerView>(new Monster(), new MonsterViewImpl()));
//        gameController.addLevel(1,map);
        

        primaryStage.setTitle("Hide'n Seek");
        primaryStage.setHeight(860);
        primaryStage.setWidth(1024);
        primaryStage.setScene(root);
        primaryStage.show();
    }
}
