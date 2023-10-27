package com.example.project_for_university.http;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;


public class CheckUserRequest implements Runnable {
    @Override
    public void run() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
//            new ObjectMapper().convertValue(user, Map.class);
//            jsonObject.put("title","foo");
//            jsonObject.put("body","bar");
//            jsonObject.put("userId", 1);

//            StringEntity stringEntity = new StringEntity();

            HttpUriRequest httpPost = RequestBuilder.post()
                    .setUri("https://jsonplaceholder.typicode.com/posts")
//                    .setEntity(stringEntity)
                    .addHeader("Content-type","application/json; charset=UTF-8")
                    .build();

            CloseableHttpResponse response = httpClient.execute(httpPost);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}