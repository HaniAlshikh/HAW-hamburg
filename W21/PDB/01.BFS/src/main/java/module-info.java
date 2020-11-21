module de.hawh.hajs.gka01 {
    requires javafx.controls;
    requires javafx.fxml;
    requires gs.core;
    requires gs.ui.javafx;

    opens de.hawh.hajs.gka01 to javafx.fxml;
    opens de.hawh.hajs.gka01.controllers to javafx.fxml;
    opens de.hawh.hajs.gka01.util to javafx.fxml;

    exports de.hawh.hajs.gka01;
}