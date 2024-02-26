package com.example.project_for_university.service.admin;

import com.example.project_for_university.Main;
import com.example.project_for_university.controllers.user.admin.models.PhType;
import com.example.project_for_university.dto.forBackend.entity.ProductionMethodEntity;
import com.example.project_for_university.dto.forBackend.entity.types.PhysicalActivityTypeEntity;
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

import static com.example.project_for_university.Main.httpClient;

public class ProductionMethodService implements CrudService<ProductionMethodEntity> {

    public static final ProductionMethodService INSTANCE = new ProductionMethodService();

    @SneakyThrows
    @Override
    public TypesResponse<ProductionMethodEntity> getAll(String email, String password) {
        CompletableFuture<TypesResponse<ProductionMethodEntity>> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            HttpUriRequest httpGet = RequestBuilder.get()
                    .setUri(Main.host.getValue() + UrlRoutes.GET_PRODUCTION_METHODS.getName())
                    .setHeader("Content-Type", "application/json")
                    .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                    .build();

            TypesResponse<ProductionMethodEntity> typesResponse = new TypesResponse<>();
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                if (response.getStatusLine().getStatusCode() == 200) {
                    typesResponse.setError(false);
                    typesResponse.setStatusCode(response.getStatusLine().getStatusCode());
                    typesResponse.setTypes(JsonToClass.parseToListObject(ProductionMethodEntity.class, response).toArray(ProductionMethodEntity[]::new));
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

        Thread getProductionMethodsThread = new Thread(runnable);
        getProductionMethodsThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public TypeResponse<ProductionMethodEntity> getById(int id, String email, String password) {
        CompletableFuture<TypeResponse<ProductionMethodEntity>> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                HttpUriRequest httpGet = RequestBuilder.get()
                        .setUri(Main.host.getValue() + UrlRoutes.GET_PRODUCTION_METHOD_BY_ID.getName() + id)
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .build();

                TypeResponse<ProductionMethodEntity> typeResponse = new TypeResponse<>();
                try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                    if (response.getStatusLine().getStatusCode() == 200) {
                        typeResponse.setError(false);
                        typeResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        typeResponse.setType(JsonToClass.parseToObject(ProductionMethodEntity.class, response));
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

        Thread getProductionMethodsThread = new Thread(runnable);
        getProductionMethodsThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public TypeResponse<ProductionMethodEntity> create(PhType phType, String email, String password) {
        CompletableFuture<TypeResponse<ProductionMethodEntity>> futureTypeList = new CompletableFuture<>();

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

                TypeResponse<ProductionMethodEntity> typeResponse = new TypeResponse<>();
                try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                    if (response.getStatusLine().getStatusCode() == 201) {
                        typeResponse.setError(false);
                        typeResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        typeResponse.setType(JsonToClass.parseToObject(ProductionMethodEntity.class, response));
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

        Thread getProductionMethodsThread = new Thread(runnable);
        getProductionMethodsThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public TypeResponse<ProductionMethodEntity> update(PhType phType, String email, String password) {
        CompletableFuture<TypeResponse<ProductionMethodEntity>> futureTypeList = new CompletableFuture<>();

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

                TypeResponse<ProductionMethodEntity> typeResponse = new TypeResponse<>();
                try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                    if (response.getStatusLine().getStatusCode() == 200) {
                        typeResponse.setError(false);
                        typeResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        typeResponse.setType(JsonToClass.parseToObject(ProductionMethodEntity.class, response));
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

        Thread getProductionMethodsThread = new Thread(runnable);
        getProductionMethodsThread.start();

        return futureTypeList.get();
    }

    @SneakyThrows
    @Override
    public TypeResponse<ProductionMethodEntity> delete(int id, String email, String password) {
        CompletableFuture<TypeResponse<ProductionMethodEntity>> futureTypeList = new CompletableFuture<>();

        Runnable runnable = () -> {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                HttpUriRequest httpGet = RequestBuilder.delete()
                        .setUri(Main.host.getValue() + UrlRoutes.DELETE_PRODUCTION_METHOD_BY_ID.getName() + id)
                        .setHeader("Content-Type", "application/json")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .build();

                TypeResponse<ProductionMethodEntity> typeResponse = new TypeResponse<>();
                try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                    if (response.getStatusLine().getStatusCode() == 200) {
                        typeResponse.setError(false);
                        typeResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        typeResponse.setType(JsonToClass.parseToObject(ProductionMethodEntity.class, response));
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

        Thread getProductionMethodsThread = new Thread(runnable);
        getProductionMethodsThread.start();

        return futureTypeList.get();
    }
}
