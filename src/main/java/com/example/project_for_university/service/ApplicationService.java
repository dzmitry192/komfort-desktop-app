package com.example.project_for_university.service;

import com.example.project_for_university.Main;
import com.example.project_for_university.dto.forBackend.entity.DesktopEntity;
import com.example.project_for_university.dto.forBackend.entity.UserEntity;
import com.example.project_for_university.enums.ServiceEnum;
import com.example.project_for_university.enums.UrlRoutes;
import com.example.project_for_university.http.JsonToClass;
import com.example.project_for_university.service.models.GetAppLatestVersionResponse;
import com.example.project_for_university.utils.AuthUtils;
import com.example.project_for_university.utils.ExceptionMessageUtil;
import lombok.SneakyThrows;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class ApplicationService {

    public static final ApplicationService INSTANCE = new ApplicationService();

    @SneakyThrows
    public GetAppLatestVersionResponse getApplicationLatestVersionResponse() {
        CompletableFuture<GetAppLatestVersionResponse> completableFuture = new CompletableFuture<>();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                    HttpUriRequest httpGet = RequestBuilder.get()
                            .setUri(Main.host.getValue() + UrlRoutes.GET_DESKTOP_LATEST_VERSION.getName())
                            .build();

                    GetAppLatestVersionResponse getAppLatestVersionResponse = new GetAppLatestVersionResponse();
                    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                        if (response.getStatusLine().getStatusCode() == 200) {
                            getAppLatestVersionResponse.setError(false);
                            getAppLatestVersionResponse.setDesktop(JsonToClass.parseToObject(DesktopEntity.class, response));
                        } else {
                            getAppLatestVersionResponse.setError(true);
                            getAppLatestVersionResponse.setMessage(ExceptionMessageUtil.getErrorMessage(ServiceEnum.MATERIAL, response.getStatusLine().getStatusCode(), ExceptionMessageUtil.getMessageFromResponse(response)));
                        }
                        getAppLatestVersionResponse.setStatusCode(response.getStatusLine().getStatusCode());
                        completableFuture.complete(getAppLatestVersionResponse);
                    } catch (IOException e) {
                        throw new IOException(e);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        return completableFuture.get();
    }

}
