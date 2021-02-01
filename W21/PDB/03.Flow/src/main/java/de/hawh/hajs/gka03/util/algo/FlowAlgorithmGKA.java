package de.hawh.hajs.gka03.util.algo;

import de.hawh.hajs.gka03.util.algo.error.FlowNotComputedError;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

/**
 * This class is used as the base for all flow algorithm implementations.
 * It provides methods and fields to handle capacities and flows.
 *
 * It's structure is heavily inspired by the "FlowAlgorithmBase" class in the GraphStream library.
 *
 * @see org.graphstream.algorithm.flow.FlowAlgorithmBase
 *
 * @author Hani Alshikh
 * @author Jannik Stuckstätte
 */
public abstract class FlowAlgorithmGKA {

    protected Graph graph;
    protected String sourceId;
    protected String sinkId;
    protected Node sourceNode;
    protected Node sinkNode;
    protected double[] flows;
    protected double[] capacities;
    protected String capcityAttribute;
    protected double maximumFlow;
    protected boolean computed;
    protected FlowAlgorithmNodeMark[] nodeMarks;

    public abstract void compute();

    /**
     * Initializes the FlowAlgorithmGKA. This method needs to be called before calling {@link
     * #compute()}.
     *
     * @param graph    graph to be used by the algorithm
     * @param sourceId id of the node acting as source of the flow to be calculated
     * @param sinkId   id of the node acting as sink of the flow to be calculated
     */
    public void init(Graph graph, String sourceId, String sinkId) {
        this.graph = graph;
        this.sourceId = sourceId;
        this.sinkId = sinkId;
        this.sourceNode = graph.getNode(sourceId);
        this.sinkNode = graph.getNode(sinkId);
        this.computed = false;
        this.capacities = new double[graph.getEdgeCount()];
        this.flows = new double[graph.getEdgeCount()];
        retrieveCapacitiesFromAttributes();
    }

    protected void initializeNodeMarks() {
        // Initializing markings and inspections
        nodeMarks = new FlowAlgorithmNodeMark[graph.getNodeCount()];
        // Marking q with (undef, ∞)
        nodeMarks[this.sourceNode.getIndex()] = new FlowAlgorithmNodeMark(true, null,
                Double.POSITIVE_INFINITY);
    }

    /**
     * Sets the attribute on the edges that is defining their capacity.
     *
     * @param attribute name of attribute.
     */
    public void setCapacityAttribute(String attribute) {
        this.capcityAttribute = attribute;
        retrieveCapacitiesFromAttributes();
    }

    /**
     * Get computed maximum flow.
     *
     * @return maximum flow
     */
    public double getMaximumFlow() {
        if (!computed) {
            throw new FlowNotComputedError();
        }
        return maximumFlow;
    }

    /**
     * Get the sum of the flows of the entering edges of the sink.
     * @return Sum of flows to sink.
     */
    protected double getFlowToSink() {
        return sinkNode.enteringEdges().mapToDouble(edge -> flows[edge.getIndex()]).sum();
    }

    /**
     * augments/extends the current path of the marked nodes from the sink
     * with the current delta
     */
    protected void extendFlowOnPath() {
        Node currentNode = sinkNode;
        FlowAlgorithmNodeMark currentMark = nodeMarks[currentNode.getIndex()];
        Node nextNode = currentMark.getPredecessor();
        while (nextNode != null) {
            Edge edgeInBetween;
            if (currentMark.isForward()) {
                edgeInBetween = currentNode.getEdgeFrom(nextNode);
            } else {
                edgeInBetween = currentNode.getEdgeToward(nextNode);
            }
            double deltaToAdd =
                currentMark.isForward() ? nodeMarks[sinkNode.getIndex()].getDelta()
                    : -nodeMarks[sinkNode.getIndex()].getDelta();
            flows[edgeInBetween.getIndex()] += deltaToAdd;
            currentNode = nextNode;
            currentMark = nodeMarks[currentNode.getIndex()];
            nextNode = currentMark.getPredecessor();
        }
        initializeNodeMarks();
    }

    /**
     * Get id of the source.
     *
     * @return id of the source
     */
    public String getSourceId() {
        return sourceId;
    }

    /**
     * Get id of the sink.
     *
     * @return id of the sink
     */
    public String getSinkId() {
        return sinkId;
    }

    public Graph getGraph() {
        return graph;
    }

    private void retrieveCapacitiesFromAttributes() {
        if (capcityAttribute == null) {
            return;
        }
        graph.edges().forEach(edge -> {
            if (edge.hasNumber(capcityAttribute)) {
                capacities[edge.getIndex()] = edge.getNumber(capcityAttribute);
            }
        });
    }
}