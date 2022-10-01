package hidenseek.controller;

import java.util.LinkedHashSet;
import java.util.Set;

import hidenseek.model.Entity;
import hidenseek.model.EntityImpl;
import hidenseek.model.components.LinearMovementComponentImpl;
import hidenseek.view.PlayerViewImpl;
import hidenseek.view.PlayerView;
import javafx.geometry.Point2D;

public final class GameWorldControllerImpl implements GameWorldController {

    private Gameloop loop;
    private Set<EntityController> entities;
    private final Renderer view;
    
    public GameWorldControllerImpl(final Renderer view) {
        this.view = view;
        this.entities = new LinkedHashSet<EntityController>();
        
        addEntities();
        
        this.loop = new GameloopFXImpl() {

            @Override
            public void tick() {
                //System.out.println("Start game loop");
                
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
                
                //Draw game
                view.refresh();
                for (EntityController ec : entities) {
                    view.update(ec);
                }
                
                //System.out.println("End game loop");
            }
        };
        this.loop.start();
    }
    
    public void addEntities() {
        Entity e1 = new EntityImpl();
        e1.attach(new LinearMovementComponentImpl(new Point2D(20, 20)));
      
        this.entities.add(new EntityControllerImpl<PlayerView>(e1, new PlayerViewImpl()));
    }

    @Override
    public void attachInputHandler() {
        
    }
}
