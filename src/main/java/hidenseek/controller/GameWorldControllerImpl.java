package hidenseek.controller;

import java.util.LinkedHashSet;
import java.util.Set;

import hidenseek.controller.entities.EntityController;
import hidenseek.model.GameLevel;
import hidenseek.model.entities.Entity;
import hidenseek.model.enums.GameState;
import hidenseek.model.worlds.GameWorld;
import hidenseek.model.worlds.GameWorldImpl;

public final class GameWorldControllerImpl implements GameWorldController {
    
    private final GameSceneController mainController;
    private final Gameloop loop;
    private final Set<EntityController> entities;
    private final Set<HudController> huds;
    private final Renderer view;
    private final InputScheme input;
    private final GameWorld model;
    private final LevelHandler level;
    
    public GameWorldControllerImpl(final GameSceneController mainController, final Renderer view, 
                                final InputScheme input, final LevelHandler level) {
        this.view = view;
        this.mainController = mainController;
        this.entities = new LinkedHashSet<>();
        this.huds = new LinkedHashSet<>();
        this.input = input;
        this.model = new GameWorldImpl();
        this.level = level;
        
        this.loop = new GameloopFXImpl() {

            @Override
            public void tick() {
                update();
            }

        };
        
        this.loadLevel(level.getCurrentLevel().get());
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
            this.view.drawEntity(entity.getView());
        });
        
        this.huds.forEach(hud -> {
            // update view
            hud.update();
            // draw hud
            this.view.drawHud(hud.getView());
        });
        
        if (this.model.getState() == GameState.OVER_LOSE) {
            this.handleGameOver();
        }
        
        if (this.model.getState() == GameState.OVER_WIN) {
            this.handleWin();
        }
        
    }
    
    @Override
    public void addEntity(final EntityController entityController) {
        this.entities.add(entityController);
        this.model.addEntity(entityController.getModel());
    }

    @Override
    public void addHud(final HudController hudController) {
        this.huds.add(hudController);
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
    
    private void removeDeadEntities(final Set<Entity> entities) {
        this.entities.removeIf(controller -> entities.contains(controller.getModel()));
        entities.forEach(entity -> model.removeEntity(entity));
    }
    
    private void handleGameOver() {
        //No more levels
        this.mainController.goToMenu();
        this.loop.stop();
    }
    
    private void handleWin() {
        //There's a next level
        if (this.level.hasNext()) {
            this.level.next();
            this.loadLevel(this.level.getCurrentLevel().get());
            return;
        }
        this.mainController.goToMenu();
        this.loop.stop();
    }
    
    private void loadLevel(final GameLevel gameLevel) {
        this.entities.clear();
        this.huds.clear();
        this.model.clearEntities();
        gameLevel.getEntities().forEach(entityController -> this.addEntity(entityController));
        gameLevel.getHuds().forEach(hudController -> this.addHud(hudController));
        this.model.setKeys(gameLevel.keysInLevel());
    }
}
