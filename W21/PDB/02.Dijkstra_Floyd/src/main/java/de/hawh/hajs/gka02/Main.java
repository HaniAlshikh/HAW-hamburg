package de.hawh.hajs.gka02;

import de.hawh.hajs.gka02.util.GraphVisualizer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

/**********************************************************************
 *
 * This main class to run the application and initialize the controller window
 *
 * @author Jannik Stuckstätte
 * @author Hani Alshikh
 *
 ************************************************************************/
public class Main extends Application {

    @Override
    public void start(Stage controller) throws IOException {
        controller.setTitle("Controller");
        controller.setAlwaysOnTop(true);
        Scene controllerScene = new Scene(GraphVisualizer.loadFXML("controller"));
        controller.setScene(controllerScene);
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        controller.setX(bounds.getMinX() + (bounds.getWidth() - controllerScene.getWidth()) * 0.8);
        controller.setY(bounds.getMinY() + (bounds.getHeight() - controllerScene.getHeight()) * 0.3);
        controller.show();
        controller.setOnCloseRequest(e -> { Platform.exit(); System.exit(0); });
    }

    public static void main(String[] args) {
        launch();
    }

}