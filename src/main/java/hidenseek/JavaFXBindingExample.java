package hidenseek;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class JavaFXBindingExample extends Application {

    @Override
    public final void start(final Stage primaryStage) throws Exception {
        final TextField input1 = new TextField();
        final TextField input2 = new TextField();
        final Label label = new Label();
        label.setPrefWidth(100);
        final HBox box = new HBox();
        box.getChildren().add(input1);
        box.getChildren().add(label);
        box.getChildren().add(input2);
        label.textProperty().bind(input1.textProperty().concat(input2.textProperty()));
        input1.textProperty().bindBidirectional(input2.textProperty());
        primaryStage.setScene(new Scene(box));
        primaryStage.setTitle("JavaFXApp3");
        primaryStage.show();
    }

    public static void run(final String... args) {
        launch();
    }

    public static final class Main {
        private Main() {
            // the constructor will never be called directly.
        }

        public static void main(final String... args) {
            JavaFXBindingExample.run(args);
        }
    }
}
