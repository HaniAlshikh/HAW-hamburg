package de.hawh.hajs.gka03.util.algo;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * FlowAlgorithmGka implementation for the Edmonds Karp maximum flow algorithm.
 *
 * @author Hani Alshikh
 * @author Jannik Stuckstätte
 */
public class EdmondsKarpGKA extends FlowAlgorithmGKA {

    private Queue<Integer> nodeIdsToInspect;

    /**
     * Runs the Edmonds & Karp algorithm and sets the maximum flow.
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
        while (!nodeIdsToInspect.isEmpty()) {
            // Mark node as inspected
            Node nodeToInspect = graph.getNode(nodeIdsToInspect.poll());
            // Retrieve delta from node to inspect
            double nodeToInspectDelta = nodeMarks[nodeToInspect.getIndex()].getDelta();

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

                    // add neighbor nodes ids to the queue
                    if (!nodeIdsToInspect.contains(nodeToMark.getIndex())) {
                        nodeIdsToInspect.add(nodeToMark.getIndex());
                    }
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

                    // add neighbor nodes ids to the queue
                    if (!nodeIdsToInspect.contains(nodeToMark.getIndex())) {
                        nodeIdsToInspect.add(nodeToMark.getIndex());
                    }
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
        nodeIdsToInspect = new LinkedList<>();
        nodeIdsToInspect.add(sourceNode.getIndex());
    }
}
