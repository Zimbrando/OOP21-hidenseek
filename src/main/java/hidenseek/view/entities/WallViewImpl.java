package hidenseek.view.entities;

import hidenseek.model.entities.Wall;
import hidenseek.view.GraphicsDevice;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.geometry.Point2D;

import java.util.List;
import java.util.stream.Collectors;

import hidenseek.model.components.CollisionComponent;


public class WallViewImpl extends AbstractEntityView implements WallView {
    
    Image image = new Image("./sprites/player-right.png");
    Wall model;
    
    public WallViewImpl(Wall model) {
        this.model = model;
    }
    
    @Override
    public void draw(final GraphicsDevice device) {       
        List<Point2D> hitboxPoints = model.getComponent(CollisionComponent.class).get().getHitbox().stream().map(point -> point.add(this.getPosition())).collect(Collectors.toList());
        device.drawPolygon(hitboxPoints, Color.RED, image);
    }


}
