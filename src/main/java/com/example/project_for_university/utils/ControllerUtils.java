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

    public static void changePage(Page page, StackPane contentArea, AllValues allValues) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(ControllerUtils.class.getResource(Objects.requireNonNull(page.getPath())));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxmlLoader);

//        FXMLLoader fxmlLoader = new FXMLLoader(node.getClass().getResource(Objects.requireNonNull(page.getPath())));
//        Parent parent = new FXMLLoader(node.getClass().getResource(Objects.requireNonNull(page.getPath())));
//
//        if (controllerClass.equals(LoginController.class)) {
//            ((LoginController) fxmlLoader.getController()).setData(allValues);
//        } else if (controllerClass.equals(ChooseOpController.class)) {
//            ((ChooseOpController) fxmlLoader.getController()).setData(allValues);
//        } else if (controllerClass.equals(AdminController.class)) {
//            ((AdminController) fxmlLoader.getController()).setData(allValues);
//        } else if (controllerClass.equals(FConditionController.class)) {
//            ((FConditionController) fxmlLoader.getController()).setData(allValues);
//        } else if (controllerClass.equals(SConditionController.class)) {
//            ((SConditionController) fxmlLoader.getController()).setData(allValues);
//        } else if (controllerClass.equals(ResultTableController.class)) {
//            ((ResultTableController) fxmlLoader.getController()).setData(allValues);
//        } else if (controllerClass.equals(FilterController.class)) {
//            ((FilterController) fxmlLoader.getController()).setData(allValues);
//        } else if (controllerClass.equals(MaterialInfoController.class)) {
//            ((MaterialInfoController) fxmlLoader.getController()).setData(allValues);
//        } else if(controllerClass.equals(PhTypeController.class)) {
//            ((PhTypeController) fxmlLoader.getController()).setData(allValues);
//        }
//
//        contentArea.getChildren().removeAll();
//        contentArea.getChildren().setAll(parent);

    }
}