package com.example.project_for_university.service;

import com.example.project_for_university.Main;
import com.example.project_for_university.dto.forBackend.ReturnAllTypesDto;
import com.example.project_for_university.enums.ServiceEnum;
import com.example.project_for_university.enums.UrlRoutes;
import com.example.project_for_university.http.JsonToClass;
import com.example.project_for_university.service.models.get.GetAllTypesResponse;
import com.example.project_for_university.utils.AuthUtils;
import com.example.project_for_university.utils.ExceptionMessageUtil;
import lombok.SneakyThrows;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AllTypesService {

    public static final AllTypesService INSTANCE = new AllTypesService();
    private final GetAllTypesResponse getAllTypesResponse = new GetAllTypesResponse();

    public GetAllTypesResponse getAllTypesThread(String email, String password) throws ExecutionException, InterruptedException {
        CompletableFuture<GetAllTypesResponse> futureUserModel = new CompletableFuture<>();

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
                    getAllTypesResponse.setError(true);
                    getAllTypesResponse.setStatusCode(response.getStatusLine().getStatusCode());
                    getAllTypesResponse.setMessage(ExceptionMessageUtil.getErrorMessage(ServiceEnum.ALL_TYPES, response.getStatusLine().getStatusCode(), null));
                } else {
                    getAllTypesResponse.setReturnAllTypesDto(JsonToClass.parseToObject(ReturnAllTypesDto.class, response));
                    getAllTypesResponse.setError(false);
                    getAllTypesResponse.setStatusCode(response.getStatusLine().getStatusCode());
                }
                futureUserModel.complete(getAllTypesResponse);
            }
        };

        Thread getAllTypesThread = new Thread(runnable);
        getAllTypesThread.start();

        return futureUserModel.get();
    }

}
