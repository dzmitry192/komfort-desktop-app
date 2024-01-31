package com.example.project_for_university.service.admin;

import com.example.project_for_university.Main;
import com.example.project_for_university.controllers.user.admin.models.PhType;
import com.example.project_for_university.dto.forBackend.entity.types.WashingTypeEntity;
import com.example.project_for_university.enums.UrlRoutes;
import com.example.project_for_university.http.JsonToClass;
import com.example.project_for_university.interfaces.CrudService;
import com.example.project_for_university.utils.AuthUtils;
import com.google.gson.JsonObject;
import lombok.SneakyThrows;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

public class WashingTypeService implements CrudService<WashingTypeEntity> {

    @SneakyThrows
    @Override
    public WashingTypeEntity[] getAll(String email, String password) {
        CompletableFuture<WashingTypeEntity[]> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                HttpUriRequest httpGet = RequestBuilder.get()
                        .setUri(Main.host.getValue() + UrlRoutes.GET_WASHING_TYPES.getName())
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .build();

                try {
                    WashingTypeEntity[] washingTypeEntities;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        washingTypeEntities = JsonToClass.parseToListObject(WashingTypeEntity.class, response).toArray(WashingTypeEntity[]::new);
                    }
                    futureTypeList.complete(washingTypeEntities);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getWashingTypesThread = new Thread(runnable);
        getWashingTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public WashingTypeEntity getById(int id, String email, String password) {
        CompletableFuture<WashingTypeEntity> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                HttpUriRequest httpGet = RequestBuilder.get()
                        .setUri(Main.host.getValue() + UrlRoutes.GET_WASHING_TYPE_BY_ID.getName() + id)
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .build();

                try {
                    WashingTypeEntity washingTypeEntity;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        washingTypeEntity = JsonToClass.parseToObject(WashingTypeEntity.class, response);
                    }
                    futureTypeList.complete(washingTypeEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getWashingTypesThread = new Thread(runnable);
        getWashingTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public WashingTypeEntity create(PhType phType, String email, String password) {
        CompletableFuture<WashingTypeEntity> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", phType.getName());

                HttpUriRequest httpGet = RequestBuilder.post()
                        .setUri(Main.host.getValue() + UrlRoutes.POST_WASHING_TYPE.getName())
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .setEntity(new StringEntity(jsonObject.toString(), StandardCharsets.UTF_8))
                        .build();

                try {
                    WashingTypeEntity washingTypeEntity;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        washingTypeEntity = JsonToClass.parseToObject(WashingTypeEntity.class, response);
                    }
                    futureTypeList.complete(washingTypeEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getWashingTypesThread = new Thread(runnable);
        getWashingTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public WashingTypeEntity update(PhType phType, String email, String password) {
        CompletableFuture<WashingTypeEntity> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", phType.getName());

                HttpUriRequest httpGet = RequestBuilder.patch()
                        .setUri(Main.host.getValue() + UrlRoutes.PATCH_WASHING_TYPE_BY_ID.getName() + phType.getId())
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .setEntity(new StringEntity(jsonObject.toString(), StandardCharsets.UTF_8))
                        .build();

                try {
                    WashingTypeEntity washingTypeEntity;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        washingTypeEntity = JsonToClass.parseToObject(WashingTypeEntity.class, response);
                    }
                    futureTypeList.complete(washingTypeEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getWashingTypesThread = new Thread(runnable);
        getWashingTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public WashingTypeEntity delete(int id, String email, String password) {
        CompletableFuture<WashingTypeEntity> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                HttpUriRequest httpGet = RequestBuilder.delete()
                        .setUri(Main.host.getValue() + UrlRoutes.DELETE_WASHING_TYPE_BY_ID.getName() + id)
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .build();

                try {
                    WashingTypeEntity washingTypeEntity;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        washingTypeEntity = JsonToClass.parseToObject(WashingTypeEntity.class, response);
                    }
                    futureTypeList.complete(washingTypeEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getWashingTypesThread = new Thread(runnable);
        getWashingTypesThread.start();

        return futureTypeList.get();
    }
}
