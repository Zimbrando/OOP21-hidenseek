package hidenseek.model.components;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import hidenseek.model.components.hearts.HeartComponent;
import hidenseek.model.components.physics.CollisionComponent;
import hidenseek.model.components.physics.MaterialComponent;
import hidenseek.model.components.physics.PositionComponent;
import hidenseek.model.entities.Entity;
import hidenseek.model.entities.Wall;
import javafx.geometry.Point2D;
import javafx.util.Pair;

import static hidenseek.model.components.Utils.*;

public class MapComponentImpl extends AbstractComponent implements MapComponent {
    
    // entity positions mapped so far
    private Set<Point2D> entityPos;
    // grid map
    private Set<Point2D> gameMap;
    // pair<v1,v2> -> path from v2 to v2
    private Map<Pair<Point2D,Point2D>, Set<Point2D>> gamePaths;
    
    /**
     * Creates game map base on this entities
     * @param entities
     */
    public MapComponentImpl() {
        this.gameMap = Set.of();
        this.gamePaths = Map.of();
        this.entityPos = Set.of();
    }
    
    @Override
    public Set<Point2D> getGameMap() {
        return this.gameMap;
    }

    @Override
    public Set<Point2D> getPath(final Point2D source, final Point2D dest) {
        return this.gamePaths.get(new Pair<>(source, dest));
    }

    @Override
    public void mapEntities(final Set<Entity> entities) {
        createMap(entities);
    }
    
    private void createMap(final Set<Entity> entities) {

        // get all materials
        Set<Entity> materials = entities.stream()
        .filter(e -> e.getComponent(PositionComponent.class).isPresent())
        .filter(e -> e.getComponent(MaterialComponent.class).isPresent())
        .filter(e -> e.getComponent(HeartComponent.class).isEmpty()) // no player and so on
        .collect(Collectors.toSet());
        
        // get all materials positions
        Set<Point2D> positions = materials.stream()
                .map(e -> e.getComponent(PositionComponent.class).get().getPosition())
                .collect(Collectors.toSet());
        
        // check if there is nothing to update
        positions = findNewMaterials(positions);
        if(positions.isEmpty()) {
            return;
        }
        // add new positions
        positions = Stream.concat(this.entityPos.stream(), positions.stream())
                .collect(Collectors.toSet());

        // get hitbox of materials
        Set<Point2D> hitboxes = materials.stream()
                .flatMap(e -> e.getComponent(CollisionComponent.class).get().getHitbox().stream().map(p -> p.add(e.getComponent(PositionComponent.class).get().getPosition())))
                .collect(Collectors.toSet());
        
        // bottom right point
        final Point2D brPoint = bottomRight(hitboxes);
        
        // upper left point
        final Point2D ulPoint = upperLeft(hitboxes);
        
        // rowCols fixed
        final int rowCols = 25;
        // grid cells width and height
        final int xJump = Math.max(1,(int)(brPoint.getX() - ulPoint.getX()) / rowCols);
        final int yJump = Math.max(1,(int)(brPoint.getY() - ulPoint.getY()) / rowCols);

        // generate grid positions based on material enities
        Set<Point2D> cellPos = Stream.iterate(new Point2D(ulPoint.getX()-xJump, ulPoint.getY()-yJump), p -> new Point2D(p.getX()+xJump, ulPoint.getY()-yJump))
                .limit(rowCols+2)
                .flatMap(p -> Stream.iterate(p, p2 -> new Point2D(p2.getX(), p2.getY()+yJump)).limit(rowCols+2))
//                .map(p -> new Wall(p,Set.of(new Point2D(0,0), new Point2D(xJump,0), new Point2D(xJump,yJump), new Point2D(0,yJump) )))
                .collect(Collectors.toSet());
        
        // filter only those without material collisions
        cellPos = removeMaterialsPoints(cellPos, materials, xJump, yJump);
        
        // generate graph from grid
        Graph<Point2D, DefaultEdge> graph = generateGraph(cellPos, (p) -> Set.of(
                p.add(new Point2D(xJump, -yJump)), //updx
                p.add(new Point2D(xJump, 0)), // dx
                p.add(new Point2D(xJump, yJump)), // downdx
                p.add(new Point2D(0, yJump)) // down
        ));
        
        // update game paths
        this.gamePaths = getAllPaths(graph);
        
        // update game map
        this.gameMap = cellPos;
        
        //update entities
        this.entityPos = positions;
    }
    
    
    
    /**
     * Remove grid position where there is a collision with a material entity
     * @param materials set of entities with materiaComponent
     * @param cells 
     */
    private Set<Point2D> removeMaterialsPoints(final Set<Point2D> cells, final Set<Entity> materials, final int cellWidth, final int cellHeight) {
     
        return cells.stream()
            .filter(p->{
                // create grid cell entity hitbox
                final Entity e = new Wall(p, Set.of(new Point2D(-cellWidth/2,-cellHeight/2), new Point2D(cellWidth/2,-cellHeight/2), new Point2D(cellWidth/2,cellHeight/2), new Point2D(-cellWidth/2,cellHeight/2) ));
                
                // check if all materials don't collide with this cell
                return materials.stream()
                        .filter(m -> m.getComponent(CollisionComponent.class).isPresent())
                        .allMatch(m -> !m.getComponent(CollisionComponent.class).get().collisionOrNearTo(e, new Point2D(0, 0)));
            })
            .collect(Collectors.toSet());
    }
    
    /**
     * Check whether some materials where not found previously
     * @param materials set of materials positions to check
     * @return
     */
    private Set<Point2D> findNewMaterials(final Set<Point2D> materials) {
        return materials.stream()
            .filter(m -> !this.entityPos.contains(m))
            .collect(Collectors.toSet());
    }
}
