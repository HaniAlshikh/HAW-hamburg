package de.hawh.hajs.gka01.test;

import de.hawh.hajs.gka01.util.GraphParser;
import de.hawh.hajs.gka01.util.TraversalHelper;
import org.graphstream.algorithm.APSP;
import org.graphstream.algorithm.APSP.APSPInfo;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class TraversalTest {


    @Test
    void testBasicSteps() throws IOException {
        Graph graph = GraphParser
            .parseFile(Path.of("./src/test/resources/edgeCaseGraph.gka"), "testBasicSteps");
        int steps = TraversalHelper.getStepsBFS(graph, "a", "b");
        assertEquals(1, steps);
    }

    @Test
    void testBasicPath() throws IOException {
        Graph graph = GraphParser
            .parseFile(Path.of("./src/test/resources/edgeCaseGraph.gka"), "testBasicPath");
        Optional<String[]> pathOpt = TraversalHelper.getShortestPathBFS(graph, "a", "b");
        assertTrue(pathOpt.isPresent());
        String[] expectedPath = new String[]{"a", "b"};
        String[] pathFound = pathOpt.get();


        for (int i = 0; i < expectedPath.length; i++) {
            assertEquals(expectedPath[i], pathFound[i]);
        }
    }

    @Test
    void testBasicNoPathFound() throws IOException {
        Graph graph = GraphParser
            .parseFile(Path.of("./src/test/resources/edgeCaseGraph.gka"), "testBasicNoPathFound");
        Optional<String[]> pathOpt = TraversalHelper.getShortestPathBFS(graph, "a", "c");
        assertFalse(pathOpt.isPresent());
    }

    @Test
    void testAllShortestPathsPresentfromHusum() throws IOException {
        Graph graph = GraphParser
            .parseFile(Path.of("./resource/graph03.gka"), "testAllShortestPathsPresentfromHusum");

        graph.nodes().forEach(node -> {
                Optional<String[]>
                    pathOpt = TraversalHelper.getShortestPathBFS(graph, "Husum", node.getId());
                if (!node.getId().equals("Husum")) {
                    assertTrue(pathOpt.isPresent());
                }
            }
        );
    }

    @Test
    void testAllShortestPathsCityNamesUnweighted() throws IOException {
        Graph graph = GraphParser
            .parseFile(Path.of("./src/test/resources/RegularGraph.gka"),
                "testAllShortestPathsSteps");

        testEveryPathFor(graph, false);
    }

    @Test
    void testAllShortestPathsForDirectedGraph() throws IOException {
        Graph graph = GraphParser
            .parseFile(Path.of("./resource/graph01.gka"),
                "testAllShortestPathsGraph03");

        testEveryPathFor(graph, true);
    }

    private void testEveryPathFor(Graph graph, boolean directed) {
        List<Node> allNodes = graph.nodes().collect(Collectors.toList());

        for (Node startNode : allNodes) {
            for (Node endNode : allNodes) {

                if (startNode.getId().equals(endNode.getId())) {
                    continue;
                }

                Optional<String[]>
                    pathOpt = TraversalHelper
                    .getShortestPathBFS(graph, startNode.getId(), endNode.getId());

                APSP apsp = new APSP();
                apsp.init(graph);
                apsp.setDirected(directed);
                apsp.compute();
                APSPInfo info = (APSPInfo) graph.getNode(startNode.getId())
                    .getAttribute(APSPInfo.ATTRIBUTE_NAME);

                if (info.targets.containsKey(endNode.getId())) {
                    assertTrue(pathOpt.isPresent());

                    assertEquals(pathOpt.get().length,
                        info.getShortestPathTo(endNode.getId()).size());
                } else {
                    assertTrue(pathOpt.isEmpty());
                }
            }
        }
    }


}
