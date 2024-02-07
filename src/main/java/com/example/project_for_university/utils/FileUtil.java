package com.example.project_for_university.utils;

import com.example.project_for_university.dto.AllValues;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Arrays;
import java.util.Base64;
import java.util.Set;

public class FileUtil {

    private static String curDataDirectoryName = "data";
    private static Path dataDirectoryPath = Paths.get(System.getProperty("user.dir") + File.separator + curDataDirectoryName);

    private static String emailFileName = "sem.txt";
    private static Path emailFilePath = Paths.get(dataDirectoryPath + File.separator + emailFileName);

    private static String appVersionFileName = "vers.txt";
    private static Path appVersionFilePath = Paths.get(dataDirectoryPath + File.separator + appVersionFileName);

    public static boolean isExistsData() {
        return Files.exists(dataDirectoryPath) && Files.isDirectory(dataDirectoryPath);
    }

    public static void createDataDirectory() {
        File dataDirectory = new File(dataDirectoryPath.toString());

        if (!dataDirectory.exists()) {
            if (dataDirectory.mkdir()) {
                System.out.println("Data directory created: " + dataDirectory.getAbsolutePath());
            } else {
                System.out.println("Failed to create data directory");
            }
        }
    }

    public static void saveEmail(String email) {
        String encodedEmail = Base64.getEncoder().encodeToString(email.getBytes());

        try {
            Files.write(emailFilePath, encodedEmail.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clearSavedEmail() {
        try {
            Files.write(emailFilePath, new String().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getSavedEmail() {
        String encodedEmail = "";

        try {
            encodedEmail = new String(Files.readAllBytes(emailFilePath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        byte[] decodedEmailBytes = Base64.getDecoder().decode(encodedEmail);
        String decodedEmail = new String(decodedEmailBytes);

        return decodedEmail;
    }

    public static void saveAppVersion(String version) {
        try {
            Files.write(appVersionFilePath, version.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final String getAppVersion() {
        String appVersion = "";
        try {
            appVersion = Arrays.toString(Files.readAllBytes(appVersionFilePath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return appVersion;
    }
}
