package hidenseek.model.components.hearts;

import hidenseek.model.components.Component;
import hidenseek.model.entities.Entity;
import hidenseek.model.enums.Heart;

public interface HeartComponent extends Component {
    
    /**
     * Get the heart
     * @return Heart of this component
     */
    Heart getHeart();


    /**
     * Get who hates this heart
     * @return true if hated, false otherwise
     */
    <T extends Entity> boolean hates(final T e);


    /**
     * Get who loves this heart
     * @return true if loved, false otherwise
     */
    <T extends Entity> boolean loves(final T e);
}
