package hidenseek.model.components.physics;

public final class ForceImpl implements Force{

    private final String identifier;
    private double intensity;
    private double direction;
    
    public ForceImpl(final String identifier) {
        this(identifier, 0, 0);
    }
    
    public ForceImpl(final String identifier, final double intensity, final double direction) {
        this.identifier = identifier;
        setIntensity(intensity);
        setDirection(direction);
    }
    
    @Override
    public double getIntensity() {
        return intensity;
    }

    @Override
    public void setIntensity(final double intensity) {
        this.intensity = intensity;
    }

    @Override
    public double getDirection() {
        return direction;
    }

    @Override
    public void setDirection(final double direction) {
        this.direction = direction;
    }

    @Override
    public double getXComponent() {
        return intensity * Math.cos(Math.toRadians(direction));
    }

    @Override
    public double getYComponent() {
        return intensity * Math.sin(Math.toRadians(direction));
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }
}