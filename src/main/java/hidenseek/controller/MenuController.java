package hidenseek.controller;

public interface MenuController {

    /**
     * Sets the game scene controller
     * @param gameController
     */
    void setSceneController(final GameSceneController gameController);
    
    void onActivate();
}
