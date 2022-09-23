package hidenseek.controller;

import hidenseek.view.EntityView;
import hidenseek.view.EntityViewImpl;

public class PlayerControllerImpl implements PlayerController {

    private EntityView view;
    
    public PlayerControllerImpl() {
        this.view = new EntityViewImpl();
    }
    
    @Override
    public void update() {
        view.update();
    }
}
