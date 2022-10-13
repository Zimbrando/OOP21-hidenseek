package hidenseek.model;

import java.util.LinkedHashSet;
import java.util.Set;

import hidenseek.model.entities.Entity;
import hidenseek.model.entities.Wall;
import javafx.geometry.Point2D;

public class GameLevelImpl implements GameLevel {

    @Override
    public Set<Entity> getWalls() {
        
        return new LinkedHashSet<Entity>(){{
            add(new Wall(new Point2D(48, 220), 
                    new LinkedHashSet<Point2D>(){{
                        add(new Point2D(0, 0));
                        add(new Point2D(118, 0));
                        add(new Point2D(118, 17));
                        add(new Point2D(0, 17));
                    }}
            ));
            
            add(new Wall(new Point2D(461, 45), 
                    new LinkedHashSet<Point2D>(){{
                        add(new Point2D(175, 243));
                        add(new Point2D(175, 17));
                        add(new Point2D(197, 17));
                        add(new Point2D(197, 0));
                        add(new Point2D(79, 0));
                        add(new Point2D(79, 17));
                        add(new Point2D(158, 17));
                        add(new Point2D(158, 176));
                        add(new Point2D(96, 176));
                        add(new Point2D(96, 92));
                        add(new Point2D(117, 92));
                        add(new Point2D(117, 75));
                        add(new Point2D(0, 75));
                        add(new Point2D(0, 92));
                        add(new Point2D(80, 92));
                        add(new Point2D(80, 192));
                        add(new Point2D(157, 192));
                        add(new Point2D(157, 243));
                    }}
            ));
            
            add(new Wall(new Point2D(217, 248), 
                    new LinkedHashSet<Point2D>(){{
                        add(new Point2D(127, 213));
                        add(new Point2D(127, 29));
                        add(new Point2D(110, 29));
                        add(new Point2D(110, 110));
                        add(new Point2D(37, 110));
                        add(new Point2D(37, 0));
                        add(new Point2D(20, 0));
                        add(new Point2D(20, 110));
                        add(new Point2D(0, 110));
                        add(new Point2D(0, 127));
                        add(new Point2D(110, 127));
                        add(new Point2D(110, 213));
                    }}
            ));
            
            add(new Wall(new Point2D(0, 75), 
                    new LinkedHashSet<Point2D>(){{
                        add(new Point2D(234, 118));
                        add(new Point2D(234, 0));
                        add(new Point2D(0, 0));
                        add(new Point2D(0, 17));
                        add(new Point2D(217, 17));
                        add(new Point2D(217, 118));
                    }}
            ));
            
            add(new Wall(new Point2D(288, 0), 
                    new LinkedHashSet<Point2D>(){{
                        add(new Point2D(0, 0));
                        add(new Point2D(17, 0));
                        add(new Point2D(17, 118));
                        add(new Point2D(0, 118));
                    }}
            ));
            
            add(new Wall(new Point2D(308, 101), 
                    new LinkedHashSet<Point2D>(){{
                        add(new Point2D(127, 308));
                        add(new Point2D(127, 110));
                        add(new Point2D(60, 110));
                        add(new Point2D(60, 0));
                        add(new Point2D(43, 0));
                        add(new Point2D(43, 110));
                        add(new Point2D(0, 110));
                        add(new Point2D(0, 127));
                        add(new Point2D(110, 127));
                        add(new Point2D(110, 308));
                    }}
            ));
            
            add(new Wall(new Point2D(27, 0), 
                    new LinkedHashSet<Point2D>(){{
                        add(new Point2D(0, 0));
                        add(new Point2D(220, 0));
                        add(new Point2D(220, 17));
                        add(new Point2D(0, 17));
                    }}
            ));
        }};
    }
   
}
