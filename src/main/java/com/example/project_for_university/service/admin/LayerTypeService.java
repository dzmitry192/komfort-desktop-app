package com.example.project_for_university.service.admin;

import com.example.project_for_university.Main;
import com.example.project_for_university.controllers.user.admin.models.PhType;
import com.example.project_for_university.dto.forBackend.entity.types.GlueTypeEntity;
import com.example.project_for_university.dto.forBackend.entity.types.LayerTypeEntity;
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

public class LayerTypeService implements CrudService<LayerTypeEntity> {

    public static final LayerTypeService INSTANCE = new LayerTypeService();

    @SneakyThrows
    @Override
    public TypesResponse<LayerTypeEntity> getAll(String email, String password) {
        CompletableFuture<TypesResponse<LayerTypeEntity>> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                HttpUriRequest httpGet = RequestBuilder.get()
                        .setUri(Main.host.getValue() + UrlRoutes.GET_LAYER_TYPES.getName())
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .build();

                TypesResponse<LayerTypeEntity> typesResponse = new TypesResponse<>();
                try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                    if (response.getStatusLine().getStatusCode() == 200) {
                        typesResponse.setError(false);
                        typesResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        typesResponse.setTypes(JsonToClass.parseToListObject(LayerTypeEntity.class, response).toArray(LayerTypeEntity[]::new));
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

        Thread getLayerTypesThread = new Thread(runnable);
        getLayerTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public TypeResponse<LayerTypeEntity> getById(int id, String email, String password) {
        CompletableFuture<TypeResponse<LayerTypeEntity>> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                HttpUriRequest httpGet = RequestBuilder.get()
                        .setUri(Main.host.getValue() + UrlRoutes.GET_LAYER_TYPE_BY_ID.getName() + id)
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .build();

                TypeResponse<LayerTypeEntity> typeResponse = new TypeResponse<>();
                try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                    if (response.getStatusLine().getStatusCode() == 200) {
                        typeResponse.setError(false);
                        typeResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        typeResponse.setType(JsonToClass.parseToObject(LayerTypeEntity.class, response));
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

        Thread getLayerTypesThread = new Thread(runnable);
        getLayerTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public TypeResponse<LayerTypeEntity> create(PhType phType, String email, String password) {
        CompletableFuture<TypeResponse<LayerTypeEntity>> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", phType.getName());

                HttpUriRequest httpPost = RequestBuilder.post()
                        .setUri(Main.host.getValue() + UrlRoutes.POST_LAYER_TYPE.getName())
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .setEntity(new StringEntity(jsonObject.toString(), StandardCharsets.UTF_8))
                        .build();

                TypeResponse<LayerTypeEntity> typeResponse = new TypeResponse<>();
                try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                    if (response.getStatusLine().getStatusCode() == 201) {
                        typeResponse.setError(false);
                        typeResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        typeResponse.setType(JsonToClass.parseToObject(LayerTypeEntity.class, response));
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

        Thread getLayerTypesThread = new Thread(runnable);
        getLayerTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public TypeResponse<LayerTypeEntity> update(PhType phType, String email, String password) {
        CompletableFuture<TypeResponse<LayerTypeEntity>> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", phType.getName());

                HttpUriRequest httpPatch = RequestBuilder.patch()
                        .setUri(Main.host.getValue() + UrlRoutes.PATCH_LAYER_TYPE_BY_ID.getName() + phType.getId())
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .setEntity(new StringEntity(jsonObject.toString(), StandardCharsets.UTF_8))
                        .build();

                TypeResponse<LayerTypeEntity> typeResponse = new TypeResponse<>();
                try (CloseableHttpResponse response = httpClient.execute(httpPatch)) {
                    if (response.getStatusLine().getStatusCode() == 200) {
                        typeResponse.setError(false);
                        typeResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        typeResponse.setType(JsonToClass.parseToObject(LayerTypeEntity.class, response));
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

        Thread getLayerTypesThread = new Thread(runnable);
        getLayerTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public TypeResponse<LayerTypeEntity> delete(int id, String email, String password) {
        CompletableFuture<TypeResponse<LayerTypeEntity>> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                HttpUriRequest httpDelete = RequestBuilder.delete()
                        .setUri(Main.host.getValue() + UrlRoutes.DELETE_LAYER_TYPE_BY_ID.getName() + id)
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .build();

                TypeResponse<LayerTypeEntity> typeResponse = new TypeResponse<>();
                try (CloseableHttpResponse response = httpClient.execute(httpDelete)) {
                    if (response.getStatusLine().getStatusCode() == 200) {
                        typeResponse.setError(false);
                        typeResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        typeResponse.setType(JsonToClass.parseToObject(LayerTypeEntity.class, response));
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

        Thread getLayerTypesThread = new Thread(runnable);
        getLayerTypesThread.start();

        return futureTypeList.get();
    }
}
