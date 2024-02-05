package com.example.project_for_university.service;

import com.example.project_for_university.Main;
import com.example.project_for_university.dto.forBackend.LoginDto;
import com.example.project_for_university.dto.forBackend.create.CreateUserDto;
import com.example.project_for_university.dto.forBackend.entity.UserEntity;
import com.example.project_for_university.enums.ErrorMessage;
import com.example.project_for_university.enums.ServiceEnum;
import com.example.project_for_university.enums.UrlRoutes;
import com.example.project_for_university.http.JsonToClass;
import com.example.project_for_university.service.models.AuthResponse;
import com.example.project_for_university.utils.ExceptionMessageUtil;
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

    private AuthResponse user = new AuthResponse();

    public AuthResponse loginThread(LoginDto loginDto) throws ExecutionException, InterruptedException {
        CompletableFuture<AuthResponse> futureUserModel = new CompletableFuture<>();

        Runnable runnable = new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                CloseableHttpResponse response;
                try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("email", loginDto.getEmail());
                    jsonObject.addProperty("password", loginDto.getPassword());

                    HttpUriRequest httpPost = RequestBuilder.post()
                            .setUri(Main.host.getValue() + UrlRoutes.AUTH_LOGIN.getName())
                            .setHeader("Content-Type", "application/json")
                            .setEntity(new StringEntity(jsonObject.toString()))
                            .build();

                    response = httpClient.execute(httpPost);
                }
                if (response.getStatusLine().getStatusCode() != 201) {
                    user.setError(true);
                    user.setMessage(ExceptionMessageUtil.getErrorMessage(ServiceEnum.AUTH_LOGIN, response.getStatusLine().getStatusCode(), null));
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

    public AuthResponse signupThread(CreateUserDto userDto) throws ExecutionException, InterruptedException {
        CompletableFuture<AuthResponse> futureUserModel = new CompletableFuture<>();

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
                        .setUri(Main.host.getValue() + UrlRoutes.AUTH_SIGNUP.getName())
                        .setHeader("Content-Type", "application/json")
                        .setEntity(new StringEntity(jsonObject.toString()))
                        .build();

                CloseableHttpResponse response = httpClient.execute(httpPost);
                if (response.getStatusLine().getStatusCode() != 201) {
                    user.setError(true);
                    user.setMessage(ExceptionMessageUtil.getErrorMessage(ServiceEnum.AUTH_SIGNUP, response.getStatusLine().getStatusCode(), null));
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
}
