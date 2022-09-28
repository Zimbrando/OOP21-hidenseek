package hidenseek.controller;

public class GameWorldControllerImpl implements GameWorldController {

    private Gameloop loop;
    
    public GameWorldControllerImpl() {
        this.loop = new GameloopFXImpl() {
            @Override
            public void tick() {
               
            }  
        };
    }
}
