package de.hawh.hajs.gka03.util;

import org.graphstream.graph.Element;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class GraphParserTest {

    @Test
    void testParseFile() throws IOException {
        Graph graph = GraphParser
                .parseFile(Path.of("./src/test/resources/edgeCaseGraph.gka"), "Test");
        assertEquals(10, graph.getEdgeCount());
        assertEquals(15, graph.getNodeCount());

        Node nodeA = graph.getNode("a");
        // directed
        assertTrue(nodeA.getEdgeToward("b").isDirected());
        // not directed
        assertFalse(nodeA.getEdgeToward("X").isDirected());
    }

    @Test
    void testSaveToFile() {
        // test against all provided gka's
        File[] files = Objects.requireNonNull(new File("resource").listFiles());
        Arrays.stream(files).sequential()
                .filter(f -> f.getName().endsWith(".gka"))
                .forEach(f -> {
                    try {
                        // parse graph
                        Graph graph = GraphParser.parseFile(f.toPath(), "Test");
                        int nodeCount = graph.getNodeCount();
                        int edgeCount = graph.getEdgeCount();
                        // save and reread to check
                        File savedFile = GraphParser.saveToFile(graph);
                        Graph graphSavedToFile = GraphParser.parseFile(savedFile.toPath(), "Test");
                        assertEquals(nodeCount, graphSavedToFile.getNodeCount());
                        assertEquals(edgeCount, graphSavedToFile.getEdgeCount());
                        // Check if id on every node is equal
                        assertEquals(graph.nodes().map(Element::getId).collect(Collectors.toSet()),
                                graphSavedToFile.nodes().map(Element::getId).collect(Collectors.toSet()));
                        // Check if id on every edge is equal
                        assertEquals(graph.edges().map(Element::getId).collect(Collectors.toSet()),
                                graphSavedToFile.edges().map(Element::getId).collect(Collectors.toSet()));
                        // Check if directed on every edge is equal
                        assertEquals(
                                graph.edges().map(edge -> Map.entry(edge.getId(), edge.isDirected()))
                                        .collect(Collectors.toSet()),
                                graphSavedToFile.edges()
                                        .map(edge -> Map.entry(edge.getId(), edge.isDirected()))
                                        .collect(Collectors.toSet()));
                        // Check if weight on every edge is equal
                        assertEquals(graph.edges().filter(edge -> edge.hasAttribute("wight"))
                                        .map(edge -> Map.entry(edge.getId(), edge.getAttribute("wight")))
                                        .collect(Collectors.toSet()),
                                graphSavedToFile.edges().filter(edge -> edge.hasAttribute("wight"))
                                        .map(edge -> Map.entry(edge.getId(), edge.getAttribute("wight")))
                                        .collect(Collectors.toSet()));
                        savedFile.deleteOnExit();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
    }
}