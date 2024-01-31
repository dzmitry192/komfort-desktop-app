package com.example.project_for_university.service.admin;

import com.example.project_for_university.Main;
import com.example.project_for_university.controllers.user.admin.models.PhType;
import com.example.project_for_university.dto.forBackend.entity.types.LayerTypeEntity;
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

public class LayerTypeService implements CrudService<LayerTypeEntity> {

    @SneakyThrows
    @Override
    public LayerTypeEntity[] getAll(String email, String password) {
        CompletableFuture<LayerTypeEntity[]> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                HttpUriRequest httpGet = RequestBuilder.get()
                        .setUri(Main.host.getValue() + UrlRoutes.GET_LAYER_TYPES.getName())
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .build();

                try {
                    LayerTypeEntity[] layerTypeEntities;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        layerTypeEntities = JsonToClass.parseToListObject(LayerTypeEntity.class, response).toArray(LayerTypeEntity[]::new);
                    }
                    futureTypeList.complete(layerTypeEntities);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getLayerTypesThread = new Thread(runnable);
        getLayerTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public LayerTypeEntity getById(int id, String email, String password) {
        CompletableFuture<LayerTypeEntity> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                HttpUriRequest httpGet = RequestBuilder.get()
                        .setUri(Main.host.getValue() + UrlRoutes.GET_LAYER_TYPE_BY_ID.getName() + id)
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .build();

                try {
                    LayerTypeEntity layerTypeEntity;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        layerTypeEntity = JsonToClass.parseToObject(LayerTypeEntity.class, response);
                    }
                    futureTypeList.complete(layerTypeEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getLayerTypesThread = new Thread(runnable);
        getLayerTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public LayerTypeEntity create(PhType phType, String email, String password) {
        CompletableFuture<LayerTypeEntity> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", phType.getName());

                HttpUriRequest httpGet = RequestBuilder.post()
                        .setUri(Main.host.getValue() + UrlRoutes.POST_LAYER_TYPE.getName())
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .setEntity(new StringEntity(jsonObject.toString(), StandardCharsets.UTF_8))
                        .build();

                try {
                    LayerTypeEntity layerTypeEntity;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        layerTypeEntity = JsonToClass.parseToObject(LayerTypeEntity.class, response);
                    }
                    futureTypeList.complete(layerTypeEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getLayerTypesThread = new Thread(runnable);
        getLayerTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public LayerTypeEntity update(PhType phType, String email, String password) {
        CompletableFuture<LayerTypeEntity> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", phType.getName());

                HttpUriRequest httpGet = RequestBuilder.patch()
                        .setUri(Main.host.getValue() + UrlRoutes.PATCH_LAYER_TYPE_BY_ID.getName() + phType.getId())
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .setEntity(new StringEntity(jsonObject.toString(), StandardCharsets.UTF_8))
                        .build();

                try {
                    LayerTypeEntity layerTypeEntity;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        layerTypeEntity = JsonToClass.parseToObject(LayerTypeEntity.class, response);
                    }
                    futureTypeList.complete(layerTypeEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getLayerTypesThread = new Thread(runnable);
        getLayerTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public LayerTypeEntity delete(int id, String email, String password) {
        CompletableFuture<LayerTypeEntity> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                HttpUriRequest httpGet = RequestBuilder.delete()
                        .setUri(Main.host.getValue() + UrlRoutes.DELETE_LAYER_TYPE_BY_ID.getName() + id)
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .build();

                try {
                    LayerTypeEntity layerTypeEntity;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        layerTypeEntity = JsonToClass.parseToObject(LayerTypeEntity.class, response);
                    }
                    futureTypeList.complete(layerTypeEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getLayerTypesThread = new Thread(runnable);
        getLayerTypesThread.start();

        return futureTypeList.get();
    }
}
