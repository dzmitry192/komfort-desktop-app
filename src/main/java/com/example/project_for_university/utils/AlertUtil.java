package com.example.project_for_university.utils;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.util.Optional;

public class AlertUtil {
    public static void show(String headerText, String contentText, Stage stage) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Предупреждение");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
        alert.initOwner(stage);

        DialogPane dialogPane = alert.getDialogPane();

        ButtonType btn_ok = new ButtonType("Ок");

        dialogPane.getButtonTypes().clear();
        dialogPane.getButtonTypes().add(btn_ok);

        dialogPane.lookupButton(btn_ok).getStyleClass().add("btn-blue-filled");
        dialogPane.getStyleClass().add("alert-dialog");

        dialogPane.getStylesheets().add(AlertUtil.class.getResource("/com/example/project_for_university/styles/shared.css").toExternalForm());

        alert.showAndWait();
        stage.show();
    }

    public static boolean showConfirmation(String headerText, String contentText, Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтверждение");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.getDialogPane().setMinWidth(Region.USE_PREF_SIZE);
        alert.initOwner(stage);


        DialogPane dialogPane = alert.getDialogPane();

        ButtonType btn_cancel = new ButtonType("Отменить");
        ButtonType btn_ok = new ButtonType("Ок");

        dialogPane.getButtonTypes().clear();
        dialogPane.getButtonTypes().addAll(btn_cancel, btn_ok);

        dialogPane.lookupButton(btn_cancel).getStyleClass().add("btn-red-filled");
        dialogPane.lookupButton(btn_ok).getStyleClass().add("btn-blue-filled");
        dialogPane.getStyleClass().add("alert-dialog");

        dialogPane.getStylesheets().add(AlertUtil.class.getResource("/com/example/project_for_university/styles/shared.css").toExternalForm());
        alert.getDialogPane().lookup(".content.label").setStyle("-fx-wrap-text: true;");

        Optional<ButtonType> result = alert.showAndWait();
        stage.show();

        return result.isPresent() && result.get() == btn_ok;
    }
}
