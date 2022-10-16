package hidenseek.controller.entities;

import java.util.Optional;
import hidenseek.model.entities.Entity;
import hidenseek.view.entities.EntityView;
import javafx.geometry.Point2D;

public interface EntityController {

    Optional<Point2D> getPosition();
    
    void setPosition(Point2D position);
    
    EntityView getView();
    
    Entity getModel();
    
    /**
     * Update view on model changes
     */
    void update();
}

