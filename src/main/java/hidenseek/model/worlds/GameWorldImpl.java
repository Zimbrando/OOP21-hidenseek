package hidenseek.model.worlds;

import java.util.Set;

import hidenseek.model.components.InputHandlerComponent;
import hidenseek.model.entities.Entity;
import javafx.scene.input.KeyCode;

public class GameWorldImpl extends AbstractEntityWorldImpl implements GameWorld {

    EntityWorld senseWorld; // handler of senses
    
    public GameWorldImpl() {
        super();
        this.senseWorld = new SenseWorldImpl();
    }
    
    @Override
    public void update() {
        // ----- handle SenseWorld:

        // - Gestione delle AI
        //      Per ogni entity che ha AIComponent, otteniamo direction e speed dalla AI
        
        this.senseWorld.update();
        
        // TODO create dynamicsWorld to handle movements and collisions
        // ----- handle DynamicsWorld:
        
        // - Gestione della posizione degli oggetti
        //      Calcolare la posizione al frame n+1 delle entity che hanno MovementComponent, in base alla loro direction e speed
        //      Controlla che la posizione calcolata sia possibile (ovvero non collide con nessun entity che non ha TriggerableComponent)
        //              Aggiornamento delle eventuale posizione di ogni entity con MovementComponent
        
        // - Gestione di tutte le collisione eccetto i muri
        //      Questa parte è effettuata logicamente nella sezione precedente, poichè viene eseguita solo per gli oggetti che si sono mossi.
        //      Trova tutti gli oggetti che si stanno intersecando e manda l'evento intersectionWith(entity) ad entrambi
        
        
    }

    @Override
    public void handleInput(final Set<KeyCode> keysPressed) {
        this.world().forEach(entity -> {
            entity.getComponent(InputHandlerComponent.class)
            .ifPresent(c -> c.computeScheme(keysPressed));
        });
    }
    
    @Override
    public void addEntity(final Entity e) {
        super.addEntity(e);
        senseWorld.addEntity(e);
    }
    
    @Override
    public void removeEntity(final Entity e) {
        super.removeEntity(e);
        senseWorld.removeEntity(e);
    }
}
