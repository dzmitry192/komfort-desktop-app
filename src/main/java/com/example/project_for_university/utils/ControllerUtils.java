package com.example.project_for_university.utils;

import com.example.project_for_university.controllers.user.ChooseOpController;
import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.enums.Page;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ControllerUtils {

    public static void changePage(Page page, StackPane contentArea, AllValues allValues, Node node) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(node.getClass().getResource(Objects.requireNonNull(page.getPath())));
        fxmlLoader.setUserData(allValues);
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxmlLoader);
    }
}