package hidenseek.model;

import hidenseek.controller.MenuController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public interface SceneManager {

    void addScreen(String name, Pane pane);

    void removeScreen(String name);
    
    void addScreenController(String name, MenuController controller);

    void removeScreenController(String name);

    void activate(String name);
    
    Pane getSceneRootByScreen(final String name);
    
    void setMainScene(Scene mainScene);

    MenuController getSceneControllerByName(String name);
}
