package hidenseek.view.entities;

import hidenseek.model.enums.LightRadius;

public interface PlayerView extends EntityMovableView {
    
    void setLightRadius(LightRadius lightRadius);
    
    LightRadius getLightRadius();
}
