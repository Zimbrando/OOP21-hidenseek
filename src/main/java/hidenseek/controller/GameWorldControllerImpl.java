package hidenseek.controller;

import java.util.List;
import java.util.Set;

import hidenseek.model.GameWorld;
import hidenseek.model.GameWorldImpl;
import hidenseek.view.GameWorldView;
import hidenseek.view.View;

public class GameWorldControllerImpl  implements GameWorldController {

    private final GameWorld model;
    private GameWorldView view;
//    private final Set<EntityController> entities;
    private final PlayerController player;
    
    public GameWorldControllerImpl(final GameWorldView view) {
        this.model = new GameWorldImpl() {

            @Override
            public void execute() {
                player.update();
            }
            
        };
        this.player = new PlayerControllerImpl();
//        this.entities.add(player);
        this.view = view;
    }

    @Override
    public void start() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void end() {
        // TODO Auto-generated method stub
        
    }
     
}
