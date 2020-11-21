package de.hawh.hajs.gka01.util;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This utility class provides methods for path finding.
 *
 * @author Hani Alshikh
 * @author Jannik Stuckstätte
 */
public final class TraversalHelper {

    // private constructor to avoid instantiation
    private TraversalHelper() {
        throw new IllegalStateException(
            String.format("%s cannot be instantiated!", GraphParser.class.getName()));
    }

    /**
     * This function calculates the steps to every other Node in the given graph using a
     * breadth-first-search and returns the steps needed to get to the given endNode.
     *
     * @param startNodeId The nodeId from which the search starts.
     * @param endNodeId   The nodeId to count the steps of the shortest path to - beginning from the
     *                    given startNode.
     * @return The HashMap of every Node and the steps to take from startNode to get there.
     * @throws NoSuchElementException If one of the given nodeIds could not be found in the given
     *                                graph.
     */
    public static int getStepsBFS(Graph graph, String startNodeId, String endNodeId)
        throws NoSuchElementException {
        ensureNodeIsPresent(graph, startNodeId);
        ensureNodeIsPresent(graph, endNodeId);

        Node startNode = graph.getNode(startNodeId);
        Node endNode = graph.getNode(endNodeId);


        Optional<HashMap<String, Integer>> stepsFromStart = bfs(startNode, endNode);

        return stepsFromStart.isPresent() ? stepsFromStart.get().get(endNodeId) : -1;
    }

    /**
     * This method returns the shortest path from the node identified by startNodeId to the node
     * identified by endNodeId on the given graph. If either startNodeId or endNodeId could not be
     * found in the given graph it throws an NoSuchElementException.
     *
     * @param startNodeId The nodeId from which to start from.
     * @param endNodeId   The nodeId to which to search a path to.
     * @return Optional of the nodes passed while traversing the shortest path starting from the
     * startNode on index 0 or empty Optional if no path was found.
     * @throws NoSuchElementException If one of the given nodeIds could not be found in the given
     *                                graph.
     */
    public static Optional<String[]> getShortestPathBFS(Graph graph, String startNodeId,
        String endNodeId) throws NoSuchElementException {

        // Checking that given nodeIds are in fact part of the given graph
        ensureNodeIsPresent(graph, startNodeId);
        ensureNodeIsPresent(graph, endNodeId);

        Node startNode = graph.getNode(startNodeId);
        Node endNode = graph.getNode(endNodeId);


        // Using bfs to get the steps to endNode and nodes traversed before that
        Optional<HashMap<String, Integer>> stepsToEndNodeOpt = bfs(startNode, endNode);

        // Check if there even is a path
        if (stepsToEndNodeOpt.isEmpty()) {
            return Optional.empty();
        }

        // Unwrap optional for better readability
        HashMap<String, Integer> stepsToEndNode = stepsToEndNodeOpt.get();

        // Initialize resulting path array. We know the path length should be the steps to endNode.
        String[] pathToEnd = new String[stepsToEndNode.get(endNode.getId()) + 1];

        // Setting last step in path to endNode
        pathToEnd[pathToEnd.length - 1] = endNodeId;
        // Initialize currentNode for backward traversal to endNode
        Node currentNode = graph.getNode(endNodeId);

        // Filling the pathToEnd Array from back to front. Skipping the last index because its already filled.
        for (int i = pathToEnd.length - 2; i >= 0; i--) {
            // Effectively final variables for usage inside stream
            int finalI = i;
            Node finalCurrentNode = currentNode;

            Optional<Node> currentNodeOpt;

            // Getting next node on shortest path
            currentNodeOpt = currentNode.enteringEdges()
                .map(
                    edge -> edge.getNode0() == finalCurrentNode ? edge.getNode1()
                        : edge.getNode0())
                .filter(node -> stepsToEndNode.containsKey(node.getId())
                    && stepsToEndNode.get(node.getId()) == finalI)
                .findFirst();

            // If no next node on path could be found something went wrong
            if (currentNodeOpt.isEmpty()) {
                return Optional.empty();
            }

            // Setting found node as current node for traversal
            currentNode = currentNodeOpt.get();
            // Setting found node to next node on path
            pathToEnd[i] = currentNode.getId();
        }

        return Optional.of(pathToEnd);
    }

    /**
     * Private implementation of a breadth-first-search to be used by the path finding methods of
     * this class.
     *
     * @param startNode The node from which to start the search from.
     * @param endNode   The node to which to search a path to.
     * @return An optional containing a HashMap which assigns the steps needed from startNode to
     * every node visited. If no path from startNode to endNode was found the optional is empty.
     */
    private static Optional<HashMap<String, Integer>> bfs(Node startNode, Node endNode) {
        // Initializing result HashMap
        HashMap<String, Integer> stepsToNodes = new HashMap<>();
        // Adding the start node with 0 steps
        stepsToNodes.put(startNode.getId(), 0);

        if (startNode.getId().equals(endNode.getId())) {
            return Optional.of(stepsToNodes);
        }

        List<Node> adjacentNewNodes = startNode.leavingEdges()
            // Getting the "other" node of incident edges
            .map(edge -> edge.getNode0() == startNode ? edge.getNode1() : edge.getNode0())
            // Getting unique values
            .collect(Collectors.toSet()).stream()
            // Filter nodes already seen
            .filter(node -> stepsToNodes.get(node.getId()) == null)
            .collect(Collectors.toList());

        for (int i = 1; adjacentNewNodes.size() > 0; i++) {
            for (Node currentNode : adjacentNewNodes) {
                stepsToNodes.put(currentNode.getId(), i);

                if (currentNode == endNode) {
                    return Optional.of(stepsToNodes);
                }
            }
            adjacentNewNodes = adjacentNewNodes.stream()
                .flatMap(node -> node.leavingEdges().map(edge -> edge.getNode0() == node ? edge.getNode1() : edge.getNode0()))
                .collect(Collectors.toSet()).stream()
                .filter(node -> stepsToNodes.get(node.getId()) == null)
                .collect(Collectors.toList());
        }

        return Optional.empty();
    }

    /**
     * This method checks if the given nodeId can be found in the given graph. If it cannot be found
     * a NoSuchElementException is thrown.
     *
     * @param graph  The graph in which to search for the given nodeId.
     * @param nodeId The nodeId to search for in the given graph.
     * @throws NoSuchElementException Thrown if nodeId could not be found in graph.
     */
    private static void ensureNodeIsPresent(Graph graph, String nodeId)
        throws NoSuchElementException {
        if (graph.getNode(nodeId) == null) {
            throw new NoSuchElementException(
                String.format("No such element in graph: %s", nodeId));
        }
    }
}
