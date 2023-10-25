package com.example.project_for_university.controllers;

import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.ContentPanes;
import com.example.project_for_university.enums.Page;
import com.example.project_for_university.utils.ControllerUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import lombok.Data;
import lombok.SneakyThrows;

import java.net.URL;
import java.util.ResourceBundle;

@Data
public class MainController extends Node implements Initializable {

    @FXML
    private StackPane mainContentPane;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AllValues allValues = new AllValues();
        ContentPanes contentPanes = new ContentPanes(mainContentPane, null);
        allValues.setContentPanes(contentPanes);
        ControllerUtils.changePage(Page.LOGIN, mainContentPane, allValues);
    }
}
