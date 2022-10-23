package hidenseek.model.components;

import java.util.Set;

import hidenseek.model.entities.Entity;
import javafx.geometry.Point2D;

public interface MapComponent extends Component {
    /**
     * Create a map based on the given entities
     * @param entities set of entity to be mapped
     */
    void mapEntities(Set<Entity> entities);
    
    /**
     * Retrieve the created paths based on entities
     * @return
     */
    Set<Point2D> getGameMap();
    
    /**
     * Get shortest path from source to dest
     * @param source
     * @param dest
     * @return
     */
    Set<Point2D> getPath(Point2D source, Point2D dest);
}
