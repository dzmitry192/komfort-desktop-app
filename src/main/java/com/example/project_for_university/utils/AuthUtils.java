package com.example.project_for_university.utils;

import lombok.Data;

import java.util.Base64;

@Data
public class AuthUtils {
    public static String header = "Authorization";
    public static String getAuth(String email, String password) {
        String encodeEmailPassword =  Base64.getEncoder().encodeToString((email + ":" + password).getBytes());
        return "Basic " + encodeEmailPassword;
    }
}
