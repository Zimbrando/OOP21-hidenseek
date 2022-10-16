package hidenseek.controller;

import java.util.LinkedHashSet;
import java.util.Set;

import hidenseek.controller.entities.EntityController;
import hidenseek.model.entities.Entity;
import hidenseek.model.worlds.GameWorld;
import hidenseek.model.worlds.GameWorldImpl;


public final class GameWorldControllerImpl implements GameWorldController {
    
    private final Gameloop loop;
    private final Set<EntityController> entities;
    private final Renderer view;
    private final InputScheme input;
    private final GameWorld model;
//TODO    private final LevelHandler level;
    
    public GameWorldControllerImpl(final Renderer view, final InputScheme input) {
        this.view = view;
        this.entities = new LinkedHashSet<>();
        this.input = input;
        this.model = new GameWorldImpl();
        
        this.loop = new GameloopFXImpl() {

            @Override
            public void tick() {
                update();
            }

        };
        this.loop.start();
    }


    @Override
    public void update() {
        // handle inputs
        model.updateInput(this.input.getCurrentPressedKeys());
        
        // update logic
        model.update();
        
        this.removeDeadEntities(model.getDeadEntities());
        
        //Draw game
        view.refresh();
        
        // update entities
        this.entities.forEach(entity -> {
            // update view
            entity.update();
            // draw entity
            entity.getPosition().ifPresent(pos -> this.view.draw(entity.getView(), pos));
        });
    }
    

    @Override
    public void addEntity(final EntityController entityController) {
        this.entities.add(entityController);
        this.model.addEntity(entityController.getModel());
    }


    @Override
    public void pause() {
        System.out.println("Game paused: press 'R' to resume");
        this.loop.stop();
    }


    @Override
    public void resume() {
        this.loop.start();
    }
    
    private void removeDeadEntities(Set<Entity> entities) {
        this.entities.removeIf(controller -> entities.contains(controller.getModel()));
        entities.forEach(entity -> model.removeEntity(entity));
    }
}
