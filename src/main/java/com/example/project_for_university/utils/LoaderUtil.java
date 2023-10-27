package com.example.project_for_university.utils;

import com.example.project_for_university.controllers.loader.LoaderController;
import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.enums.Component;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class LoaderUtil {
    public static void mount(StackPane loaderContentPane) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoaderUtil.class.getResource(Objects.requireNonNull(Component.LOADER.getPath())));
        Parent parent = fxmlLoader.load();

        loaderContentPane.getChildren().removeAll();
        loaderContentPane.getChildren().setAll(parent);
    }

    public static void unmount(StackPane loaderContentPane) throws IOException {
        loaderContentPane.getChildren().removeAll();
        loaderContentPane.getChildren().setAll();
    }

    public static void showModal(Stage rootStage, AllValues allValues) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoaderUtil.class.getResource(Component.LOADER.getPath()));
        Parent modal = fxmlLoader.load();

        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL);

        modalStage.initOwner(rootStage);
        modalStage.initStyle(StageStyle.UNDECORATED);
        modalStage.setHeight(100);
        modalStage.setWidth(100);

        allValues.setLoaderStage(modalStage);

        Scene modalScene = new Scene(modal);
        modalStage.setScene(modalScene);

        double primaryX = rootStage.getX();
        double primaryY = rootStage.getY();
        double primaryWidth = rootStage.getWidth();
        double primaryHeight = rootStage.getHeight();

        double centerX = primaryX + primaryWidth / 2 - modalStage.getWidth() / 2;
        double centerY = primaryY + primaryHeight / 2 - modalStage.getHeight() / 2;

        modalStage.setX(centerX);
        modalStage.setY(centerY);

        modalStage.showAndWait();
    }

    public static void closeModal(Stage loaderStage) {
        loaderStage.close();
    }
}
