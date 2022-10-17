package hidenseek.model.components.brains;

import java.util.Set;

import hidenseek.model.components.Component;
import hidenseek.model.entities.Entity;

public interface BrainComponent extends Component{
    
    /**
     * Use components to retrieve information about the world,
     * then act according to the heart.
     */
    void neuroImpulse();
    
    //TODO remove this, only for test
    Set<Entity> cells();
}
