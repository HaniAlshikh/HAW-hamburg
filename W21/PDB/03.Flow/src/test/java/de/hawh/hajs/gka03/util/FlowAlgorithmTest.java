package de.hawh.hajs.gka03.util;

import de.hawh.hajs.gka03.util.algo.EdmondsKarpGKA;
import de.hawh.hajs.gka03.util.algo.FordFulkersonGKA;
import de.hawh.hajs.gka03.util.generator.NetGenerator;
import org.graphstream.algorithm.flow.EdmondsKarpAlgorithm;
import org.graphstream.algorithm.flow.FordFulkersonAlgorithm;
import org.graphstream.graph.Graph;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlowAlgorithmTest {

    boolean saveToFile = true;

    @Test
    void testGraphstreamTestCaseOne() throws IOException {
        Graph testcase1 = GraphParser
                .parseFile(Path.of("./src/test/resources/graphstream_testcase1.gka"),
                        "graphstream_testcase1");
        testGraph(testcase1);
    }

    @Test
    void testGraphstreamTestCaseTwo() throws IOException {
        Graph testcase2 = GraphParser
                .parseFile(Path.of("./src/test/resources/graphstream_testcase2.gka"),
                        "graphstream_testcase1");
        testGraph(testcase2);
    }

    @Test
    void testFordFulkersonHugeNet() {
        Graph hugeNet = NetGenerator.generate(800, 300000, 0.0, 10.0, "weight");
        runFordFulkersonGKA(hugeNet, "q", "s");
    }

    @Test
    void testEdmonsKarpHugeNet() {
        Graph hugeNet = NetGenerator.generate(800, 300000, 0.0, 10.0, "weight");
        runEdmondsKarpGKA(hugeNet, "q", "s");
    }

    @Test
    void testGraph04() throws IOException {
        Graph graph04 = GraphParser
            .parseFile(Path.of("./src/test/resources/digraph04.gka"), "graph04");
        testGraph(graph04);
    }

    @Test
    void testBothAlgorithmRuntime(){
        Graph bigNet = NetGenerator.generate(800, 300000, 1.0, 10.0, "weight");
        GraphParser.saveToFile(bigNet);
        float runtimeFF = getRuntimeFordFulkerson(bigNet, "q", "s");
        System.out.println("Ford Fulkerson runtime: " + runtimeFF + "ms");
        float runtimeEK = getRuntimeEdmondsKarp(bigNet, "q", "s");
        System.out.println("Edmonds Karp runtime: " + runtimeEK + "ms");
    }

    @Test
    void testBothAlgorithmRuntimeBigNetJohan() throws IOException {
        Graph bigNetJohan = GraphParser.parseFile(Path.of("./src/test/resources/-306304222-saved.gka"), "bignetJohan");
        float runtimeFF = getRuntimeFordFulkerson(bigNetJohan, "q", "s");
        System.out.println("Ford Fulkerson runtime: " + runtimeFF + "ms");
        float runtimeEK = getRuntimeEdmondsKarp(bigNetJohan, "q", "s");
        System.out.println("Edmonds Karp runtime: " + runtimeEK + "ms");
    }

    @Test
    void testBothGKAAlgorithmsHugeNet() {
        Graph hugeNet = NetGenerator.generate(800, 300000, 0.0, 10.0, "weight");
        double resultEdmonsKarpGKA = runEdmondsKarpGKA(hugeNet, "q", "s");
        double resultFordFulkersonGKA = runFordFulkersonGKA(hugeNet, "q", "s");
        assertEquals(resultEdmonsKarpGKA, resultFordFulkersonGKA, 0.000001);
    }

    @Test
    void testRandomGraphNTimes() {
        saveToFile = false;
        for (int i = 0; i < 1000; i++) {
            testRandomGraph(30, 40, 0.0, 10.0);
        }
    }

    @Test
    void testBigNet() {
        saveToFile = false;
        testRandomGraphSingleFlow(50, 800, 0.0, 10.0, "q", "s");
    }

    void testRandomGraph(int nodeCount, int edgeCount, double capacityLowerBound,
        double capacityUpperBound) {
        Graph graph = NetGenerator
            .generate(nodeCount, edgeCount, capacityLowerBound, capacityUpperBound, "weight");

        //DEBUG
        if (saveToFile) {
            GraphParser.saveToFile(graph);
        }
        testGraph(graph);
    }

    void testRandomGraphSingleFlow(int nodeCount, int edgeCount, double capacityLowerBound,
        double capacityUpperBound, String sourceId, String sinkId) {
        Graph graph = NetGenerator
            .generate(nodeCount, edgeCount, capacityLowerBound, capacityUpperBound, "weight");

        testSingleFlowAllAlgorithms(graph, sourceId, sinkId);
    }

    void testGraph(Graph graph) {
        for (var i = 0; i < graph.getNodeCount(); i++) {
            for (var k = 0; k < graph.getNodeCount(); k++) {
                if (i != k) {
                    testSingleFlowAllAlgorithms(graph, graph.getNode(i).getId(), graph.getNode(k).getId()
                    );
                }
            }
        }
    }

    void testSingleFlowAllAlgorithms(Graph graph, String sourceId, String sinkId) {
        System.out.println("Testing source(" + sourceId + ") sink(" + sinkId + ") ...");

        double fordFulkersonGKAResult = runFordFulkersonGKA(graph, sourceId, sinkId);
        double fordFulkersonGSResult = runFordFulkersonGS(graph, sourceId, sinkId);

        assertEquals(fordFulkersonGSResult, fordFulkersonGKAResult,
            0.000001);

        double edmondsKarpGKAResult = runEdmondsKarpGKA(graph, sourceId, sinkId);
        double edmondsKarpGSResult = runEmdondsKarpGS(graph, sourceId, sinkId);

        assertEquals(edmondsKarpGSResult, edmondsKarpGKAResult);

        assertEquals(edmondsKarpGKAResult, fordFulkersonGKAResult, 0.000001);
        // DEBUG
        System.out.println("Result: " + edmondsKarpGKAResult);
    }

    float getRuntimeFordFulkerson(Graph graph, String sourceId, String sinkId){
        System.out.println("Starting measuring runtime of FordFulkersonGKA...");
        long starTime = System.nanoTime();
        runFordFulkersonGKA(graph, sourceId, sinkId);
        long endTime = System.nanoTime();
        return (endTime - starTime) / 1000000.0f;
    }

    float getRuntimeEdmondsKarp(Graph graph, String sourceId, String sinkId){
        System.out.println("Starting measuring runtime of EdmondsKarpGKA...");
        long starTime = System.nanoTime();
        runEdmondsKarpGKA(graph, sourceId, sinkId);
        long endTime = System.nanoTime();
        return (endTime - starTime) / 1000000.0f;
    }

    double runFordFulkersonGKA(Graph graph, String sourceId, String sinkId) {
        FordFulkersonGKA fordFulkersonGKA = new FordFulkersonGKA();
        fordFulkersonGKA.init(graph, sourceId, sinkId);
        fordFulkersonGKA.setCapacityAttribute("weight");
        fordFulkersonGKA.compute();
        double result = fordFulkersonGKA.getMaximumFlow();
        System.out.println("Ford Fulkerson GKA result: " + result);
        return result;
    }

    double runEdmondsKarpGKA(Graph graph, String sourceId, String sinkId) {
        EdmondsKarpGKA edmondsKarpGKA = new EdmondsKarpGKA();
        edmondsKarpGKA.init(graph, sourceId, sinkId);
        edmondsKarpGKA.setCapacityAttribute("weight");
        edmondsKarpGKA.compute();
        double result = edmondsKarpGKA.getMaximumFlow();
        System.out.println("Edmonds Karp GKA result: " + result);
        return result;
    }

    double runFordFulkersonGS(Graph graph, String sourceId, String sinkId) {
        FordFulkersonAlgorithm refFordFulkersonAlgo = new FordFulkersonAlgorithm();
        refFordFulkersonAlgo.init(graph, sourceId, sinkId);
        refFordFulkersonAlgo.setCapacityAttribute("weight");
        refFordFulkersonAlgo.compute();
        double result = refFordFulkersonAlgo.getMaximumFlow();
        System.out.println("Ford Fulkerson GS result: " + result);
        return result;
    }

    double runEmdondsKarpGS(Graph graph, String sourceId, String sinkId) {
        EdmondsKarpAlgorithm refEdmondsKarpAlgo = new EdmondsKarpAlgorithm();
        refEdmondsKarpAlgo.init(graph, sourceId, sinkId);
        refEdmondsKarpAlgo.setCapacityAttribute("weight");
        refEdmondsKarpAlgo.compute();
        double result = refEdmondsKarpAlgo.getMaximumFlow();
        System.out.println("Edmonds Karp GS result: " + result);
        return result;
    }

}
