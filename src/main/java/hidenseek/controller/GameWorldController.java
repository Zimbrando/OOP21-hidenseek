package hidenseek.controller;

public interface GameWorldController {

    void update();
  
    void pause();
    
    void resume();
    
    void addEntity(EntityController entityController);
}
