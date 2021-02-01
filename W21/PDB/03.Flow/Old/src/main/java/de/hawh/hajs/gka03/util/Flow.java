package de.hawh.hajs.gka03.util;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.Graphs;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public abstract class Flow {

    // TODO: find a cleaner way
    protected static final BigDecimal INFINITY = BigDecimal.valueOf(Double.MAX_VALUE);

    private int visitedToken = 1;
    private final int[] visited;

    protected Graph graph;
    protected int s;
    protected int t;
    protected BigDecimal maxFlow = BigDecimal.ZERO;

    protected Flow(Graph graph, Node s, Node t) {
        this.graph = toResidual(graph);
        this.s = this.graph.getNode(s.getId()).getIndex();
        this.t = this.graph.getNode(t.getId()).getIndex();
        visited = new int[graph.getNodeCount()];
    }

    abstract void solve();

    // TODO: this approach generate too many edges which makes the algo slower
    public static Graph toResidual(Graph aGraph) {
        Graph graph = Graphs.clone(aGraph);
        // TODO: manipulating the stream collection effects the stream
        //  ->
        List<Edge> edges = graph.edges().collect(Collectors.toList());

        AtomicInteger residualCounter = new AtomicInteger();

        // TODO: if we have e(u, v) and e(v, u) already
        //  - take the edge with the max capacity and make the other one Residual?
        //  - use them both and create Residual?
        //  - make them Residual to each other?
        //  -> it doesn't matter as they should be residual for each other
        edges.stream().filter(e -> !isResidual(e)).forEach(e -> {
            // assuming a residual already exists
            Edge residual = e.getTargetNode().getEdgeToward(e.getSourceNode());

            // TODO: deal with multiple edges for (u, v)
            //  should we simply merge them? or it doesn't matter
            // mergedEdge = mergeParallelEdges

            // if e not directed ignore the assumption and create extra two edges
            if (!e.isDirected()) {
                Edge old = graph.removeEdge(e);
                e = graph.addEdge(e.getNode0()+"-"+old.getAttribute("weight")+"-"+e.getNode1(),
                        e.getNode0(), e.getNode1(), true);
                residual = graph.addEdge(e.getNode1()+"-"+old.getAttribute("weight")+"-"+e.getNode0(),
                        e.getNode1(), e.getNode0(), true);

                e.setAttribute("weight", old.getAttribute("weight"));
                residual.setAttribute("weight", old.getAttribute("weight"));
            }

            // if assumption was false
            if (residual == null) {
                String id = e.getTargetNode()+"-"+e.getAttribute("weight")+">"+e.getSourceNode()
                        + "_" + residualCounter.incrementAndGet();
                residual = graph.addEdge(id, e.getTargetNode(), e.getSourceNode(), true);
                residual.setAttribute("weight", BigDecimal.ZERO);
                residual.setAttribute("idGenerated", true);
            }

            e.setAttribute("flow", BigDecimal.ZERO);
            residual.setAttribute("flow", BigDecimal.ZERO);

            e.setAttribute("residual", residual);
            residual.setAttribute("residual", e);
        });

        return graph;
    }

    //public static void mergeEdges(Graph graph) {
    //    graph.nodes().forEach(n -> {
    //        graph.nodes().forEach(targetNode -> {
    //
    //            List<Edge> edges = n.leavingEdges()
    //                    .filter(e -> e.getTargetNode() == targetNode)
    //                    .collect(Collectors.toList());
    //
    //            BigDecimal weightSum = BigDecimal.ZERO;
    //
    //            for (Edge edge : edges) {
    //                weightSum = weightSum.add(edge.getAttribute("weight", BigDecimal.class));
    //                graph.removeEdge(edge);
    //            }
    //
    //            Edge newE = graph.addEdge(n.getId() + targetNode.getId(), n, targetNode, true);
    //            newE.setAttribute("weight", weightSum);
    //        });
    //    });
    //
    //    graph.edges().filter(e -> !isResidual(e)).forEach(e -> {
    //        Edge residual = e.getTargetNode().getEdgeToward(e.getSourceNode());
    //
    //        e.setAttribute("flow", BigDecimal.ZERO);
    //        residual.setAttribute("flow", BigDecimal.ZERO);
    //
    //        e.setAttribute("residual", residual);
    //        residual.setAttribute("residual", e);
    //    });
    //}

    // ---------------- helper methods ------------------

    private static Boolean isResidual(Edge edge) {
        return edge.hasAttribute("residual", Edge.class);
    }

    protected void markAllNodesAsUnvisited() {
        visitedToken++;
    }

    protected boolean visited(int i) {
        return visited[i] == visitedToken;
    }

    protected void visit(int i) {
        visited[i] = visitedToken;
    }

    protected BigDecimal remainingCapacity(Edge edge) {
        return edge.getAttribute("weight", BigDecimal.class).subtract(edge.getAttribute("flow", BigDecimal.class));
    }

    protected void augment(Edge edge, BigDecimal bottleNeck) {
        // flow += bottleNeck
        edge.setAttribute("flow", edge.getAttribute("flow", BigDecimal.class).add(bottleNeck));
        // residual.flow -= bottleNeck;
        Edge residual = edge.getAttribute("residual", Edge.class);
        residual.setAttribute("flow", residual.getAttribute("flow", BigDecimal.class).subtract(bottleNeck));
    }
}
