package hidenseek.controller;

import hidenseek.model.entities.Entity;
import hidenseek.view.HudView;

public interface HudController {

    void update();
    
    HudView getView();
    
    Entity getModel();
}
