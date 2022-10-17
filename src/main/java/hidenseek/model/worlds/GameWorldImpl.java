package hidenseek.model.worlds;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import hidenseek.model.components.InputHandlerComponent;
import hidenseek.model.components.LifeComponent;
import hidenseek.model.entities.Entity;

import javafx.scene.input.KeyCode;

public class GameWorldImpl extends AbstractEntityWorldImpl implements GameWorld {

    final private EntityWorld senseWorld; // handler of senses
    final private EntityWorld dynamicsWorld; // handler of senses
    private Set<KeyCode> keysPressed;
    
    public GameWorldImpl() {
        super();
        this.senseWorld = new SenseWorldImpl();
        this.dynamicsWorld = new DinamicsWorldImpl();
        this.keysPressed = new HashSet<>();
    }
    
    @Override
    public void update() {

        // ----- handle inputs:
        this.handleInput();

        // ----- handle SenseWorld:
        // - Gestione delle AI
        //      Per ogni entity che ha AIComponent, otteniamo direction e speed dalla AI
        this.senseWorld.update();
        
        // ----- handle DynamicsWorld:
        // - Gestione della posizione degli oggetti
        //      Calcolare la posizione al frame n+1 delle entity che hanno MovementComponent, in base alla loro direction e speed
        //      Controlla che la posizione calcolata sia possibile (ovvero non collide con nessun entity che non ha TriggerableComponent)
        //              Aggiornamento delle eventuale posizione di ogni entity con MovementComponent
        // - Gestione di tutte le collisione eccetto i muri
        //      Questa parte è effettuata logicamente nella sezione precedente, poichè viene eseguita solo per gli oggetti che si sono mossi.
        //      Trova tutti gli oggetti che si stanno intersecando e manda l'evento intersectionWith(entity) ad entrambi
        this.dynamicsWorld.update();
    }

    @Override
    public void updateInput(final Set<KeyCode> keysPressed) {
        this.keysPressed = Set.copyOf(keysPressed);
    }

    private void handleInput() {
        this.world().forEach(entity -> {
            entity.getComponent(InputHandlerComponent.class)
            .ifPresent(c -> c.computeScheme(this.keysPressed));
        });
    }
    
    @Override
    public void addEntity(final Entity e) {
        super.addEntity(e);
        this.senseWorld.addEntity(e);
        this.dynamicsWorld.addEntity(e);
    }
    
    @Override
    public void removeEntity(final Entity e) {
        super.removeEntity(e);
        this.senseWorld.removeEntity(e);
        this.dynamicsWorld.removeEntity(e);
    }
    
    @Override
    public Set<Entity> getDeadEntities() {
        return this.world().stream()
                .filter(entity -> entity.getComponent(LifeComponent.class).isPresent())
                .filter(entity -> !entity.getComponent(LifeComponent.class).get().isAlive())
                .collect(Collectors.toSet());
    }

    @Override
    public boolean hasWin() {
        return false;
    }
}
