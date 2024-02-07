package com.example.project_for_university.controllers;

import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.ContentPanes;
import com.example.project_for_university.dto.forBackend.PaginationDto;
import com.example.project_for_university.enums.Component;
import com.example.project_for_university.providers.DataProvider;
import com.example.project_for_university.service.ApplicationService;
import com.example.project_for_university.service.models.GetAppLatestVersionResponse;
import com.example.project_for_university.utils.AlertUtil;
import com.example.project_for_university.utils.ComponentUtil;
import com.example.project_for_university.utils.FileUtil;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

@Data
public class MainController implements Initializable, DataProvider {

    private AllValues allValues;
    private Component lastComponent;
    @FXML
    private StackPane mainContentPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            AllValues allValues = new AllValues();
            ContentPanes contentPanes = new ContentPanes(mainContentPane, null);

            Scene rootScene = mainContentPane.getScene();
            Stage rootStage = (Stage) rootScene.getWindow();

            if(!FileUtil.isExistsData()) {
                FileUtil.createDataDirectory();
            }

            //запрос на версию приложения, ее установка в allValues.appVersion и запись в файл
            GetAppLatestVersionResponse response = ApplicationService.INSTANCE.getApplicationLatestVersionResponse();
            if(response.isError()) {
                System.out.println("ОШИБКА ПРИ ПОЛУЧЕНИИ ПОСЛЕДНЕЙ ВЕРСИИ ПРИЛОЖЕНИЯ");
            } else {
                String latestAppVersion = response.getDesktop().getVersion();
                String curAppVersion = FileUtil.getAppVersion();
                if(!curAppVersion.equals(latestAppVersion)) {
                    FileUtil.saveAppVersion(latestAppVersion);
                    allValues.setAppVersion(latestAppVersion);
                }
            }


            //content-panes
            allValues.setContentPanes(contentPanes);
            allValues.setRootStage(rootStage);


            try {
                ComponentUtil.mount(Component.LOGIN, mainContentPane, allValues);
            } catch (IOException | ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void setData(AllValues allValues) {
        this.allValues = allValues;
    }
}
