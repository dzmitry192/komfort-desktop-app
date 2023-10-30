package com.example.project_for_university.service;

import com.example.project_for_university.Main;
import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.forBackend.entity.types.PartialMaterialEntity;
import com.example.project_for_university.http.JsonToClass;
import com.example.project_for_university.service.models.FilterMaterialsModel;
import com.example.project_for_university.utils.AuthUtils;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class FilterSearchService {
    private final FilterMaterialsModel filterMaterialsModel = new FilterMaterialsModel();

    @SneakyThrows
    public FilterMaterialsModel getFilterMaterialsThread(AllValues allValues, HashMap<String, String> queryParams) {
        CompletableFuture<FilterMaterialsModel> futureMaterials = new CompletableFuture<>();

        Runnable runnable = new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                CloseableHttpClient httpClient = HttpClients.createDefault();

                NameValuePair[] nameValuePairs = new BasicNameValuePair[queryParams.size()];
                int i = 0;
                for(Map.Entry<String, String> entry : queryParams.entrySet()) {
                    nameValuePairs[i] = new BasicNameValuePair(entry.getKey(), entry.getValue());
                    i++;
                }

                HttpUriRequest httpGet = RequestBuilder.get()
                        .setUri(Main.host.getValue() + "/material")
                        .setHeader(AuthUtils.header, AuthUtils.getAuth(allValues.getUser().getEmail(), allValues.getUser().getPassword()))
                        .setHeader("Content-Type", "application/json")
                        .addParameters(nameValuePairs)
                        .addParameter("page", String.valueOf(allValues.getPaginationDto().getPage()))
                        .addParameter("perPage", String.valueOf(allValues.getPaginationDto().getPerPage()))
                        .build();

                CloseableHttpResponse response = httpClient.execute(httpGet);
                if(response.getStatusLine().getStatusCode() != 200) {
                    filterMaterialsModel.setError(true);
                } else {
                    filterMaterialsModel.setPartialMaterials(JsonToClass.parseToListObject(PartialMaterialEntity.class, response).toArray(PartialMaterialEntity[]::new));
                    filterMaterialsModel.setError(false);
                }
                futureMaterials.complete(filterMaterialsModel);
            }
        };

        Thread getFilterMaterialsThread = new Thread(runnable);
        getFilterMaterialsThread.start();

        return futureMaterials.get();
    }
}
