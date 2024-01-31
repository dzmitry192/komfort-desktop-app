package com.example.project_for_university.service.admin;

import com.example.project_for_university.Main;
import com.example.project_for_university.controllers.user.admin.models.PhType;
import com.example.project_for_university.dto.forBackend.entity.types.GlueTypeEntity;
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

public class GlueTypeService implements CrudService<GlueTypeEntity> {
    @SneakyThrows
    @Override
    public GlueTypeEntity[] getAll(String email, String password) {
        CompletableFuture<GlueTypeEntity[]> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                HttpUriRequest httpGet = RequestBuilder.get()
                        .setUri(Main.host.getValue() + UrlRoutes.GET_GLUE_TYPES.getName())
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .build();

                try {
                    GlueTypeEntity[] glueTypeEntities;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        glueTypeEntities = JsonToClass.parseToListObject(GlueTypeEntity.class, response).toArray(GlueTypeEntity[]::new);
                    }
                    futureTypeList.complete(glueTypeEntities);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getGlueTypesThread = new Thread(runnable);
        getGlueTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public GlueTypeEntity getById(int id, String email, String password) {
        CompletableFuture<GlueTypeEntity> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                HttpUriRequest httpGet = RequestBuilder.get()
                        .setUri(Main.host.getValue() + UrlRoutes.GET_GLUE_TYPE_BY_ID.getName() + id)
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .build();

                try {
                    GlueTypeEntity glueTypeEntity;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        glueTypeEntity = JsonToClass.parseToObject(GlueTypeEntity.class, response);
                    }
                    futureTypeList.complete(glueTypeEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getGlueTypesThread = new Thread(runnable);
        getGlueTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public GlueTypeEntity create(PhType phType, String email, String password) {
        CompletableFuture<GlueTypeEntity> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", phType.getName());

                HttpUriRequest httpGet = RequestBuilder.post()
                        .setUri(Main.host.getValue() + UrlRoutes.POST_GLUE_TYPE.getName())
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .setEntity(new StringEntity(jsonObject.toString(), StandardCharsets.UTF_8))
                        .build();

                try {
                    GlueTypeEntity glueTypeEntity;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        glueTypeEntity = JsonToClass.parseToObject(GlueTypeEntity.class, response);
                    }
                    futureTypeList.complete(glueTypeEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getGlueTypesThread = new Thread(runnable);
        getGlueTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public GlueTypeEntity update(PhType phType, String email, String password) {
        CompletableFuture<GlueTypeEntity> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", phType.getName());

                HttpUriRequest httpGet = RequestBuilder.patch()
                        .setUri(Main.host.getValue() + UrlRoutes.PATCH_GLUE_TYPE_BY_ID.getName() + phType.getId())
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .setEntity(new StringEntity(jsonObject.toString(), StandardCharsets.UTF_8))
                        .build();

                try {
                    GlueTypeEntity glueTypeEntity;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        glueTypeEntity = JsonToClass.parseToObject(GlueTypeEntity.class, response);
                    }
                    futureTypeList.complete(glueTypeEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getGlueTypesThread = new Thread(runnable);
        getGlueTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public GlueTypeEntity delete(int id, String email, String password) {
        CompletableFuture<GlueTypeEntity> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                HttpUriRequest httpGet = RequestBuilder.delete()
                        .setUri(Main.host.getValue() + UrlRoutes.DELETE_GLUE_TYPE_BY_ID.getName() + id)
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .build();

                try {
                    GlueTypeEntity glueTypeEntity;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        glueTypeEntity = JsonToClass.parseToObject(GlueTypeEntity.class, response);
                    }
                    futureTypeList.complete(glueTypeEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getGlueTypesThread = new Thread(runnable);
        getGlueTypesThread.start();

        return futureTypeList.get();
    }
}
