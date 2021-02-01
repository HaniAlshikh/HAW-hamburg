package de.hawh.hajs.gka02.util;

import de.hawh.hajs.gka02.util.TraversalHelper.Algorithm;
import org.graphstream.algorithm.APSP;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TraversalHelperTest {

    private static Graph regularGraph;
    private static Graph edgeCaseGraph;
    private static Graph weightedGraph;
    private static Graph directedWeightedGraph;
    private static Graph graphContainingNegativeCycle;
    private static Graph graph03;


    @BeforeAll
    static void beforeAll() throws IOException {
        regularGraph = parse("RegularGraph");
        edgeCaseGraph = parse("edgeCaseGraph");
        weightedGraph = parse("weightedGraph");
        directedWeightedGraph = parse("directedWeightedGraph");
        graphContainingNegativeCycle = parse("graphContainingNegativeWeightedCycle");
        graph03 = GraphParser.parseFile(Path.of("resource/graph03.gka"), "graph03");
    }


    // Dijkstra vs Floyd Warshall
    @Test
    void testEachPathDijkstraVsFloydWarshall() {
        // graph3
        dijkstraVsFloydWarshall(graph03);
        // allgemeine Konstruktion
        dijkstraVsFloydWarshall(GraphGenerator.generate("General Graph",
            10, 20, 1, 10, GraphGenerator.GraphType.DIRECTED));
    }

    // BIG
    @Test
    void testBIG() {
        Graph big = GraphGenerator.generate("BIG",
            100, 3500, 1, 10,
            GraphGenerator.GraphType.DIRECTED);

        // TODO: APSP can't handle multinode
        // TODO: we can't test big graphs with negative weights
        //  as the possibility of a negative cycle is too high
        //  which result in no path for all nodes.
        //Graph bigNegative = GraphGenerator.generate("BIG",
        //    100, 3500, -10, 10,
        //    GraphGenerator.GraphType.DIRECTED);
        //testEveryPathFor(bigNegative, true, TraversalHelper.Algorithm.FLOYD_WARSHALL);

        dijkstraVsFloydWarshall(big);
    }


    // Dijkstra
    @Test
    void testBasicPathDijkstra() {
        testBasicPathUndirected(TraversalHelper.Algorithm.DIJKSTRA);
        testBasicPathDirected(TraversalHelper.Algorithm.DIJKSTRA);
    }

    @Test
    void testBasicNoPathFoundDijkstra() {
        Optional<List<Node>> path = TraversalHelper.getShortestPath(
            directedWeightedGraph.getNode("1"), directedWeightedGraph.getNode("12"),
            Algorithm.DIJKSTRA);
        assertFalse(path.isPresent());
    }

    @Test
    void testAllShortestPathsPresentfromHusumDijkstra() {
        testAllShortestPathsPresentFromHusum(TraversalHelper.Algorithm.DIJKSTRA);
    }

    @Test
    void testAllShortestPathsCityNamesWeightedDijkstra() {
        testEveryPathFor(regularGraph, false, TraversalHelper.Algorithm.DIJKSTRA);
    }

    @Test
    void testAllShortestPathsForDirectedGraphDijkstra() {
        testEveryPathFor(directedWeightedGraph, true, TraversalHelper.Algorithm.DIJKSTRA);
    }

    @Test
    void testGraphContainingNegativeCycleDijkstra() {
        assertThrows(IllegalArgumentException.class, () -> TraversalHelper.getShortestPath(
            graphContainingNegativeCycle.getNode("1"), graphContainingNegativeCycle.getNode("4"),
            Algorithm.DIJKSTRA));
    }

    /**
     * The following test case demonstrates a false results in the GS Dijkstra implementation
     */
    //@Test
    //void testAllShortestPathsForDirectedGraphDijkstraAgainstGSDijkstra() {
    //    testEveryPathForGSDijkstra(directedWeightedGraph, true);
    //}

    // Floyd-Warshall

    @Test
    void testBasicPathFloydWarshall() {
        testBasicPathUndirected(TraversalHelper.Algorithm.FLOYD_WARSHALL);
        testBasicPathDirected(TraversalHelper.Algorithm.FLOYD_WARSHALL);
    }

    @Test
    void testBasicNoPathFoundFloydWarshall() {
        Optional<List<Node>> path = TraversalHelper.getShortestPath(
            edgeCaseGraph.getNode("a"), edgeCaseGraph.getNode("c"), Algorithm.FLOYD_WARSHALL);
        assertFalse(path.isPresent());
    }

    @Test
    void testAllShortestPathsPresentfromHusumFloydWarshall() {
        testAllShortestPathsPresentFromHusum(TraversalHelper.Algorithm.FLOYD_WARSHALL);
    }

    @Test
    void testAllShortestPathsCityNamesWeightedFloydWarshall() {
        testEveryPathFor(regularGraph, false, TraversalHelper.Algorithm.FLOYD_WARSHALL);
    }

    @Test
    void testAllShortestPathsForDirectedGraphFloydWarshall() {
        testEveryPathFor(directedWeightedGraph, true, TraversalHelper.Algorithm.FLOYD_WARSHALL);
    }

    @Test
    void testGraphContainingNegativeCycleFloydWarshall() {
        assertThrows(IllegalArgumentException.class, () -> TraversalHelper.getShortestPath(
            graphContainingNegativeCycle.getNode("1"), graphContainingNegativeCycle.getNode("4"),
            Algorithm.FLOYD_WARSHALL));
    }

    private void testBasicPathUndirected(TraversalHelper.Algorithm algorithmToTest) {
        // not directed
        Optional<List<Node>> path = TraversalHelper.getShortestPath(
            weightedGraph.getNode("s"), weightedGraph.getNode("v4"), algorithmToTest);
        assertTrue(path.isPresent());
        assertEquals("[s, v3, v4]", path.get().toString());
        assertNotEquals("[s, q, v4]", path.get().toString());
    }

    private void testBasicPathDirected(TraversalHelper.Algorithm algorithmToTest) {
        Optional<List<Node>> path = TraversalHelper.getShortestPath(
            weightedGraph.getNode("x"), weightedGraph.getNode("z"), algorithmToTest);
        assertTrue(path.isPresent());
        assertEquals("[x, m, z]", path.get().toString());
        assertNotEquals("[x, y, w, z]", path.get().toString());
        assertNotEquals("[x, z]", path.get().toString());
    }

    private void testAllShortestPathsPresentFromHusum(TraversalHelper.Algorithm algorithmToTest) {
        regularGraph.nodes().forEach(node -> {
            Optional<List<Node>> path = TraversalHelper.getShortestPath(
                regularGraph.getNode("Husum"), node, algorithmToTest);
            if (!node.getId().equals("Husum")) {
                assertTrue(path.isPresent());
            }
        });
    }

    private void testEveryPathFor(Graph graph, boolean directed,
        TraversalHelper.Algorithm algorithmToTest) {

        graph.nodes().forEach(startNode ->
                graph.nodes().forEach(endNode -> {
            if (!startNode.getId().equals(endNode.getId())) {
                Optional<List<Node>> pathOpt = TraversalHelper.getShortestPath(
                    startNode, endNode, algorithmToTest);

                APSP apsp = new APSP(graph, "weight", directed);
                apsp.compute();
                APSP.APSPInfo info = (APSP.APSPInfo) graph.getNode(startNode.getId())
                    .getAttribute(APSP.APSPInfo.ATTRIBUTE_NAME);

                if (info.targets.containsKey(endNode.getId())) {
                    assertTrue(pathOpt.isPresent());
                    assertEquals(
                        info.getShortestPathTo(endNode.getId()).getPathWeight("weight"),
                        TraversalHelper.getPathWeight(pathOpt.get()));
                } else {
                    assertTrue(pathOpt.isEmpty());
                }
            }
        }));
    }

    //private void testEveryPathForGSDijkstra(Graph graph, boolean directed) {
    //    graph.nodes().forEach(startNode -> {
    //        graph.nodes().forEach(endNode -> {
    //            if (!startNode.getId().equals(endNode.getId())) {
    //                System.out.println("Testing between " + startNode + " and " + endNode + ":");
    //
    //                Optional<List<Node>> pathOpt = TraversalHelper.getShortestPath(
    //                    startNode, endNode, Algorithm.DIJKSTRA);
    //
    //                org.graphstream.algorithm.Dijkstra dijkstra = new org.graphstream.algorithm.Dijkstra();
    //                dijkstra.init(graph);
    //                dijkstra.setSource(startNode);
    //                dijkstra.compute();
    //
    //                if (!dijkstra.getPath(endNode).empty()) {
    //                    assertTrue(pathOpt.isPresent());
    //
    //                    System.out.println("Ours: " + pathOpt.get());
    //                    System.out.println("Theirs: " + dijkstra.getPath(endNode));
    //                    System.out.println();
    //
    //                    assertEquals(dijkstra.getPath(endNode).getPathWeight("weight"),
    //                        TraversalHelper.getPathWeight(pathOpt.get()));
    //                } else {
    //                    assertTrue(pathOpt.isEmpty());
    //                    System.out.println("No Path found");
    //                    System.out.println();
    //                }
    //            }
    //        });
    //    });
    //}

    private void dijkstraVsFloydWarshall(Graph graph) {
        Dijkstra.resetAccessCounter();
        graph.nodes().forEach(startNode ->
                graph.nodes().forEach(endNode -> {
            if (!startNode.getId().equals(endNode.getId())) {
                System.out.println("Testing between " + startNode + " and " + endNode + ":");

                Optional<List<Node>> dijkstraPath = getPathAndLog(
                    startNode, endNode, Algorithm.DIJKSTRA);
                Optional<List<Node>> floydWarshallPath = getPathAndLog(
                    startNode, endNode, Algorithm.FLOYD_WARSHALL);

                double dijkstraPathWeight = 0D, floydWarshallPathWeight = 0D;
                if (dijkstraPath.isPresent()) {
                    dijkstraPathWeight = TraversalHelper.getPathWeight(dijkstraPath.get());
                }
                if (floydWarshallPath.isPresent()) {
                    floydWarshallPathWeight = TraversalHelper
                        .getPathWeight(floydWarshallPath.get());
                }

                assertEquals(floydWarshallPathWeight, dijkstraPathWeight);
                System.out.println();
            }
        }));
    }

    private static Optional<List<Node>> getPathAndLog(Node start, Node end,
        TraversalHelper.Algorithm algo) {
        Optional<List<Node>> path = TraversalHelper.getShortestPath(start, end, algo);

        System.out.println(algo);
        System.out.println(
                "Number of access on the Graph: " + TraversalHelper.getAccessCounter(algo));
        System.out.println("Path: " + path);

        return path;
    }

    private static Graph parse(String filename) throws IOException {
        return GraphParser
            .parseFile(Path.of("./src/test/resources/" + filename + ".gka"), filename);
    }

}