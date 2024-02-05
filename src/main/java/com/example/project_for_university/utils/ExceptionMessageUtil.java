package com.example.project_for_university.utils;

import com.example.project_for_university.enums.ErrorMessage;
import com.example.project_for_university.enums.ServiceEnum;
import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

public class ExceptionMessageUtil {
    public static String getErrorMessage(ServiceEnum serviceEnum, int statusCode, String message) {
        if(serviceEnum.equals(ServiceEnum.AUTH_LOGIN)) {
            return switch (statusCode) {
                case 400 -> ErrorMessage.LOGIN_400.getMessage();
                case 401 -> ErrorMessage.LOGIN_401.getMessage();
                case 404 -> ErrorMessage.LOGIN_404.getMessage();
                default -> ErrorMessage.DEFAULT.getMessage();
            };
        } else if(serviceEnum.equals(ServiceEnum.AUTH_SIGNUP)) {
            return switch (statusCode) {
                case 400 -> message.contains("email must be an email") ? ErrorMessage.SIGNUP_400_EMAIL.getMessage() : ErrorMessage.SIGNUP_400_PASSWORD.getMessage();
                case 401 -> ErrorMessage.SIGNUP_401.getMessage();
                case 404 -> ErrorMessage.SIGNUP_404.getMessage();
                default -> ErrorMessage.DEFAULT.getMessage();
            };
        } else if(serviceEnum.equals(ServiceEnum.MATERIAL)) {
            return switch (statusCode) {
                case 500 -> ErrorMessage.SERVER_ERROR.getMessage();
                case 403 -> message.contains("You can update") ? ErrorMessage.MATERIAL_PATCH_403.getMessage() : ErrorMessage.MATERIAL_DELETE_403.getMessage();
                case 404 -> message.contains("No such material") ? ErrorMessage.REPORT_NOT_FOUND.getMessage() : ErrorMessage.ENTITY_NOT_FOUND.getMessage();
                default -> ErrorMessage.DEFAULT.getMessage();
            };
        } else if(serviceEnum.equals(ServiceEnum.ALL_TYPES)) {
            return switch (statusCode) {
                case 500 -> ErrorMessage.SERVER_ERROR.getMessage();
                default -> ErrorMessage.DEFAULT.getMessage();
            };
        } else if(serviceEnum.equals(ServiceEnum.TYPE)) {
            return switch (statusCode) {
                case 400 -> ErrorMessage.TYPE_400.getMessage();
                case 404 -> ErrorMessage.ENTITY_NOT_FOUND.getMessage();
                case 500 -> message.contains("Some entities refer") ? ErrorMessage.TYPE_500.getMessage() : ErrorMessage.SERVER_ERROR.getMessage();
                default -> ErrorMessage.DEFAULT.getMessage();
            };
        }

        return ErrorMessage.DEFAULT.getMessage();
    }

    @SneakyThrows
    public static String getMessageFromResponse(CloseableHttpResponse response) {
        return JsonParser.parseString(EntityUtils.toString(response.getEntity())).getAsJsonObject().get("message").toString();
    }
}
