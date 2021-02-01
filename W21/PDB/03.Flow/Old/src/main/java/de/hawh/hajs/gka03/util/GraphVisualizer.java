package de.hawh.hajs.gka03.util;

import de.hawh.hajs.gka03.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.fx_viewer.FxViewPanel;
import org.graphstream.ui.fx_viewer.FxViewer;
import org.graphstream.ui.javafx.FxGraphRenderer;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

/**********************************************************************
 *
 * This class provides methods to visualize and display a graph
 *
 * @author Jannik Stuckstätte
 * @author Hani Alshikh
 *
 ************************************************************************/

public class GraphVisualizer {

    private final Stage graphStage;
    private Graph graph;

    private double height = 800;
    private double width = 600;

    public GraphVisualizer() {
        System.setProperty("org.graphstream.ui", "javafx");
        graphStage = new Stage();
    }

    public GraphVisualizer(Graph graph) {
        this();
        updateGraph(graph);
    }


    /**
     * display the graph of the graph visualizer instance
     */
    public void display() {
        display(height, width);
    }


    /**
     * overload of {@link #display()} with the ability to specify
     * height and widht
     *
     * @param height stage/window height
     * @param width stage/window width
     */
    public void display(double height, double width) {
        display(new Scene(getGraphstreamPanel(graph), height, width));
    }

    /**
     * see {@link #display(List path, double height, double width)}
     *
     * @param path string array of the nodes id's containing the path
     *             from start node to end node in order
     */
    public void display(List<Node> path) {
        display(path, height, width);
    }


    /**
     * overload of {@link #display()} for shortest Path Graphs
     * which adds the path and the number of edges to the
     * end of the graph window/scene
     *
     * @param path string array of the nodes id's containing the path
     *             from start node to end node in order
     * @param height stage/window height
     * @param width stage/window width
     */
    public void display(List<Node> path, double height, double width) {
        // create the graph panel
        FxViewPanel graphViewer = GraphVisualizer.getGraphstreamPanel(graph);
        // add it to a VBox with the info labels
        VBox shortestPathVBox = new VBox(graphViewer, new VBox(new Label(
                "Shortest path form " + path.get(0).getId() + " to " + path.get(path.size()-1).getId() +
                        ": " + path.toString()), new Label(
                "Number of edges = " + (path.size() - 1))));
        // display the scene
        display(new Scene(shortestPathVBox, height, width));
    }

    /**
     * overload of {@link #display()} to display a provided scene
     *
     * @param sceneToDisplay the scene to be displayed
     */
    public void display(Scene sceneToDisplay) {
        graphStage.setScene(sceneToDisplay);
        graphStage.show();
    }


    /**
     * overload of {@link #updateGraph(Graph graph, double height, double width)}
     *
     * @param graph the graph to set and display
     */
    public void updateGraph(Graph graph) {
        updateGraph(graph, height, width);
    }


    /**
     * shortcut method to set and display the provided graph at once
     *
     * @param graph the graph to set and display
     * @param height stage/window height
     * @param width stage/window width
     */
    public void updateGraph(Graph graph, double height, double width) {
        setGraph(graph);
        display(new Scene(getGraphstreamPanel(this.graph), height, width));
    }


    /**
     * creates a subgraph of an exiting graph using a String array of nodes
     *
     * @param graph the graph to be subgraphed
     * @param nodes the nodes to pick
     *
     * @return the subgraph according to the provided nodes
     */
    public static Graph subGraphOf(Graph graph, List<Node> nodes) {
        Graph subGraph = new MultiGraph(graph.getId()+"-subGraph", false, true);
        // iterate over the provided node array and copy the node and it's edges to the new graph
        for (int i = 0; i < nodes.size()-1; i++) { // last one is out as it has no edges

            Edge edgeToCopy = nodes.get(i).getEdgeToward(nodes.get(i+1));
            Edge e = subGraph.addEdge(edgeToCopy.getId(), edgeToCopy.getSourceNode().getId(),
                    edgeToCopy.getTargetNode().getId(), edgeToCopy.isDirected());

            edgeToCopy.attributeKeys().forEach(attr -> e.setAttribute(attr, edgeToCopy.getAttribute(attr)));
        }
        return subGraph;
    }

    /**
     * parse a file to a graph and format it according to {@link #formatGraph(Graph graph)}
     *
     * @param filePath the file to be parsed
     * @param id the name/id of the graph
     *
     * @return the parsed and formatted graph
     *
     * @throws IOException if file couldn't be read successfully
     */
    public static Graph loadGraphFromFile(Path filePath, String id) throws IOException {
        return GraphParser.parseFile(filePath, id);
    }

    /**
     * helper method to improve the graph visual
     *
     * @param graph the graph to be formated
     */
    public static void formatGraph(Graph graph) {
        for (int i = 0; i < graph.getEdgeCount(); i++) {
            Edge currentEdge = graph.getEdge(i);
            StringBuilder edgeLabel = new StringBuilder(currentEdge.getNode0()+""+currentEdge.getNode1());
            if (currentEdge.getAttribute("weight") != null)
                edgeLabel.append(" ").append(currentEdge.getAttribute("weight"));
            currentEdge.setAttribute("ui.label", edgeLabel);
        }
        for (int i = 0; i < graph.getNodeCount(); i++) {
            Node currentNode = graph.getNode(i);
            currentNode.setAttribute("ui.style", "shape:circle;fill-color: grey;size: 45px;");
            currentNode.setAttribute("ui.label", currentNode.getId());
        }
    }


    /**
     * helper method to quickly parse and load fxml files
     *
     * @param fxml name of the fxml file in the resources folder without the extension
     *
     * @return Parent the Parent instance of the loaded fxml
     *
     * @throws IOException if reading/getting the file failed
     */
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }


    /**
     * helper method to get a Graphstream panel of the graph visualisation
     *
     * @param graph the graph to be visualized
     *
     * @return FxViewPanel the panel containg the visualized graph
     */
    public static FxViewPanel getGraphstreamPanel(Graph graph) {
        FxViewer graphViewer = new FxViewer(graph, FxViewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        graphViewer.enableAutoLayout();
        graph.setAttribute("ui.antialias");
        graph.setAttribute("ui.quality");
        //graph.setAttribute("ui.stylesheet", styleSheet);
        return (FxViewPanel) graphViewer.addDefaultView(false, new FxGraphRenderer());
    }


    // region ******** getter and setter ********

    public Optional<Graph> getGraph() {
        return Optional.ofNullable(graph);
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
        formatGraph(this.graph);
    }

    public Stage getGraphStage() {
        return graphStage;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    // endregion

}
