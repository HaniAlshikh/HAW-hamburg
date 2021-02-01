package de.hawh.hajs.gka03.util.algo;

import org.graphstream.graph.Node;

/**
 * stores needed data for the flowAlgorithmGKA
 *
 * @author Hani Halshikh
 * @author Jannik Stuckstätte
 */
public class FlowAlgorithmNodeMark {
    private final Node predecessor;
    private final double delta;
    private final boolean forward;

    public FlowAlgorithmNodeMark(boolean forward, Node predecessor, double delta) {
        this.forward = forward;
        this.predecessor = predecessor;
        this.delta = delta;
    }

    public boolean isForward() {
        return forward;
    }

    public Node getPredecessor() {
        return predecessor;
    }

    public double getDelta() {
        return delta;
    }
}
