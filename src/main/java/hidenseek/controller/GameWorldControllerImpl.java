package hidenseek.controller;

import java.util.LinkedHashSet;
import java.util.Set;

import hidenseek.controller.entities.AbstractEntityController;
import hidenseek.controller.entities.EntityController;
import hidenseek.controller.entities.EntityControllerImpl;
import hidenseek.model.components.PositionComponent;
import hidenseek.model.components.PositionComponentImpl;
import hidenseek.model.components.brains.BrainComponent;
import hidenseek.model.components.senses.SenseComponent;
import hidenseek.model.entities.Entity;
import hidenseek.model.entities.Wall;
import hidenseek.model.enums.SenseConfidence;
import hidenseek.model.worlds.GameWorld;
import hidenseek.model.worlds.GameWorldImpl;
import hidenseek.view.GraphicsDevice;
import hidenseek.view.entities.EntityView;
import hidenseek.view.entities.WallView;
import hidenseek.view.entities.WallViewImpl;
import javafx.geometry.Point2D;


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
        



        //TODO remove this
        // Draw sense range
        this.model.world().stream()
        .filter(e -> e.getComponent(SenseComponent.class).isPresent())  // get all entities 
        .map(e -> new EntityControllerImpl<EntityView>(new Wall(new Point2D(0, 0), Set.of()), new WallView() {
            
            @Override
            public void draw(GraphicsDevice device, Point2D position) {
                // TODO Auto-generated method stub
                device.drawCircle(e.getComponent(SenseComponent.class).get().range(), e.getComponent(PositionComponent.class).get().getPosition(), null);
                
            }
        }))
        .forEach(e -> e.getPosition().ifPresent(pos -> this.view.draw(e.getView(), pos)));;

        //TODO remove this
        // Draw grid AI
        this.model.world().stream()
        .filter(e -> e.getComponent(BrainComponent.class).isPresent())  // get all entities 
        .flatMap(e -> e.getComponent(BrainComponent.class).get().cells().stream())
        .map(c -> new EntityControllerImpl<WallView>(c, new WallViewImpl((Wall)c)))
        .forEach(e -> e.getPosition().ifPresent(pos -> this.view.draw(e.getView(), pos)));;
        
        
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
