package hidenseek.controller.fxml;

import hidenseek.controller.GameSceneController;

public interface MenuController {
    
    /**
     * The width to assign to the gui
     * @param width
     *          
     */
    
    void setWidth(double width);
    
    /**
     * The height to assign to the gui
     * @param height
     *           
     */
    
    void setHeight(double height);
    
    /**
     * 
     * @return the gui's width.
     */
    
    double getWidth();
    
    /**
     * 
     * @return the gui's height.
     */
    
    double getHeight();
        
    /**
     * Sets the game scene controller
     * @param gameController
     */
    
    void setSceneController(GameSceneController gameController);
    
    /**
     * This method is called before the scene activation
     */
    
    void onActivate();
}
