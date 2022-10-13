package hidenseek.model.worlds;

import java.util.Set;

public interface World<T> {

    /**
     * Compute updates
     */
    void update();
    
    /**
     * Retrieve all the T entities in the world
     * @return set of T entities 
     */
    Set<T> world();
}
