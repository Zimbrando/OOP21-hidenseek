package hidenseek.model;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import hidenseek.model.components.InputHandlerComponent;
import hidenseek.model.entities.Entity;
import hidenseek.model.entities.Wall;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;

public class GameLevelImpl implements GameLevel {

    @Override
    public Set<Entity> getWalls() {
        
        return new LinkedHashSet<Entity>(){{
            add(new Wall(new Point2D(650, 100), 
                    new LinkedHashSet<Point2D>(){{
                        add(new Point2D(0, 0));
                        add(new Point2D(200, 0));
                        add(new Point2D(200, 40));
                        add(new Point2D(0, 40));
                    }}
            ));
            
            add(new Wall(new Point2D(350, 130), 
                    new LinkedHashSet<Point2D>(){{
                        add(new Point2D(0, 0));
                        add(new Point2D(60, 0));
                        add(new Point2D(60, 300));
                        add(new Point2D(0, 300));
                    }}
            ));

            add(new Wall(new Point2D(300, 500), 
                    new LinkedHashSet<Point2D>(){{
                        add(new Point2D(0, 0));
                        add(new Point2D(300, 0));
                        add(new Point2D(300, 150));
                        add(new Point2D(260, 150));
                        add(new Point2D(260, 40));
                        add(new Point2D(0, 40));
                    }}
            ));
        }};
    }
   
}
