package de.hawh.hajs.gka03.util.generator;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BooleanSupplier;


/**********************************************************************
 *
 * This utility class provides methods to generate graphstream graphs
 * according to user input
 *
 * @author Jannik Stuckstätte
 * @author Hani Alshikh
 *
 ************************************************************************/
public final class GraphGenerator {

    public enum GraphType {
        DIRECTED,
        NOT_DIRECTED,
        BOTH
    }

    /**
     * generate a graph according the the nodesCount and edgesCount
     * weights are sat according to the weightLowerBound inclusive and
     * the weightUpperBound exclusive
     *
     * @param id the generated graph id/name
     * @param nodesCount number of nodes in the generated graph
     * @param edgesCount number of edges in the generated graph
     * @param weightLowerBound the lower bound for the generated edges weight
     * @param weightUpperBound the upper bound for the generated edges weight
     * @param graphType specify if the graph is directed, not directed or both
     * @return the generated graph
     */
    public static Graph generate(String id, int nodesCount, int edgesCount,
                                 double weightLowerBound, double weightUpperBound, GraphType graphType) {
        ThreadLocalRandom r = ThreadLocalRandom.current();

        BooleanSupplier graphTypeBool = () -> {
            switch (graphType) {
                case DIRECTED: return true;
                case NOT_DIRECTED: return false;
                default: return r.nextBoolean();
            }
        };

        Graph graph = new MultiGraph(id, false, true);

        for (int i = 0; i < nodesCount; i++) graph.addNode("v"+i);
        for (int j = 0; j < edgesCount; j++) {
            Edge e = graph.addEdge(
                    "e"+j, r.nextInt(nodesCount), r.nextInt(nodesCount), graphTypeBool.getAsBoolean());
            e.setAttribute("weight", (double) Math.round(
                    r.nextDouble(weightLowerBound, weightUpperBound) * 100.0) / 100.0);
        }

        return graph;
    }


    // private constructor to avoid instantiation
    private GraphGenerator(){
        throw new IllegalStateException(
                String.format("%s cannot be instantiated!", GraphGenerator.class.getName()));
    }
}
