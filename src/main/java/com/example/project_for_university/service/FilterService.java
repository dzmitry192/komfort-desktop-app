package com.example.project_for_university.service;

import com.example.project_for_university.Main;
import com.example.project_for_university.dto.forBackend.entity.ProductionMethodEntity;
import com.example.project_for_university.dto.forBackend.entity.types.MembraneLayerPolymerTypeEntity;
import com.example.project_for_university.http.JsonToClass;
import com.example.project_for_university.service.models.FilterServiceModel;
import com.example.project_for_university.utils.AuthUtils;
import lombok.SneakyThrows;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class FilterService {

    public FilterServiceModel filterThread(String email, String password) throws ExecutionException, InterruptedException {
        CompletableFuture<FilterServiceModel> futureFilterModel = new CompletableFuture<>();

        Runnable runnable = new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                CloseableHttpClient httpClient = HttpClients.createDefault();

                HttpUriRequest httpGet_memb = RequestBuilder.get()
                        .setUri(Main.host.getValue() + "/membrane-layer-polymer-type")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .setHeader("Content-Type", "application/json")
                        .build();

                CloseableHttpResponse response = httpClient.execute(httpGet_memb);
                MembraneLayerPolymerTypeEntity[] membraneLayerPolymerTypeEntities = JsonToClass.parseToListObject(MembraneLayerPolymerTypeEntity.class, response).toArray(MembraneLayerPolymerTypeEntity[]::new);

                HttpUriRequest httpGet_prod = RequestBuilder.get()
                        .setUri(Main.host.getValue() + "/production-method")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(email, password))
                        .setHeader("Content-Type", "application/json")
                        .build();

                CloseableHttpResponse response_prod = httpClient.execute(httpGet_prod);
                ProductionMethodEntity[] productionMethodEntities = JsonToClass.parseToListObject(ProductionMethodEntity.class, response_prod).toArray(ProductionMethodEntity[]::new);

                FilterServiceModel filterServiceModel = new FilterServiceModel(membraneLayerPolymerTypeEntities, productionMethodEntities);

                futureFilterModel.complete(filterServiceModel);
            }
        };

        Thread filterThread = new Thread(runnable);
        filterThread.start();

        return futureFilterModel.get();
    }

}
