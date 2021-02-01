package de.hawh.hajs.gka02.util;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**********************************************************************
 *
 * This class provides a private implementation of Floyd Warshall algorithm
 * and a method to reconstruct the shortest path.
 *
 * @author Jannik Stuckstätte
 * @author Hani Alshikh
 *
 ************************************************************************/
public final class FloydWarshall {
    private final Graph graph;
    private final int nodeCount;
    private final double[][] distanceMatrix;
    private final int[][] transitionMatrix;
    private boolean computed;
    private boolean negativeCycle;
    private int graphAccessCounter;

    public FloydWarshall(Graph graph) {
        this.graph = graph;
        this.nodeCount = graph.getNodeCount();
        this.distanceMatrix = new double[nodeCount][nodeCount];
        this.transitionMatrix = new int[nodeCount][nodeCount];
    }

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
    public Optional<List<Node>> getShortestPath(Node sourceNode, Node targetNode) throws IllegalArgumentException {
        // make sure nodes are present and from the same graph
        TraversalHelper.ensureNodeIsPresent(graph, sourceNode.getId(), targetNode.getId());

        if (!computed) {
            // calculate the path using private Floyd Warshall implementation
            compute();
        }
        if (negativeCycle) {
            // no path found if Negative Cycle was found
            throw new IllegalArgumentException();
        }

        // no path found if node index between source and target node is infinity
        int startNodeIndex = sourceNode.getIndex();
        int endNodeIndex = targetNode.getIndex();
        if (distanceMatrix[startNodeIndex][endNodeIndex] == Double.POSITIVE_INFINITY)
            return Optional.empty();

        // construct the path list
        List<Integer> shortestPathIndices = buildPath(startNodeIndex,
                endNodeIndex);
        List<Node> shortestPathNodes = shortestPathIndices.stream()
                .map(graph::getNode)
                .collect(Collectors.toList());

        return Optional.of(shortestPathNodes);
    }

    /**
     * helper method to recursively construct the path using a transition matrix
     * calculated with the floyd warshall algorithm.
     *
     * Negative Cycle and index check should be done before using the method
     *
     * @param sourceNodeIndex The source Node index to start from.
     * @param targetNodeIndex The Target Node index to search a path to.
     * @return Optional of the nodes passed while traversing the shortest path starting from the
     * sourceNode on index 0 or empty Optional if no path was found.
     * @throws NoSuchElementException If one of the given nodes could not be found in the graph
     */
    private List<Integer> buildPath(int sourceNodeIndex, int targetNodeIndex) {
        int transitNodeIndex = transitionMatrix[sourceNodeIndex][targetNodeIndex];
        // recursion break point
        if (transitNodeIndex < 0) return List.of(sourceNodeIndex, targetNodeIndex);
        // build to the transition node
        List<Integer> pathToTransitNode = buildPath(sourceNodeIndex,
                transitNodeIndex);
        // build from the transition node
        List<Integer> pathFromTransitNode = buildPath(transitNodeIndex,
                targetNodeIndex);

        // merge both paths and return them
        return Stream.concat(pathToTransitNode.stream(), pathFromTransitNode.stream().skip(1))
                .collect(Collectors.toList());
    }

    /**
     * Private implementation of the Floyd Warshall algorithm
     * to be used by the path finding methods of this class.
     */
    public void compute() {
        // initialize distance and transition matrix
        initializeDistanceMatrix();
        // "Für j=1,...,|V|:"
        for (int j = 0; j < nodeCount; j++) {
            // "Für i=1,...,|V|:"
            for (int i = 0; i < nodeCount; i++) {
                // i != j
                if (i == j) continue;
                // "Für k=1,...,|V|"
                for (int k = 0; k < nodeCount; k++) {
                    // k != j
                    if (k == j) continue;
                    if (distanceMatrix[i][j] + distanceMatrix[j][k]
                            < distanceMatrix[i][k]) {
                        // "Setze dik:=min{dik, dij + djk}"
                        distanceMatrix[i][k] =
                                distanceMatrix[i][j] + distanceMatrix[j][k];
                        // "Falls dik verändert wurde, setze tik := j."
                        transitionMatrix[i][k] = j;
                    }
                    graphAccessCounter++;
                }
                // "Falls dii<0 ist, brich den Algorithmus vorzeitig ab."
                if (distanceMatrix[i][i] < 0) {
                    this.negativeCycle = true;
                    this.computed = true;
                    return;
                }
            }
        }
        this.computed = true;
    }

    /**
     * helper method to calculate/initialize the distance and transition matrix
     * for a given graph
     */
    private void initializeDistanceMatrix() {
        for (int i = 0; i < nodeCount; i++) {
            for (int j = 0; j < nodeCount; j++) {
                // 0 fur i = j
                if (i == j) distanceMatrix[i][j] = 0; // node loops
                else {
                    Node nodeI = graph.getNode(i), nodeJ = graph.getNode(j);
                    distanceMatrix[i][j] = nodeI.leavingEdges()
                            // make sure we consider all edges from vi to vj
                            .filter(e -> e.getOpposite(nodeI) == nodeJ)
                            // lij for vivj ∈ E und i , j (fall back to 1 if e has no weight)
                            .mapToDouble(e -> e.hasAttribute("weight") ?
                                    e.getAttribute("weight", Double.class) : 1).min()
                            // ∞ sonst
                            .orElse(Double.POSITIVE_INFINITY);
                    // und tij := 0 (direct edge between vi and vj)
                    if (distanceMatrix[i][j] != Double.POSITIVE_INFINITY)
                        transitionMatrix[i][j] = -1; // -1 as graphstream start node indexing by 0
                }
            }
        }
    }

    // region ******** getter and setter ********

    /**
     * Basic getter for the access counter.
     * @return Current value oof the access counter.
     */
    public int getGraphAccessCounter() {
        return graphAccessCounter;
    }

    public Graph getGraph() {
        return graph;
    }

    // endregion
}
