package hidenseek.controller.fxml;

import hidenseek.controller.GameSceneController;

public interface MenuController {
    
    /**
     * The width to assign to the gui
     * @param width
     *          
     */
    
    void setWidth(final double width);
    
    /**
     * The height to assign to the gui
     * @param height
     *           
     */
    
    void setHeight(final double height);
    
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
    
    void setSceneController(final GameSceneController gameController);
    
    /**
     * 
     */
    
    void onActivate();
}
