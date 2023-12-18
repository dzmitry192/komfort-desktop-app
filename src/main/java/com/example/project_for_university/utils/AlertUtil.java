package com.example.project_for_university.utils;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

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

    public static boolean showConfirmation(String headerText, String contentText, Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтверждение");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        alert.initOwner(stage);

        Optional<ButtonType> result = alert.showAndWait();
        stage.show();

        return result.isPresent() && result.get() == ButtonType.OK;
    }
}
