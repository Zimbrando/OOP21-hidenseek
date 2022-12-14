package hidenseek.model.components.brains;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import hidenseek.model.components.AbstractDependencyComponent;
import hidenseek.model.components.hearts.HeartComponent;
import hidenseek.model.components.physics.ForceImpl;
import hidenseek.model.components.physics.MoveComponent;
import hidenseek.model.components.physics.PositionComponent;
import hidenseek.model.components.senses.SenseComponent;
import hidenseek.model.entities.Entity;
import hidenseek.model.enums.Direction;
import hidenseek.model.enums.Heart;
import javafx.geometry.Point2D;


/**
 * 
 * @author Marco Sangiorgi
 *
 */
public abstract class AbstractBrainComponent extends AbstractDependencyComponent implements BrainComponent {

    /**
     * Map: HEART -> <Predicate, Bifunction>, BiConsumer
     */
    Map<Heart, Predicate<Entity>> mapIsTargetable;
    Map<Heart, BiFunction<Entity, Entity, Entity>> mapConfrontTargetables;
    Map<Heart, BiConsumer<Optional<Entity>, Set<Entity>>> macActionOnTarget;
    
    public AbstractBrainComponent() {
        // define dependencies
        super(Set.of(
                HeartComponent.class, 
                SenseComponent.class, 
                PositionComponent.class, 
                MoveComponent.class
                ));
        // init maps
        this.mapIsTargetable = new HashMap<>();
        this.mapConfrontTargetables = new HashMap<>();
        this.macActionOnTarget = new HashMap<>();
    }

    @Override
    public void neuroImpulse() {
        // check dependency components
        if(!this.checkDependencies()) {
            return;
        }
        // get owner
        final Entity owner = this.getOwner().get();
        // get all sense components
        final Set<SenseComponent> senseComponents = owner.getComponents().stream().filter(c -> SenseComponent.class.isInstance(c)).map(c -> SenseComponent.class.cast(c)).collect(Collectors.toSet());
        // get all sensed entities
        final Set<Entity> sensedEntities = senseComponents.stream().flatMap(c -> c.world().stream()).collect(Collectors.toSet());
        // get target 
        final Optional<Entity> target = getTarget(sensedEntities);
        // do action on target
        actionOnTarget(target, sensedEntities);
    }
    
    /**
     * Find target among given entities, based on heart
     * Note: requires HeartComponent, PositionComponent
     * @param entities given entities
     * @return A optional of the target, if any
     */
    private Optional<Entity> getTarget(final Set<Entity> entities) {
        // get heart
        final Heart heart = this.getOwner().get().getComponent(HeartComponent.class).get().getHeart();
        // check heart in map
        if(!mapIsTargetable.containsKey(heart)) {
            return Optional.empty();
        }
        // get predicate to filter entities
        final Predicate<Entity> isTargetable = mapIsTargetable.get(heart);
        // get bifunction to choose between targetables
        final BiFunction<Entity, Entity, Entity> confrontTargetables = mapConfrontTargetables.get(heart);
        
        // get target using behavior
        return entities.stream()
                .filter(e -> isTargetable.test(e))
                .reduce((e1,e2) -> confrontTargetables.apply(e1,e2));
    }
    
    
    /**
     * 
     * @param target
     * @param entities
     */
    private void actionOnTarget(final Optional<Entity> target, final Set<Entity> entities) {
        // get heart
        final Heart heart = this.getOwner().get().getComponent(HeartComponent.class).get().getHeart();
        // check heart in map
        if(!macActionOnTarget.containsKey(heart)) {
            return;
        }
        // use biconsumer of heart, to consume target and set of sensed entities
        macActionOnTarget.get(heart).accept(target, entities);
    }
    
    
    /**
     * Decide what behavior must have a specific heart
     * @param heart Heart to whitch apply this filtering behavior
     * @param isTargetable Predicate to filter all Entity sensed
     * @param confrontTargetables Bifunction to confront by pairs all the entities filtered before,
     *                                  returning the one preferred
     * @param actionOnTarget Biconsumer to 
     */
    public void setBehaviour(final Heart heart
            , final Predicate<Entity> isTargetable
            , final BiFunction<Entity, Entity, Entity> confrontTargetables
            , final BiConsumer<Optional<Entity>, Set<Entity>> actionOnTarget) {
        this.mapIsTargetable.put(heart, isTargetable);
        this.mapConfrontTargetables.put(heart, confrontTargetables);
        this.macActionOnTarget.put(heart, actionOnTarget);
    }
    
    /**
     * move towards next position
     * @param nextPosition point2D of next position
     */
    public void move(final Point2D nextPosition) {
        // get movement component
        final PositionComponent position = this.getOwner().get().getComponent(PositionComponent.class).get();
        final MoveComponent movement = this.getOwner().get().getComponent(MoveComponent.class).get();
        // move based on target position
        movement.removeForce(force -> force.getIdentifier().startsWith("ai"));
        // move right
        if(nextPosition.getX() > position.getPosition().getX()) {
            movement.addForce(new ForceImpl("ai-horizontal", 1, Direction.RIGHT.getValue()));
        }
        // move left
        if(nextPosition.getX() < position.getPosition().getX()){
            movement.addForce(new ForceImpl("ai-horizontal", 1, Direction.LEFT.getValue()));
        }
        // move down
        if(nextPosition.getY() > position.getPosition().getY()) {
            movement.addForce(new ForceImpl("ai-vertical", 1, Direction.DOWN.getValue()));
        }
        // move up
        if(nextPosition.getY() < position.getPosition().getY()) {
            movement.addForce(new ForceImpl("ai-vertical", 1, Direction.UP.getValue()));
        }
    }
    
}
