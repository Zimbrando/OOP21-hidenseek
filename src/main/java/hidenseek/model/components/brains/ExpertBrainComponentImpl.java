package hidenseek.model.components.brains;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import hidenseek.model.components.PositionComponent;
import hidenseek.model.components.PositionComponentImpl;
import hidenseek.model.components.hearts.HeartComponent;
import hidenseek.model.components.CollisionComponent;
import hidenseek.model.components.CollisionComponentImpl;
import hidenseek.model.components.Component;
import hidenseek.model.components.Force;
import hidenseek.model.components.MaterialComponent;
import hidenseek.model.components.MoveComponent;
import hidenseek.model.entities.AbstractEntity;
import hidenseek.model.entities.Entity;
import hidenseek.model.entities.Player;
import hidenseek.model.entities.Wall;
import hidenseek.model.enums.Direction;
import hidenseek.model.enums.Heart;
import javafx.geometry.Point2D;

import static hidenseek.model.components.Utils.distanceBetween;


/**
 * 
 * @author Marco Sangiorgi
 *
 */
public class ExpertBrainComponentImpl extends AbstractBrainComponentImpl implements BrainComponent {

    // next position useful when no targets are reachable
    private Optional<Point2D> targetPosition;
    private Set<Entity> cells;
    private Set<Entity> path;
    private Optional<Point2D> targetCell;
    private Optional<Point2D> sourceCell;
    
    public ExpertBrainComponentImpl() {
        // define dependencies
        super();
        this.targetPosition = Optional.empty();
        this.cells = Set.of();
        this.path = Set.of();
        this.targetCell = Optional.empty();
        this.sourceCell = Optional.empty();
        
        // Set heart.GOOD behavior
        // TODO set Heart.GOOD behavior

        // Set heart.EVIL behavior
        setBehaviour(Heart.EVIL
                , e  -> e.getComponent(HeartComponent.class).isPresent()
                        && e.getComponent(PositionComponent.class).isPresent()
                        && this.getOwner().get().getComponent(HeartComponent.class).get().hates(e)
                , (e1,e2) -> distanceBetween(this.getOwner().get(),e1) < distanceBetween(this.getOwner().get(),e2) ? e1 : e2
                , (e1, setE) -> moveTowards(e1, setE));
    }
    
    /**
     * Naive movement. It just move towards the target, regardless of walls.
     * If no target is provide it moves randomly
     * @param target
     */
    private void moveTowards(final Optional<Entity> target, final Set<Entity> entities) {

        //check target
        if(target.isEmpty()) {
            // generate random position
            if(targetPosition.isEmpty()) {
                this.targetPosition = getRandomReachablePosition(entities);
            }
        } else {
            // get target position
            final PositionComponent posTarget = target.get().getComponent(PositionComponent.class).get();
            // update target position
            this.targetPosition = Optional.of(posTarget.getPosition());
        }
        // if no target position is found
        if(targetPosition.isEmpty()) {
            return;
        }
        // find shortest path to target position
        final Optional<Point2D> nextPosition = findShortestPath(target.orElse(createGhostEntity(targetPosition.get(),Set.of(new Point2D(-1,-1), new Point2D(1,-1), new Point2D(1,1), new Point2D(-1,1) ))), entities);
        // if no path is found
        if(nextPosition.isEmpty()) {
            return;
        }
        // get movement component
        final PositionComponent position = this.getOwner().get().getComponent(PositionComponent.class).get();
        final MoveComponent movement = this.getOwner().get().getComponent(MoveComponent.class).get();
        // move based on target position

        double moveXIntensity = 0;
        Direction moveXDirection = Direction.DOWN;
        
        double moveYIntensity = 0;
        Direction moveYDirection = Direction.DOWN;
        
        if(nextPosition.get().getX() > position.getPosition().getX()) {
            moveXDirection = Direction.RIGHT;
            moveXIntensity = 2.5;
        } else if(nextPosition.get().getX() < position.getPosition().getX()) {
            moveXDirection = Direction.LEFT;
            moveXIntensity = 2.5;
        }
        if(nextPosition.get().getY() > position.getPosition().getY()) {
            moveYDirection = Direction.DOWN;
            moveYIntensity = 2.5;
        } else if(nextPosition.get().getY() < position.getPosition().getY()) {
            moveYDirection = Direction.UP;
            moveYIntensity = 2.5;
        }


        movement.removeForce(force -> force.getIdentifier().startsWith("ai"));
        movement.addForce(new Force("ai-horizontal", moveXIntensity, moveXDirection.getValue()));
        movement.addForce(new Force("ai-vertical", moveYIntensity, moveYDirection.getValue()));

    }

    private Optional<Point2D> getRandomReachablePosition(final Set<Entity> entities) {
        //TODO use entities (walls)
        
        
        return Optional.empty();
    }
    
    /**
     * Find shortest path to reach target, 
     * avoiding entities that respect the avoidCheck predicate.
     * @return
     */
    private Optional<Point2D> findShortestPath(final Entity target, final Set<Entity> entities) {

        
        // create ghost target
        Entity owner = this.getOwner().get();
        
        Set<Point2D> hitbox = owner.getComponent(CollisionComponent.class).get().getHitbox();
//        final Entity target = createGhostEntity(targetPosition,hitbox);

//                createGhostEntity(this.getOwner().get().getComponent(PositionComponent.class).get().getPosition(),
//                Set.of(new Point2D(-1,-1), new Point2D(1,-1), new Point2D(1,1), new Point2D(-1,1) )
//                );
//        entities.remove(target);
        
        
        // get all materials
        Set<Entity> materials = Stream.concat(Stream.of(owner, target), entities.stream()
        .filter(e -> e.getComponent(PositionComponent.class).isPresent())
        .filter(e -> e.getComponent(MaterialComponent.class).isPresent()))
        .collect(Collectors.toSet());
        
        
//        materials.forEach(System.out::println);
        
        // get all materials positions
        Set<Point2D> positions = materials.stream()
                .map(e -> e.getComponent(PositionComponent.class).get().getPosition())
                .collect(Collectors.toSet());
        
        
        // check it there no materials
        if(positions.isEmpty()) {
            return Optional.empty();
        }

//        positions.forEach(p -> System.out.println(p));
        

            // bottom right point
        final Point2D brPoint = positions.stream().reduce(new Point2D(Integer.MIN_VALUE,Integer.MIN_VALUE), (p1,p2) -> new Point2D(Math.max(p1.getX(), p2.getX()),Math.max(p1.getY(), p2.getY())));

            // upper left point
        final Point2D ulPoint = positions.stream().reduce(new Point2D(Integer.MAX_VALUE, Integer.MAX_VALUE), (p1,p2) -> new Point2D(Math.min(p1.getX(), p2.getX()),Math.min(p1.getY(), p2.getY())));
        
        // rowCols fixed
        final int rowCols = 7;
        
        final int xJump = Math.max(1,(int)(brPoint.getX() - ulPoint.getX()) / rowCols);
        final int yJump = Math.max(1,(int)(brPoint.getY() - ulPoint.getY()) / rowCols);
        

        
        
        //        // cell size fixed
//        final Point2D ownerBrPoint = hitbox.stream().reduce(new Point2D(Integer.MIN_VALUE,Integer.MIN_VALUE), (p1,p2) -> new Point2D(Math.max(p1.getX(), p2.getX()),Math.max(p1.getY(), p2.getY())));
//
//            // upper left point
//        final Point2D ownerUlPoint = hitbox.stream().reduce(new Point2D(Integer.MAX_VALUE, Integer.MAX_VALUE), (p1,p2) -> new Point2D(Math.min(p1.getX(), p2.getX()),Math.min(p1.getY(), p2.getY())));
//        
//        int cellWidth = (int)(ownerBrPoint.getX() - ownerUlPoint.getX()) * 3/ 4;
//        
//        final int rowCols = Math.max(1,(int)((brPoint.getX() - ulPoint.getX()) / cellWidth));
//        
//        final int xJump = Math.max(1,cellWidth);
//        final int yJump = Math.max(1,cellWidth);


//        CollisionComponent col = this.getOwner().get().getComponent(CollisionComponent.class).get();
//        col.getHitbox().forEach(p -> col.removeHitboxPoint(p));
//        Set.of(new Point2D(0,0), new Point2D(xJump,0), new Point2D(xJump,yJump), new Point2D(0,yJump) )
//        .forEach(p -> coll.addHitboxPoint(p));
        // generate grid position of enities sensed

        final Set<Point2D> cells = Stream.iterate(new Point2D(ulPoint.getX()-xJump, ulPoint.getY()-yJump), p -> new Point2D(p.getX()+xJump, ulPoint.getY()-yJump))
            .limit(rowCols+2)
            .flatMap(p -> Stream.iterate(p, p2 -> new Point2D(p2.getX(), p2.getY()+yJump)).limit(rowCols+2))
            .collect(Collectors.toSet());
        

        final SimpleGraph<Point2D, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        // traverse grid and link graph
        cells.stream().forEach(p -> {
            graph.addVertex(p);
        });
        cells.stream().forEach(p -> {
            Point2D offset;
            // updx
            offset = p.add(new Point2D(xJump, -yJump));
            if(cells.contains(offset)) {
                graph.addEdge(p, offset);
            }
            // dx
            offset = p.add(new Point2D(xJump, 0));
            if(cells.contains(offset)) {
                graph.addEdge(p, offset);
            }
            // down dx
            offset = p.add(new Point2D(xJump, yJump));
            if(cells.contains(offset)) {
                graph.addEdge(p, offset);
            }
            // down
            offset = p.add(new Point2D(0, yJump));
            if(cells.contains(offset)) {
                graph.addEdge(p, offset);
            }
        });
        
        
//        cells.forEach(c -> System.out.println("before:"+ c));
        this.targetCell = Optional.ofNullable(null);
        this.sourceCell = Optional.ofNullable(null);
        
        // remove grid position where there is a material entity
        this.cells = cells.stream()
            .map(c -> {
                    // create fake entity
                    return new Wall(c, Set.of(new Point2D(0,0), new Point2D(xJump,0), new Point2D(xJump,yJump), new Point2D(0,yJump) ));
                    
             })
            .filter(e->{
                
                    // check if all materials don't collide with this cell
                final boolean out = materials.stream().filter(m -> !m.equals(target) && !m.equals(owner)).allMatch(m -> !m.getComponent(CollisionComponent.class).get().collisionWith(e));
                if(!out) {
                    graph.removeVertex(e.getComponent(PositionComponent.class).get().getPosition());
                    return false;
                }

                if(distanceBetween(e, target) < 27) {
//                if(e.getComponent(CollisionComponent.class).get().collisionWith(target)) {
                    this.targetCell = Optional.of(e.getComponent(PositionComponent.class).get().getPosition());
                    return true;
                }
              if(distanceBetween(e, owner) < 27) {
//                if(e.getComponent(CollisionComponent.class).get().collisionWith(owner)) {
                    this.sourceCell = Optional.of(e.getComponent(PositionComponent.class).get().getPosition());
                    return true;
                }
                return out;
            })
            .collect(Collectors.toSet());
        
        if(targetCell.isEmpty() || this.sourceCell.isEmpty()) {
            //System.out.println("target empty");
            return Optional.empty();
        }

        final Optional<GraphPath<Point2D, DefaultEdge>> path = Optional.ofNullable(new DijkstraShortestPath<>(graph).getPath(this.sourceCell.get(),this.targetCell.get()));
         
         if(path.isEmpty()) {
             //System.out.println("path empty");
             return Optional.empty();
         }
         Set<Entity> pa = path.get()
                .getVertexList()
                .stream()
                .map(c -> {
                    // create fake entity
                    return new Wall(c, Set.of(new Point2D(0,0), new Point2D(xJump,0), new Point2D(xJump,yJump), new Point2D(0,yJump) ));
                    
                })
                
                // filter some sources
                .filter(e-> distanceBetween(e, owner) > 27)
                .collect(Collectors.toSet());
         
         Optional<Entity> nextCell = pa.stream().reduce((c1,c2) -> {
                     return  !c1.getComponent(CollisionComponent.class).get().collisionWith(owner)
                             && distanceBetween(c1, this.getOwner().get()) < distanceBetween(c2, owner) 
                             ? c1
                             : c2;
         });
         this.path = pa;
         if(nextCell.isEmpty()) {
             return Optional.empty();
         }
         
         return Optional.of(nextCell.get().getComponent(PositionComponent.class).get().getPosition());
         
        
        // 
//        cells.forEach(System.out::println);
//        cells.stream().map(e -> graph.addVertex(e));


//        cells.forEach(p -> System.out.println(p));
        
        
    }
    
    private Entity createGhostEntity(final Point2D position, Set<Point2D> hitbox) {
        return new Wall(position, hitbox);
    }

    @Override
    public Set<Entity> cells() {
        // TODO Auto-generated method stub
        return this.cells;
    }

    @Override
    public Set<Entity> path() {
        // TODO Auto-generated method stub
        return this.path;
    }
    
}

