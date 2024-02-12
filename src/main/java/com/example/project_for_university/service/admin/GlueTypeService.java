package com.example.project_for_university.service.admin;

import com.example.project_for_university.Main;
import com.example.project_for_university.controllers.user.admin.models.PhType;
import com.example.project_for_university.dto.forBackend.entity.types.BendingTypeEntity;
import com.example.project_for_university.dto.forBackend.entity.types.GlueTypeEntity;
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

public class GlueTypeService implements CrudService<GlueTypeEntity> {

    public static final GlueTypeService INSTANCE = new GlueTypeService();

    @SneakyThrows
    @Override
    public TypesResponse<GlueTypeEntity> getAll(String email, String password) {
        CompletableFuture<TypesResponse<GlueTypeEntity>> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                HttpUriRequest httpGet = RequestBuilder.get()
                        .setUri(Main.host.getValue() + UrlRoutes.GET_GLUE_TYPES.getName())
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .build();

                TypesResponse<GlueTypeEntity> typesResponse = new TypesResponse<>();
                try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                    if (response.getStatusLine().getStatusCode() == 200) {
                        typesResponse.setError(false);
                        typesResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        typesResponse.setTypes(JsonToClass.parseToListObject(GlueTypeEntity.class, response).toArray(GlueTypeEntity[]::new));
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

        Thread getGlueTypesThread = new Thread(runnable);
        getGlueTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public TypeResponse<GlueTypeEntity> getById(int id, String email, String password) {
        CompletableFuture<TypeResponse<GlueTypeEntity>> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                HttpUriRequest httpGet = RequestBuilder.get()
                        .setUri(Main.host.getValue() + UrlRoutes.GET_GLUE_TYPE_BY_ID.getName() + id)
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .build();

                TypeResponse<GlueTypeEntity> typeResponse = new TypeResponse<>();
                try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                    if (response.getStatusLine().getStatusCode() == 200) {
                        typeResponse.setError(false);
                        typeResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        typeResponse.setType(JsonToClass.parseToObject(GlueTypeEntity.class, response));
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

        Thread getGlueTypesThread = new Thread(runnable);
        getGlueTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public TypeResponse<GlueTypeEntity> create(PhType phType, String email, String password) {
        CompletableFuture<TypeResponse<GlueTypeEntity>> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", phType.getName());

                HttpUriRequest httpPost = RequestBuilder.post()
                        .setUri(Main.host.getValue() + UrlRoutes.POST_GLUE_TYPE.getName())
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .setEntity(new StringEntity(jsonObject.toString(), StandardCharsets.UTF_8))
                        .build();

                TypeResponse<GlueTypeEntity> typeResponse = new TypeResponse<>();
                try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                    if (response.getStatusLine().getStatusCode() == 201) {
                        typeResponse.setError(false);
                        typeResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        typeResponse.setType(JsonToClass.parseToObject(GlueTypeEntity.class, response));
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

        Thread getGlueTypesThread = new Thread(runnable);
        getGlueTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public TypeResponse<GlueTypeEntity> update(PhType phType, String email, String password) {
        CompletableFuture<TypeResponse<GlueTypeEntity>> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", phType.getName());

                HttpUriRequest httpPatch = RequestBuilder.patch()
                        .setUri(Main.host.getValue() + UrlRoutes.PATCH_GLUE_TYPE_BY_ID.getName() + phType.getId())
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .setEntity(new StringEntity(jsonObject.toString(), StandardCharsets.UTF_8))
                        .build();

                TypeResponse<GlueTypeEntity> typeResponse = new TypeResponse<>();
                try (CloseableHttpResponse response = httpClient.execute(httpPatch)) {
                    if (response.getStatusLine().getStatusCode() == 200) {
                        typeResponse.setError(false);
                        typeResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        typeResponse.setType(JsonToClass.parseToObject(GlueTypeEntity.class, response));
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

        Thread getGlueTypesThread = new Thread(runnable);
        getGlueTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public TypeResponse<GlueTypeEntity> delete(int id, String email, String password) {
        CompletableFuture<TypeResponse<GlueTypeEntity>> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                HttpUriRequest httpDelete = RequestBuilder.delete()
                        .setUri(Main.host.getValue() + UrlRoutes.DELETE_GLUE_TYPE_BY_ID.getName() + id)
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .build();

                TypeResponse<GlueTypeEntity> typeResponse = new TypeResponse<>();
                try (CloseableHttpResponse response = httpClient.execute(httpDelete)) {
                    if (response.getStatusLine().getStatusCode() == 200) {
                        typeResponse.setError(false);
                        typeResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        typeResponse.setType(JsonToClass.parseToObject(GlueTypeEntity.class, response));
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

        Thread getGlueTypesThread = new Thread(runnable);
        getGlueTypesThread.start();

        return futureTypeList.get();
    }
}
