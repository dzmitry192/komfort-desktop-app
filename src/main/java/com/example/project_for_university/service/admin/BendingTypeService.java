package com.example.project_for_university.service.admin;

import com.example.project_for_university.Main;
import com.example.project_for_university.controllers.user.admin.models.PhType;
import com.example.project_for_university.dto.forBackend.entity.types.BendingTypeEntity;
import com.example.project_for_university.enums.ServiceEnum;
import com.example.project_for_university.enums.UrlRoutes;
import com.example.project_for_university.http.JsonToClass;
import com.example.project_for_university.interfaces.CrudService;
import com.example.project_for_university.service.models.TypeResponse;
import com.example.project_for_university.service.models.TypesResponse;
import com.example.project_for_university.utils.AuthUtils;
import com.example.project_for_university.utils.ExceptionMessageUtil;
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

public class BendingTypeService implements CrudService<BendingTypeEntity> {

    public static final BendingTypeService INSTANCE = new BendingTypeService();

    @SneakyThrows
    @Override
    public TypesResponse<BendingTypeEntity> getAll(String email, String password) {
        CompletableFuture<TypesResponse<BendingTypeEntity>> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                HttpUriRequest httpGet = RequestBuilder.get()
                        .setUri(Main.host.getValue() + UrlRoutes.GET_BENDING_TYPES.getName())
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .setHeader("Content-Type", "application/json")
                        .build();

                TypesResponse<BendingTypeEntity> typesResponse = new TypesResponse<>();
                try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                    if (response.getStatusLine().getStatusCode() == 200) {
                        typesResponse.setError(false);
                        typesResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        typesResponse.setTypes(JsonToClass.parseToListObject(BendingTypeEntity.class, response).toArray(BendingTypeEntity[]::new));
                    } else {
                        typesResponse.setError(true);
                        typesResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        typesResponse.setMessage(ExceptionMessageUtil.getErrorMessage(ServiceEnum.TYPE, response.getStatusLine().getStatusCode(), null));
                    }
                }
                futureTypeList.complete(typesResponse);
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
    public TypeResponse<BendingTypeEntity> getById(int id, String email, String password) {
        CompletableFuture<TypeResponse<BendingTypeEntity>> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                HttpUriRequest httpGet = RequestBuilder.get()
                        .setUri(Main.host.getValue() + UrlRoutes.GET_BENDING_TYPE_BY_ID.getName() + id)
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .setHeader("Content-Type", "application/json")
                        .build();

                TypeResponse<BendingTypeEntity> typeResponse = new TypeResponse<>();
                try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                    if (response.getStatusLine().getStatusCode() == 200) {
                        typeResponse.setError(false);
                        typeResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        typeResponse.setType(JsonToClass.parseToObject(BendingTypeEntity.class, response));
                    } else {
                        typeResponse.setError(true);
                        typeResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        typeResponse.setMessage(ExceptionMessageUtil.getErrorMessage(ServiceEnum.TYPE, response.getStatusLine().getStatusCode(),null));
                    }
                }
                futureTypeList.complete(typeResponse);
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
    public TypeResponse<BendingTypeEntity> create(PhType phType, String email, String password) {
        CompletableFuture<TypeResponse<BendingTypeEntity>> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", phType.getName());

                HttpUriRequest httpPost = RequestBuilder.post()
                        .setUri(Main.host.getValue() + UrlRoutes.POST_BENDING_TYPE.getName())
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .setHeader("Content-Type", "application/json")
                        .setEntity(new StringEntity(jsonObject.toString(), StandardCharsets.UTF_8))
                        .build();

                TypeResponse<BendingTypeEntity> typeResponse = new TypeResponse<>();
                try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                    if (response.getStatusLine().getStatusCode() == 201) {
                        typeResponse.setError(false);
                        typeResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        typeResponse.setType(JsonToClass.parseToObject(BendingTypeEntity.class, response));
                    } else {
                        typeResponse.setError(true);
                        typeResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        typeResponse.setMessage(ExceptionMessageUtil.getErrorMessage(ServiceEnum.TYPE, response.getStatusLine().getStatusCode(),null));
                    }
                }
                futureTypeList.complete(typeResponse);
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
    public TypeResponse<BendingTypeEntity> update(PhType phType, String email, String password) {
        CompletableFuture<TypeResponse<BendingTypeEntity>> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", phType.getName());

                HttpUriRequest httpPatch = RequestBuilder.patch()
                        .setUri(Main.host.getValue() + UrlRoutes.PATCH_BENDING_TYPE_BY_ID.getName() + phType.getId())
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .setHeader("Content-Type", "application/json")
                        .setEntity(new StringEntity(jsonObject.toString(), StandardCharsets.UTF_8))
                        .build();

                TypeResponse<BendingTypeEntity> typeResponse = new TypeResponse<>();
                try (CloseableHttpResponse response = httpClient.execute(httpPatch)) {
                    if (response.getStatusLine().getStatusCode() == 200) {
                        typeResponse.setError(false);
                        typeResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        typeResponse.setType(JsonToClass.parseToObject(BendingTypeEntity.class, response));
                    } else {
                        typeResponse.setError(true);
                        typeResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        typeResponse.setMessage(ExceptionMessageUtil.getErrorMessage(ServiceEnum.TYPE, response.getStatusLine().getStatusCode(),null));
                    }
                }
                futureTypeList.complete(typeResponse);
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
    public TypeResponse<BendingTypeEntity> delete(int id, String email, String password) {
        CompletableFuture<TypeResponse<BendingTypeEntity>> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                HttpUriRequest httpDelete = RequestBuilder.delete()
                        .setUri(Main.host.getValue() + UrlRoutes.DELETE_BENDING_TYPE_BY_ID.getName() + id)
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .build();

                TypeResponse<BendingTypeEntity> typeResponse = new TypeResponse<>();
                try (CloseableHttpResponse response = httpClient.execute(httpDelete)) {
                    if (response.getStatusLine().getStatusCode() == 200) {
                        typeResponse.setError(false);
                        typeResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        typeResponse.setType(JsonToClass.parseToObject(BendingTypeEntity.class, response));
                    } else {
                        typeResponse.setError(true);
                        typeResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        typeResponse.setMessage(ExceptionMessageUtil.getErrorMessage(ServiceEnum.TYPE, response.getStatusLine().getStatusCode(),ExceptionMessageUtil.getMessageFromResponse(response)));
                    }
                }
                futureTypeList.complete(typeResponse);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getBendingTypesThread = new Thread(runnable);
        getBendingTypesThread.start();

        return futureTypeList.get();
    }
}
