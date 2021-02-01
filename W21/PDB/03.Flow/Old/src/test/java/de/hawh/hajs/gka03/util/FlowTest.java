package de.hawh.hajs.gka03.util;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

// basic test cases
public class FlowTest {

    @Test
    void toResidualBasic() throws IOException {
        Graph graph = GraphParser
                .parseFile(Path.of("src/test/resources/networkFlow_Basic.txt"), "basic");
        Graph residualGraph = Flow.toResidual(graph);
        GraphParser.saveToFile(residualGraph);

        // all edges has residual with weight/capacity == 0
        residualGraph.edges().forEach(e -> {
            if (e.getAttribute("weight", BigDecimal.class).compareTo(BigDecimal.ZERO) == 0)
                assertTrue(e.getAttribute("residual", Edge.class)
                        .getAttribute("weight", BigDecimal.class).compareTo(BigDecimal.ZERO) > 0);

            else if (e.getAttribute("weight", BigDecimal.class).compareTo(BigDecimal.ZERO) > 0) {
                assertEquals(e.getAttribute("residual", Edge.class)
                        .getAttribute("weight", BigDecimal.class).compareTo(BigDecimal.ZERO), 0);
            }

            else assertNotNull(e.getAttribute("weight", BigDecimal.class));
        });
    }


    @Test
    void toResidualEdgeCases() throws IOException { // TODO
        Graph graph = GraphParser
                .parseFile(Path.of("src/test/resources/toResidualTestCases.txt"), "test");
        Graph residualGraph = Flow.toResidual(graph);
        GraphParser.saveToFile(residualGraph);
    }


    @ParameterizedTest
    @ValueSource(classes = {
            FordFulkerson.class,
            EdmondsKarp.class
    })
    void basicGraph_maxFlowIs20(Class<? extends Flow> algo) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Graph graph = GraphParser
                .parseFile(Path.of("src/test/resources/networkFlow_Basic.txt"), "basic");

        BigDecimal maxFlow = getMaxFlow(algo, graph, "s", "t");

        assertEquals(maxFlow.compareTo(new BigDecimal(20)), 0);
    }


    @ParameterizedTest
    @ValueSource(classes = {
            FordFulkerson.class,
            EdmondsKarp.class
    })
    void Graph04_maxFlowIs25(Class<? extends Flow> algo) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Graph graph = GraphParser
                .parseFile(Path.of("resource/graph04.gka"), "graph04");

        BigDecimal maxFlow = getMaxFlow(algo, graph, "q", "s");

        assertEquals(maxFlow.compareTo(new BigDecimal(25)), 0);
    }


    @Test
    void bigNet() {
        Graph bigNet = GraphGenerator.generate("BigNet", 50, 800,
                1, 20,
                GraphGenerator.GraphType.DIRECTED);
        FordFulkerson fordFulkerson = new FordFulkerson(bigNet, bigNet.getNode(0), bigNet.getNode(bigNet.getNodeCount() - 1));
        EdmondsKarp edmondsKarp = new EdmondsKarp(bigNet, bigNet.getNode(0), bigNet.getNode(bigNet.getNodeCount() - 1));

        fordFulkerson.solve();
        edmondsKarp.solve();

        System.out.println(fordFulkerson.maxFlow);
        System.out.println(edmondsKarp.maxFlow);

        assertEquals(fordFulkerson.maxFlow, edmondsKarp.maxFlow);
    }


    @Test
    void bigNetGraphen01() {
        Graph bigNet = GraphGenerator.generate("BigNet", 800, 300_000,
                1, 100,
                GraphGenerator.GraphType.DIRECTED);
        FordFulkerson fordFulkerson = new FordFulkerson(bigNet, bigNet.getNode(0), bigNet.getNode(bigNet.getNodeCount() - 1));
        EdmondsKarp edmondsKarp = new EdmondsKarp(bigNet, bigNet.getNode(0), bigNet.getNode(bigNet.getNodeCount() - 1));

        fordFulkerson.solve(); // takes forever :(
        edmondsKarp.solve(); // it takes 2m30s

        assertEquals(fordFulkerson.maxFlow, edmondsKarp.maxFlow);
    }


    @Test
    void bigNetGraphen02() {
        Graph bigNet = GraphGenerator.generate("BigNet", 2500, 2000_000,
                1, 100,
                GraphGenerator.GraphType.DIRECTED);
        FordFulkerson fordFulkerson = new FordFulkerson(bigNet, bigNet.getNode(0), bigNet.getNode(bigNet.getNodeCount() - 1));
        EdmondsKarp edmondsKarp = new EdmondsKarp(bigNet, bigNet.getNode(0), bigNet.getNode(bigNet.getNodeCount() - 1));

        fordFulkerson.solve();
        edmondsKarp.solve();

        assertEquals(fordFulkerson.maxFlow, edmondsKarp.maxFlow);
    }


    @Test
    void debugging() throws IOException {
        //Graph bigNet = GraphGenerator.generate("BigNet", 200, 5000,
        //        1, 20,
        //        GraphGenerator.GraphType.DIRECTED);
        //GraphParser.saveToFile(bigNet);

        Graph bigNet = GraphParser.parseFile(Path.of("BigNet-saved.gka"), "BigNet-test");

        FordFulkerson fordFulkerson = new FordFulkerson(bigNet, bigNet.getNode(0), bigNet.getNode(bigNet.getNodeCount() - 1));
        EdmondsKarp edmondsKarp = new EdmondsKarp(bigNet, bigNet.getNode(0), bigNet.getNode(bigNet.getNodeCount() - 1));

        fordFulkerson.solve();
        edmondsKarp.solve();

        System.out.println(fordFulkerson.maxFlow);
        System.out.println(edmondsKarp.maxFlow);

        assertEquals(fordFulkerson.maxFlow, edmondsKarp.maxFlow);

    }


    private BigDecimal getMaxFlow(Class<? extends Flow> algo, Graph graph, String s, String t) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return (BigDecimal)
                algo.getDeclaredMethod("compute", Graph.class, Node.class, Node.class)
                        .invoke(null, graph, graph.getNode(s), graph.getNode(t));
    }
}
