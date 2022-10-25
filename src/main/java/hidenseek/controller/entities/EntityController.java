package hidenseek.controller.entities;

import java.util.Optional;
import hidenseek.model.entities.Entity;
import hidenseek.view.entities.EntityView;
import javafx.geometry.Point2D;

public interface EntityController {

    /**
     * @return The position of the entity in the GameWorld
     */
    Optional<Point2D> getPosition();
    
    /**
     * Set the entity to a specific position
     * @param position
     */
    void setPosition(Point2D position);
    
    /**
     * @return The associated view
     */
    EntityView getView();
    
    /**
     * @return The associated model
     */
    Entity getModel();
    
    /**
     * Update view on model changes
     */
    void update();
}

