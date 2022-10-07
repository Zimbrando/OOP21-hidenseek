package hidenseek.model;

import java.util.HashSet;
import java.util.Set;

import hidenseek.model.components.InputHandlerComponent;
import hidenseek.model.entities.Entity;
import javafx.scene.input.KeyCode;

public class GameWorldImpl implements GameWorld {


    private final Set<Entity> entities;
    
    public GameWorldImpl() {
        this.entities = new HashSet<>();
    }
    
    @Override
    public void update() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void handleInput(Set<KeyCode> keysPressed) {
        this.entities.forEach(entity -> {
            entity.getComponent(InputHandlerComponent.class)
            .ifPresent(c -> c.computeScheme(keysPressed));
        });
    }
    
    @Override
    public void addEntity(Entity entity) {
        
    }
    
    

}
