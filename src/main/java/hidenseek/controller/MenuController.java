package hidenseek.controller;

import javafx.fxml.FXML;

public interface MenuController {
    
    /**
     * 
     * @param width
     *          The width to assign to the gui
     */
    void setWidth(final double width);
    
    /**
     * 
     * @param height
     *          The height to assign to the gui 
     */
    void setHeight(final double height);
    
    /**
     * 
     * @return the gui's width
     */
    double getWidth();
    
    /**
     * 
     * @return the gui's height
     */
    double getHeight();
        
    /**
     * Sets the game scene controller
     * @param gameController
     */
    
    void setSceneController(final GameSceneController gameController);
    
}
