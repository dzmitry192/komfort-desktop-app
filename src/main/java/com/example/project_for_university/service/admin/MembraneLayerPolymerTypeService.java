package com.example.project_for_university.service.admin;

import com.example.project_for_university.Main;
import com.example.project_for_university.controllers.user.admin.models.PhType;
import com.example.project_for_university.dto.forBackend.entity.types.LayerTypeEntity;
import com.example.project_for_university.dto.forBackend.entity.types.MembraneLayerPolymerTypeEntity;
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

public class MembraneLayerPolymerTypeService implements CrudService<MembraneLayerPolymerTypeEntity> {

    public static final MembraneLayerPolymerTypeService INSTANCE = new MembraneLayerPolymerTypeService();

    @SneakyThrows
    @Override
    public TypesResponse<MembraneLayerPolymerTypeEntity> getAll(String email, String password) {
        CompletableFuture<TypesResponse<MembraneLayerPolymerTypeEntity>> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {

            HttpUriRequest httpGet = RequestBuilder.get()
                    .setUri(Main.host.getValue() + UrlRoutes.GET_MEMBRANE_LAYER_POLYMER_TYPES.getName())
                    .setHeader("Content-Type", "application/json")
                    .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                    .build();

            TypesResponse<MembraneLayerPolymerTypeEntity> typesResponse = new TypesResponse<>();
            try (CloseableHttpResponse response = Main.httpClient.execute(httpGet)) {
                if (response.getStatusLine().getStatusCode() == 200) {
                    typesResponse.setError(false);
                    typesResponse.setStatusCode(response.getStatusLine().getStatusCode());
                    typesResponse.setTypes(JsonToClass.parseToListObject(MembraneLayerPolymerTypeEntity.class, response).toArray(MembraneLayerPolymerTypeEntity[]::new));
                } else {
                    typesResponse.setError(true);
                    typesResponse.setStatusCode(response.getStatusLine().getStatusCode());
                    typesResponse.setMessage(ExceptionMessageUtil.getErrorMessage(ServiceEnum.TYPE, response.getStatusLine().getStatusCode(), null));
                }
            } catch (IOException e) {
                System.out.println("FFFFFFFFFFFFFFF");
            }
            futureTypeList.complete(typesResponse);
        };

        Thread getMembranePolymerTypesThread = new Thread(runnable);
        getMembranePolymerTypesThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public TypeResponse<MembraneLayerPolymerTypeEntity> getById(int id, String email, String password) {
        CompletableFuture<TypeResponse<MembraneLayerPolymerTypeEntity>> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                HttpUriRequest httpGet = RequestBuilder.get()
                        .setUri(Main.host.getValue() + UrlRoutes.GET_MEMBRANE_LAYER_POLYMER_TYPE_BY_ID.getName() + id)
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .build();

                TypeResponse<MembraneLayerPolymerTypeEntity> typeResponse = new TypeResponse<>();
                try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                    if (response.getStatusLine().getStatusCode() == 200) {
                        typeResponse.setError(false);
                        typeResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        typeResponse.setType(JsonToClass.parseToObject(MembraneLayerPolymerTypeEntity.class, response));
                    } else {
                        typeResponse.setError(true);
                        typeResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        typeResponse.setMessage(ExceptionMessageUtil.getErrorMessage(ServiceEnum.TYPE, response.getStatusLine().getStatusCode(), null));
                    }
                }
                futureTypeList.complete(typeResponse);
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
    public TypeResponse<MembraneLayerPolymerTypeEntity> create(PhType phType, String email, String password) {
        CompletableFuture<TypeResponse<MembraneLayerPolymerTypeEntity>> futureTypeList = new CompletableFuture<>();

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

                TypeResponse<MembraneLayerPolymerTypeEntity> typeResponse = new TypeResponse<>();
                try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                    if (response.getStatusLine().getStatusCode() == 201) {
                        typeResponse.setError(false);
                        typeResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        typeResponse.setType(JsonToClass.parseToObject(MembraneLayerPolymerTypeEntity.class, response));
                    } else {
                        typeResponse.setError(true);
                        typeResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        typeResponse.setMessage(ExceptionMessageUtil.getErrorMessage(ServiceEnum.TYPE, response.getStatusLine().getStatusCode(), null));
                    }
                }
                futureTypeList.complete(typeResponse);
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
    public TypeResponse<MembraneLayerPolymerTypeEntity> update(PhType phType, String email, String password) {
        CompletableFuture<TypeResponse<MembraneLayerPolymerTypeEntity>> futureTypeList = new CompletableFuture<>();

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

                TypeResponse<MembraneLayerPolymerTypeEntity> typeResponse = new TypeResponse<>();
                try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                    if (response.getStatusLine().getStatusCode() == 200) {
                        typeResponse.setError(false);
                        typeResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        typeResponse.setType(JsonToClass.parseToObject(MembraneLayerPolymerTypeEntity.class, response));
                    } else {
                        typeResponse.setError(true);
                        typeResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        typeResponse.setMessage(ExceptionMessageUtil.getErrorMessage(ServiceEnum.TYPE, response.getStatusLine().getStatusCode(), null));
                    }
                }
                futureTypeList.complete(typeResponse);
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
    public TypeResponse<MembraneLayerPolymerTypeEntity> delete(int id, String email, String password) {
        CompletableFuture<TypeResponse<MembraneLayerPolymerTypeEntity>> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                HttpUriRequest httpGet = RequestBuilder.delete()
                        .setUri(Main.host.getValue() + UrlRoutes.DELETE_MEMBRANE_LAYER_POLYMER_TYPE_BY_ID.getName() + id)
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .build();

                TypeResponse<MembraneLayerPolymerTypeEntity> typeResponse = new TypeResponse<>();
                try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                    if (response.getStatusLine().getStatusCode() == 200) {
                        typeResponse.setError(false);
                        typeResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        typeResponse.setType(JsonToClass.parseToObject(MembraneLayerPolymerTypeEntity.class, response));
                    } else {
                        typeResponse.setError(true);
                        typeResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        typeResponse.setMessage(ExceptionMessageUtil.getErrorMessage(ServiceEnum.TYPE, response.getStatusLine().getStatusCode(), ExceptionMessageUtil.getMessageFromResponse(response)));
                    }
                }
                futureTypeList.complete(typeResponse);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        Thread getMembranePolymerTypesThread = new Thread(runnable);
        getMembranePolymerTypesThread.start();

        return futureTypeList.get();
    }
}
