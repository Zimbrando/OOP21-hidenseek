package hidenseek.view.entities;

import hidenseek.view.GraphicsDevice;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.geometry.Point2D;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



public class WallViewImpl extends AbstractEntityView implements WallView {
    
    private static final Image WALL_SPRITE = new Image("./assets/wall.png");
    private final ImagePattern WALL_SPRITE_PATTERN = new ImagePattern(WALL_SPRITE, 0, 0, 50, 50, false);
    private List<Point2D> polygonPoints;
    
    @Override
    public void draw(final GraphicsDevice device) {       
        device.drawPolygon(polygonPoints, WALL_SPRITE_PATTERN);
    }

    @Override
    public void setHitbox(final Set<Point2D> hitbox) {
        this.polygonPoints = hitbox.stream()
                    .map(point -> point.add(this.getPosition()))
                    .collect(Collectors.toList());
    }
    
}
