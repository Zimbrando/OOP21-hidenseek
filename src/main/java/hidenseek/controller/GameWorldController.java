package hidenseek.controller;

import hidenseek.controller.entities.EntityController;

public interface GameWorldController {

    void update(double delta);
  
    void pause();
    
    void resume();
    
    void addEntity(EntityController entityController);
    
    void addHud(HudController hudController);
}
