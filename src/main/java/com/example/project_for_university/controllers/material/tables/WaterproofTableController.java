package com.example.project_for_university.controllers.material.tables;


import com.example.project_for_university.dto.forBackend.calculate.CalculateWaterproofFunctionDto;
import com.example.project_for_university.enums.Component;
import com.example.project_for_university.utils.ComponentUtil;
import com.example.project_for_university.utils.ValidationUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.providers.DataProvider;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WaterproofTableController implements DataProvider, Initializable {
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

        if(allValues.getCreateMaterialDto().getWaterproofFunction() == null) {
            allValues.getCreateMaterialDto().setWaterproofFunction(new CalculateWaterproofFunctionDto());
        }

        for (int i = 0; i < allValues.getSideBarButtonsEventHandlers().size(); i++) {
            allValues.getSideBarButtons().get(i).removeEventHandler(MouseEvent.MOUSE_CLICKED, allValues.getSideBarButtonsEventHandlers().get(i));
        }
        allValues.getSideBarButtonsEventHandlers().clear();

        for (var button : allValues.getSideBarButtons()) {
            EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("waterproof sideBartBtn");
                    // тут сетишь значения условий в allValues
                }
            };
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, clickHandler);
            allValues.getSideBarButtonsEventHandlers().add(clickHandler);
        }
    }

    @FXML
    @SneakyThrows()
    void back_btn_clicked(MouseEvent event) {
        allValues.setLastCreateMaterialComponent(Component.CONDITION_1);
        ComponentUtil.mount(Component.CONDITION_1, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @FXML
    @SneakyThrows()
    void next_btn_clicked(MouseEvent event) {
        allValues.setLastCreateMaterialComponent(Component.HOMEOSTASIS_TABLE);
        ComponentUtil.mount(Component.HOMEOSTASIS_TABLE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //экспериме
        materialBlottingPressure_experimental_1.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        materialBlottingPressure_experimental_1.textProperty().addListener((observable, oldValue, newValue) -> allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingPressure_experimental_1(Integer.parseInt(newValue)));

        waterproof_experimental_1.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        waterproof_experimental_1.textProperty().addListener((observable, oldValue, newValue) -> allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproof_experimental_1(Integer.parseInt(newValue)));

        materialBlottingTime_experimental_1.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        materialBlottingTime_experimental_1.textProperty().addListener((observable, oldValue, newValue) -> allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingTime_experimental_1(Integer.parseInt(newValue)));

        waterproofRealizationCriteria_experimental_1.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        waterproofRealizationCriteria_experimental_1.textProperty().addListener((observable, oldValue, newValue) -> allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproofRealizationCriteria_experimental_1(Integer.parseInt(newValue)));

        waterproofRealizationCriteria_experimental_2.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        waterproofRealizationCriteria_experimental_2.textProperty().addListener((observable, oldValue, newValue) -> allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproofRealizationCriteria_experimental_2(Integer.parseInt(newValue)));

        dynamicWaterproofCriteria_experimental_1.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        dynamicWaterproofCriteria_experimental_1.textProperty().addListener((observable, oldValue, newValue) -> allValues.getCreateMaterialDto().getWaterproofFunction().setDynamicWaterproofCriteria_experimental_1(Integer.parseInt(newValue)));

        dynamicWaterproofCriteria_experimental_2.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        dynamicWaterproofCriteria_experimental_2.textProperty().addListener((observable, oldValue, newValue) -> allValues.getCreateMaterialDto().getWaterproofFunction().setDynamicWaterproofCriteria_experimental_2(Integer.parseInt(newValue)));

        dynamicWaterproofCriteria_experimental_3.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        dynamicWaterproofCriteria_experimental_3.textProperty().addListener((observable, oldValue, newValue) -> allValues.getCreateMaterialDto().getWaterproofFunction().setDynamicWaterproofCriteria_experimental_3(Integer.parseInt(newValue)));

        dynamicWaterproofCriteria_experimental_4.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        dynamicWaterproofCriteria_experimental_4.textProperty().addListener((observable, oldValue, newValue) -> allValues.getCreateMaterialDto().getWaterproofFunction().setDynamicWaterproofCriteria_experimental_4(Integer.parseInt(newValue)));

        hydrostaticPressureIncreaseSpeed.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        hydrostaticPressureIncreaseSpeed.textProperty().addListener((observable, oldValue, newValue) -> allValues.getCreateMaterialDto().getWaterproofFunction().setHydrostaticPressureIncreaseSpeed(Integer.parseInt(newValue)));

        hydrostaticPressure.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        hydrostaticPressure.textProperty().addListener((observable, oldValue, newValue) -> allValues.getCreateMaterialDto().getWaterproofFunction().setHydrostaticPressure(Integer.parseInt(newValue)));

        waterproofTime.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        waterproofTime.textProperty().addListener((observable, oldValue, newValue) -> allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproofTime(Integer.parseInt(newValue)));
    }
}
