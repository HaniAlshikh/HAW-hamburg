package de.hawh.hajs.gka02.factory;

import org.graphstream.graph.implementations.AbstractGraph;
import org.graphstream.graph.implementations.MultiNode;

/**********************************************************************
 *
 * This class extends the graphstream multiNode with some helpful attributs
 * and implements a Comparable which is used for the priorityQueue in our
 * Dijkstra implementation.
 *
 * @author Jannik Stuckstätte
 * @author Hani Alshikh
 *
 ************************************************************************/
public class ExtendedNode extends MultiNode implements Comparable<ExtendedNode> {

    private ExtendedNode predecessor;
    private double distance;

    public ExtendedNode(AbstractGraph graph, String id) {
        super(graph, id);
        reset();
    }


    /**
     * restore initialization value to rerun Dijkstra on the graph
     */
    public void reset() {
        this.distance = Double.POSITIVE_INFINITY;
        this.predecessor = null;
    }

    @Override
    public int compareTo(ExtendedNode o) {
        return Double.compare(getDistance(), o.getDistance());
    }

    // region ******** getter and setter ********

    public ExtendedNode getPredecessor() {
        return this.predecessor;
    }

    public void setPredecessor(ExtendedNode predecessor) {
        this.predecessor = predecessor;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    // endregion
}
