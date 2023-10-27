package com.example.project_for_university.controllers.material.tables;

import com.example.project_for_university.enums.Component;
import com.example.project_for_university.utils.ComponentUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.providers.DataProvider;
import lombok.SneakyThrows;

public class ReliabilityTableController implements DataProvider {
    private AllValues allValues;

    @FXML
    private HBox back_btn;

    @FXML
    private TextField impactCyclesCnt;

    @FXML
    private TextField maxWaterResistanceLvl;

    @FXML
    private HBox next_btn;

    @FXML
    private TextField relativeBlottingPressureAfterLoad_base;

    @FXML
    private TextField relativeBlottingPressureAfterLoad_calculated;

    @FXML
    private TextField relativeBlottingPressureAfterLoad_experimental_1;

    @FXML
    private TextField relativeBlottingPressureAfterLoad_weight;

    @FXML
    private TextField relativeBlottingTimeAfterLoad_base;

    @FXML
    private TextField relativeBlottingTimeAfterLoad_calculated;

    @FXML
    private TextField relativeBlottingTimeAfterLoad_experimental_1;

    @FXML
    private TextField relativeBlottingTimeAfterLoad_weight;

    @FXML
    private TextField relativeWaterResistanceAfterLoad_base;

    @FXML
    private TextField relativeWaterResistanceAfterLoad_calculated;

    @FXML
    private TextField relativeWaterResistanceAfterLoad_experimental_1;

    @FXML
    private TextField relativeWaterResistanceAfterLoad_weight;

    @FXML
    private TextField waterproofFunctionResource_base;

    @FXML
    private TextField waterproofFunctionResource_weight;

    @FXML
    private TextField waterproofRealizationCriteriaAfterLoad_experimental_1;

    @FXML
    private TextField waterproofRealizationCriteriaAfterLoad_experimental_2;

    @FXML
    private TextField waterproofRealizationCriteriaAfterLoad_weight;

    @Override
    public void setData(AllValues allValues) {
        this.allValues = allValues;
    }

    @FXML
    @SneakyThrows()
    void back_btn_clicked(MouseEvent event) {
        ComponentUtil.mount(Component.HOMEOSTASIS_TABLE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @FXML
    @SneakyThrows()
    void next_btn_clicked(MouseEvent event) {
        ComponentUtil.mount(Component.ESTIMATION_TABLE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }
}
