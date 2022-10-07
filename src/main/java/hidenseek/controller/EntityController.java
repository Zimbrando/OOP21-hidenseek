package hidenseek.controller;

import java.util.Optional;
import hidenseek.model.entities.Entity;
import hidenseek.view.EntityView;
import javafx.geometry.Point2D;

interface EntityController {

    Optional<Point2D> getPosition();
    
    EntityView getView();
    
    Entity getModel();
}

