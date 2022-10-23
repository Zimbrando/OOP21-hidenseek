package hidenseek.model.components;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm.SingleSourcePaths;
import org.jgrapht.alg.shortestpath.FloydWarshallShortestPaths;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import hidenseek.model.components.physics.PositionComponent;
import hidenseek.model.entities.Entity;
import javafx.geometry.Point2D;
import javafx.util.Pair;

final public class Utils {
    
    private Utils() {
    }
    
    static public double distanceBetween(final Entity e1, final Entity e2) {
        // check position component
        if(e1.getComponent(PositionComponent.class).isEmpty()
        || e2.getComponent(PositionComponent.class).isEmpty()) {
            return -1;
        }
        // get position components
        final PositionComponent pos1 = e1.getComponent(PositionComponent.class).get();
        final PositionComponent pos2 = e2.getComponent(PositionComponent.class).get();
        return pos1.getPosition().distance(pos2.getPosition());
    }
    

    /**
     * Generate a simple graph where each nodes is connected to its nearby
     * @return
     */
    static public <V> Graph<V, DefaultEdge> generateGraph(final Set<V> cells, final Function<V, Set<V>> connectTo) {
        
        final SimpleGraph<V,DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        // traverse grid and link graph
        cells.stream().forEach(p -> {
            graph.addVertex(p);
        });
        cells.stream().forEach(p -> {
            connectTo.apply(p).stream()
                .filter(pnear -> cells.contains(pnear))
                .forEach(pnear -> graph.addEdge(p, pnear));
        });
        
        return graph;
    }
    

    /**
     * Generate all path from all nodes of the given graph
     * @return
     */
    static public <V> Map<Pair<V,V>,Set<V>> getAllPaths(final Graph<V, DefaultEdge> graph) {
        final Map<Pair<V,V>, Set<V>> map = new HashMap<>();
        final FloydWarshallShortestPaths<V, DefaultEdge> fl = new FloydWarshallShortestPaths<>(graph);
        
//        DijkstraShortestPath<V, DefaultEdge> d = new DijkstraShortestPath<>(graph);
        // get all path from each source vertex
        graph.vertexSet().stream()
            .forEach(v1 -> { // foreach source
                final SingleSourcePaths<V, DefaultEdge> paths = fl.getPaths(v1);
                // iterate over all again
                graph.vertexSet().stream()
                    .forEach(v2 -> // foreach sink
                {
                    // get all path from v1 to v2
                    Set<V> path = Set.of();
                    if(Optional.ofNullable(paths.getPath(v2)).isPresent()) {
                        path = paths.getPath(v2).getVertexList().stream().collect(Collectors.toSet());
                    }
                    map.put(new Pair<>(v1,v2), path);
                }
                    );
            });
        return map;
    }
    
    /**
     * Retrieve upper left vertex of a set o point
     * @param points
     * @return upper left point
     */
    static public Point2D upperLeft(final Set<Point2D> points) {
        return points.stream().reduce(new Point2D(Integer.MAX_VALUE, Integer.MAX_VALUE), (p1,p2) -> new Point2D(Math.min(p1.getX(), p2.getX()),Math.min(p1.getY(), p2.getY())));
    }

    /**
     * Retrieve bottom right vertex of a set o point
     * @param points
     * @return bottom right point
     */
    static public Point2D bottomRight(final Set<Point2D> points) {
        return points.stream().reduce(new Point2D(Integer.MIN_VALUE,Integer.MIN_VALUE), (p1,p2) -> new Point2D(Math.max(p1.getX(), p2.getX()),Math.max(p1.getY(), p2.getY())));
    }
    
    
}