package hidenseek.controller;

import hidenseek.view.EntityView;
import javafx.geometry.Point2D;

interface Renderer {

    <V extends EntityView> void draw(V view, Point2D position);

}
