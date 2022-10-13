package hidenseek.controller;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import hidenseek.controller.entities.EntityController;
import hidenseek.model.components.CollisionComponent;
import hidenseek.model.components.Force;
import hidenseek.model.components.MaterialComponent;
import hidenseek.model.components.MoveComponent;
import hidenseek.model.components.PositionComponent;
import hidenseek.model.worlds.GameWorld;
import hidenseek.model.worlds.GameWorldImpl;
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
            Optional<MoveComponent> moveComponent = entity.getModel().getComponent(MoveComponent.class);
            Optional<CollisionComponent> collisionComponent = entity.getModel().getComponent(CollisionComponent.class);
            Optional<MaterialComponent> materialComponent = entity.getModel().getComponent(MaterialComponent.class);

            if(!positionComponent.isPresent()) {
                return;
            }
            
            if(!moveComponent.isPresent()) {
                return;
            }

            //COLLISION DETECT METHOD 2
            //TODO: write it better with no code repetition

            Point2D resultantOffset = new Point2D(0, 0);
            for(Force force : moveComponent.get().getForces().toArray(new Force[0])) {
                if(!collisionComponent.isPresent()) {
                    return;
                }

                int forceX = (int)Math.abs(force.getXComponent());
                int forceXSign = force.getXComponent() < 0 ? -1 : 1;
                Boolean forceXAccepted = false;
                int forceY = (int)Math.abs(force.getYComponent());
                int forceYSign = force.getYComponent() < 0 ? -1 : 1;
                Boolean forceYAccepted = false;
                
                if(!materialComponent.isPresent()) {
                    resultantOffset = resultantOffset.add(new Point2D(forceX * forceXSign, forceY * forceYSign));
                    continue;
                }
                
                while(forceX > 0 && !forceXAccepted) {
                    final int finalForceX = forceX * forceXSign;
                    if(!this.entities.stream().anyMatch(entity1 -> 
                                            entity1.getModel().getComponent(MaterialComponent.class).isPresent() && 
                                            collisionComponent.get().willCollisionWith(entity1.getModel(), new Point2D(finalForceX + forceXSign, 0)))) {
                        
                        resultantOffset = resultantOffset.add(new Point2D(finalForceX, 0));
                        forceXAccepted = true;
                        break;
                    }
                    forceX--;
                }

                while(forceY > 0 && !forceYAccepted) {
                    final int finalForceY = forceY * forceYSign;
                    if(!this.entities.stream().anyMatch(entity1 -> 
                                            entity1.getModel().getComponent(MaterialComponent.class).isPresent() && 
                                            collisionComponent.get().willCollisionWith(entity1.getModel(), new Point2D(0, finalForceY + forceXSign)))) {
                        
                        resultantOffset = resultantOffset.add(new Point2D(0, finalForceY));
                        forceYAccepted = true;
                        break;
                    }
                    forceY--;
                }
            }
           
            
            positionComponent.get().setPosition(positionComponent.get().getPosition().add(resultantOffset));
            
            
            //this.entities.stream().filter(entity1 -> collisionComponent.get().collisionWith(entity1.getModel())).forEach(entity1 -> {
        
                //System.out.println("COLLISION BETWEEN " + entity.toString() + " AND " + entity1.toString());
            //});
        });

        System.out.println(this.input.getCurrentPressedKeys());
        
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
