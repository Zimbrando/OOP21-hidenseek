package hidenseek.controller;

import java.util.LinkedHashSet;
import java.util.Set;

import hidenseek.model.GameWorld;
import hidenseek.model.GameWorldImpl;

public final class GameWorldControllerImpl implements GameWorldController {
    
    private final Gameloop loop;
    private final Set<EntityController> entities;
    private final Renderer view;
    private final InputScheme input;
    private final GameWorld model;
//TODO    private final LevelHandler level;
    
    
    public GameWorldControllerImpl(final Renderer view, final InputScheme input) {
        this.view = view;
        this.entities = new LinkedHashSet<EntityController>();
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
        //Gestione degli input da tastiera e mouse
        //      Per ogni entity che ha InputComponent, aggiorniamo la lista di keys, pulsanti del mouse premuti e posizione del cursore
        //      Otteniamo direction e speed dalla entity
        
        //Gestione delle AI
        //      Per ogni entity che ha AIComponent, otteniamo direction e speed dalla AI
        
        //Gestione della posizione degli oggetti
        //      Calcolare la posizione al frame n+1 delle entity che hanno MovementComponent, in base alla loro direction e speed
        //      Controlla che la posizione calcolata sia possibile (ovvero non collide con nessun entity che non ha TriggerableComponent)
        //              Aggiornamento delle eventuale posizione di ogni entity con MovementComponent
        
        //Gestione di tutte le collisione eccetto i muri
        //Questa parte è effettuata logicamente nella sezione precedente, poichè viene eseguita solo per gli oggetti che si sono mossi.
        //      Trova tutti gli oggetti che si stanno intersecando e manda l'evento intersectionWith(entity) ad entrambi
        
        // handle inputs
        model.handleInput(this.input.getCurrentPressedKeys());
        // update logic
        model.update();

        System.out.println(this.input.getCurrentPressedKeys());
        
        //Draw game
        view.refresh();
        // update view
        this.entities.forEach(entity -> {
            this.view.update(entity);
        });
    }
    

    @Override
    public void addEntity(EntityController entityController) {
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

}
