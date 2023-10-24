package com.example.project_for_university;

import com.example.project_for_university.utils.ControllerUtils;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static StringProperty host = new SimpleStringProperty("http://localhost:9000");

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(ControllerUtils.loginRoute));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Вход");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}