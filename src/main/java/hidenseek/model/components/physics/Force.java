package hidenseek.model.components.physics;

public interface Force {
    
    /**
     * @return the force intensity
     */
    double getIntensity();    
    
    /**
     * Set the force intensity
     * @param intensity
     */
    void setIntensity(double intensity);
    
    /**
     * @return the force direction
     */
    double getDirection(); 
    
    /**
     * Set the force direction
     * @param direction
     */
    void setDirection(double direction);

    /**
     * @return the force x component
     */
    double getXComponent();
    
    /**
     * @return the force y component
     */
    double getYComponent();
    
    /**
     * @return the force identifier
     */
    String getIdentifier();
}