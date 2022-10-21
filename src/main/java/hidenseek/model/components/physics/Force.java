package hidenseek.model.components.physics;

final public class Force{

    private final String identifier;
    private double intensity;
    private double direction;
    
    public Force(String identifier) {
        this(identifier, 0, 0);
    }
    
    public Force(String identifier, double intensity, double direction) {
        this.identifier = identifier;
        setIntensity(intensity);
        setDirection(direction);
    }
    
    public double getIntensity() {
        return intensity;
    }
    
    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }
    
    public double getDirection() {
        return direction;
    }
    
    public void setDirection(double direction) {
        this.direction = direction;
    }

    public double getXComponent() {
        return intensity * Math.cos(Math.toRadians(direction));
    }
    
    public double getYComponent() {
        return intensity * Math.sin(Math.toRadians(direction));
    }
    
    public String getIdentifier() {
        return identifier;
    }
}