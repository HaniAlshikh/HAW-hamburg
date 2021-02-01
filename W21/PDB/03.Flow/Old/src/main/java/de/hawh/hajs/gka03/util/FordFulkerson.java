package de.hawh.hajs.gka03.util;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public final class FordFulkerson extends Flow {

    public static BigDecimal compute(Graph graph, Node s, Node t) {
        FordFulkerson fordFulkerson = new FordFulkerson(graph, s, t);
        fordFulkerson.solve();
        return fordFulkerson.maxFlow;
    }

    @Override
    void solve() {
        // Find max flow by adding all augmenting path flows.
        // dfs will calculate the maxFlow for each leavingEdge from s
        for (BigDecimal f = dfs(s, INFINITY); f.compareTo(BigDecimal.ZERO) != 0; f = dfs(s, INFINITY)) {
            markAllNodesAsUnvisited();
            maxFlow = maxFlow.add(f);
        }
    }

    private BigDecimal dfs(int node, BigDecimal flow) {
        // At sink node, return augmented path flow.
        if (node == t) return flow;

        visit(node);

        List<Edge> leavingEdges = graph.getNode(node).leavingEdges().collect(Collectors.toList());
        for (Edge e : leavingEdges) {
            BigDecimal rCap = remainingCapacity(e);
            if (!visited(e.getTargetNode().getIndex()) && rCap.compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal bottleNeck = dfs(e.getTargetNode().getIndex(), flow.min(rCap));

                // Augment flow with bottle neck value
                if (bottleNeck.compareTo(BigDecimal.ZERO) > 0) {
                    augment(e, bottleNeck);
                    return bottleNeck;
                }
            }
        }
        return BigDecimal.ZERO;
    }

    FordFulkerson(Graph graph, Node s, Node t) {
        super(graph, s, t);
    }
}
