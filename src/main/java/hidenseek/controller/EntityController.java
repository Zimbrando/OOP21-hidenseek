package hidenseek.controller;

import java.util.Set;

import hidenseek.model.entities.Entity;
import hidenseek.view.EntityView;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;

interface EntityController {

    Point2D getPosition();
    
    void handleInput(Set<KeyCode> keysPressed);

    EntityView getView();
    
    Entity getModel();
}

