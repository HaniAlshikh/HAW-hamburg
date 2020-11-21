package de.hawh.hajs.gka01.util;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**********************************************************************
 *
 * This utility class provides methods to parse and save .gka files/graphs
 *
 * @author Jannik Stuckstätte
 * @author Hani Alshikh
 *
 ************************************************************************/

public final class GraphParser {

    /**
     * the main pattern for parsing .gka files
     *
     * Groups: a -> b (c) : 0
     *     01. a                    "node1"
     *     02. -> b (c) : 0         "edgePart"
     *     03. ->                   "type"
     *     04. b                    "node"
     *     05. (c)
     *     06. c                    "edgeName"
     *     07. : 0
     *     08. 0                    "wight"
     *
     */
    private static final Pattern validElementPattern = Pattern.compile(
            "^(?<node1>\\w+)(?<edgePart>\\s*(?<type>-[->])\\s*(?<node2>\\w+)" +
                    "(\\s*\\((?<edgeName>\\w+)\\))?(\\s*:\\s*(?<wight>[0-9]+)?)?)?;([\\s\\t]*//.*)?$",
            Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CHARACTER_CLASS);

    private GraphParser() {
        throw new IllegalStateException(
            String.format("%s cannot be instantiated!", GraphParser.class.getName()));
    }


    /**
     * parse a given file and generate it's respected graph according to the following schema
     *
     * directed
     * <name node1>[ -> <name node2>[(<edge name>)][: <edgeweight>]];
     * not directed
     * <name node1>[ -- <name node2> [(<edge name>)][: <edgeweight>]];
     *
     * @param filePath to the file to be parsed
     * @param id of the parsed graph
     *
     * @return Graph the parsed graph
     * @throws IOException if file couldn't be read or processed
     */
    public static Graph parseFile(Path filePath, String id) throws IOException {
        // setup base graph
        // TODO: experement with Graph insted of MultiGraph
        Graph graph = new MultiGraph(id, false, true);

        // read and parse data
        try (Stream<String> lines = Files.lines(filePath, StandardCharsets.UTF_8)) {
            // keep count of the douplicated edge id's
            // atomic as it is used in a stream
            AtomicInteger dupCounter = new AtomicInteger();
            lines.forEach(line -> {
                Matcher matcher = validElementPattern.matcher(line);
                while (matcher.find()) {
                    if (matcher.group("edgePart") == null) {
                        graph.addNode(matcher.group("node1"));
                        continue;
                    }

                    // edgeID
                    boolean idGenerated = false; // helper to resale files in original state
                    String edgeID = matcher.group("edgeName");
                    if (edgeID == null) { // if no edge name/id was provided generate one
                        edgeID = matcher.group("node1") + "" + matcher.group("node2");
                        idGenerated = true;
                    } else {
                        String finalEdgeID = edgeID;
                        if (graph.edges().anyMatch(
                            e -> e.getId().equals(finalEdgeID))) // we have duplicate edge name
                        {
                            edgeID +=
                                "_dup" + (dupCounter.incrementAndGet()); // suffix it with _dup
                        }
                    }

                    // add edges and needed nodes
                    Edge edge = graph.addEdge(edgeID,
                            matcher.group("node1"),
                            matcher.group("node2"),
                            matcher.group("type").equals("->"));

                    // saveToFile helper attribute
                    edge.setAttribute("idGenerated", idGenerated);

                    // set wight
                    if (matcher.group("wight") != null) {
                        int wight = Integer.parseInt(matcher.group("wight"));
                        if (wight >= 0) {
                            edge.setAttribute("wight", wight);
                        }
                    }
                }
            });
        } catch (IOException ex) {
            System.out.printf("Couldn't read file '%s'%n", filePath.toString());
            System.out.println(ex.getMessage());
            throw ex;
        }
        return graph;
    }

    /**
     * saves a graph to a .gka file according to the following schema
     *
     * directed
     * <name node1>[ -> <name node2>[(<edge name>)][: <edgeweight>]];
     * not directed
     * <name node1>[ -- <name node2> [(<edge name>)][: <edgeweight>]];
     *
     * @param graph the graph to be saved
     *
     * @return the file to which the graph was saved
     */
    public static File saveToFile(Graph graph) {
        try (BufferedWriter writer = new BufferedWriter(
            new FileWriter(graph.getId() + "-saved.gka"))) {
            graph.nodes().filter(node -> node.edges().count() == 0).forEach(freeNode -> {
                try {
                    writer.append(freeNode.getId())
                        .append(";");                                           // 1 -> b (ab) : 10;
                    writer.newLine();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });

            graph.edges().forEach(e -> {
                try {
                    writer.append(e.getNode0().getId())                             // a
                            .append(" ").append(e.isDirected() ? "->" : "--")       // -[->]
                            .append(" ").append(e.getNode1().getId())               // b
                            .append(e.hasAttribute("idGenerated") ?              // (ab)
                            e.getAttribute("idGenerated", Boolean.class) ? ""
                                : " (" + e.getId() + ")" : "")
                            .append(e.hasAttribute("wight") ?                    // : 10
                            e.getAttribute("wight") == null ? "" : " : " + e.getAttribute("wight")
                            : "")
                            .append(";");                                           // 1 -> b (ab) : 10;
                    writer.newLine();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
        } catch (IOException ioException) {
            System.out.println("An error has occurred while trying to save");
            ioException.printStackTrace();
        }
        return new File(graph.getId() + "-saved.gka");
    }

}