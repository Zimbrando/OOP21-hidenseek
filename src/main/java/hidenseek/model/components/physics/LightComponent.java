package hidenseek.model.components.physics;

import hidenseek.model.components.UpgradableComponent;
import hidenseek.model.enums.LightRadius;

public interface LightComponent extends UpgradableComponent {
    
    LightRadius getRadius();    
    
    void setRadius(LightRadius radius);
    
}
