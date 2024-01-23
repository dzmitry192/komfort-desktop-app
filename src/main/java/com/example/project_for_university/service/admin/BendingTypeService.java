package com.example.project_for_university.service.admin;

import com.example.project_for_university.Main;
import com.example.project_for_university.controllers.user.admin.models.PhType;
import com.example.project_for_university.dto.forBackend.entity.types.BendingTypeEntity;
import com.example.project_for_university.enums.UrlRoutes;
import com.example.project_for_university.http.JsonToClass;
import com.example.project_for_university.interfaces.CrudService;
import com.google.gson.JsonObject;
import lombok.SneakyThrows;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class BendingTypeService implements CrudService<BendingTypeEntity> {
    @SneakyThrows
    @Override
    public BendingTypeEntity[] getAll() {
        CompletableFuture<BendingTypeEntity[]> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                HttpUriRequest httpGet = RequestBuilder.get()
                        .setUri(Main.host.getValue() + UrlRoutes.GET_BENDING_TYPES.getName())
                        .setHeader("Content-Type", "application/json")
                        .build();

                try {
                    BendingTypeEntity[] bendingTypeEntities;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        bendingTypeEntities = JsonToClass.parseToListObject(BendingTypeEntity.class, response).toArray(BendingTypeEntity[]::new);
                    }
                    futureTypeList.complete(bendingTypeEntities);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getBendingTypesThread = new Thread(runnable);
        getBendingTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public BendingTypeEntity getById(int id) {
        CompletableFuture<BendingTypeEntity> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                HttpUriRequest httpGet = RequestBuilder.get()
                        .setUri(Main.host.getValue() + UrlRoutes.GET_BENDING_TYPE_BY_ID.getName() + id)
                        .setHeader("Content-Type", "application/json")
                        .build();

                try {
                    BendingTypeEntity bendingTypeEntity;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        bendingTypeEntity = JsonToClass.parseToObject(BendingTypeEntity.class, response);
                    }
                    futureTypeList.complete(bendingTypeEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getBendingTypesThread = new Thread(runnable);
        getBendingTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public BendingTypeEntity create(PhType phType) {
        CompletableFuture<BendingTypeEntity> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", phType.getName());

                HttpUriRequest httpGet = RequestBuilder.get()
                        .setUri(Main.host.getValue() + UrlRoutes.POST_BENDING_TYPE.getName())
                        .setHeader("Content-Type", "application/json")
                        .setEntity(new StringEntity(jsonObject.toString()))
                        .build();

                try {
                    BendingTypeEntity bendingTypeEntity;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        bendingTypeEntity = JsonToClass.parseToObject(BendingTypeEntity.class, response);
                    }
                    futureTypeList.complete(bendingTypeEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getBendingTypesThread = new Thread(runnable);
        getBendingTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public BendingTypeEntity update(PhType phType) {
        CompletableFuture<BendingTypeEntity> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", phType.getName());

                HttpUriRequest httpGet = RequestBuilder.patch()
                        .setUri(Main.host.getValue() + UrlRoutes.PATCH_BENDING_TYPE_BY_ID.getName() + phType.getId())
                        .setHeader("Content-Type", "application/json")
                        .setEntity(new StringEntity(jsonObject.toString()))
                        .build();

                try {
                    BendingTypeEntity bendingTypeEntity;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        bendingTypeEntity = JsonToClass.parseToObject(BendingTypeEntity.class, response);
                    }
                    futureTypeList.complete(bendingTypeEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getBendingTypesThread = new Thread(runnable);
        getBendingTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public BendingTypeEntity delete(int id) {
        CompletableFuture<BendingTypeEntity> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                HttpUriRequest httpGet = RequestBuilder.patch()
                        .setUri(Main.host.getValue() + UrlRoutes.DELETE_BENDING_TYPE_BY_ID.getName() + id)
                        .setHeader("Content-Type", "application/json")
                        .build();

                try {
                    BendingTypeEntity bendingTypeEntity;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        bendingTypeEntity = JsonToClass.parseToObject(BendingTypeEntity.class, response);
                    }
                    futureTypeList.complete(bendingTypeEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getBendingTypesThread = new Thread(runnable);
        getBendingTypesThread.start();

        return futureTypeList.get();
    }
}
