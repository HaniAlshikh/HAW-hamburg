package de.hawh.hajs.gka02.controllers;

import de.hawh.hajs.gka02.util.TraversalHelper;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import org.graphstream.graph.Node;

import java.net.URL;
import java.util.ResourceBundle;

/**********************************************************************
 *
 * This class provides the backend functionality for the node chooser dialog
 *
 * @author Jannik Stuckstätte
 * @author Hani Alshikh
 *
 ************************************************************************/

public class ShortestPathController implements Initializable {

    public ChoiceBox<Node> fromNodeChoiceBox;
    public ChoiceBox<Node> toNodeChoiceBox;
    public ChoiceBox<TraversalHelper.Algorithm> usingChoiceBox;
    public Button cancelBtn;
    public Button findBtn;

    private Boolean dataProvided = false;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // fill the choiceBox with the current graph nodes
        Node[] nodes = MainController.getMainGraph().getGraph().orElseThrow().nodes().toArray(Node[]::new);
        fromNodeChoiceBox.getItems().addAll(nodes);
        fromNodeChoiceBox.setValue(nodes[0]);
        toNodeChoiceBox.getItems().addAll(nodes);
        toNodeChoiceBox.setValue(nodes[1]);
        usingChoiceBox.getItems().addAll(TraversalHelper.Algorithm.values());
        usingChoiceBox.setValue(TraversalHelper.Algorithm.DIJKSTRA);
    }


    /**
     * get the chosen nodes and close the dialog
     */
    public void handleFindBtnEvent() {
        this.dataProvided = true;
        closeStage();
    }

    /**
     * close the dialog and ignore the chosen data
     */
    public void handleCancelBtnEvent() {
        closeStage();
    }

    private void closeStage() {
        ((Stage) cancelBtn.getScene().getWindow()).close();
    }

    // region ******** getter and setter ********

    public Boolean getDataProvided() {
        return dataProvided;
    }

    public Node getFromNode() {
        return fromNodeChoiceBox.getValue();
    }
    public Node getToNode() {
        return toNodeChoiceBox.getValue();
    }

    public TraversalHelper.Algorithm getAlgoToUse() {
        return usingChoiceBox.getValue();
    }

    // endregion
}
