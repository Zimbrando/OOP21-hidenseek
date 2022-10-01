package hidenseek.controller;

import hidenseek.model.Entity;
import hidenseek.view.EntityView;
import javafx.geometry.Point2D;

interface EntityController {

    Point2D getPosition();
    
    void move();

    EntityView getView();
    
    Entity getModel();
}

