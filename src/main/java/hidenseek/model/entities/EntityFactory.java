package hidenseek.model.entities;

import javafx.geometry.Point2D;

public interface EntityFactory {

    Entity createPlayer(Point2D position, int playerSpeed);
    
    Entity createWall(Point2D position);
    
    Entity createEnemy(Point2D position);
    
    Entity createPowerup(Point2D position);
}
