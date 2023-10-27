package com.example.project_for_university.utils;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class AlertUtil {
    public static void show(String headerText, String contentText, Stage stage) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Предупреждение");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        alert.initOwner(stage);

        alert.showAndWait();
        stage.show();
    }
}
