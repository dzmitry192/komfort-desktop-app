package com.example.project_for_university.service;

import com.example.project_for_university.Main;
import com.example.project_for_university.dto.forBackend.LoginDto;
import com.example.project_for_university.dto.forBackend.create.CreateUserDto;
import com.example.project_for_university.dto.forBackend.entity.UserEntity;
import com.example.project_for_university.http.JsonToClass;
import com.example.project_for_university.service.models.UserModel;
import com.google.gson.JsonObject;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Data
public class AuthService {

    private UserModel user = new UserModel();

    public UserModel loginThread(LoginDto loginDto) throws ExecutionException, InterruptedException {
        CompletableFuture<UserModel> futureUserModel = new CompletableFuture<>();

        Runnable runnable = new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                CloseableHttpClient httpClient = HttpClients.createDefault();

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("email", loginDto.getEmail());
                jsonObject.addProperty("password", loginDto.getPassword());

                HttpUriRequest httpPost = RequestBuilder.post()
                        .setUri(Main.host.getValue() + "/auth/login")
                        .setHeader("Content-Type", "application/json")
                        .setEntity(new StringEntity(jsonObject.toString()))
                        .build();

                CloseableHttpResponse response = httpClient.execute(httpPost);
                if (response.getStatusLine().getStatusCode() != 201) {
                    user.setError(true);
                    user.setErrorType(response.getStatusLine().getStatusCode());
                    user.setErrorMessage(getLoginErrorMessage(response.getStatusLine()));
                } else {
                    user.setError(false);
                    UserEntity userEntity = JsonToClass.parseToObject(UserEntity.class, response);
                    userEntity.setPassword(loginDto.getPassword());
                    user.setUser(userEntity);
                }
                futureUserModel.complete(user);
            }
        };

        Thread loginThread = new Thread(runnable);
        loginThread.start();

        return futureUserModel.get();
    }

    public UserModel signupThread(CreateUserDto userDto) throws ExecutionException, InterruptedException {
        CompletableFuture<UserModel> futureUserModel = new CompletableFuture<>();

        Runnable runnable = new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                CloseableHttpClient httpClient = HttpClients.createDefault();

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("fio", userDto.getFio());
                jsonObject.addProperty("email", userDto.getEmail());
                jsonObject.addProperty("password", userDto.getPassword());

                HttpUriRequest httpPost = RequestBuilder.post()
                        .setUri(Main.host.getValue() + "/auth/signup")
                        .setHeader("Content-Type", "application/json")
                        .setEntity(new StringEntity(jsonObject.toString()))
                        .build();

                CloseableHttpResponse response = httpClient.execute(httpPost);
                if (response.getStatusLine().getStatusCode() != 201) {
                    user.setError(true);
                    user.setErrorType(response.getStatusLine().getStatusCode());
                    user.setErrorMessage(getSignupErrorMessage(response.getStatusLine()));
                } else {
                    user.setError(false);
                    UserEntity userEntity = JsonToClass.parseToObject(UserEntity.class, response);
                    userEntity.setPassword(userDto.getPassword());
                    user.setUser(userEntity);
                }
                futureUserModel.complete(user);
            }
        };

        Thread signupThread = new Thread(runnable);
        signupThread.start();

        return futureUserModel.get();
    }

    public String getLoginErrorMessage(StatusLine status) {
        switch (status.getStatusCode()) {
            case 400:
                return "Некорректный email";
            case 401:
                return "Неверный пароаль";
            case 404:
                return "Пользователь с таким email не найден";
            default:
                return "Непредвиденная ошибка";
        }
    }

    public String getSignupErrorMessage(StatusLine status) {
        switch (status.getStatusCode()) {
            case 400:
                return "Некорректный email";
            case 401:
                return "Пользователь с таким email уже существует";
            case 404:
                return "Пользователь с таким email не найден";
            default:
                return "Непредвиденная ошибка";
        }
    }
}
