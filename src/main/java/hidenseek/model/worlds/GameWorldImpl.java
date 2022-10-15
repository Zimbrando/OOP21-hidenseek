package hidenseek.model.worlds;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import hidenseek.model.components.CollisionComponent;
import hidenseek.model.components.Force;
import hidenseek.model.components.InputHandlerComponent;
import hidenseek.model.components.LifeComponent;
import hidenseek.model.components.MaterialComponent;
import hidenseek.model.components.MoveComponent;
import hidenseek.model.components.PositionComponent;
import hidenseek.model.entities.Entity;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;

public class GameWorldImpl extends AbstractEntityWorldImpl implements GameWorld {

    EntityWorld senseWorld; // handler of senses
    
    public GameWorldImpl() {
        super();
        this.senseWorld = new SenseWorldImpl();
    }
    
    @Override
    public void update() {
        
        this.handleCollisions();
        
        // ----- handle SenseWorld:

        // - Gestione delle AI
        //      Per ogni entity che ha AIComponent, otteniamo direction e speed dalla AI
        
        this.senseWorld.update();
        
        // TODO create dynamicsWorld to handle movements and collisions
        // ----- handle DynamicsWorld:    
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
    
    @Override
    public Set<Entity> getDeadEntities() {
        return this.world().stream()
                .filter(entity -> entity.getComponent(LifeComponent.class).isPresent())
                .filter(entity -> !entity.getComponent(LifeComponent.class).get().isAlive())
                .collect(Collectors.toSet());
    }
      
    private void handleCollisions() {
        // phisics
        this.world().stream().forEach(entity -> {

            Optional<PositionComponent> positionComponent = entity.getComponent(PositionComponent.class);
            Optional<MoveComponent> moveComponent = entity.getComponent(MoveComponent.class);
            Optional<CollisionComponent> collisionComponent = entity.getComponent(CollisionComponent.class);
            Optional<MaterialComponent> materialComponent = entity.getComponent(MaterialComponent.class);

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
                    if(!this.world().stream().anyMatch(entity1 -> 
                                            entity1.getComponent(MaterialComponent.class).isPresent() && 
                                            collisionComponent.get().willCollisionWith(entity1, new Point2D(finalForceX + forceXSign, 0)))) {
                        
                        resultantOffset = resultantOffset.add(new Point2D(finalForceX, 0));
                        forceXAccepted = true;
                        break;
                    }
                    forceX--;
                }

                while(forceY > 0 && !forceYAccepted) {
                    final int finalForceY = forceY * forceYSign;
                    if(!this.world().stream().anyMatch(entity1 -> 
                                            entity1.getComponent(MaterialComponent.class).isPresent() && 
                                            collisionComponent.get().willCollisionWith(entity1, new Point2D(0, finalForceY + forceXSign)))) {
                        
                        resultantOffset = resultantOffset.add(new Point2D(0, finalForceY));
                        forceYAccepted = true;
                        break;
                    }
                    forceY--;
                }
            }
           
            
            positionComponent.get().setPosition(positionComponent.get().getPosition().add(resultantOffset));
            
        });
    }
}
