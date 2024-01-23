package com.example.project_for_university.service.admin;

import com.example.project_for_university.Main;
import com.example.project_for_university.controllers.user.admin.models.PhType;
import com.example.project_for_university.dto.forBackend.entity.types.PhysicalActivityTypeEntity;
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

public class PhysicalActivityTypeService implements CrudService<PhysicalActivityTypeEntity> {

    @SneakyThrows
    @Override
    public PhysicalActivityTypeEntity[] getAll() {
        CompletableFuture<PhysicalActivityTypeEntity[]> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                HttpUriRequest httpGet = RequestBuilder.get()
                        .setUri(Main.host.getValue() + UrlRoutes.GET_PHYSICAL_ACTIVITY_TYPES.getName())
                        .setHeader("Content-Type", "application/json")
                        .build();

                try {
                    PhysicalActivityTypeEntity[] physicalActivityTypeEntities;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        physicalActivityTypeEntities = JsonToClass.parseToListObject(PhysicalActivityTypeEntity.class, response).toArray(PhysicalActivityTypeEntity[]::new);
                    }
                    futureTypeList.complete(physicalActivityTypeEntities);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getPhysicalActivityTypesThread = new Thread(runnable);
        getPhysicalActivityTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public PhysicalActivityTypeEntity getById(int id) {
        CompletableFuture<PhysicalActivityTypeEntity> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                HttpUriRequest httpGet = RequestBuilder.get()
                        .setUri(Main.host.getValue() + UrlRoutes.GET_PHYSICAL_ACTIVITY_TYPE_BY_ID.getName() + id)
                        .setHeader("Content-Type", "application/json")
                        .build();

                try {
                    PhysicalActivityTypeEntity physicalActivityTypeEntity;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        physicalActivityTypeEntity = JsonToClass.parseToObject(PhysicalActivityTypeEntity.class, response);
                    }
                    futureTypeList.complete(physicalActivityTypeEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getPhysicalActivityTypesThread = new Thread(runnable);
        getPhysicalActivityTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public PhysicalActivityTypeEntity create(PhType phType) {
        CompletableFuture<PhysicalActivityTypeEntity> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", phType.getName());
                jsonObject.addProperty("description", phType.getDescription());

                HttpUriRequest httpGet = RequestBuilder.get()
                        .setUri(Main.host.getValue() + UrlRoutes.POST_PHYSICAL_ACTIVITY_TYPE.getName())
                        .setHeader("Content-Type", "application/json")
                        .setEntity(new StringEntity(jsonObject.toString()))
                        .build();

                try {
                    PhysicalActivityTypeEntity physicalActivityTypeEntity;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        physicalActivityTypeEntity = JsonToClass.parseToObject(PhysicalActivityTypeEntity.class, response);
                    }
                    futureTypeList.complete(physicalActivityTypeEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getPhysicalActivityTypesThread = new Thread(runnable);
        getPhysicalActivityTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public PhysicalActivityTypeEntity update(PhType phType) {
        CompletableFuture<PhysicalActivityTypeEntity> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", phType.getName());
                jsonObject.addProperty("description", phType.getDescription());

                HttpUriRequest httpGet = RequestBuilder.patch()
                        .setUri(Main.host.getValue() + UrlRoutes.PATCH_PHYSICAL_ACTIVITY_TYPE_BY_ID.getName() + phType.getId())
                        .setHeader("Content-Type", "application/json")
                        .setEntity(new StringEntity(jsonObject.toString()))
                        .build();

                try {
                    PhysicalActivityTypeEntity physicalActivityTypeEntity;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        physicalActivityTypeEntity = JsonToClass.parseToObject(PhysicalActivityTypeEntity.class, response);
                    }
                    futureTypeList.complete(physicalActivityTypeEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getPhysicalActivityTypesThread = new Thread(runnable);
        getPhysicalActivityTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public PhysicalActivityTypeEntity delete(int id) {
        CompletableFuture<PhysicalActivityTypeEntity> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                HttpUriRequest httpGet = RequestBuilder.patch()
                        .setUri(Main.host.getValue() + UrlRoutes.DELETE_PHYSICAL_ACTIVITY_TYPE_BY_ID.getName() + id)
                        .setHeader("Content-Type", "application/json")
                        .build();

                try {
                    PhysicalActivityTypeEntity physicalActivityTypeEntity;
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        physicalActivityTypeEntity = JsonToClass.parseToObject(PhysicalActivityTypeEntity.class, response);
                    }
                    futureTypeList.complete(physicalActivityTypeEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getPhysicalActivityTypesThread = new Thread(runnable);
        getPhysicalActivityTypesThread.start();

        return futureTypeList.get();
    }
}
