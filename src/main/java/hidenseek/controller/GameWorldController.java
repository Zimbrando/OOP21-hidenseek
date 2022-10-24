package hidenseek.controller;

import hidenseek.controller.entities.EntityController;
import hidenseek.controller.huds.HudController;

public interface GameWorldController {

    void update(double delta);
  
    void pause();
    
    void resume();
    
    void addEntity(EntityController entityController);
    
    void addHud(HudController hudController);
}
