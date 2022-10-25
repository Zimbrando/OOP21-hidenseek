package hidenseek.model.components.physics;

public interface Force {
    
    /**
     * @return the force intensity
     */
    public double getIntensity();    
    
    /**
     * Set the force intensity
     * @param intensity
     */
    public void setIntensity(double intensity);
    
    /**
     * @return the force direction
     */
    public double getDirection(); 
    
    /**
     * Set the force direction
     * @param direction
     */
    public void setDirection(double direction);

    /**
     * @return the force x component
     */
    public double getXComponent();
    
    /**
     * @return the force y component
     */
    public double getYComponent();
    
    /**
     * @return the force identifier
     */
    public String getIdentifier();
}