package com.example.project_for_university.service;

import com.example.project_for_university.Main;
import com.example.project_for_university.controllers.user.ChooseOpController;
import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.forBackend.entity.UserEntity;
import com.example.project_for_university.http.JsonToClass;
import com.google.gson.JsonObject;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class LoginService {

    public void login(String email, String password, Label label, MouseEvent event, AllValues allValues) throws IOException {
//        if (true) {
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            stage.close();
//            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/com/example/project_for_university/fxml/user/choose-operation.fxml"));
//            Scene scene = new Scene(fxmlLoader.load());
//            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            window.setScene(scene);
//            window.show();
//
//            allValues.setUser(new UserEntity(1, "rummo d s", "dfsf@aynd.by", "123", true));
//
//            ((ChooseOpController) fxmlLoader.getController()).setData(allValues);
//            return;
//        }

        if(email != "" && password != "") {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("email", email);
            jsonObject.addProperty("password", password);
            HttpUriRequest httpPost = RequestBuilder.post()
                    .setUri(Main.host.getValue() + "/auth/login")
                    .setHeader("Content-Type", "application/json")
                    .setEntity(new StringEntity(jsonObject.toString()))
                    .build();
            CloseableHttpResponse response = httpClient.execute(httpPost);
            if(response.getStatusLine().getStatusCode() == 201) {
                allValues.setUser(JsonToClass.parseToObject(UserEntity.class, response));
                allValues.getUser().setPassword(password);
//                ControllerUtils.changeWindow(this, 3, event, "Программа для расчётов");
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/com/example/project_for_university/fxml/user/choose-operation.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
                ((ChooseOpController) fxmlLoader.getController()).setData(allValues);
            } else {
                label.setText("Пользователя с такими данными не найдено!");
                label.setTextFill(Color.RED);
            }
        } else {
            label.setText("Заполните все поля!");
            label.setTextFill(Color.RED);
        }
    }
}
