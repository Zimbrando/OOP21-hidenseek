package hidenseek.model.components.physics;

import hidenseek.model.components.AbstractObservableComponent;
import hidenseek.model.enums.LightRadius;

public class LightComponentImpl extends AbstractObservableComponent implements LightComponent {

    private final LightRadius defaultRadius;
    private LightRadius lightRadius;
    
    public LightComponentImpl(final LightRadius radius) {
        this.lightRadius = radius;
        this.defaultRadius = radius;
    }
    
    @Override
    public LightRadius getRadius() {
        return this.lightRadius;
    }

    @Override
    public void setRadius(final LightRadius radius) {
        this.lightRadius = radius;
    }

    @Override
    public void reset() {
        this.setRadius(this.defaultRadius);
    }

    @Override
    public boolean isUpgraded() {
        return this.lightRadius != this.defaultRadius;
    }
    
}
