package de.hawh.hajs.gka03.util.algo;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.util.Optional;

/**
 * FlowAlgorithmGka implementation for the Ford-Fulkerson maximum flow algorithm.
 *
 * @author Hani Alshikh
 * @author Jannik Stuckstätte
 */
public class FordFulkersonGKA extends FlowAlgorithmGKA {

    private boolean[] nodesInspected;

    /**
     * Runs the Ford-Fulkerson algorithm and sets the maximum flow.
     *
     * The {@link #init(Graph, String, String)} method has to be called before calling this method.
     *
     * @see #init(Graph, String, String)
     */
    @Override
    public void compute() {
        // 1. initialisation
        initializeNodeMarks();

        // 2. inspecting and marking
        Optional<Node> nodeToInspectOpt;
        while ((nodeToInspectOpt = getNextNodeToInspect()).isPresent()) {
            Node nodeToInspect = nodeToInspectOpt.get();
            // Retrieve delta from node to inspect
            double nodeToInspectDelta = nodeMarks[nodeToInspect.getIndex()].getDelta();
            // Mark node as inspected
            nodesInspected[nodeToInspect.getIndex()] = true;

            nodeToInspect.leavingEdges()
                // filter only the edges with unmarked target nodes
                .filter(edge -> nodeMarks[edge.getOpposite(nodeToInspect).getIndex()] == null)
                // where f(e) < c(e)
                .filter(edge -> flows[edge.getIndex()] < capacities[edge.getIndex()])
                .forEach(edge -> {
                    Node nodeToMark = edge.getOpposite(nodeToInspect);
                    // calculate delta minimum
                    double nodeToMarkDelta = Math
                        .min((capacities[edge.getIndex()] - flows[edge.getIndex()]),
                            nodeToInspectDelta);

                    // mark the target node
                    nodeMarks[nodeToMark.getIndex()] = new FlowAlgorithmNodeMark(true,
                        nodeToInspect,
                        nodeToMarkDelta);
                });

            nodeToInspect.enteringEdges()
                // filter only the edges with unmarked source nodes
                .filter(edge -> nodeMarks[edge.getOpposite(nodeToInspect).getIndex()] == null)
                // where f(e) > 0
                .filter(edge -> flows[edge.getIndex()] > 0)
                .forEach(edge -> {
                    Node nodeToMark = edge.getOpposite(nodeToInspect);
                    // calculate delta minimum
                    double nodeToMarkDelta = Math
                        .min(flows[edge.getIndex()],
                            nodeToInspectDelta);

                    // mark the source node
                    nodeMarks[nodeToMark.getIndex()] = new FlowAlgorithmNodeMark(false,
                        nodeToInspect,
                        nodeToMarkDelta);
                });

            if (nodeMarks[sinkNode.getIndex()] != null) {
                // 3. Extending flow
                extendFlowOnPath();
            }
        }
        // 4. Termination
        computed = true;
        maximumFlow = getFlowToSink();
    }

    @Override
    protected void initializeNodeMarks() {
        super.initializeNodeMarks();
        nodesInspected = new boolean[graph.getNodeCount()];
    }

    /**
     * Used for finding the next node to inspect. This is explicitly non deterministic.
     *
     * @return Node to inspect next.
     */
    private Optional<Node> getNextNodeToInspect() {
        return graph.nodes().filter(node -> nodeMarks[node.getIndex()] != null)
            .filter(node -> !nodesInspected[node.getIndex()]).findAny();
    }
}
