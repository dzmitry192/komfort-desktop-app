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

public class HomeostasisTableController implements DataProvider {
    private AllValues allValues;

    @FXML
    private TextField avgOutdoorAirSpeed;

    @FXML
    private HBox back_btn;

    @FXML
    private TextField comfHumidityInsideClothes;

    @FXML
    private TextField comfTempInsideClothes;

    @FXML
    private TextArea equipment;

    @FXML
    private TextField m;

    @FXML
    private TextField m1max;

    @FXML
    private TextField m1min;

    @FXML
    private TextField m1s;

    @FXML
    private TextField m2max;

    @FXML
    private TextField m2min;

    @FXML
    private TextField m2s;

    @FXML
    private TextField maxOutdoorHumidity;

    @FXML
    private TextField maxOutdoorTemp;

    @FXML
    private TextField minOutdoorHumidity;

    @FXML
    private TextField minOutdoorTemp;

    @FXML
    private HBox next_btn;

    @FXML
    private TextField s;

    @FXML
    private TextField s0_1;

    @FXML
    private TextField s0_2;

    @FXML
    private TextField sampleSurfaceArea;

    @FXML
    private TextField t_1;

    @FXML
    private TextField t_2;

    @FXML
    private TextField tos;

    @FXML
    private TextField totalThermalResistance_base;

    @FXML
    private TextField totalThermalResistance_weight;

    @FXML
    private TextField waterPermeabilityDynamicCriteria_base;

    @FXML
    private TextField waterPermeabilityDynamicCriteria_weight;

    @FXML
    private TextField waterPermeability_base;

    @FXML
    private TextField waterPermeability_weight;

    @Override
    public void setData(AllValues allValues) {
        this.allValues = allValues;
    }

    @FXML
    @SneakyThrows()
    void back_btn_clicked(MouseEvent event) {
        ComponentUtil.mount(Component.WATERPROOF_TABLE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @FXML
    @SneakyThrows()
    void next_btn_clicked(MouseEvent event) {
        ComponentUtil.mount(Component.RELIABILITY_TABLE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

}
