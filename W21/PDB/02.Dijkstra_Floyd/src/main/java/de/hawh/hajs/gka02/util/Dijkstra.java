package de.hawh.hajs.gka02.util;

import de.hawh.hajs.gka02.factory.ExtendedNode;
import org.graphstream.graph.Node;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**********************************************************************
 *
 * This utility class provides a private implementation of Dijkstra algorithm
 * and a method to reconstruct the shortest path.
 *
 * @author Jannik Stuckstätte
 * @author Hani Alshikh
 *
 ************************************************************************/
public final class Dijkstra {

    private static final AtomicInteger accessCounter = new AtomicInteger(0);

    /**
     * This method returns the shortest path from the node identified by sourceNode to the node
     * identified by targetNode in the containing graph. If either sourceNode or targetNode could
     * not be found in the sourceNode containing graph it throws an NoSuchElementException.
     *
     * @param sourceNode The source Node to start from.
     * @param targetNode The Target Node to search a path to.
     * @return Optional of the nodes passed while traversing the shortest path starting from the
     * sourceNode on index 0 or empty Optional if no path was found.
     * @throws NoSuchElementException If one of the given nodes could not be found in the graph
     */
    public static Optional<List<Node>> getShortestPath(Node sourceNode, Node targetNode) {
        // make sure nodes are present and from the same graph
        TraversalHelper.ensureNodeIsPresent(sourceNode.getGraph(), sourceNode.getId(), targetNode.getId());
        // using an extended implementation of Graphstream MultiNode as a "data structure"
        ExtendedNode source = (ExtendedNode) sourceNode, target = (ExtendedNode) targetNode;
        // calculate the path using private dijkstra implementation
        dijkstra(source, target);
        // if the target node has no predecessor we know there is no path
        if (target.getPredecessor() == null) return Optional.empty();
        // construct the path list
        List<Node> path = new ArrayList<>();
        for (ExtendedNode current = target; current != null; current = current.getPredecessor())
            path.add(current);
        Collections.reverse(path);
        return Optional.of(path);
    }

    /**
     * Private implementation of the dijkstra algorithm to be used by the path finding methods of
     * this class.
     *
     * @param sourceNode The node from which to start the search from.
     * @param targetNode The node to which to search a path to.
     */
    private static void dijkstra(ExtendedNode sourceNode, ExtendedNode targetNode) {
        // make sure to recalculate each time
        sourceNode.getGraph().nodes().forEach(n -> ((ExtendedNode) n).reset());
        resetAccessCounter();
        // initialize the starting node with 0
        sourceNode.setDistance(0);
        // by using a PriorityQueue we can skip
        // - tagging nodes as visited (by popping the visited ones)
        // - choosing the node with smallest distance (as they are prioritized first)
        PriorityQueue<ExtendedNode> toProcessNodes = new PriorityQueue<>();
        // add the source node to start the iteration
        toProcessNodes.add(sourceNode);

        while (!toProcessNodes.isEmpty()) {
            accessCounter.incrementAndGet();
            // mark it as visited (remove it from the queue)
            ExtendedNode currentNode = toProcessNodes.poll();
            // check if we are currently visiting the target node and break
            if (currentNode == targetNode) break; // no need to calculate the whole graph

            // otherwise go through all neighbor nodes
            currentNode.leavingEdges().forEach(e -> {
                accessCounter.incrementAndGet();
                // getOpposite will make sure to get the target/neighbor end of the edge
                ExtendedNode neighborNode = (ExtendedNode) e.getOpposite(currentNode);
                // calculate the new distance
                if (!e.hasAttribute("weight")) throw new IllegalArgumentException(e+" has no weight");
                double weight = e.getAttribute("weight", Double.class);
                if (weight < 0) throw new IllegalArgumentException(e+" has negativ weight");
                double newDistance = currentNode.getDistance() + weight;

                // and check if it's smaller then the old distance
                if (newDistance < neighborNode.getDistance()) {
                    // remove and add to re-prioritize the queue
                    // after changing the values
                    toProcessNodes.remove(neighborNode);
                    neighborNode.setDistance(newDistance);
                    neighborNode.setPredecessor(currentNode);
                    toProcessNodes.add(neighborNode);
                }
            });
        }
    }

    // region ******** getter and setter ********

    public static int getAccessCounter() {
        return accessCounter.get();
    }

    public static void resetAccessCounter() {
        accessCounter.getAndSet(0);
    }

    // endregion

    // private constructor to avoid instantiation
    private Dijkstra() {
        throw new IllegalStateException(
                String.format("%s cannot be instantiated!", GraphParser.class.getName()));
    }
}
