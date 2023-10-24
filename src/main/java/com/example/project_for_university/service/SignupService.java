package com.example.project_for_university.service;

import com.example.project_for_university.Main;
import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.forBackend.entity.UserEntity;
import com.example.project_for_university.http.JsonToClass;
import com.google.gson.JsonObject;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class SignupService {
    public void signup(String fio, String email, String password, Label status_lbl, AllValues allValues) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("fio", fio);
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("password", password);

        HttpUriRequest httpPost = RequestBuilder.post()
                .setUri(Main.host.getValue() + "/auth/signup")
                .setHeader("Content-Type", "application/json")
                .setEntity(new StringEntity(jsonObject.toString()))
                .build();

        CloseableHttpResponse response = httpClient.execute(httpPost);
        switch (response.getStatusLine().getStatusCode()) {
            case 401:
                status_lbl.setText("Пользователь с таким email уже существует!");
                break;
            case 400:
                status_lbl.setText("Вы ввели некорректный email!");
                break;
            case 201:
                status_lbl.setTextFill(Color.GREEN);
                status_lbl.setText("Регистрация прошла успешно!");
                allValues.setUser(JsonToClass.parseToObject(UserEntity.class, response));
                allValues.getUser().setPassword(password);
                break;
            default:
                status_lbl.setText("Произошла ошибка, попробуйте ещё раз!");
        }
    }
}
