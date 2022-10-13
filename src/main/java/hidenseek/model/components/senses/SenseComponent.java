package hidenseek.model.components.senses;

import java.util.Set;

import hidenseek.model.components.Component;
import hidenseek.model.entities.Entity;
import hidenseek.model.enums.SenseConfidence;

public interface SenseComponent extends Component{

    /**
     * Query an Entity through this SenseComponent,
     * when an Entity is sensed, it is added to the sensed world using this SenseComponent.
     * @param e Entity to query through this sense
     * @return SenseConfident of given entity, SenseConfidence.NONE if not sensed
     */
    <T extends Entity> SenseConfidence feel(T e);

    /**
     * 
     * @return A set of entities sensed so far
     */
    Set<Entity> world();
    

}
