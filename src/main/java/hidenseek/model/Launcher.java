package hidenseek.model;

import hidenseek.controller.entities.EntityControllerImpl;
import hidenseek.controller.GameWorldController;
import hidenseek.controller.GameWorldControllerImpl;
import hidenseek.controller.InputScheme;
import hidenseek.controller.InputSchemeImpl;
import hidenseek.controller.RendererImpl;
import hidenseek.model.components.PositionComponent;
import hidenseek.model.entities.Enemy;
import hidenseek.model.entities.Player;
import hidenseek.model.entities.Wall;
import hidenseek.controller.Renderer;
import hidenseek.view.CanvasDeviceImpl;
import hidenseek.view.EnemyView;
import hidenseek.view.EnemyViewImpl;
import hidenseek.view.PlayerView;
import hidenseek.view.PlayerViewImpl;
import hidenseek.view.WallView;
import hidenseek.view.WallViewImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
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
        
        final GameLevel gameLevel = new GameLevelImpl();
        gameLevel.getWalls().forEach(wall -> {
            gameController.addEntity(new EntityControllerImpl<WallView>(wall, new WallViewImpl((Wall)wall)));
        });

        
        //NOTE: we are passing Model to PlayerViewImpl and EnemyViewImpl, but only for debug.
        //It's used to draw collision hitbox. It'll be removed.
        
        Player player = new Player();
        player.getComponent(PositionComponent.class).get().setPosition(new Point2D(30, 30));
        gameController.addEntity(new EntityControllerImpl<PlayerView>(player, new PlayerViewImpl(player)));

        Enemy enemy = new Enemy();
        enemy.getComponent(PositionComponent.class).get().setPosition(new Point2D(700, 400));
        gameController.addEntity(new EntityControllerImpl<EnemyView>(enemy, new EnemyViewImpl(enemy)));

        //gameController.addLevel(1, map);
        

        primaryStage.setTitle("Hide'n Seek");
        primaryStage.setWidth(1600);
        primaryStage.setHeight(900);
        primaryStage.setScene(root);
        primaryStage.show();
    }
}
