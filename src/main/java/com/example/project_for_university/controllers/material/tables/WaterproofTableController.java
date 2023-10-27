package com.example.project_for_university.controllers.material.tables;


import com.example.project_for_university.enums.Component;
import com.example.project_for_university.utils.ComponentUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.providers.DataProvider;
import lombok.SneakyThrows;

import java.io.IOException;

public class WaterproofTableController implements DataProvider {
    private AllValues allValues;

    @FXML
    private HBox back_btn;

    @FXML
    private TextField dynamicWaterproofCriteria_base;

    @FXML
    private TextField dynamicWaterproofCriteria_experimental_1;

    @FXML
    private TextField dynamicWaterproofCriteria_experimental_2;

    @FXML
    private TextField dynamicWaterproofCriteria_experimental_3;

    @FXML
    private TextField dynamicWaterproofCriteria_experimental_4;

    @FXML
    private TextField dynamicWaterproofCriteria_weight;

    @FXML
    private TextArea equipment;

    @FXML
    private TextField hydrostaticPressure;

    @FXML
    private TextField hydrostaticPressureIncreaseSpeed;

    @FXML
    private TextField materialBlottingPressure_base;

    @FXML
    private TextField materialBlottingPressure_calculated;

    @FXML
    private TextField materialBlottingPressure_experimental_1;

    @FXML
    private TextField materialBlottingPressure_weight;

    @FXML
    private TextField materialBlottingTime_base;

    @FXML
    private TextField materialBlottingTime_calculated;

    @FXML
    private TextField materialBlottingTime_experimental_1;

    @FXML
    private TextField materialBlottingTime_weight;

    @FXML
    private HBox next_btn;

    @FXML
    private TextField waterproofRealizationCriteria_base;

    @FXML
    private TextField waterproofRealizationCriteria_experimental_1;

    @FXML
    private TextField waterproofRealizationCriteria_experimental_2;

    @FXML
    private TextField waterproofRealizationCriteria_weight;

    @FXML
    private TextField waterproofTime;

    @FXML
    private TextField waterproof_base;

    @FXML
    private TextField waterproof_calculated;

    @FXML
    private TextField waterproof_experimental_1;

    @FXML
    private TextField waterproof_weight;

    @Override
    public void setData(AllValues allValues) {
        this.allValues = allValues;
    }

    @FXML
    @SneakyThrows()
    void back_btn_clicked(MouseEvent event) {
        ComponentUtil.mount(Component.CONDITION_2, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @FXML
    @SneakyThrows()
    void next_btn_clicked(MouseEvent event) {
        ComponentUtil.mount(Component.HOMEOSTASIS_TABLE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

}
