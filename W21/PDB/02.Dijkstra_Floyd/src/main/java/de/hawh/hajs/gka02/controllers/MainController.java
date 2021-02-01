package de.hawh.hajs.gka02.controllers;


import de.hawh.hajs.gka02.Main;
import de.hawh.hajs.gka02.util.GraphGenerator;
import de.hawh.hajs.gka02.util.GraphParser;
import de.hawh.hajs.gka02.util.GraphVisualizer;
import de.hawh.hajs.gka02.util.TraversalHelper;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**********************************************************************
 *
 * This class provides the backend functionality for the controller window
 *
 * @author Jannik Stuckstätte
 * @author Hani Alshikh
 *
 ************************************************************************/

// Controllers are initialized (loaded) when the window loads
public class MainController implements Initializable {

    // according to the documentation having Controller elements
    // public is normally ok. declaring them private requires
    // extra @FXML tag
    public Button saveGraph;
    public Button loadGraph;
    public Button findShortestPath;
    public Button generateGraph;

    private static final GraphVisualizer mainGraph = new GraphVisualizer();
    private final GraphVisualizer shortestPathGraph = new GraphVisualizer();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}


    /**
     * displays a file chooser and load the file as a graph in a separate window/stage
     *
     * Note: allowed file extension is .gka
     * 
     * @throws IOException if reading/getting the file was not successful
     */
    public void handleLoadGraphBtn() throws IOException {
        File file = chooseFile("Please choose the .gka file",
                new FileChooser.ExtensionFilter("GKA", "*.gka"));

        if (file != null) { // in case no file was chosen (clicked cancel)
        mainGraph.updateGraph(GraphVisualizer.loadGraphFromFile(file.toPath(),
                // remove the extension from of the name.
                file.getName().replaceFirst("[.][^.]+$", "")));
        findShortestPath.setDisable(false);
        saveGraph.setDisable(false);
        }
    }

    public void handleGenerateGraphBtn() throws IOException {

        // get the shortest path Controller which contains all data like from and to nodes
        GeneratorController gc = askForGeneratorInfo();
        // skip if cancel is clicked
        if (gc.getDataProvided()) {
            mainGraph.updateGraph(
                    GraphGenerator.generate(gc.getGraphName(), gc.getNodesNum(), gc.getEdgesNum(),
                            gc.getWeightLower(), gc.getWeightUpper(), gc.getType()));
            findShortestPath.setDisable(false);
            saveGraph.setDisable(false);
        }
    }

    /**
     * ask for the from and to nodes, calculate the shortest path
     * and display it if found otherwise alert
     * 
     * @throws IOException if fxml loader failed to load the controller
     */
    public void handleFindShortestPathBtn() throws IOException {
        // get the shortest path Controller which contains all data like from and to nodes
        ShortestPathController spc = askForFromToNodes();
        // skip if cancel is clicked
        if (spc.getDataProvided()) {
            // calculate the shortest path between chosen nodes
            Optional<List<Node>> path = TraversalHelper
                    .getShortestPath(spc.getFromNode(),spc.getToNode(), spc.getAlgoToUse());
            // display or alert
            if (path.isPresent()) {
                shortestPathGraph.setGraph(GraphVisualizer.subGraphOf(mainGraph.getGraph().orElseThrow(), path.get()));
                shortestPathGraph.display(path.get(), 400, 300);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No path found");
                alert.setHeaderText("The path between "+spc.getFromNode()+" and "+spc.getToNode()+" doesn't exists");
                alert.show();
            }
        }
    }


    /**
     * saves open graph to a .gka file.
     * if multiple graphs are open asks for the graph to be saved
     */
    public void saveGraph() {
        // shortest path graph is an option
        if (shortestPathGraph.getGraph().isPresent()) {
            Dialog<Graph> dialog = new ChoiceDialog<>(
                    shortestPathGraph.getGraph().get(),
                    mainGraph.getGraph().orElseThrow(),
                    shortestPathGraph.getGraph().get());

            dialog.setTitle("Please choose which graph to save");
            dialog.setHeaderText("Available graphs");
            Optional<Graph> result = dialog.showAndWait();
            result.ifPresent(GraphParser::saveToFile);
        } else {
            GraphParser.saveToFile(mainGraph.getGraph().orElseThrow());
        }
    }


    /**
     * helper method to display a choice dialog to get the from and to nodes
     *
     * @return ShortestPathController the controller instance, which contains the data
     * @throws IOException if fxml loader failed to load the controller
     */
    private static ShortestPathController askForFromToNodes() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("shortestPathController.fxml"));
        Stage shortestPath = new Stage();
        shortestPath.setTitle("Please choose the nodes");
        Scene shortestPathScene = new Scene(fxmlLoader.load());
        ShortestPathController spc = fxmlLoader.getController();
        shortestPath.setScene(shortestPathScene);
        shortestPath.showAndWait();
        return spc;
    }


    private static GeneratorController askForGeneratorInfo() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("generatorController.fxml"));
        Stage generatorController = new Stage();
        generatorController.setTitle("Graph Generator");
        Scene generatorControllerScene = new Scene(fxmlLoader.load());
        GeneratorController gc = fxmlLoader.getController();
        generatorController.setScene(generatorControllerScene);
        generatorController.showAndWait();
        return gc;
    }

    /**
     * helper method to open a chooser dialog
     *
     * @param title the title of the file chooser dialog
     * @param extensionFilter array of extension filter objects
     *
     * @return the choosen file
     */
    public static File chooseFile(String title, FileChooser.ExtensionFilter... extensionFilter) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.getExtensionFilters().addAll(extensionFilter);
        return fileChooser.showOpenDialog(new Stage());
    }

    // region ******** getter and setter ********

    public static GraphVisualizer getMainGraph() {
        return mainGraph;
    }

    // endregion
}
