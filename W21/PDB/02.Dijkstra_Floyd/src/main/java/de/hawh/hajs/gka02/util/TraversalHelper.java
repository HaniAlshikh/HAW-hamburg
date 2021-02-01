package de.hawh.hajs.gka02.util;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * This utility class provides methods for path finding.
 *
 * @author Hani Alshikh
 * @author Jannik Stuckstätte
 */
public final class TraversalHelper {

    public enum Algorithm {
        DIJKSTRA,
        FLOYD_WARSHALL
    }

    private static FloydWarshall floydWarshall;

    /**
     * This method gets the shortest path between the source node and
     * the target node using the provided algorithm
     *
     * @param sourceNode The node from which to start the search from.
     * @param targetNode The node to which to search a path to.
     * @param algorithm the algorithm to be used
     * @return optional of the shortest path
     */
    public static Optional<List<Node>> getShortestPath(
        Node sourceNode, Node targetNode, Algorithm algorithm) {
        switch (algorithm) {
            case DIJKSTRA:
                return Dijkstra.getShortestPath(sourceNode, targetNode);
            case FLOYD_WARSHALL:
                if (floydWarshall == null || floydWarshall.getGraph() != sourceNode.getGraph())
                    floydWarshall = new FloydWarshall(sourceNode.getGraph());
                return floydWarshall.getShortestPath(sourceNode, targetNode);
        }
        return Optional.empty();
    }

    /**
     * This method return the access counter value for the algorithm provided
     *
     * @param algorithm the algorithm to be used
     * @return number of times the graph was accessed
     */
    public static int getAccessCounter(Algorithm algorithm) {
        switch (algorithm) {
            case FLOYD_WARSHALL:
                if (floydWarshall != null)
                    return floydWarshall.getGraphAccessCounter();
                return 0;
            case DIJKSTRA:
                return Dijkstra.getAccessCounter();
            default:
                return 0;
        }
    }

    /**
     * This method checks if the given nodeId|s can be found in the given graph. If it cannot be
     * found a NoSuchElementException is thrown.
     *
     * @param graph   The graph in which to search for the given nodeId.
     * @param nodeIds The nodeId to search for in the given graph.
     * @throws NoSuchElementException Thrown if nodeId could not be found in graph.
     */
    public static void ensureNodeIsPresent(Graph graph, String... nodeIds)
        throws NoSuchElementException {
        for (String nodeId : nodeIds) {
            if (graph.getNode(nodeId) == null) {
                throw new NoSuchElementException(
                    String.format("No such element in graph: %s", nodeId));
            }
        }
    }


    /**
     * calculate the path weight of a list of nodes/the path
     *
     * @param path a list of nodes
     * @return double value of the weight
     */
    public static double getPathWeight(List<Node> path) {
        double pathWeightSum = 0.0;
        for (int i = 1; i < path.size(); i++) {
            Node currentNode = path.get(i - 1);
            Node nextNode = path.get(i);
            Optional<Edge> currentEdgeOpt = currentNode.leavingEdges()
                    .filter(edge -> edge.getOpposite(currentNode) == nextNode)
                    .min(Comparator.comparing(e->e.getAttribute("weight", Double.class)));
            pathWeightSum += currentEdgeOpt.orElseThrow().getAttribute("weight", Double.class);
        }
        return Math.round(pathWeightSum * 100D) / 100D;
    }

    // private constructor to avoid instantiation
    private TraversalHelper() {
        throw new IllegalStateException(
                String.format("%s cannot be instantiated!", GraphParser.class.getName()));
    }

}
