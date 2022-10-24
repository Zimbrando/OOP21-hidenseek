package hidenseek.model;

import java.util.HashMap;
import java.util.Map;
import hidenseek.controller.MenuController;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class SceneManagerImpl implements SceneManager {

    private final Map<String, Pane> screenMap = new HashMap<>();
    private final Map<String,MenuController> sceneControllers = new HashMap<>();
    private Scene main;

    @Override
    public void addScreen(final String name, final Pane pane){
         this.screenMap.put(name, pane);     
    }

    @Override
    public void removeScreen(final String name){
         this.screenMap.remove(name);
    }
    
    @Override
    public void addScreenController(final String name, final MenuController controller){
        this.sceneControllers.put(name, controller);         
    }

    @Override
    public void removeScreenController(final String name){
        this.sceneControllers.remove(name);
    }
    
    @Override
    public MenuController getSceneControllerByName(final String name){
        return this.sceneControllers.get(name);    
    }

    @Override
    public void activate(final String name){
        getSceneControllerByName(name).onActivate();
        this.main.setRoot(this.screenMap.get(name));
    }
    
    @Override
    public Pane getSceneRootByScreen(final String name){
        return this.screenMap.get(name);    
    }
    
    @Override
    public void setMainScene(final Scene mainScene) {
        this.main = mainScene;
    }

}
