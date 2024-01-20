package com.example.project_for_university.controllers.material.tables;

import com.example.project_for_university.dto.forBackend.calculate.CalculateReliabilityFunctionDto;
import com.example.project_for_university.dto.forBackend.create.CreateMaterialDto;
import com.example.project_for_university.enums.Component;
import com.example.project_for_university.exception.CustomException;
import com.example.project_for_university.exception.ExceptionMessage;
import com.example.project_for_university.exception.ExceptionType;
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
import java.util.concurrent.ExecutionException;

public class ReliabilityTableController implements DataProvider, Initializable {
    private AllValues allValues;

    @FXML
    private TextArea equipment;

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

        if (allValues.getCreateMaterialDto().getReliabilityFunction() == null) {
            allValues.getCreateMaterialDto().setReliabilityFunction(new CalculateReliabilityFunctionDto());
        }

        fillReliabilityTable();

        for (int i = 0; i < allValues.getSideBarButtonsEventHandlers().size(); i++) {
            allValues.getSideBarButtons().get(i).removeEventHandler(MouseEvent.MOUSE_CLICKED, allValues.getSideBarButtonsEventHandlers().get(i));
        }
        allValues.getSideBarButtonsEventHandlers().clear();

        for (var button : allValues.getSideBarButtons()) {
            EventHandler<MouseEvent> clickHandler = event -> {
                System.out.println("reliability sideBartBtn");
                saveDataFromInputs();
            };
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, clickHandler);
            allValues.getSideBarButtonsEventHandlers().add(clickHandler);
        }
    }

    private void fillReliabilityTable() {
        CalculateReliabilityFunctionDto reliabilityFunctionDto = allValues.getCreateMaterialDto().getReliabilityFunction();

        if (reliabilityFunctionDto != null) {
            //col 1
            if (reliabilityFunctionDto.getRelativeBlottingPressureAfterLoad_experimental_1() != -1) {
                relativeBlottingPressureAfterLoad_experimental_1.setText(String.valueOf(reliabilityFunctionDto.getRelativeBlottingPressureAfterLoad_experimental_1()));
            }
            if (reliabilityFunctionDto.getRelativeWaterResistanceAfterLoad_experimental_1() != -1) {
                relativeWaterResistanceAfterLoad_experimental_1.setText(String.valueOf(reliabilityFunctionDto.getRelativeWaterResistanceAfterLoad_experimental_1()));
            }
            if (reliabilityFunctionDto.getRelativeBlottingTimeAfterLoad_experimental_1() != -1) {
                relativeBlottingTimeAfterLoad_experimental_1.setText(String.valueOf(reliabilityFunctionDto.getRelativeBlottingTimeAfterLoad_experimental_1()));
            }
            if (reliabilityFunctionDto.getWaterproofRealizationCriteriaAfterLoad_experimental_1() != -1) {
                waterproofRealizationCriteriaAfterLoad_experimental_1.setText(String.valueOf(reliabilityFunctionDto.getWaterproofRealizationCriteriaAfterLoad_experimental_1()));
            }
            if (reliabilityFunctionDto.getWaterproofRealizationCriteriaAfterLoad_experimental_2() != -1) {
                waterproofRealizationCriteriaAfterLoad_experimental_2.setText(String.valueOf(reliabilityFunctionDto.getWaterproofRealizationCriteriaAfterLoad_experimental_2()));
            }
            if (reliabilityFunctionDto.getMaxWaterResistanceLvl() != -1) {
                maxWaterResistanceLvl.setText(String.valueOf(reliabilityFunctionDto.getMaxWaterResistanceLvl()));
            }
            if (reliabilityFunctionDto.getImpactCyclesCnt() != -1) {
                impactCyclesCnt.setText(String.valueOf(reliabilityFunctionDto.getImpactCyclesCnt()));
            }

            //col 2
            if (reliabilityFunctionDto.getRelativeBlottingPressureAfterLoad_calculated() != -1) {
                relativeBlottingPressureAfterLoad_calculated.setText(String.valueOf(reliabilityFunctionDto.getRelativeBlottingPressureAfterLoad_calculated()));
            }
            if (reliabilityFunctionDto.getRelativeWaterResistanceAfterLoad_calculated() != -1) {
                relativeWaterResistanceAfterLoad_calculated.setText(String.valueOf(reliabilityFunctionDto.getRelativeWaterResistanceAfterLoad_calculated()));
            }
            if (reliabilityFunctionDto.getRelativeBlottingTimeAfterLoad_calculated() != -1) {
                relativeBlottingTimeAfterLoad_calculated.setText(String.valueOf(reliabilityFunctionDto.getRelativeBlottingTimeAfterLoad_calculated()));
            }

            //col 3
            if (reliabilityFunctionDto.getRelativeBlottingPressureAfterLoad_base() != -1) {
                relativeBlottingPressureAfterLoad_base.setText(String.valueOf(reliabilityFunctionDto.getRelativeBlottingPressureAfterLoad_base()));
            }
            if (reliabilityFunctionDto.getRelativeWaterResistanceAfterLoad_base() != -1) {
                relativeWaterResistanceAfterLoad_base.setText(String.valueOf(reliabilityFunctionDto.getRelativeWaterResistanceAfterLoad_base()));
            }
            if (reliabilityFunctionDto.getRelativeBlottingTimeAfterLoad_base() != -1) {
                relativeBlottingTimeAfterLoad_base.setText(String.valueOf(reliabilityFunctionDto.getRelativeBlottingTimeAfterLoad_base()));
            }
            if (reliabilityFunctionDto.getWaterproofFunctionResource_base() != -1) {
                waterproofFunctionResource_base.setText(String.valueOf(reliabilityFunctionDto.getWaterproofFunctionResource_base()));
            }

            //col 4
            if (reliabilityFunctionDto.getRelativeBlottingPressureAfterLoad_weight() != -1) {
                relativeBlottingPressureAfterLoad_weight.setText(String.valueOf(reliabilityFunctionDto.getRelativeBlottingPressureAfterLoad_weight()));
            }
            if (reliabilityFunctionDto.getRelativeWaterResistanceAfterLoad_weight() != -1) {
                relativeWaterResistanceAfterLoad_weight.setText(String.valueOf(reliabilityFunctionDto.getRelativeWaterResistanceAfterLoad_weight()));
            }
            if (reliabilityFunctionDto.getRelativeBlottingTimeAfterLoad_weight() != -1) {
                relativeBlottingTimeAfterLoad_weight.setText(String.valueOf(reliabilityFunctionDto.getRelativeBlottingTimeAfterLoad_weight()));
            }
            if (reliabilityFunctionDto.getWaterproofRealizationCriteriaAfterLoad_weight() != -1) {
                waterproofRealizationCriteriaAfterLoad_weight.setText(String.valueOf(reliabilityFunctionDto.getWaterproofRealizationCriteriaAfterLoad_weight()));
            }
            if (reliabilityFunctionDto.getWaterproofFunctionResource_weight() != -1) {
                waterproofFunctionResource_weight.setText(String.valueOf(reliabilityFunctionDto.getWaterproofFunctionResource_weight()));
            }

            //equipment
            if (reliabilityFunctionDto.getEquipment() != null) {
                equipment.setText(reliabilityFunctionDto.getEquipment());
            }
        }
    }

    private void saveDataFromInputs() {
        //1 row
        if (!relativeBlottingPressureAfterLoad_experimental_1.getText().isEmpty() && ValidationUtils.isValid(relativeBlottingPressureAfterLoad_experimental_1.getText())) {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeBlottingPressureAfterLoad_experimental_1(Double.parseDouble(relativeBlottingPressureAfterLoad_experimental_1.getText()));
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeBlottingPressureAfterLoad_experimental_1(-1);
        }

        if (!relativeBlottingPressureAfterLoad_calculated.getText().isEmpty() && ValidationUtils.isValid(relativeBlottingPressureAfterLoad_calculated.getText())) {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeBlottingPressureAfterLoad_calculated(Double.parseDouble(relativeBlottingPressureAfterLoad_calculated.getText()));
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeBlottingPressureAfterLoad_calculated(-1);
        }

        if (!relativeBlottingPressureAfterLoad_base.getText().isEmpty() && ValidationUtils.isValid(relativeBlottingPressureAfterLoad_base.getText())) {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeBlottingPressureAfterLoad_base(Double.parseDouble(relativeBlottingPressureAfterLoad_base.getText()));
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeBlottingPressureAfterLoad_base(-1);
        }

        if (!relativeBlottingPressureAfterLoad_weight.getText().isEmpty() && ValidationUtils.isValid(relativeBlottingPressureAfterLoad_weight.getText())) {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeBlottingPressureAfterLoad_weight(Double.parseDouble(relativeBlottingPressureAfterLoad_weight.getText()));
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeBlottingPressureAfterLoad_weight(-1);
        }

        //2 row
        if (!relativeWaterResistanceAfterLoad_experimental_1.getText().isEmpty() && ValidationUtils.isValid(relativeWaterResistanceAfterLoad_experimental_1.getText())) {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeWaterResistanceAfterLoad_experimental_1(Double.parseDouble(relativeWaterResistanceAfterLoad_experimental_1.getText()));
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeWaterResistanceAfterLoad_experimental_1(-1);
        }

        if (!relativeWaterResistanceAfterLoad_calculated.getText().isEmpty() && ValidationUtils.isValid(relativeWaterResistanceAfterLoad_calculated.getText())) {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeWaterResistanceAfterLoad_calculated(Double.parseDouble(relativeWaterResistanceAfterLoad_calculated.getText()));
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeWaterResistanceAfterLoad_calculated(-1);
        }

        if (!relativeWaterResistanceAfterLoad_base.getText().isEmpty() && ValidationUtils.isValid(relativeWaterResistanceAfterLoad_base.getText())) {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeWaterResistanceAfterLoad_base(Double.parseDouble(relativeWaterResistanceAfterLoad_base.getText()));
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeWaterResistanceAfterLoad_base(-1);
        }

        if (!relativeWaterResistanceAfterLoad_weight.getText().isEmpty() && ValidationUtils.isValid(relativeWaterResistanceAfterLoad_weight.getText())) {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeWaterResistanceAfterLoad_weight(Double.parseDouble(relativeWaterResistanceAfterLoad_weight.getText()));
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeWaterResistanceAfterLoad_weight(-1);
        }

        //3 row
        if (!relativeBlottingTimeAfterLoad_experimental_1.getText().isEmpty() && ValidationUtils.isValid(relativeBlottingTimeAfterLoad_experimental_1.getText())) {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeBlottingTimeAfterLoad_experimental_1(Double.parseDouble(relativeBlottingTimeAfterLoad_experimental_1.getText()));
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeBlottingTimeAfterLoad_experimental_1(-1);
        }

        if (!relativeBlottingTimeAfterLoad_calculated.getText().isEmpty() && ValidationUtils.isValid(relativeBlottingTimeAfterLoad_calculated.getText())) {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeBlottingTimeAfterLoad_calculated(Double.parseDouble(relativeBlottingTimeAfterLoad_calculated.getText()));
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeBlottingTimeAfterLoad_calculated(-1);
        }

        if (!relativeBlottingTimeAfterLoad_base.getText().isEmpty() && ValidationUtils.isValid(relativeBlottingTimeAfterLoad_base.getText())) {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeBlottingTimeAfterLoad_base(Double.parseDouble(relativeBlottingTimeAfterLoad_base.getText()));
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeBlottingTimeAfterLoad_base(-1);
        }

        if (!relativeBlottingTimeAfterLoad_weight.getText().isEmpty() && ValidationUtils.isValid(relativeBlottingTimeAfterLoad_weight.getText())) {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeBlottingTimeAfterLoad_weight(Double.parseDouble(relativeBlottingTimeAfterLoad_weight.getText()));
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeBlottingTimeAfterLoad_weight(-1);
        }

        //4 row
        if (!waterproofRealizationCriteriaAfterLoad_experimental_1.getText().isEmpty() && ValidationUtils.isValid(waterproofRealizationCriteriaAfterLoad_experimental_1.getText())) {
            allValues.getCreateMaterialDto().getReliabilityFunction().setWaterproofRealizationCriteriaAfterLoad_experimental_1(Double.parseDouble(waterproofRealizationCriteriaAfterLoad_experimental_1.getText()));
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setWaterproofRealizationCriteriaAfterLoad_experimental_1(-1);
        }

        if (!waterproofRealizationCriteriaAfterLoad_experimental_2.getText().isEmpty() && ValidationUtils.isValid(waterproofRealizationCriteriaAfterLoad_experimental_2.getText())) {
            allValues.getCreateMaterialDto().getReliabilityFunction().setWaterproofRealizationCriteriaAfterLoad_experimental_2(Double.parseDouble(waterproofRealizationCriteriaAfterLoad_experimental_2.getText()));
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setWaterproofRealizationCriteriaAfterLoad_experimental_2(-1);
        }

        if (!waterproofRealizationCriteriaAfterLoad_weight.getText().isEmpty() && ValidationUtils.isValid(waterproofRealizationCriteriaAfterLoad_weight.getText())) {
            allValues.getCreateMaterialDto().getReliabilityFunction().setWaterproofRealizationCriteriaAfterLoad_weight(Double.parseDouble(waterproofRealizationCriteriaAfterLoad_weight.getText()));
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setWaterproofRealizationCriteriaAfterLoad_weight(-1);
        }

        //5 row
        if (!waterproofFunctionResource_base.getText().isEmpty() && ValidationUtils.isValid(waterproofFunctionResource_base.getText())) {
            allValues.getCreateMaterialDto().getReliabilityFunction().setWaterproofFunctionResource_base(Double.parseDouble(waterproofFunctionResource_base.getText()));
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setWaterproofFunctionResource_base(-1);
        }

        if (!waterproofFunctionResource_weight.getText().isEmpty() && ValidationUtils.isValid(waterproofFunctionResource_weight.getText())) {
            allValues.getCreateMaterialDto().getReliabilityFunction().setWaterproofFunctionResource_weight(Double.parseDouble(waterproofFunctionResource_weight.getText()));
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setWaterproofFunctionResource_weight(-1);
        }

        //conds
        if (!maxWaterResistanceLvl.getText().isEmpty() && ValidationUtils.isValid(maxWaterResistanceLvl.getText())) {
            allValues.getCreateMaterialDto().getReliabilityFunction().setMaxWaterResistanceLvl(Double.parseDouble(maxWaterResistanceLvl.getText()));
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setMaxWaterResistanceLvl(-1);
        }

        if (!impactCyclesCnt.getText().isEmpty() && ValidationUtils.isValid(impactCyclesCnt.getText())) {
            allValues.getCreateMaterialDto().getReliabilityFunction().setImpactCyclesCnt(Double.parseDouble(impactCyclesCnt.getText()));
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setImpactCyclesCnt(-1);
        }

        //equipment
        if(equipment.getText() != null) {
            allValues.getCreateMaterialDto().getReliabilityFunction().setEquipment(equipment.getText());
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setEquipment(null);
        }
    }

    private boolean validateAndSetData() {
        CalculateReliabilityFunctionDto relFuncDto = allValues.getCreateMaterialDto().getReliabilityFunction();

        try {
            //проверка 1 строки
            ArrayList<String> fStr = new ArrayList<>(List.of(
                    relativeBlottingPressureAfterLoad_calculated.getText(),
                    relativeBlottingPressureAfterLoad_base.getText(),
                    relativeBlottingPressureAfterLoad_weight.getText()
            ));
            if (relativeBlottingPressureAfterLoad_experimental_1.getText().isEmpty() && fStr.stream().allMatch(String::isEmpty)) {
                relFuncDto.setRelativeBlottingPressureAfterLoad_experimental_1(-1);
                relFuncDto.setRelativeBlottingPressureAfterLoad_calculated(-1);
                relFuncDto.setRelativeBlottingPressureAfterLoad_base(-1);
                relFuncDto.setRelativeBlottingPressureAfterLoad_weight(-1);
            } else {
                if ((fStr.stream().anyMatch(String::isEmpty) && !relativeBlottingPressureAfterLoad_experimental_1.getText().isEmpty()) || (fStr.stream().anyMatch(el -> !el.isEmpty()) && relativeBlottingPressureAfterLoad_experimental_1.getText().isEmpty())) {
                    throw new CustomException(ExceptionMessage.INVALID_INPUT.getMessage() + "в строке - 1", ExceptionType.INVALID_INPUT_TYPE.getType());
                } else if (fStr.stream().anyMatch(el -> !ValidationUtils.isValid(el)) || !ValidationUtils.isValid(relativeBlottingPressureAfterLoad_experimental_1.getText())) {
                    throw new CustomException(ExceptionMessage.INVALID_VALUE.getMessage() + "в строке - 1", ExceptionType.INVALID_VALUE_TYPE.getType());
                } else {
                    relFuncDto.setRelativeBlottingPressureAfterLoad_experimental_1(Double.parseDouble(relativeBlottingPressureAfterLoad_experimental_1.getText()));
                    relFuncDto.setRelativeBlottingPressureAfterLoad_calculated(Double.parseDouble(relativeBlottingPressureAfterLoad_calculated.getText()));
                    relFuncDto.setRelativeBlottingPressureAfterLoad_base(Double.parseDouble(relativeBlottingPressureAfterLoad_base.getText()));
                    relFuncDto.setRelativeBlottingPressureAfterLoad_weight(Double.parseDouble(relativeBlottingPressureAfterLoad_weight.getText()));
                }
            }

            //проверка 2 строки
            ArrayList<String> sStr = new ArrayList<>(List.of(
                    relativeWaterResistanceAfterLoad_calculated.getText(),
                    relativeWaterResistanceAfterLoad_base.getText(),
                    relativeWaterResistanceAfterLoad_weight.getText()
            ));
            if (relativeWaterResistanceAfterLoad_experimental_1.getText().isEmpty() && sStr.stream().allMatch(String::isEmpty)) {
                relFuncDto.setRelativeWaterResistanceAfterLoad_experimental_1(-1);
                relFuncDto.setRelativeWaterResistanceAfterLoad_calculated(-1);
                relFuncDto.setRelativeWaterResistanceAfterLoad_base(-1);
                relFuncDto.setRelativeWaterResistanceAfterLoad_weight(-1);
            } else {
                if ((sStr.stream().anyMatch(String::isEmpty) && !relativeWaterResistanceAfterLoad_experimental_1.getText().isEmpty()) || (sStr.stream().anyMatch(el -> !el.isEmpty()) && relativeWaterResistanceAfterLoad_experimental_1.getText().isEmpty())) {
                    throw new CustomException(ExceptionMessage.INVALID_INPUT.getMessage() + "в строке - 2", ExceptionType.INVALID_INPUT_TYPE.getType());
                } else if (sStr.stream().anyMatch(el -> !ValidationUtils.isValid(el)) || !ValidationUtils.isValid(relativeWaterResistanceAfterLoad_experimental_1.getText())) {
                    throw new CustomException(ExceptionMessage.INVALID_VALUE.getMessage() + "в строке - 2", ExceptionType.INVALID_VALUE_TYPE.getType());
                } else {
                    relFuncDto.setRelativeWaterResistanceAfterLoad_experimental_1(Double.parseDouble(relativeWaterResistanceAfterLoad_experimental_1.getText()));
                    relFuncDto.setRelativeWaterResistanceAfterLoad_calculated(Double.parseDouble(relativeWaterResistanceAfterLoad_calculated.getText()));
                    relFuncDto.setRelativeWaterResistanceAfterLoad_base(Double.parseDouble(relativeWaterResistanceAfterLoad_base.getText()));
                    relFuncDto.setRelativeWaterResistanceAfterLoad_weight(Double.parseDouble(relativeWaterResistanceAfterLoad_weight.getText()));
                }
            }

            //проверка 3 строки
            ArrayList<String> tStr = new ArrayList<>(List.of(
                    relativeBlottingTimeAfterLoad_calculated.getText(),
                    relativeBlottingTimeAfterLoad_base.getText(),
                    relativeBlottingTimeAfterLoad_weight.getText()
            ));
            if (relativeBlottingTimeAfterLoad_experimental_1.getText().isEmpty() && tStr.stream().allMatch(String::isEmpty)) {
                relFuncDto.setRelativeBlottingTimeAfterLoad_experimental_1(-1);
                relFuncDto.setRelativeBlottingTimeAfterLoad_calculated(-1);
                relFuncDto.setRelativeBlottingTimeAfterLoad_base(-1);
                relFuncDto.setRelativeBlottingTimeAfterLoad_weight(-1);
            } else {
                if ((tStr.stream().anyMatch(String::isEmpty) && !relativeBlottingTimeAfterLoad_experimental_1.getText().isEmpty()) || (tStr.stream().anyMatch(el -> !el.isEmpty()) && relativeBlottingTimeAfterLoad_experimental_1.getText().isEmpty())) {
                    throw new CustomException(ExceptionMessage.INVALID_INPUT.getMessage() + "в строке - 3", ExceptionType.INVALID_INPUT_TYPE.getType());
                } else if (tStr.stream().anyMatch(el -> !ValidationUtils.isValid(el)) || !ValidationUtils.isValid(relativeBlottingTimeAfterLoad_experimental_1.getText())) {
                    throw new CustomException(ExceptionMessage.INVALID_VALUE.getMessage() + "в строке - 3", ExceptionType.INVALID_VALUE_TYPE.getType());
                } else {
                    relFuncDto.setRelativeBlottingTimeAfterLoad_experimental_1(Double.parseDouble(relativeBlottingTimeAfterLoad_experimental_1.getText()));
                    relFuncDto.setRelativeBlottingTimeAfterLoad_calculated(Double.parseDouble(relativeBlottingTimeAfterLoad_calculated.getText()));
                    relFuncDto.setRelativeBlottingTimeAfterLoad_base(Double.parseDouble(relativeBlottingTimeAfterLoad_base.getText()));
                    relFuncDto.setRelativeBlottingTimeAfterLoad_weight(Double.parseDouble(relativeBlottingTimeAfterLoad_weight.getText()));
                }
            }

            //проверка 4 строки
            ArrayList<String> fourthExpStr = new ArrayList<>(List.of(
                    waterproofRealizationCriteriaAfterLoad_experimental_1.getText(),
                    waterproofRealizationCriteriaAfterLoad_experimental_2.getText()
            ));
            if (fourthExpStr.stream().allMatch(String::isEmpty) && waterproofRealizationCriteriaAfterLoad_weight.getText().isEmpty()) {
                relFuncDto.setWaterproofRealizationCriteriaAfterLoad_experimental_1(-1);
                relFuncDto.setWaterproofRealizationCriteriaAfterLoad_experimental_2(-1);
                relFuncDto.setWaterproofRealizationCriteriaAfterLoad_weight(-1);
            } else {
                if ((waterproofRealizationCriteriaAfterLoad_weight.getText().isEmpty() && fourthExpStr.stream().anyMatch(el -> !el.isEmpty())) || (!waterproofRealizationCriteriaAfterLoad_weight.getText().isEmpty() && fourthExpStr.stream().anyMatch(String::isEmpty))) {
                    throw new CustomException(ExceptionMessage.INVALID_INPUT.getMessage() + "в строке - 4", ExceptionType.INVALID_INPUT_TYPE.getType());
                } else if (!ValidationUtils.isValid(waterproofRealizationCriteriaAfterLoad_weight.getText()) || fourthExpStr.stream().anyMatch(el -> !ValidationUtils.isValid(el))) {
                    throw new CustomException(ExceptionMessage.INVALID_VALUE.getMessage() + "в строке - 4", ExceptionType.INVALID_VALUE_TYPE.getType());
                } else {
                    relFuncDto.setWaterproofRealizationCriteriaAfterLoad_experimental_1(Double.parseDouble(waterproofRealizationCriteriaAfterLoad_experimental_1.getText()));
                    relFuncDto.setWaterproofRealizationCriteriaAfterLoad_experimental_2(Double.parseDouble(waterproofRealizationCriteriaAfterLoad_experimental_2.getText()));
                    relFuncDto.setWaterproofRealizationCriteriaAfterLoad_weight(Double.parseDouble(waterproofRealizationCriteriaAfterLoad_weight.getText()));
                }
            }

            //проверка 5 строки
            ArrayList<String> fifthStr = new ArrayList<>(List.of(
                    waterproofFunctionResource_base.getText(),
                    waterproofFunctionResource_weight.getText()
            ));
            if (fifthStr.stream().allMatch(String::isEmpty)) {
                relFuncDto.setWaterproofFunctionResource_base(-1);
                relFuncDto.setWaterproofFunctionResource_weight(-1);
            } else {
                if (fifthStr.stream().anyMatch(String::isEmpty)) {
                    throw new CustomException(ExceptionMessage.INVALID_INPUT.getMessage() + "в строке - 5", ExceptionType.INVALID_INPUT_TYPE.getType());
                } else if (fifthStr.stream().anyMatch(el -> !ValidationUtils.isValid(el))) {
                    throw new CustomException(ExceptionMessage.INVALID_VALUE.getMessage() + "в строке -5", ExceptionType.INVALID_VALUE_TYPE.getType());
                } else {
                    relFuncDto.setWaterproofFunctionResource_base(Double.parseDouble(waterproofFunctionResource_base.getText()));
                    relFuncDto.setWaterproofFunctionResource_weight(Double.parseDouble(waterproofFunctionResource_weight.getText()));
                }
            }

            //проверка условий
            ArrayList<String> conds = new ArrayList<>(List.of(
                    maxWaterResistanceLvl.getText(),
                    impactCyclesCnt.getText()
            ));
            if (conds.stream().anyMatch(String::isEmpty)) {
                throw new CustomException(ExceptionMessage.INVALID_CONDITIONS.getMessage(), ExceptionType.INVALID_CONDITIONS_TYPE.getType());
            } else if (conds.stream().anyMatch(el -> !ValidationUtils.isValid(el))) {
                throw new CustomException(ExceptionMessage.INVALID_CONDITIONS_VALUE.getMessage(), ExceptionType.INVALID_CONDITIONS_VALUE_TYPE.getType());
            } else {
                relFuncDto.setMaxWaterResistanceLvl(Double.parseDouble(maxWaterResistanceLvl.getText()));
                relFuncDto.setImpactCyclesCnt(Double.parseDouble(impactCyclesCnt.getText()));
            }

            //проверка оборудования
            if(equipment.getText().isEmpty()) {
                throw new CustomException(ExceptionMessage.INVALID_EQUIPMENT.getMessage(), ExceptionType.INVALID_EQUIPMENT_TYPE.getType());
            } else {
                relFuncDto.setEquipment(equipment.getText());
            }

            //проверка суммы весомостей
            ArrayList<Double> weights = new ArrayList<>(List.of(
                    relFuncDto.getRelativeBlottingPressureAfterLoad_weight(),
                    relFuncDto.getRelativeWaterResistanceAfterLoad_weight(),
                    relFuncDto.getRelativeBlottingTimeAfterLoad_weight(),
                    relFuncDto.getWaterproofRealizationCriteriaAfterLoad_weight(),
                    relFuncDto.getWaterproofFunctionResource_weight()
            ));
            Optional<Double> sumWeights = weights.stream().filter(el -> el != -1).reduce(Double::sum);
            if(sumWeights.isPresent()) {
                if(sumWeights.get() != 1) {
                    throw new CustomException(ExceptionMessage.INVALID_WEIGHTS_SUM.getMessage(), ExceptionType.INVALID_WEIGHTS_SUM_TYPE.getType());
                }
            }

            allValues.getCreateMaterialDto().setReliabilityFunction(relFuncDto);

            return true;
        } catch (CustomException e) {
            AlertUtil.show(e.getType(), e.getMessage(), allValues.getRootStage());
        }

        return false;
    }

    @FXML
    void btn_clearFields_clicked(MouseEvent event) {
        boolean isTrue = AlertUtil.showConfirmation("Очистить все поля на этой стадии?", "Все поля в этой стадии создания будут полностью очищены", allValues.getRootStage());
        if (isTrue) {
            allValues.getCreateMaterialDto().setReliabilityFunction(null);
        }
    }

    @FXML
    void btn_reset_clicked(MouseEvent event) throws IOException, ExecutionException, InterruptedException {
        boolean isTrue = AlertUtil.showConfirmation("Полностью сбросить прогресс?", "Весть прогресс создания будет полностью очищен, создание начнется сначала", allValues.getRootStage());
        if (isTrue) {
            allValues.setCreateMaterialDto(new CreateMaterialDto());
            allValues.setLastCreateMaterialComponent(null);
            ComponentUtil.mount(Component.CONDITION_1, allValues.getContentPanes().getLoggedInStackPane(), allValues);
        }
    }

    @FXML
    @SneakyThrows()
    void back_btn_clicked(MouseEvent event) {
        saveDataFromInputs();
        allValues.setLastCreateMaterialComponent(Component.HOMEOSTASIS_TABLE);
        ComponentUtil.mount(Component.HOMEOSTASIS_TABLE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @FXML
    @SneakyThrows()
    void next_btn_clicked(MouseEvent event) {
        if(validateAndSetData()) {
            allValues.setLastCreateMaterialComponent(Component.ESTIMATION_TABLE);
            ComponentUtil.mount(Component.ESTIMATION_TABLE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        экспериментальные значения
        relativeBlottingPressureAfterLoad_experimental_1.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        relativeWaterResistanceAfterLoad_experimental_1.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        relativeBlottingTimeAfterLoad_experimental_1.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        waterproofRealizationCriteriaAfterLoad_experimental_1.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        waterproofRealizationCriteriaAfterLoad_experimental_2.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        maxWaterResistanceLvl.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        impactCyclesCnt.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));

        //расчетные значения
        relativeBlottingPressureAfterLoad_calculated.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        relativeWaterResistanceAfterLoad_calculated.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        relativeBlottingTimeAfterLoad_calculated.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));

        //базовые значения
        relativeBlottingPressureAfterLoad_base.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        relativeWaterResistanceAfterLoad_base.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        relativeBlottingTimeAfterLoad_base.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        waterproofFunctionResource_base.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));

        //значения весомости
        relativeBlottingPressureAfterLoad_weight.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        relativeWaterResistanceAfterLoad_weight.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        relativeBlottingTimeAfterLoad_weight.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        waterproofRealizationCriteriaAfterLoad_weight.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        waterproofFunctionResource_weight.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
    }
}
