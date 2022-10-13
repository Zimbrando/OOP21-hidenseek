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
import hidenseek.model.components.PositionComponent;
import hidenseek.model.components.hearts.HeartComponent;
import hidenseek.model.components.senses.SenseComponent;
import hidenseek.model.components.MoveComponent;
import hidenseek.model.entities.Entity;
import hidenseek.model.enums.Heart;
import javafx.util.Pair;


/**
 * 
 * @author Marco Sangiorgi
 *
 */
public abstract class AbstractBrainComponentImpl extends AbstractDependencyComponent implements BrainComponent {

    /**
     * Map: HEART -> Predicate, Bifunction, BiConsumer
     */
    Map<Heart, Pair<Pair<Predicate<Entity>, BiFunction<Entity, Entity, Entity>>, BiConsumer<Optional<Entity>, Set<Entity>>>> heartBeahaviours;
    
    public AbstractBrainComponentImpl() {
        // define dependencies
        super(Set.of(
                HeartComponent.class, 
                SenseComponent.class, 
                PositionComponent.class, 
                MoveComponent.class
                ));
        // init map
        this.heartBeahaviours = new HashMap<>();
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
        if(!heartBeahaviours.containsKey(heart)) {
            return Optional.empty();
        }
        // get predicate to filter entities
        final Predicate<Entity> isTargetable = heartBeahaviours.get(heart).getKey().getKey();
        // get bifunction to choose between targetables
        final BiFunction<Entity, Entity, Entity> confrontTargetables = heartBeahaviours.get(heart).getKey().getValue();
        
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
        if(!heartBeahaviours.containsKey(heart)) {
            return;
        }
        // use biconsumer of heart, to consume target and set of sensed entities
        heartBeahaviours.get(heart).getValue().accept(target, entities);
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
        this.heartBeahaviours.put(heart, new Pair<>(new Pair<>(isTargetable, confrontTargetables), actionOnTarget));
    }
    
}
