package com.example.project_for_university.controllers.material.tables;


import com.example.project_for_university.dto.forBackend.calculate.CalculateWaterproofFunctionDto;
import com.example.project_for_university.enums.Component;
import com.example.project_for_university.utils.AlertUtil;
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
import java.util.*;

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

        if (allValues.getCreateMaterialDto().getWaterproofFunction() == null) {
            allValues.getCreateMaterialDto().setWaterproofFunction(new CalculateWaterproofFunctionDto());
        }

        fillWaterproofTable();

        for (int i = 0; i < allValues.getSideBarButtonsEventHandlers().size(); i++) {
            allValues.getSideBarButtons().get(i).removeEventHandler(MouseEvent.MOUSE_CLICKED, allValues.getSideBarButtonsEventHandlers().get(i));
        }
        allValues.getSideBarButtonsEventHandlers().clear();

        for (var button : allValues.getSideBarButtons()) {
            EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("waterproof sideBartBtn");
                    saveDataFromInputs();
                }
            };
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, clickHandler);
            allValues.getSideBarButtonsEventHandlers().add(clickHandler);
        }
    }

    private void fillWaterproofTable() {
        CalculateWaterproofFunctionDto waterproofFunctionDto = allValues.getCreateMaterialDto().getWaterproofFunction();

        if (waterproofFunctionDto != null) {
            //1 column
            if (waterproofFunctionDto.getMaterialBlottingPressure_experimental_1() != -1) {
                materialBlottingPressure_experimental_1.setText(String.valueOf(waterproofFunctionDto.getMaterialBlottingPressure_experimental_1()));
            }
            if (waterproofFunctionDto.getWaterproof_experimental_1() != -1) {
                waterproof_experimental_1.setText(String.valueOf(waterproofFunctionDto.getWaterproof_experimental_1()));
            }
            if (waterproofFunctionDto.getMaterialBlottingTime_experimental_1() != -1) {
                materialBlottingTime_experimental_1.setText(String.valueOf(waterproofFunctionDto.getMaterialBlottingTime_experimental_1()));
            }
            if (waterproofFunctionDto.getWaterproofRealizationCriteria_experimental_1() != -1) {
                waterproofRealizationCriteria_experimental_1.setText(String.valueOf(waterproofFunctionDto.getWaterproofRealizationCriteria_experimental_1()));
            }
            if (waterproofFunctionDto.getWaterproofRealizationCriteria_experimental_2() != -1) {
                waterproofRealizationCriteria_experimental_2.setText(String.valueOf(waterproofFunctionDto.getWaterproofRealizationCriteria_experimental_2()));
            }
            if (waterproofFunctionDto.getDynamicWaterproofCriteria_experimental_1() != -1) {
                dynamicWaterproofCriteria_experimental_1.setText(String.valueOf(waterproofFunctionDto.getDynamicWaterproofCriteria_experimental_1()));
            }
            if (waterproofFunctionDto.getDynamicWaterproofCriteria_experimental_2() != -1) {
                dynamicWaterproofCriteria_experimental_2.setText(String.valueOf(waterproofFunctionDto.getDynamicWaterproofCriteria_experimental_2()));
            }
            if (waterproofFunctionDto.getDynamicWaterproofCriteria_experimental_3() != -1) {
                dynamicWaterproofCriteria_experimental_3.setText(String.valueOf(waterproofFunctionDto.getDynamicWaterproofCriteria_experimental_3()));
            }
            if (waterproofFunctionDto.getDynamicWaterproofCriteria_experimental_4() != -1) {
                dynamicWaterproofCriteria_experimental_4.setText(String.valueOf(waterproofFunctionDto.getDynamicWaterproofCriteria_experimental_4()));
            }
            if (waterproofFunctionDto.getHydrostaticPressureIncreaseSpeed() != -1) {
                hydrostaticPressureIncreaseSpeed.setText(String.valueOf(waterproofFunctionDto.getHydrostaticPressureIncreaseSpeed()));
            }
            if (waterproofFunctionDto.getHydrostaticPressure() != -1) {
                hydrostaticPressure.setText(String.valueOf(waterproofFunctionDto.getHydrostaticPressure()));
            }
            if (waterproofFunctionDto.getWaterproofTime() != -1) {
                waterproofTime.setText(String.valueOf(waterproofFunctionDto.getWaterproofTime()));
            }

            //2 column
            if (waterproofFunctionDto.getMaterialBlottingPressure_calculated() != -1) {
                materialBlottingPressure_calculated.setText(String.valueOf(waterproofFunctionDto.getMaterialBlottingPressure_calculated()));
            }
            if (waterproofFunctionDto.getWaterproof_calculated() != -1) {
                waterproof_calculated.setText(String.valueOf(waterproofFunctionDto.getWaterproof_calculated()));
            }
            if (waterproofFunctionDto.getMaterialBlottingTime_calculated() != -1) {
                materialBlottingTime_calculated.setText(String.valueOf(waterproofFunctionDto.getMaterialBlottingTime_calculated()));
            }

            //3 column
            if (waterproofFunctionDto.getMaterialBlottingPressure_base() != -1) {
                materialBlottingPressure_base.setText(String.valueOf(waterproofFunctionDto.getMaterialBlottingPressure_base()));
            }
            if (waterproofFunctionDto.getWaterproof_base() != -1) {
                waterproof_base.setText(String.valueOf(waterproofFunctionDto.getWaterproof_base()));
            }
            if (waterproofFunctionDto.getMaterialBlottingTime_base() != -1) {
                materialBlottingTime_base.setText(String.valueOf(waterproofFunctionDto.getMaterialBlottingTime_base()));
            }
            if (waterproofFunctionDto.getWaterproofRealizationCriteria_base() != -1) {
                waterproofRealizationCriteria_base.setText(String.valueOf(waterproofFunctionDto.getWaterproofRealizationCriteria_base()));
            }
            if (waterproofFunctionDto.getDynamicWaterproofCriteria_base() != -1) {
                dynamicWaterproofCriteria_base.setText(String.valueOf(waterproofFunctionDto.getDynamicWaterproofCriteria_base()));
            }

            //4 column
            if (waterproofFunctionDto.getMaterialBlottingPressure_weight() != -1) {
                materialBlottingPressure_weight.setText(String.valueOf(waterproofFunctionDto.getMaterialBlottingPressure_weight()));
            }
            if (waterproofFunctionDto.getWaterproof_weight() != -1) {
                waterproof_weight.setText(String.valueOf(waterproofFunctionDto.getWaterproof_weight()));
            }
            if (waterproofFunctionDto.getMaterialBlottingTime_weight() != -1) {
                materialBlottingTime_weight.setText(String.valueOf(waterproofFunctionDto.getMaterialBlottingTime_weight()));
            }
            if (waterproofFunctionDto.getWaterproofRealizationCriteria_weight() != -1) {
                waterproofRealizationCriteria_weight.setText(String.valueOf(waterproofFunctionDto.getWaterproofRealizationCriteria_weight()));
            }
            if (waterproofFunctionDto.getDynamicWaterproofCriteria_weight() != -1) {
                dynamicWaterproofCriteria_weight.setText(String.valueOf(waterproofFunctionDto.getDynamicWaterproofCriteria_weight()));
            }

            //equipment
            if (waterproofFunctionDto.getEquipment() != null) {
                equipment.setText(waterproofFunctionDto.getEquipment());
            }
        }
    }

    private void saveDataFromInputs() {
        //1 row
        if (!materialBlottingPressure_experimental_1.getText().isEmpty() && ValidationUtils.isValid(materialBlottingPressure_experimental_1.getText())) {
            allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingPressure_experimental_1(Double.parseDouble(materialBlottingPressure_experimental_1.getText()));
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingPressure_experimental_1(-1);
        }

        if (!materialBlottingPressure_calculated.getText().isEmpty() && ValidationUtils.isValid(materialBlottingPressure_calculated.getText())) {
            allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingPressure_calculated(Double.parseDouble(materialBlottingPressure_calculated.getText()));
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingPressure_calculated(-1);
        }

        if (!materialBlottingPressure_base.getText().isEmpty() && ValidationUtils.isValid(materialBlottingPressure_base.getText())) {
            allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingPressure_base(Double.parseDouble(materialBlottingPressure_base.getText()));
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingPressure_base(-1);
        }

        if (!materialBlottingPressure_weight.getText().isEmpty() && ValidationUtils.isValid(materialBlottingPressure_weight.getText())) {
            allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingPressure_weight(Double.parseDouble(materialBlottingPressure_weight.getText()));
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingPressure_weight(-1);
        }

        //2 row
        if (!waterproof_experimental_1.getText().isEmpty() && ValidationUtils.isValid(waterproof_experimental_1.getText())) {
            allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproof_experimental_1(Double.parseDouble(waterproof_experimental_1.getText()));
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproof_experimental_1(-1);
        }

        if (!waterproof_calculated.getText().isEmpty() && ValidationUtils.isValid(waterproof_calculated.getText())) {
            allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproof_calculated(Double.parseDouble(waterproof_calculated.getText()));
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproof_calculated(-1);
        }

        if (!waterproof_base.getText().isEmpty() && ValidationUtils.isValid(waterproof_base.getText())) {
            allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproof_base(Double.parseDouble(waterproof_base.getText()));
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproof_base(-1);
        }

        if (!waterproof_weight.getText().isEmpty() && ValidationUtils.isValid(waterproof_weight.getText())) {
            allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproof_weight(Double.parseDouble(waterproof_weight.getText()));
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproof_weight(-1);
        }

        //3 row
        if (!materialBlottingTime_experimental_1.getText().isEmpty() && ValidationUtils.isValid(materialBlottingTime_experimental_1.getText())) {
            allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingTime_experimental_1(Double.parseDouble(materialBlottingTime_experimental_1.getText()));
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingTime_experimental_1(-1);
        }

        if (!materialBlottingTime_calculated.getText().isEmpty() && ValidationUtils.isValid(materialBlottingTime_calculated.getText())) {
            allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingTime_calculated(Double.parseDouble(materialBlottingTime_calculated.getText()));
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingTime_calculated(-1);
        }

        if (!materialBlottingTime_base.getText().isEmpty() && ValidationUtils.isValid(materialBlottingTime_base.getText())) {
            allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingTime_base(Double.parseDouble(materialBlottingTime_base.getText()));
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingTime_base(-1);
        }

        if (!materialBlottingTime_weight.getText().isEmpty() && ValidationUtils.isValid(materialBlottingTime_weight.getText())) {
            allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingTime_weight(Double.parseDouble(materialBlottingTime_weight.getText()));
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingTime_weight(-1);
        }

        //4 row
        if (!waterproofRealizationCriteria_experimental_1.getText().isEmpty() && ValidationUtils.isValid(waterproofRealizationCriteria_experimental_1.getText())) {
            allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproofRealizationCriteria_experimental_1(Double.parseDouble(waterproofRealizationCriteria_experimental_1.getText()));
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproofRealizationCriteria_experimental_1(-1);
        }

        if (!waterproofRealizationCriteria_experimental_2.getText().isEmpty() && ValidationUtils.isValid(waterproofRealizationCriteria_experimental_2.getText())) {
            allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproofRealizationCriteria_experimental_2(Double.parseDouble(waterproofRealizationCriteria_experimental_2.getText()));
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproofRealizationCriteria_experimental_2(-1);
        }

        if (!waterproofRealizationCriteria_base.getText().isEmpty() && ValidationUtils.isValid(waterproofRealizationCriteria_base.getText())) {
            allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproofRealizationCriteria_base(Double.parseDouble(waterproofRealizationCriteria_base.getText()));
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproofRealizationCriteria_base(-1);
        }

        if (!waterproofRealizationCriteria_weight.getText().isEmpty() && ValidationUtils.isValid(waterproofRealizationCriteria_weight.getText())) {
            allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproofRealizationCriteria_weight(Double.parseDouble(waterproofRealizationCriteria_weight.getText()));
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproofRealizationCriteria_weight(-1);
        }

        //5 row
        if (!dynamicWaterproofCriteria_experimental_1.getText().isEmpty() && ValidationUtils.isValid(dynamicWaterproofCriteria_experimental_1.getText())) {
            allValues.getCreateMaterialDto().getWaterproofFunction().setDynamicWaterproofCriteria_experimental_1(Double.parseDouble(dynamicWaterproofCriteria_experimental_1.getText()));
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setDynamicWaterproofCriteria_experimental_1(-1);
        }

        if (!dynamicWaterproofCriteria_experimental_2.getText().isEmpty() && ValidationUtils.isValid(dynamicWaterproofCriteria_experimental_2.getText())) {
            allValues.getCreateMaterialDto().getWaterproofFunction().setDynamicWaterproofCriteria_experimental_2(Double.parseDouble(dynamicWaterproofCriteria_experimental_2.getText()));
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setDynamicWaterproofCriteria_experimental_2(-1);
        }

        if (!dynamicWaterproofCriteria_experimental_3.getText().isEmpty() && ValidationUtils.isValid(dynamicWaterproofCriteria_experimental_3.getText())) {
            allValues.getCreateMaterialDto().getWaterproofFunction().setDynamicWaterproofCriteria_experimental_3(Double.parseDouble(dynamicWaterproofCriteria_experimental_3.getText()));
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setDynamicWaterproofCriteria_experimental_3(-1);
        }

        if (!dynamicWaterproofCriteria_experimental_4.getText().isEmpty() && ValidationUtils.isValid(dynamicWaterproofCriteria_experimental_4.getText())) {
            allValues.getCreateMaterialDto().getWaterproofFunction().setDynamicWaterproofCriteria_experimental_4(Double.parseDouble(dynamicWaterproofCriteria_experimental_4.getText()));
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setDynamicWaterproofCriteria_experimental_4(-1);
        }

        if (!dynamicWaterproofCriteria_base.getText().isEmpty() && ValidationUtils.isValid(dynamicWaterproofCriteria_base.getText())) {
            allValues.getCreateMaterialDto().getWaterproofFunction().setDynamicWaterproofCriteria_base(Double.parseDouble(dynamicWaterproofCriteria_base.getText()));
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setDynamicWaterproofCriteria_base(-1);
        }

        if (!dynamicWaterproofCriteria_weight.getText().isEmpty() && ValidationUtils.isValid(dynamicWaterproofCriteria_weight.getText())) {
            allValues.getCreateMaterialDto().getWaterproofFunction().setDynamicWaterproofCriteria_weight(Double.parseDouble(dynamicWaterproofCriteria_weight.getText()));
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setDynamicWaterproofCriteria_weight(-1);
        }

        //conds
        if (!hydrostaticPressureIncreaseSpeed.getText().isEmpty() && ValidationUtils.isValid(hydrostaticPressureIncreaseSpeed.getText())) {
            allValues.getCreateMaterialDto().getWaterproofFunction().setHydrostaticPressureIncreaseSpeed(Double.parseDouble(hydrostaticPressureIncreaseSpeed.getText()));
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setHydrostaticPressureIncreaseSpeed(-1);
        }

        if (!hydrostaticPressure.getText().isEmpty() && ValidationUtils.isValid(hydrostaticPressure.getText())) {
            allValues.getCreateMaterialDto().getWaterproofFunction().setHydrostaticPressure(Double.parseDouble(hydrostaticPressure.getText()));
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setHydrostaticPressure(-1);
        }

        if (!waterproofTime.getText().isEmpty() && ValidationUtils.isValid(waterproofTime.getText())) {
            allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproofTime(Double.parseDouble(waterproofTime.getText()));
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproofTime(-1);
        }

        //equipment
        if (waterproofTime.getText() != null) {
            allValues.getCreateMaterialDto().getWaterproofFunction().setEquipment(equipment.getText());
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setEquipment(null);
        }
    }

    private boolean validateAndSetData() {
        CalculateWaterproofFunctionDto waterFuncDto = allValues.getCreateMaterialDto().getWaterproofFunction();

        try {
            //проверка 1 строки
            ArrayList<String> fStr = new ArrayList<>(List.of(
                    materialBlottingPressure_calculated.getText(),
                    materialBlottingPressure_base.getText(),
                    materialBlottingPressure_weight.getText()
            ));
            if (materialBlottingPressure_experimental_1.getText().isEmpty() && fStr.stream().allMatch(String::isEmpty)) {
                waterFuncDto.setMaterialBlottingPressure_experimental_1(-1);
                waterFuncDto.setMaterialBlottingPressure_calculated(-1);
                waterFuncDto.setMaterialBlottingPressure_base(-1);
                waterFuncDto.setMaterialBlottingPressure_weight(-1);
            } else {
                if ((fStr.stream().anyMatch(String::isEmpty) && !materialBlottingPressure_experimental_1.getText().isEmpty()) && (fStr.stream().anyMatch(el -> !el.isEmpty()) && materialBlottingPressure_experimental_1.getText().isEmpty())) {
                    throw new NoSuchElementException();
                } else if (fStr.stream().noneMatch(ValidationUtils::isValid) || !ValidationUtils.isValid(materialBlottingPressure_experimental_1.getText())) {
                    throw new IllegalArgumentException();
                } else {
                    waterFuncDto.setMaterialBlottingPressure_experimental_1(Double.parseDouble(materialBlottingPressure_experimental_1.getText()));
                    waterFuncDto.setMaterialBlottingPressure_calculated(Double.parseDouble(materialBlottingPressure_calculated.getText()));
                    waterFuncDto.setMaterialBlottingPressure_base(Double.parseDouble(materialBlottingPressure_base.getText()));
                    waterFuncDto.setMaterialBlottingPressure_weight(Double.parseDouble(materialBlottingPressure_weight.getText()));
                }
            }

            //проверка 2 строки
            ArrayList<String> sStr = new ArrayList<>(List.of(
                    waterproof_calculated.getText(),
                    waterproof_base.getText(),
                    waterproof_weight.getText()
            ));
            if (waterproof_experimental_1.getText().isEmpty() && sStr.stream().allMatch(String::isEmpty)) {
                waterFuncDto.setWaterproof_experimental_1(-1);
                waterFuncDto.setWaterproof_calculated(-1);
                waterFuncDto.setWaterproof_base(-1);
                waterFuncDto.setWaterproof_weight(-1);
            } else {
                if ((sStr.stream().anyMatch(String::isEmpty) && !waterproof_experimental_1.getText().isEmpty()) && (fStr.stream().anyMatch(el -> !el.isEmpty()) && waterproof_experimental_1.getText().isEmpty())) {
                    throw new NoSuchElementException();
                } else if (fStr.stream().noneMatch(ValidationUtils::isValid) || !ValidationUtils.isValid(waterproof_experimental_1.getText())) {
                    throw new IllegalArgumentException();
                } else {
                    waterFuncDto.setWaterproof_experimental_1(Double.parseDouble(waterproof_experimental_1.getText()));
                    waterFuncDto.setWaterproof_calculated(Double.parseDouble(waterproof_calculated.getText()));
                    waterFuncDto.setWaterproof_base(Double.parseDouble(waterproof_base.getText()));
                    waterFuncDto.setWaterproof_weight(Double.parseDouble(waterproof_weight.getText()));
                }
            }

            //проверка 3 строки
            ArrayList<String> tStr = new ArrayList<>(List.of(
                    materialBlottingTime_calculated.getText(),
                    materialBlottingTime_base.getText(),
                    materialBlottingTime_weight.getText()
            ));
            if (materialBlottingTime_experimental_1.getText().isEmpty() && tStr.stream().allMatch(String::isEmpty)) {
                waterFuncDto.setMaterialBlottingTime_experimental_1(-1);
                waterFuncDto.setMaterialBlottingTime_calculated(-1);
                waterFuncDto.setMaterialBlottingTime_base(-1);
                waterFuncDto.setMaterialBlottingTime_weight(-1);
            } else {
                if ((sStr.stream().anyMatch(String::isEmpty) && !materialBlottingTime_experimental_1.getText().isEmpty()) && (fStr.stream().anyMatch(el -> !el.isEmpty()) && materialBlottingTime_experimental_1.getText().isEmpty())) {
                    throw new NoSuchElementException();
                } else if (fStr.stream().noneMatch(ValidationUtils::isValid) || !ValidationUtils.isValid(materialBlottingTime_experimental_1.getText())) {
                    throw new IllegalArgumentException();
                } else {
                    waterFuncDto.setMaterialBlottingTime_experimental_1(Double.parseDouble(materialBlottingTime_experimental_1.getText()));
                    waterFuncDto.setMaterialBlottingTime_calculated(Double.parseDouble(materialBlottingTime_calculated.getText()));
                    waterFuncDto.setMaterialBlottingTime_base(Double.parseDouble(materialBlottingTime_base.getText()));
                    waterFuncDto.setMaterialBlottingTime_weight(Double.parseDouble(materialBlottingTime_weight.getText()));
                }
            }

            //проверка 4 строки
            ArrayList<String> fourthStr = new ArrayList<>(List.of(
                    waterproofRealizationCriteria_base.getText(),
                    waterproofRealizationCriteria_weight.getText()
            ));
            ArrayList<String> fourthExpStr = new ArrayList<>(List.of(
                    waterproofRealizationCriteria_experimental_1.getText(),
                    waterproofRealizationCriteria_experimental_2.getText()
            ));
            if (fourthExpStr.stream().allMatch(String::isEmpty) && fourthStr.stream().allMatch(String::isEmpty)) {
                waterFuncDto.setWaterproofRealizationCriteria_experimental_1(-1);
                waterFuncDto.setWaterproofRealizationCriteria_experimental_2(-1);
                waterFuncDto.setWaterproofRealizationCriteria_base(-1);
                waterFuncDto.setWaterproofRealizationCriteria_weight(-1);
            } else {
                if ((fourthStr.stream().noneMatch(String::isEmpty) && fourthExpStr.stream().anyMatch(el -> !el.isEmpty())) && (fourthStr.stream().anyMatch(el -> !el.isEmpty()) && fourthExpStr.stream().allMatch(String::isEmpty))) {
                    throw new NoSuchElementException();
                } else if (fourthStr.stream().noneMatch(ValidationUtils::isValid) || fourthExpStr.stream().noneMatch(ValidationUtils::isValid)) {
                    throw new IllegalArgumentException();
                } else {
                    waterFuncDto.setWaterproofRealizationCriteria_experimental_1(Double.parseDouble(waterproofRealizationCriteria_experimental_1.getText()));
                    waterFuncDto.setWaterproofRealizationCriteria_experimental_2(Double.parseDouble(waterproofRealizationCriteria_experimental_2.getText()));
                    waterFuncDto.setWaterproofRealizationCriteria_base(Double.parseDouble(waterproofRealizationCriteria_base.getText()));
                    waterFuncDto.setWaterproofRealizationCriteria_weight(Double.parseDouble(waterproofRealizationCriteria_weight.getText()));
                }
            }

            //проверка 5 строки
            ArrayList<String> fifthStr = new ArrayList<>(List.of(
                    dynamicWaterproofCriteria_base.getText(),
                    dynamicWaterproofCriteria_weight.getText()
            ));
            ArrayList<String> fifthExpStr = new ArrayList<>(List.of(
                    dynamicWaterproofCriteria_experimental_1.getText(),
                    dynamicWaterproofCriteria_experimental_2.getText(),
                    dynamicWaterproofCriteria_experimental_3.getText(),
                    dynamicWaterproofCriteria_experimental_4.getText()
            ));
            if (fifthExpStr.stream().allMatch(String::isEmpty) && fifthStr.stream().allMatch(String::isEmpty)) {
                waterFuncDto.setDynamicWaterproofCriteria_experimental_1(-1);
                waterFuncDto.setDynamicWaterproofCriteria_experimental_2(-1);
                waterFuncDto.setDynamicWaterproofCriteria_experimental_3(-1);
                waterFuncDto.setDynamicWaterproofCriteria_experimental_4(-1);
                waterFuncDto.setDynamicWaterproofCriteria_base(-1);
                waterFuncDto.setDynamicWaterproofCriteria_weight(-1);
            } else {
                if ((fifthStr.stream().noneMatch(String::isEmpty) && fifthExpStr.stream().anyMatch(el -> !el.isEmpty())) && (fifthStr.stream().anyMatch(el -> !el.isEmpty()) && fifthExpStr.stream().allMatch(String::isEmpty))) {
                    throw new NoSuchElementException();
                } else if (fifthStr.stream().noneMatch(ValidationUtils::isValid) || fifthExpStr.stream().noneMatch(ValidationUtils::isValid)) {
                    throw new IllegalArgumentException();
                } else {
                    waterFuncDto.setDynamicWaterproofCriteria_experimental_1(Double.parseDouble(dynamicWaterproofCriteria_experimental_1.getText()));
                    waterFuncDto.setDynamicWaterproofCriteria_experimental_2(Double.parseDouble(dynamicWaterproofCriteria_experimental_2.getText()));
                    waterFuncDto.setDynamicWaterproofCriteria_experimental_3(Double.parseDouble(dynamicWaterproofCriteria_experimental_3.getText()));
                    waterFuncDto.setDynamicWaterproofCriteria_experimental_4(Double.parseDouble(dynamicWaterproofCriteria_experimental_4.getText()));
                    waterFuncDto.setDynamicWaterproofCriteria_base(Double.parseDouble(dynamicWaterproofCriteria_base.getText()));
                    waterFuncDto.setDynamicWaterproofCriteria_weight(Double.parseDouble(dynamicWaterproofCriteria_weight.getText()));
                }
            }

            //проверка условий
            ArrayList<String> conds = new ArrayList<>(List.of(
                    hydrostaticPressureIncreaseSpeed.getText(),
                    hydrostaticPressure.getText(),
                    waterproofTime.getText()
            ));
            if (conds.stream().anyMatch(String::isEmpty)) {
                throw new NoSuchElementException();
            } else if (conds.stream().noneMatch(ValidationUtils::isValid)) {
                throw new IllegalArgumentException();
            } else {
                waterFuncDto.setHydrostaticPressureIncreaseSpeed(Double.parseDouble(hydrostaticPressureIncreaseSpeed.getText()));
                waterFuncDto.setHydrostaticPressure(Double.parseDouble(hydrostaticPressure.getText()));
                waterFuncDto.setWaterproofTime(Double.parseDouble(waterproofTime.getText()));
            }

            //проверка оборудования
            if(equipment.getText().isEmpty()) {
                throw new NoSuchElementException();
            } else {
                waterFuncDto.setEquipment(equipment.getText());
            }

            //проверка суммы весомостей
            ArrayList<Double> weights = new ArrayList<>(List.of(
                    waterFuncDto.getMaterialBlottingPressure_weight(),
                    waterFuncDto.getWaterproof_weight(),
                    waterFuncDto.getMaterialBlottingTime_weight(),
                    waterFuncDto.getWaterproofRealizationCriteria_weight(),
                    waterFuncDto.getDynamicWaterproofCriteria_weight()
            ));
            Optional<Double> sumWeights = weights.stream().filter(el -> el != -1).reduce(Double::sum);
            if(sumWeights.isPresent()) {
                if(sumWeights.get() != 1) {
                    throw new IllegalArgumentException();
                }
            }

        } catch (NoSuchElementException e) {
            AlertUtil.show("Вы не заполнили все поля", "Закройте это окно и дозаполните всё необходимые поля", allValues.getRootStage());
        } catch (IllegalArgumentException e) {
            AlertUtil.show("Вы ввели некорректные значения", "Закройте это окно и проверьте правильность введенных значений", allValues.getRootStage());
        }

        return false;
    }

    @FXML
    @SneakyThrows()
    void back_btn_clicked(MouseEvent event) {
        saveDataFromInputs();
        allValues.setLastCreateMaterialComponent(Component.CONDITION_1);
        ComponentUtil.mount(Component.CONDITION_1, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @FXML
    @SneakyThrows()
    void next_btn_clicked(MouseEvent event) {
        if (validateAndSetData()) {
            allValues.setLastCreateMaterialComponent(Component.HOMEOSTASIS_TABLE);
            ComponentUtil.mount(Component.HOMEOSTASIS_TABLE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //экспериментальные значения
        materialBlottingPressure_experimental_1.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        waterproof_experimental_1.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        materialBlottingTime_experimental_1.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        waterproofRealizationCriteria_experimental_1.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        waterproofRealizationCriteria_experimental_2.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        dynamicWaterproofCriteria_experimental_1.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        dynamicWaterproofCriteria_experimental_2.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        dynamicWaterproofCriteria_experimental_3.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        dynamicWaterproofCriteria_experimental_4.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        hydrostaticPressureIncreaseSpeed.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        hydrostaticPressure.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        waterproofTime.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));

        //расчетные значения
        materialBlottingPressure_calculated.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        waterproof_calculated.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        materialBlottingTime_calculated.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));

        //базовые значения
        materialBlottingPressure_base.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        waterproof_base.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        materialBlottingTime_base.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        waterproofRealizationCriteria_base.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        dynamicWaterproofCriteria_base.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));

        //значения весомости
        materialBlottingPressure_weight.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        waterproof_weight.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        materialBlottingTime_weight.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        waterproofRealizationCriteria_weight.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        dynamicWaterproofCriteria_weight.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
    }
}
