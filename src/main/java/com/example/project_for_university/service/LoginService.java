package com.example.project_for_university.service;

import com.example.project_for_university.Main;
import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.forBackend.entity.UserEntity;
import com.example.project_for_university.http.JsonToClass;
import com.google.gson.JsonObject;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class LoginService {

    public boolean login(String email, String password, Label status_lbl, AllValues allValues) throws IOException {
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
        switch (response.getStatusLine().getStatusCode()) {
            case 404:
                status_lbl.setText("Пользователь с таким email не найден!");
                break;
            case 401:
                status_lbl.setText("Вы ввели неверный пароль, попробуйте ещё раз!");
                break;
            case 400:
                status_lbl.setText("Вы ввели некорректный email!");
                break;
            case 201:
                allValues.setUser(JsonToClass.parseToObject(UserEntity.class, response));
                allValues.getUser().setPassword(password);
                return true;
            default:
                status_lbl.setText("Произошла ошибка, попробуйте ещё раз!");
        }

        return false;
    }
}
