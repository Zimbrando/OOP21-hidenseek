package hidenseek.controller;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import hidenseek.controller.entities.EntityController;
import hidenseek.model.GameWorld;
import hidenseek.model.GameWorldImpl;
import hidenseek.model.components.CollisionComponent;
import hidenseek.model.components.Force;
import hidenseek.model.components.MaterialComponent;
import hidenseek.model.components.MoveComponent;
import hidenseek.model.components.PositionComponent;
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
        
        // phisics
        this.entities.stream().forEach(entity -> {
            Optional<PositionComponent> positionComponent = entity.getModel().getComponent(PositionComponent.class);
            if(!positionComponent.isPresent()) {
                return;
            }
            
            Optional<MoveComponent> moveComponent = entity.getModel().getComponent(MoveComponent.class);
            if(!moveComponent.isPresent()) {
                return;
            }
            
            
            //COLLISION DETECT METHOD 1
            /*
            Force entityForce = moveComponent.get().getResultantForce();
            Point2D entityDesiredOffset = new Point2D(entityForce.getXComponent(), entityForce.getYComponent());
            
            Optional<CollisionComponent> collisionComponent = entity.getModel().getComponent(CollisionComponent.class);
            if(collisionComponent.isPresent() && this.entities.stream().anyMatch(entity1 -> collisionComponent.get().willCollisionWith(entity1.getModel(), entityDesiredOffset))) {
                return;
            }
            positionComponent.get().setPosition(positionComponent.get().getPosition().add(entityDesiredOffset));
            */
            

            //COLLISION DETECT METHOD 2
            //TODO: write it better with no code repetition
            final double SPEED_CONST = 1.2;
            Point2D resultantOffset = new Point2D(0, 0);
            for(Force force : moveComponent.get().getForces().toArray(new Force[0])) {

                Optional<CollisionComponent> collisionComponent = entity.getModel().getComponent(CollisionComponent.class);
                
                if(!collisionComponent.isPresent()) {
                    return;
                }
                
                if(!this.entities.stream().anyMatch(entity1 -> entity1.getModel().getComponent(MaterialComponent.class).isPresent() && collisionComponent.get().willCollisionWith(entity1.getModel(), new Point2D(force.getXComponent() * SPEED_CONST, 0)))) {
                    resultantOffset = resultantOffset.add(new Point2D(force.getXComponent() * SPEED_CONST, 0));
                }
                
                if(!this.entities.stream().anyMatch(entity1 -> entity1.getModel().getComponent(MaterialComponent.class).isPresent() && collisionComponent.get().willCollisionWith(entity1.getModel(), new Point2D(0, force.getYComponent() * SPEED_CONST)))) {
                    resultantOffset = resultantOffset.add(new Point2D(0, force.getYComponent() * SPEED_CONST));
                }
            }
           
            
            positionComponent.get().setPosition(positionComponent.get().getPosition().add(resultantOffset));
        });
        
        
        

        //Draw game
        view.refresh();
        
        // update view
        this.entities.forEach(entity -> {
            this.view.update(entity);
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

}
