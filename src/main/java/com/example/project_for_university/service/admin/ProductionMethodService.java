package com.example.project_for_university.service.admin;

import com.example.project_for_university.Main;
import com.example.project_for_university.controllers.user.admin.models.PhType;
import com.example.project_for_university.dto.forBackend.entity.ProductionMethodEntity;
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

public class ProductionMethodService implements CrudService<ProductionMethodEntity> {

    @SneakyThrows
    @Override
    public ProductionMethodEntity[] getAll(String email, String password) {
        CompletableFuture<ProductionMethodEntity[]> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                HttpUriRequest httpGet = RequestBuilder.get()
                        .setUri(Main.host.getValue() + UrlRoutes.GET_PRODUCTION_METHODS.getName())
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .build();

                try {
                    ProductionMethodEntity[] productionMethodEntities;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        productionMethodEntities = JsonToClass.parseToListObject(ProductionMethodEntity.class, response).toArray(ProductionMethodEntity[]::new);
                    }
                    futureTypeList.complete(productionMethodEntities);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getProductionMethodsThread = new Thread(runnable);
        getProductionMethodsThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public ProductionMethodEntity getById(int id, String email, String password) {
        CompletableFuture<ProductionMethodEntity> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                HttpUriRequest httpGet = RequestBuilder.get()
                        .setUri(Main.host.getValue() + UrlRoutes.GET_PRODUCTION_METHOD_BY_ID.getName() + id)
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .build();

                try {
                    ProductionMethodEntity productionMethodEntity;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        productionMethodEntity = JsonToClass.parseToObject(ProductionMethodEntity.class, response);
                    }
                    futureTypeList.complete(productionMethodEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getProductionMethodsThread = new Thread(runnable);
        getProductionMethodsThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public ProductionMethodEntity create(PhType phType, String email, String password) {
        CompletableFuture<ProductionMethodEntity> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", phType.getName());

                HttpUriRequest httpGet = RequestBuilder.post()
                        .setUri(Main.host.getValue() + UrlRoutes.POST_PRODUCTION_METHOD.getName())
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .setEntity(new StringEntity(jsonObject.toString(), StandardCharsets.UTF_8))
                        .build();

                try {
                    ProductionMethodEntity productionMethodEntity;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        productionMethodEntity = JsonToClass.parseToObject(ProductionMethodEntity.class, response);
                    }
                    futureTypeList.complete(productionMethodEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getProductionMethodsThread = new Thread(runnable);
        getProductionMethodsThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public ProductionMethodEntity update(PhType phType, String email, String password) {
        CompletableFuture<ProductionMethodEntity> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", phType.getName());

                HttpUriRequest httpGet = RequestBuilder.patch()
                        .setUri(Main.host.getValue() + UrlRoutes.PATCH_PRODUCTION_METHOD_BY_ID.getName() + phType.getId())
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .setEntity(new StringEntity(jsonObject.toString(), StandardCharsets.UTF_8))
                        .build();

                try {
                    ProductionMethodEntity productionMethodEntity;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        productionMethodEntity = JsonToClass.parseToObject(ProductionMethodEntity.class, response);
                    }
                    futureTypeList.complete(productionMethodEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getProductionMethodsThread = new Thread(runnable);
        getProductionMethodsThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public ProductionMethodEntity delete(int id, String email, String password) {
        CompletableFuture<ProductionMethodEntity> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                HttpUriRequest httpGet = RequestBuilder.delete()
                        .setUri(Main.host.getValue() + UrlRoutes.DELETE_PRODUCTION_METHOD_BY_ID.getName() + id)
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .build();

                try {
                    ProductionMethodEntity productionMethodEntity;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        productionMethodEntity = JsonToClass.parseToObject(ProductionMethodEntity.class, response);
                    }
                    futureTypeList.complete(productionMethodEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getProductionMethodsThread = new Thread(runnable);
        getProductionMethodsThread.start();

        return futureTypeList.get();
    }
}
