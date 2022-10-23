package hidenseek;

import hidenseek.model.Launcher;
import javafx.application.Application;
import javafx.application.Platform;

/** Main application entry-point. */

public final class App {

    public static void main(final String[] args) {
        Application.launch(Launcher.class, args);
        
        //This should help close correctly the program when closing the window
        Platform.setImplicitExit(true);
    }
}
