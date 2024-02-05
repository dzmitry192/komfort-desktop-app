package com.example.project_for_university.service.admin;

import com.example.project_for_university.Main;
import com.example.project_for_university.controllers.user.admin.models.PhType;
import com.example.project_for_university.dto.forBackend.entity.types.AbrasionTypeEntity;
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
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

public class AbrasionTypeService implements CrudService<AbrasionTypeEntity> {

    public static final AbrasionTypeService INSTANCE = new AbrasionTypeService();

    @SneakyThrows
    @Override
    public TypesResponse<AbrasionTypeEntity> getAll(String email, String password) {
        CompletableFuture<TypesResponse<AbrasionTypeEntity>> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                HttpUriRequest httpGet = RequestBuilder.get()
                        .setUri(Main.host.getValue() + UrlRoutes.GET_ABRASION_TYPES.getName())
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .setHeader("Content-Type", "application/json")
                        .build();

                TypesResponse<AbrasionTypeEntity> typesResponse = new TypesResponse<>();
                try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                    if (response.getStatusLine().getStatusCode() == 200) {
                        typesResponse.setTypes(JsonToClass.parseToListObject(AbrasionTypeEntity.class, response).toArray(AbrasionTypeEntity[]::new));
                        typesResponse.setError(false);
                    } else {
                        typesResponse.setError(true);
                        typesResponse.setMessage(ExceptionMessageUtil.getErrorMessage(ServiceEnum.TYPE, response.getStatusLine().getStatusCode(), null));
                    }
                }
                futureTypeList.complete(typesResponse);
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
    public TypeResponse<AbrasionTypeEntity> getById(int id, String email, String password) {
        CompletableFuture<TypeResponse<AbrasionTypeEntity>> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                HttpUriRequest httpGet = RequestBuilder.get()
                        .setUri(Main.host.getValue() + UrlRoutes.GET_ABRASION_TYPE_BY_ID.getName() + id)
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .setHeader("Content-Type", "application/json")
                        .build();

                TypeResponse<AbrasionTypeEntity> typeResponse = new TypeResponse<>();
                try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                    if (response.getStatusLine().getStatusCode() == 200) {
                        typeResponse.setType(JsonToClass.parseToObject(AbrasionTypeEntity.class, response));
                        typeResponse.setError(false);
                    } else {
                        typeResponse.setError(true);
                        typeResponse.setMessage(ExceptionMessageUtil.getErrorMessage(ServiceEnum.TYPE, response.getStatusLine().getStatusCode(), null));
                    }
                }
                futureTypeList.complete(typeResponse);
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
    public TypeResponse<AbrasionTypeEntity> create(PhType phType, String email, String password) {
        CompletableFuture<TypeResponse<AbrasionTypeEntity>> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", phType.getName());

                HttpUriRequest httpPost = RequestBuilder.post()
                        .setUri(Main.host.getValue() + UrlRoutes.POST_ABRASION_TYPE.getName())
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .setHeader("Content-Type", "application/json")
                        .setEntity(new StringEntity(jsonObject.toString(), StandardCharsets.UTF_8))
                        .build();

                TypeResponse<AbrasionTypeEntity> typeResponse = new TypeResponse<>();
                try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                    if (response.getStatusLine().getStatusCode() == 201) {
                        typeResponse.setType(JsonToClass.parseToObject(AbrasionTypeEntity.class, response));
                        typeResponse.setError(false);
                    } else {
                        typeResponse.setError(true);
                        typeResponse.setMessage(ExceptionMessageUtil.getErrorMessage(ServiceEnum.TYPE, response.getStatusLine().getStatusCode(), null));
                    }
                }
                futureTypeList.complete(typeResponse);
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
    public TypeResponse<AbrasionTypeEntity> update(PhType phType, String email, String password) {
        CompletableFuture<TypeResponse<AbrasionTypeEntity>> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", phType.getName());

                HttpUriRequest httpPatch = RequestBuilder.patch()
                        .setUri(Main.host.getValue() + UrlRoutes.PATCH_ABRASION_TYPE_BY_ID.getName() + phType.getId())
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .setHeader("Content-Type", "application/json")
                        .setEntity(new StringEntity(jsonObject.toString(), StandardCharsets.UTF_8))
                        .build();

                TypeResponse<AbrasionTypeEntity> typeResponse = new TypeResponse<>();
                try (CloseableHttpResponse response = httpClient.execute(httpPatch)) {
                    if (response.getStatusLine().getStatusCode() == 200) {
                        typeResponse.setType(JsonToClass.parseToObject(AbrasionTypeEntity.class, response));
                        typeResponse.setError(false);
                    } else {
                        typeResponse.setError(true);
                        typeResponse.setMessage(ExceptionMessageUtil.getErrorMessage(ServiceEnum.TYPE, response.getStatusLine().getStatusCode(), null));
                    }
                }
                futureTypeList.complete(typeResponse);
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
    public TypeResponse<AbrasionTypeEntity> delete(int id, String email, String password) {
        CompletableFuture<TypeResponse<AbrasionTypeEntity>> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                HttpUriRequest httpDelete = RequestBuilder.delete()
                        .setUri(Main.host.getValue() + UrlRoutes.DELETE_ABRASION_TYPE_BY_ID.getName() + id)
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .setHeader("Content-Type", "application/json")
                        .build();

                TypeResponse<AbrasionTypeEntity> typeResponse = new TypeResponse<>();
                try (CloseableHttpResponse response = httpClient.execute(httpDelete)) {
                    if (response.getStatusLine().getStatusCode() == 200) {
                        typeResponse.setType(JsonToClass.parseToObject(AbrasionTypeEntity.class, response));
                        typeResponse.setError(false);
                    } else {
                        typeResponse.setError(true);
                        typeResponse.setMessage(ExceptionMessageUtil.getErrorMessage(ServiceEnum.TYPE, response.getStatusLine().getStatusCode(), ExceptionMessageUtil.getMessageFromResponse(response)));
                    }
                }
                futureTypeList.complete(typeResponse);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getAbrassionTypesThread = new Thread(runnable);
        getAbrassionTypesThread.start();

        return futureTypeList.get();
    }
}
