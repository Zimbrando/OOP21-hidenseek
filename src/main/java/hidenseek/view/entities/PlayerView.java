package hidenseek.view.entities;

import hidenseek.model.enums.LightRadius;

public interface PlayerView extends EntityMovableView {
    
    /**
     * @param lightRadius
     *          Player light radius
     */
    void setLightRadius(LightRadius lightRadius);
    
    /**
     * @return The current light radius
     */
    LightRadius getLightRadius();
}
