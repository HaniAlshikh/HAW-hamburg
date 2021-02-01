package de.hawh.hajs.gka02.controllers;

import de.hawh.hajs.gka02.util.GraphGenerator;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GeneratorController implements Initializable {
    public ChoiceBox<GraphGenerator.GraphType> typeChoiceBox;
    public TextField graphName;
    public TextField nodesNum;
    public TextField edgesNum;
    public TextField weightLower;
    public TextField weightUpper;
    public Button cancelBtn;
    public Button generateBtn;

    private Boolean dataProvided = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeChoiceBox.getItems().addAll(GraphGenerator.GraphType.values());
        typeChoiceBox.setValue(GraphGenerator.GraphType.DIRECTED);
    }

    public void handleCancelBtn() {
        closeStage();
    }

    public void handleGenerateBtn() {
        dataProvided = true;
        closeStage();
    }

    private void closeStage() {
        ((Stage) cancelBtn.getScene().getWindow()).close();
    }

    // region ******** getter and setter ********


    public GraphGenerator.GraphType getType() {
        return typeChoiceBox.getValue();
    }

    public String getGraphName() {
        return graphName.getText();
    }

    public int getNodesNum() {
        return Integer.parseInt(nodesNum.getText());
    }

    public int getEdgesNum() {
        return Integer.parseInt(edgesNum.getText());
    }

    public Boolean getDataProvided() {
        return dataProvided;
    }

    public double getWeightLower() {
        return Double.parseDouble(weightLower.getText());
    }

    public double getWeightUpper() {
        return Double.parseDouble(weightUpper.getText());
    }

    // endregion
}
