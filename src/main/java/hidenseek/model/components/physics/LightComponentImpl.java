package hidenseek.model.components.physics;

import hidenseek.model.components.AbstractObservableComponent;
import hidenseek.model.enums.LightRadius;

public class LightComponentImpl extends AbstractObservableComponent implements LightComponent {

    private LightRadius defaultRadius = LightRadius.SMALL;
    private LightRadius lightRadius = LightRadius.SMALL;
    
    
    @Override
    public LightRadius getRadius() {
        return lightRadius;
    }

    @Override
    public void setRadius(LightRadius radius) {
        this.lightRadius = radius;
    }

    @Override
    public void reset() {
        setRadius(LightRadius.LARGE);
    }

    @Override
    public boolean isUpgraded() {
        return lightRadius != defaultRadius;
    }
    

    
}
