package hidenseek.model.components.senses;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import hidenseek.model.components.AbstractComponent;
import hidenseek.model.components.SenseComponent;
import hidenseek.model.entities.Entity;
import hidenseek.model.enums.SenseConfidence;

public abstract class AbstractSenseComponent extends AbstractComponent implements SenseComponent {

    final private Map<Entity, SenseConfidence> entityMap;
    
    public AbstractSenseComponent() {
        this.entityMap = new HashMap<>();
    }
    
    @Override
    public <T extends Entity> SenseConfidence feel(final T e) {
        // create new sensed entity
        final SenseConfidence conf = senseCheck(e);
        // check if sensed
        if(conf != SenseConfidence.NONE) {
            // add it to set
            entityMap.put(e, conf);
        }
        return conf;
    }
    
    
    @Override
    public Set<Entity> world() {
        return this.entityMap.keySet();
    }

    /**
     * Check if an entity can be felt by this sense,
     * and Get the confidence of sensing this entity
     * @param e Entity to query through sense
     * @return confidence of sensed entity, NONE if not sensed
     */
    abstract SenseConfidence senseCheck(Entity e);
    
}
