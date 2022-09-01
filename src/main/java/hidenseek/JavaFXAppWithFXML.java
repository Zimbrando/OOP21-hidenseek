package hidenseek;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFXAppWithFXML extends Application {

    @Override
    public final void start(final Stage primaryStage) throws Exception {
        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/SimpleGui.fxml"));
        final Scene scene = new Scene(root, 1000, 800);
        final Label lbl = (Label) scene.lookup("#myLabel");
        lbl.setText(".........................");
        primaryStage.setTitle("App FXML");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void run(final String[] args) {
        launch(args);
    }

    public static final class Main {
        private Main() {
            // the constructor will never be called directly.
        }

        public static void main(final String... args) {
            JavaFXAppWithFXML.run(args);
        }
    }

    static class AnotherStage extends Stage {
        private static final int SCENE_WIDTH = 100;
        private static final int SCENE_HEIGHT = 500;

        AnotherStage() {
            super();
            setTitle("New stage created at " + System.currentTimeMillis());
            final VBox pane = new VBox();
            pane.getChildren().add(new Label("First label"));
            pane.getChildren().add(new Label("Second label"));
            setScene(new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT));
        }
    }
}
