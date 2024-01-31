package com.example.project_for_university.service;

import com.example.project_for_university.Main;
import com.example.project_for_university.dto.forBackend.ReturnAllTypesDto;
import com.example.project_for_university.enums.UrlRoutes;
import com.example.project_for_university.http.JsonToClass;
import com.example.project_for_university.service.models.ReturnAllTypesModel;
import com.example.project_for_university.utils.AuthUtils;
import lombok.SneakyThrows;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ReturnAllTypesService {
    private final ReturnAllTypesModel returnAllTypesModel = new ReturnAllTypesModel();

    public ReturnAllTypesModel getAllTypesThread(String email, String password) throws ExecutionException, InterruptedException {
        CompletableFuture<ReturnAllTypesModel> futureUserModel = new CompletableFuture<>();

        Runnable runnable = new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                CloseableHttpClient httpClient = HttpClients.createDefault();

                HttpUriRequest httpGet = RequestBuilder.get()
                        .setUri(Main.host.getValue() + UrlRoutes.GET_ALL_TYPES.getName())
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .setHeader("Content-Type", "application/json")
                        .build();

                CloseableHttpResponse response = httpClient.execute(httpGet);
                if(response.getStatusLine().getStatusCode() != 200) {
                    returnAllTypesModel.setError(true);
                } else {
                    returnAllTypesModel.setReturnAllTypesDto(JsonToClass.parseToObject(ReturnAllTypesDto.class, response));
                    returnAllTypesModel.setError(false);
                }
                futureUserModel.complete(returnAllTypesModel);
            }
        };

        Thread getAllTypesThread = new Thread(runnable);
        getAllTypesThread.start();

        return futureUserModel.get();
    }

}
