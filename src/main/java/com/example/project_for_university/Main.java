package com.example.project_for_university;

import com.example.project_for_university.enums.Component;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class Main extends Application {

    public static StringProperty host = new SimpleStringProperty("https://komfort.isit.my.to");
//    public static StringProperty host = new SimpleStringProperty("http://localhost");

    public static CloseableHttpClient httpClient = getHttpClient();

    private static CloseableHttpClient getHttpClient() {
        if(httpClient == null) {
            SocketConfig socketConfig = SocketConfig.custom().setSoKeepAlive(true).setSoTimeout(3600000).build();
            httpClient = HttpClients.custom()
                    .setDefaultSocketConfig(socketConfig)
                    .build();
        }
        return httpClient;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(Component.MAIN.getPath()));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("KOMFORT (Комплексная оценка материалов по функционально обоснованному расчету требований)");
        stage.setScene(scene);
        stage.setWidth(1250);
        stage.setMinWidth(1000);
        stage.setHeight(825);
        stage.setMinHeight(650);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}