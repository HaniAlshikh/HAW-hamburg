package de.hawh.hajs.gka01.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import org.graphstream.graph.Element;

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

    public ChoiceBox<String> fromNodeChoiceBox;
    public ChoiceBox<String> toNodeChoiceBox;
    public Button cancelBtn;
    public Button findBtn;

    private String fromNode;
    private String toNode;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // fill the choiceBox with the current graph nodes
        String[] nodes = MainController.getMainGraph().getGraph().orElseThrow()
                .nodes().map(Element::getId).toArray(String[]::new);
        fromNodeChoiceBox.getItems().addAll(nodes);
        fromNodeChoiceBox.setValue(nodes[0]);
        toNodeChoiceBox.getItems().addAll(nodes);
        toNodeChoiceBox.setValue(nodes[1]);
    }


    /**
     * get the chosen nodes and close the dialog
     */
    public void handleFindBtnEvent() {
        fromNode = fromNodeChoiceBox.getValue();
        toNode = toNodeChoiceBox.getValue();
        ((Stage) findBtn.getScene().getWindow()).close();
    }


    /**
     * close the dialog and ignore the chosen data
     */
    public void handleCancelBtnEvent() {
        ((Stage) cancelBtn.getScene().getWindow()).close();
    }

    // region ******** getter and setter ********

    public String getFromNode() {
        return fromNode;
    }
    public String getToNode() {
        return toNode;
    }

    // endregion
}
