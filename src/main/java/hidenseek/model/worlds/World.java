package hidenseek.model.worlds;

import java.util.Set;

public interface World<T> {

    /**
     * Compute updates
     * @param delta
     *          Delta time between current and last frame
     */
    void update(double delta);
    
    /**
     * Retrieve all the T entities in the world
     * @return set of T entities 
     */
    Set<T> world();
}
