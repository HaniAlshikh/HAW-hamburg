package de.hawh.hajs.gka03.util;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Queue;

public final class EdmondsKarp extends Flow {

    public static BigDecimal compute(Graph graph, Node s, Node t) {
        EdmondsKarp edmondsKarp = new EdmondsKarp(graph, s, t);
        edmondsKarp.solve();
        return edmondsKarp.maxFlow;
    }

    @Override
    void solve() {
        BigDecimal flow;
        do {
            markAllNodesAsUnvisited();
            flow = bfs();
            maxFlow = maxFlow.add(flow);
        } while (flow.compareTo(BigDecimal.ZERO) != 0);
    }


    private BigDecimal bfs() {
        Edge[] path = new Edge[graph.getNodeCount()];

        // TODO: The queue can be optimized to use a faster queue
        Queue<Integer> q = new ArrayDeque<>(graph.getNodeCount());
        visit(s);
        q.offer(s);

        // Perform BFS from source to sink
        while (!q.isEmpty()) {
            int node = q.poll();
            if (node == t) break;

            graph.getNode(node).leavingEdges().forEach(e -> {
                BigDecimal rCap = remainingCapacity(e);
                if (!visited(e.getTargetNode().getIndex()) && rCap.compareTo(BigDecimal.ZERO) > 0) {
                    visit(e.getTargetNode().getIndex());
                    path[e.getTargetNode().getIndex()] = e;
                    q.offer(e.getTargetNode().getIndex());
                }
            });
        }

        // Sink not reachable!
        if (path[t] == null) return BigDecimal.ZERO;

        BigDecimal bottleNeck = INFINITY;

        // Find augmented path and bottle neck
        for (Edge edge = path[t]; edge != null; edge = path[edge.getSourceNode().getIndex()])
            bottleNeck = bottleNeck.min(remainingCapacity(edge));

        // Retrace augmented path and update flow values.
        for (Edge edge = path[t]; edge != null; edge = path[edge.getSourceNode().getIndex()])
            augment(edge, bottleNeck);

        // Return bottleneck flow
        return bottleNeck;
    }

    EdmondsKarp(Graph graph, Node s, Node t) {
        super(graph, s, t);
    }
}
