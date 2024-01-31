package com.example.project_for_university.service.admin;

import com.example.project_for_university.Main;
import com.example.project_for_university.controllers.user.admin.models.PhType;
import com.example.project_for_university.dto.forBackend.entity.types.MembraneLayerPolymerTypeEntity;
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

public class MembraneLayerPolymerTypeService implements CrudService<MembraneLayerPolymerTypeEntity> {
    @SneakyThrows
    @Override
    public MembraneLayerPolymerTypeEntity[] getAll(String email, String password) {
        CompletableFuture<MembraneLayerPolymerTypeEntity[]> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                HttpUriRequest httpGet = RequestBuilder.get()
                        .setUri(Main.host.getValue() + UrlRoutes.GET_MEMBRANE_LAYER_POLYMER_TYPES.getName())
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .build();

                try {
                    MembraneLayerPolymerTypeEntity[] membraneLayerPolymerTypeEntities;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        membraneLayerPolymerTypeEntities = JsonToClass.parseToListObject(MembraneLayerPolymerTypeEntity.class, response).toArray(MembraneLayerPolymerTypeEntity[]::new);
                    }
                    futureTypeList.complete(membraneLayerPolymerTypeEntities);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getMembranePolymerTypesThread = new Thread(runnable);
        getMembranePolymerTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public MembraneLayerPolymerTypeEntity getById(int id, String email, String password) {
        CompletableFuture<MembraneLayerPolymerTypeEntity> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                HttpUriRequest httpGet = RequestBuilder.get()
                        .setUri(Main.host.getValue() + UrlRoutes.GET_MEMBRANE_LAYER_POLYMER_TYPE_BY_ID.getName() + id)
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .build();

                try {
                    MembraneLayerPolymerTypeEntity membraneLayerPolymerTypeEntity;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        membraneLayerPolymerTypeEntity = JsonToClass.parseToObject(MembraneLayerPolymerTypeEntity.class, response);
                    }
                    futureTypeList.complete(membraneLayerPolymerTypeEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getMembranePolymerTypesThread = new Thread(runnable);
        getMembranePolymerTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public MembraneLayerPolymerTypeEntity create(PhType phType, String email, String password) {
        CompletableFuture<MembraneLayerPolymerTypeEntity> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", phType.getName());

                HttpUriRequest httpGet = RequestBuilder.post()
                        .setUri(Main.host.getValue() + UrlRoutes.POST_MEMBRANE_LAYER_POLYMER_TYPE.getName())
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .setEntity(new StringEntity(jsonObject.toString(), StandardCharsets.UTF_8))
                        .build();

                try {
                    MembraneLayerPolymerTypeEntity membraneLayerPolymerTypeEntity;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        membraneLayerPolymerTypeEntity = JsonToClass.parseToObject(MembraneLayerPolymerTypeEntity.class, response);
                    }
                    futureTypeList.complete(membraneLayerPolymerTypeEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getMembranePolymerTypesThread = new Thread(runnable);
        getMembranePolymerTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public MembraneLayerPolymerTypeEntity update(PhType phType, String email, String password) {
        CompletableFuture<MembraneLayerPolymerTypeEntity> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", phType.getName());

                HttpUriRequest httpGet = RequestBuilder.patch()
                        .setUri(Main.host.getValue() + UrlRoutes.PATCH_MEMBRANE_LAYER_POLYMER_TYPE_BY_ID.getName() + phType.getId())
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .setEntity(new StringEntity(jsonObject.toString(), StandardCharsets.UTF_8))
                        .build();

                try {
                    MembraneLayerPolymerTypeEntity membraneLayerPolymerTypeEntity;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        membraneLayerPolymerTypeEntity = JsonToClass.parseToObject(MembraneLayerPolymerTypeEntity.class, response);
                    }
                    futureTypeList.complete(membraneLayerPolymerTypeEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getMembranePolymerTypesThread = new Thread(runnable);
        getMembranePolymerTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public MembraneLayerPolymerTypeEntity delete(int id, String email, String password) {
        CompletableFuture<MembraneLayerPolymerTypeEntity> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                HttpUriRequest httpGet = RequestBuilder.delete()
                        .setUri(Main.host.getValue() + UrlRoutes.DELETE_MEMBRANE_LAYER_POLYMER_TYPE_BY_ID.getName() + id)
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .build();

                try {
                    MembraneLayerPolymerTypeEntity membraneLayerPolymerTypeEntity;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        membraneLayerPolymerTypeEntity = JsonToClass.parseToObject(MembraneLayerPolymerTypeEntity.class, response);
                    }
                    futureTypeList.complete(membraneLayerPolymerTypeEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getMembranePolymerTypesThread = new Thread(runnable);
        getMembranePolymerTypesThread.start();

        return futureTypeList.get();
    }
}
