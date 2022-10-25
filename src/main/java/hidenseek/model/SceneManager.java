package hidenseek.model;

import hidenseek.controller.fxml.MenuController;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public interface SceneManager {

    /**
     * Adds a screen to the scene manager
     * 
     * @param name of the .fxml UI file.
     * @param pane intended as the root object of the same file as mentioned above.
     */
    
    void addScreen(String name, Pane pane);

    /**
     * Removes a screen from the scene manager
     * @param name of the .fxml UI file.
     */
    
    void removeScreen(String name);
    
    /**
     * Adds the related scene controller to the scene manager.
     * @param name of the .fxml UI file.
     * @param controller of the .fxml file mentioned above.
     */
    
    void addScreenController(String name, MenuController controller);

    /**
     * Removes the specified scene controller by name parameter.
     * @param name of the .fxml UI file.
     */
    
    void removeScreenController(String name);
    
    /**
     * Sets the current scene's root object to the specified UI name file root object.
     * @param name
     */
    
    void activate(String name);
    
    /**
     * 
     * @param name
     * @return The current scene's root object.
     */
    
    Pane getSceneRootByScreen(String name);
    
    /**
     * Instantiates the main scene.
     * @param mainScene
     */
    
    void setMainScene(Scene mainScene);
    
    /**
     * 
     * @param name of the UI .fxml file from which the controller is requested.
     * @return The specified scene controller.
     */
    
    MenuController getSceneControllerByName(String name);
}
