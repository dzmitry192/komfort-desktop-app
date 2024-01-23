package com.example.project_for_university.service.admin;

import com.example.project_for_university.Main;
import com.example.project_for_university.controllers.user.admin.models.PhType;
import com.example.project_for_university.dto.forBackend.entity.types.AbrasionTypeEntity;
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

public class AbrasionTypeService implements CrudService<AbrasionTypeEntity> {
    @SneakyThrows
    @Override
    public AbrasionTypeEntity[] getAll() {
        CompletableFuture<AbrasionTypeEntity[]> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                HttpUriRequest httpGet = RequestBuilder.get()
                        .setUri(Main.host.getValue() + UrlRoutes.GET_ABRASION_TYPES.getName())
                        .setHeader("Content-Type", "application/json")
                        .build();

                try {
                    AbrasionTypeEntity[] abrasionTypeEntities;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        abrasionTypeEntities = JsonToClass.parseToListObject(AbrasionTypeEntity.class, response).toArray(AbrasionTypeEntity[]::new);
                    }
                    futureTypeList.complete(abrasionTypeEntities);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getAbrassionTypesThread = new Thread(runnable);
        getAbrassionTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public AbrasionTypeEntity getById(int id) {
        CompletableFuture<AbrasionTypeEntity> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                HttpUriRequest httpGet = RequestBuilder.get()
                        .setUri(Main.host.getValue() + UrlRoutes.GET_ABRASION_TYPE_BY_ID.getName() + id)
                        .setHeader("Content-Type", "application/json")
                        .build();

                try {
                    AbrasionTypeEntity abrasionTypeEntity;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        abrasionTypeEntity = JsonToClass.parseToObject(AbrasionTypeEntity.class, response);
                    }
                    futureTypeList.complete(abrasionTypeEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getAbrassionTypesThread = new Thread(runnable);
        getAbrassionTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public AbrasionTypeEntity create(PhType phType) {
        CompletableFuture<AbrasionTypeEntity> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", phType.getName());

                HttpUriRequest httpGet = RequestBuilder.get()
                        .setUri(Main.host.getValue() + UrlRoutes.POST_ABRASION_TYPE.getName())
                        .setHeader("Content-Type", "application/json")
                        .setEntity(new StringEntity(jsonObject.toString()))
                        .build();

                try {
                    AbrasionTypeEntity abrasionTypeEntity;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        abrasionTypeEntity = JsonToClass.parseToObject(AbrasionTypeEntity.class, response);
                    }
                    futureTypeList.complete(abrasionTypeEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getAbrassionTypesThread = new Thread(runnable);
        getAbrassionTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public AbrasionTypeEntity update(PhType phType) {
        CompletableFuture<AbrasionTypeEntity> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", phType.getName());

                HttpUriRequest httpGet = RequestBuilder.patch()
                        .setUri(Main.host.getValue() + UrlRoutes.PATCH_ABRASION_TYPE_BY_ID.getName() + phType.getId())
                        .setHeader("Content-Type", "application/json")
                        .setEntity(new StringEntity(jsonObject.toString()))
                        .build();

                try {
                    AbrasionTypeEntity abrasionTypeEntity;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        abrasionTypeEntity = JsonToClass.parseToObject(AbrasionTypeEntity.class, response);
                    }
                    futureTypeList.complete(abrasionTypeEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getAbrassionTypesThread = new Thread(runnable);
        getAbrassionTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public AbrasionTypeEntity delete(int id) {
        CompletableFuture<AbrasionTypeEntity> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                HttpUriRequest httpGet = RequestBuilder.patch()
                        .setUri(Main.host.getValue() + UrlRoutes.DELETE_ABRASION_TYPE_BY_ID.getName() + id)
                        .setHeader("Content-Type", "application/json")
                        .build();

                try {
                    AbrasionTypeEntity abrasionTypeEntity;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        abrasionTypeEntity = JsonToClass.parseToObject(AbrasionTypeEntity.class, response);
                    }
                    futureTypeList.complete(abrasionTypeEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getAbrassionTypesThread = new Thread(runnable);
        getAbrassionTypesThread.start();

        return futureTypeList.get();
    }
}
