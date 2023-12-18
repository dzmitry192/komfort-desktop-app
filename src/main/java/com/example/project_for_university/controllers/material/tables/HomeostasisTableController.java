package com.example.project_for_university.controllers.material.tables;

import com.example.project_for_university.dto.forBackend.calculate.CalculateHomeostasisFunctionDto;
import com.example.project_for_university.dto.forBackend.create.CreateMaterialDto;
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
import java.util.concurrent.ExecutionException;

public class HomeostasisTableController implements DataProvider, Initializable {
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

        if (allValues.getCreateMaterialDto().getHomeostasisFunction() == null) {
            allValues.getCreateMaterialDto().setHomeostasisFunction(new CalculateHomeostasisFunctionDto());
        }

        fillHomeostasisTable();

        for (int i = 0; i < allValues.getSideBarButtonsEventHandlers().size(); i++) {
            allValues.getSideBarButtons().get(i).removeEventHandler(MouseEvent.MOUSE_CLICKED, allValues.getSideBarButtonsEventHandlers().get(i));
        }
        allValues.getSideBarButtonsEventHandlers().clear();

        for (var button : allValues.getSideBarButtons()) {
            EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("homeostasis sideBartBtn");
                    saveDataFromInputs();
                }
            };
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, clickHandler);
            allValues.getSideBarButtonsEventHandlers().add(clickHandler);
        }
    }

    private void fillHomeostasisTable() {
        CalculateHomeostasisFunctionDto homeostasisFunctionDto = allValues.getCreateMaterialDto().getHomeostasisFunction();

        if (homeostasisFunctionDto != null) {
            //col 1
            if (homeostasisFunctionDto.getM1s() != -1) {
                m1s.setText(String.valueOf(homeostasisFunctionDto.getM1s()));
            }
            if (homeostasisFunctionDto.getM2s() != -1) {
                m2s.setText(String.valueOf(homeostasisFunctionDto.getM2s()));
            }
            if (homeostasisFunctionDto.getS0_1() != -1) {
                s0_1.setText(String.valueOf(homeostasisFunctionDto.getS0_1()));
            }
            if (homeostasisFunctionDto.getT_1() != -1) {
                t_1.setText(String.valueOf(homeostasisFunctionDto.getT_1()));
            }
            if (homeostasisFunctionDto.getM1min() != -1) {
                m1min.setText(String.valueOf(homeostasisFunctionDto.getM1min()));
            }
            if (homeostasisFunctionDto.getM2min() != -1) {
                m2min.setText(String.valueOf(homeostasisFunctionDto.getM2min()));
            }
            if (homeostasisFunctionDto.getM1max() != -1) {
                m1max.setText(String.valueOf(homeostasisFunctionDto.getM1max()));
            }
            if (homeostasisFunctionDto.getM2max() != -1) {
                m2max.setText(String.valueOf(homeostasisFunctionDto.getM2max()));
            }
            if (homeostasisFunctionDto.getS0_2() != -1) {
                s0_2.setText(String.valueOf(homeostasisFunctionDto.getS0_2()));
            }
            if (homeostasisFunctionDto.getT_2() != -1) {
                t_2.setText(String.valueOf(homeostasisFunctionDto.getT_2()));
            }
            if (homeostasisFunctionDto.getTos() != -1) {
                tos.setText(String.valueOf(homeostasisFunctionDto.getTos()));
            }
            if (homeostasisFunctionDto.getS() != -1) {
                s.setText(String.valueOf(homeostasisFunctionDto.getS()));
            }
            if (homeostasisFunctionDto.getM() != -1) {
                m.setText(String.valueOf(homeostasisFunctionDto.getM()));
            }
            if (homeostasisFunctionDto.getComfTempInsideClothes() != -1) {
                comfTempInsideClothes.setText(String.valueOf(homeostasisFunctionDto.getComfTempInsideClothes()));
            }
            if (homeostasisFunctionDto.getComfHumidityInsideClothes() != -1) {
                comfHumidityInsideClothes.setText(String.valueOf(homeostasisFunctionDto.getComfHumidityInsideClothes()));
            }
            if (homeostasisFunctionDto.getMinOutdoorHumidity() != -1) {
                minOutdoorHumidity.setText(String.valueOf(homeostasisFunctionDto.getMinOutdoorHumidity()));
            }
            if (homeostasisFunctionDto.getMinOutdoorTemp() != -1) {
                minOutdoorTemp.setText(String.valueOf(homeostasisFunctionDto.getMinOutdoorTemp()));
            }
            if (homeostasisFunctionDto.getMaxOutdoorTemp() != -1) {
                maxOutdoorTemp.setText(String.valueOf(homeostasisFunctionDto.getMaxOutdoorTemp()));
            }
            if (homeostasisFunctionDto.getMaxOutdoorHumidity() != -1) {
                maxOutdoorHumidity.setText(String.valueOf(homeostasisFunctionDto.getMaxOutdoorHumidity()));
            }
            if (homeostasisFunctionDto.getAvgOutdoorAirSpeed() != -1) {
                avgOutdoorAirSpeed.setText(String.valueOf(homeostasisFunctionDto.getAvgOutdoorAirSpeed()));
            }
            if (homeostasisFunctionDto.getSampleSurfaceArea() != -1) {
                sampleSurfaceArea.setText(String.valueOf(homeostasisFunctionDto.getSampleSurfaceArea()));
            }

            //col 2
            if (homeostasisFunctionDto.getWaterPermeability_base() != -1) {
                waterPermeability_base.setText(String.valueOf(homeostasisFunctionDto.getWaterPermeability_base()));
            }
            if (homeostasisFunctionDto.getWaterPermeabilityDynamicCriteria_base() != -1) {
                waterPermeabilityDynamicCriteria_base.setText(String.valueOf(homeostasisFunctionDto.getWaterPermeabilityDynamicCriteria_base()));
            }
            if (homeostasisFunctionDto.getTotalThermalResistance_base() != -1) {
                totalThermalResistance_base.setText(String.valueOf(homeostasisFunctionDto.getTotalThermalResistance_base()));
            }

            //col 3
            if (homeostasisFunctionDto.getWaterPermeability_weight() != -1) {
                waterPermeability_weight.setText(String.valueOf(homeostasisFunctionDto.getWaterPermeability_weight()));
            }
            if (homeostasisFunctionDto.getWaterPermeabilityDynamicCriteria_weight() != -1) {
                waterPermeabilityDynamicCriteria_weight.setText(String.valueOf(homeostasisFunctionDto.getWaterPermeabilityDynamicCriteria_weight()));
            }
            if (homeostasisFunctionDto.getTotalThermalResistance_weight() != -1) {
                totalThermalResistance_weight.setText(String.valueOf(homeostasisFunctionDto.getTotalThermalResistance_weight()));
            }

            //equipment
            if (homeostasisFunctionDto.getEquipment() != null) {
                equipment.setText(homeostasisFunctionDto.getEquipment());
            }
        }
    }

    private void saveDataFromInputs() {
        //1 row
        if (!m1s.getText().isEmpty() && ValidationUtils.isValid(m1s.getText())) {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setM1s(Double.parseDouble(m1s.getText()));
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setM1s(-1);
        }

        if (!m2s.getText().isEmpty() && ValidationUtils.isValid(m2s.getText())) {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setM2s(Double.parseDouble(m2s.getText()));
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setM2s(-1);
        }

        if (!s0_1.getText().isEmpty() && ValidationUtils.isValid(s0_1.getText())) {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setS0_1(Double.parseDouble(s0_1.getText()));
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setS0_1(-1);
        }

        if (!t_1.getText().isEmpty() && ValidationUtils.isValid(t_1.getText())) {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setT_1(Double.parseDouble(t_1.getText()));
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setT_1(-1);
        }

        if (!waterPermeability_base.getText().isEmpty() && ValidationUtils.isValid(waterPermeability_base.getText())) {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setWaterPermeability_base(Double.parseDouble(waterPermeability_base.getText()));
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setWaterPermeability_base(-1);
        }

        if (!waterPermeability_weight.getText().isEmpty() && ValidationUtils.isValid(waterPermeability_weight.getText())) {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setWaterPermeability_weight(Double.parseDouble(waterPermeability_weight.getText()));
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setWaterPermeability_weight(-1);
        }

        //2 row
        if (!m1min.getText().isEmpty() && ValidationUtils.isValid(m1min.getText())) {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setM1min(Double.parseDouble(m1min.getText()));
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setM1min(-1);
        }

        if (!m2min.getText().isEmpty() && ValidationUtils.isValid(m2min.getText())) {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setM2min(Double.parseDouble(m2min.getText()));
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setM2min(-1);
        }

        if (!m1max.getText().isEmpty() && ValidationUtils.isValid(m1max.getText())) {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setM1max(Double.parseDouble(m1max.getText()));
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setM1max(-1);
        }

        if (!m2max.getText().isEmpty() && ValidationUtils.isValid(m2max.getText())) {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setM2max(Double.parseDouble(m2max.getText()));
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setM2max(-1);
        }

        if (!s0_2.getText().isEmpty() && ValidationUtils.isValid(s0_2.getText())) {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setS0_2(Double.parseDouble(s0_2.getText()));
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setS0_2(-1);
        }

        if (!t_2.getText().isEmpty() && ValidationUtils.isValid(t_2.getText())) {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setT_2(Double.parseDouble(t_2.getText()));
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setT_2(-1);
        }

        if (!waterPermeabilityDynamicCriteria_base.getText().isEmpty() && ValidationUtils.isValid(waterPermeabilityDynamicCriteria_base.getText())) {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setWaterPermeabilityDynamicCriteria_base(Double.parseDouble(waterPermeabilityDynamicCriteria_base.getText()));
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setWaterPermeabilityDynamicCriteria_base(-1);
        }

        if (!waterPermeabilityDynamicCriteria_weight.getText().isEmpty() && ValidationUtils.isValid(waterPermeabilityDynamicCriteria_weight.getText())) {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setWaterPermeabilityDynamicCriteria_weight(Double.parseDouble(waterPermeabilityDynamicCriteria_weight.getText()));
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setWaterPermeabilityDynamicCriteria_weight(-1);
        }

        //3 row
        if (!tos.getText().isEmpty() && ValidationUtils.isValid(tos.getText())) {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setTos(Double.parseDouble(tos.getText()));
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setTos(-1);
        }

        if (!s.getText().isEmpty() && ValidationUtils.isValid(s.getText())) {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setS(Double.parseDouble(s.getText()));
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setS(-1);
        }

        if (!m.getText().isEmpty() && ValidationUtils.isValid(m.getText())) {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setM(Double.parseDouble(m.getText()));
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setM(-1);
        }

        if (!totalThermalResistance_base.getText().isEmpty() && ValidationUtils.isValid(totalThermalResistance_base.getText())) {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setTotalThermalResistance_base(Double.parseDouble(totalThermalResistance_base.getText()));
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setTotalThermalResistance_base(-1);
        }

        if (!totalThermalResistance_weight.getText().isEmpty() && ValidationUtils.isValid(totalThermalResistance_weight.getText())) {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setTotalThermalResistance_weight(Double.parseDouble(totalThermalResistance_weight.getText()));
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setTotalThermalResistance_weight(-1);
        }

        //conds
        if (!comfTempInsideClothes.getText().isEmpty() && ValidationUtils.isValid(comfTempInsideClothes.getText())) {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setComfTempInsideClothes(Double.parseDouble(comfTempInsideClothes.getText()));
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setComfTempInsideClothes(-1);
        }

        if (!comfHumidityInsideClothes.getText().isEmpty() && ValidationUtils.isValid(comfHumidityInsideClothes.getText())) {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setComfHumidityInsideClothes(Double.parseDouble(comfHumidityInsideClothes.getText()));
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setComfHumidityInsideClothes(-1);
        }

        if (!minOutdoorHumidity.getText().isEmpty() && ValidationUtils.isValid(minOutdoorHumidity.getText())) {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setMinOutdoorHumidity(Double.parseDouble(minOutdoorHumidity.getText()));
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setMinOutdoorHumidity(-1);
        }

        if (!minOutdoorTemp.getText().isEmpty() && ValidationUtils.isValid(minOutdoorTemp.getText())) {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setMinOutdoorTemp(Double.parseDouble(minOutdoorTemp.getText()));
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setMinOutdoorTemp(-1);
        }

        if (!maxOutdoorTemp.getText().isEmpty() && ValidationUtils.isValid(maxOutdoorTemp.getText())) {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setMaxOutdoorTemp(Double.parseDouble(maxOutdoorTemp.getText()));
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setMaxOutdoorTemp(-1);
        }

        if (!maxOutdoorHumidity.getText().isEmpty() && ValidationUtils.isValid(maxOutdoorHumidity.getText())) {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setMaxOutdoorHumidity(Double.parseDouble(maxOutdoorHumidity.getText()));
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setMaxOutdoorHumidity(-1);
        }

        if (!avgOutdoorAirSpeed.getText().isEmpty() && ValidationUtils.isValid(avgOutdoorAirSpeed.getText())) {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setAvgOutdoorAirSpeed(Double.parseDouble(avgOutdoorAirSpeed.getText()));
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setAvgOutdoorAirSpeed(-1);
        }

        if (!sampleSurfaceArea.getText().isEmpty() && ValidationUtils.isValid(sampleSurfaceArea.getText())) {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setSampleSurfaceArea(Double.parseDouble(sampleSurfaceArea.getText()));
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setSampleSurfaceArea(-1);
        }

        //equipment
        if (equipment.getText() != null) {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setEquipment(equipment.getText());
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setEquipment(null);
        }
    }

    private boolean validateAndSetData() {
        CalculateHomeostasisFunctionDto homeoFuncDto = allValues.getCreateMaterialDto().getHomeostasisFunction();

        try {
            //1 row
            ArrayList<String> firstStr = new ArrayList<>(List.of(
                    waterPermeability_base.getText(),
                    waterPermeability_weight.getText()
            ));
            ArrayList<String> firstExpStr = new ArrayList<>(List.of(
                    m1s.getText(),
                    m2s.getText(),
                    s0_1.getText(),
                    t_1.getText()
            ));
            if (firstExpStr.stream().allMatch(String::isEmpty) && firstStr.stream().allMatch(String::isEmpty)) {
                homeoFuncDto.setM1s(-1);
                homeoFuncDto.setM2s(-1);
                homeoFuncDto.setS0_1(-1);
                homeoFuncDto.setT_1(-1);
                homeoFuncDto.setWaterPermeability_base(-1);
                homeoFuncDto.setWaterPermeability_weight(-1);
            } else {
                if ((firstStr.stream().noneMatch(String::isEmpty) && firstExpStr.stream().anyMatch(el -> !el.isEmpty())) && (firstStr.stream().anyMatch(el -> !el.isEmpty()) && firstExpStr.stream().allMatch(String::isEmpty))) {
                    throw new NoSuchElementException();
                } else if (firstStr.stream().noneMatch(ValidationUtils::isValid) || firstExpStr.stream().noneMatch(ValidationUtils::isValid)) {
                    throw new IllegalArgumentException();
                } else {
                    homeoFuncDto.setM1s(Double.parseDouble(m1s.getText()));
                    homeoFuncDto.setM2s(Double.parseDouble(m2s.getText()));
                    homeoFuncDto.setS0_1(Double.parseDouble(s0_1.getText()));
                    homeoFuncDto.setT_1(Double.parseDouble(t_1.getText()));
                    homeoFuncDto.setWaterPermeability_base(Double.parseDouble(waterPermeability_base.getText()));
                    homeoFuncDto.setWaterPermeability_weight(Double.parseDouble(waterPermeability_weight.getText()));
                }
            }

            //2 row
            ArrayList<String> secondStr = new ArrayList<>(List.of(
                    waterPermeabilityDynamicCriteria_base.getText(),
                    waterPermeabilityDynamicCriteria_weight.getText()
            ));
            ArrayList<String> secondExpStr = new ArrayList<>(List.of(
                    m1min.getText(),
                    m2min.getText(),
                    m1max.getText(),
                    m2max.getText(),
                    s0_2.getText(),
                    t_2.getText()
            ));
            if (secondExpStr.stream().allMatch(String::isEmpty) && secondStr.stream().allMatch(String::isEmpty)) {
                homeoFuncDto.setM1min(-1);
                homeoFuncDto.setM2min(-1);
                homeoFuncDto.setM1max(-1);
                homeoFuncDto.setM2max(-1);
                homeoFuncDto.setS0_2(-1);
                homeoFuncDto.setT_2(-1);
                homeoFuncDto.setWaterPermeabilityDynamicCriteria_base(-1);
                homeoFuncDto.setWaterPermeabilityDynamicCriteria_weight(-1);
            } else {
                if ((secondStr.stream().noneMatch(String::isEmpty) && secondExpStr.stream().anyMatch(el -> !el.isEmpty())) && (secondStr.stream().anyMatch(el -> !el.isEmpty()) && secondExpStr.stream().allMatch(String::isEmpty))) {
                    throw new NoSuchElementException();
                } else if (secondStr.stream().noneMatch(ValidationUtils::isValid) || secondExpStr.stream().noneMatch(ValidationUtils::isValid)) {
                    throw new IllegalArgumentException();
                } else {
                    homeoFuncDto.setM1min(Double.parseDouble(m1min.getText()));
                    homeoFuncDto.setM2min(Double.parseDouble(m2min.getText()));
                    homeoFuncDto.setM1max(Double.parseDouble(m1max.getText()));
                    homeoFuncDto.setM2max(Double.parseDouble(m2max.getText()));
                    homeoFuncDto.setS0_2(Double.parseDouble(s0_2.getText()));
                    homeoFuncDto.setT_2(Double.parseDouble(t_2.getText()));
                    homeoFuncDto.setWaterPermeabilityDynamicCriteria_base(Double.parseDouble(waterPermeabilityDynamicCriteria_base.getText()));
                    homeoFuncDto.setWaterPermeabilityDynamicCriteria_weight(Double.parseDouble(waterPermeabilityDynamicCriteria_weight.getText()));
                }
            }

            //3 row
            ArrayList<String> thirdStr = new ArrayList<>(List.of(
                    totalThermalResistance_base.getText(),
                    totalThermalResistance_weight.getText()
            ));
            ArrayList<String> thirdExpStr = new ArrayList<>(List.of(
                    tos.getText(),
                    s.getText(),
                    m.getText()
            ));
            if (thirdExpStr.stream().allMatch(String::isEmpty) && thirdStr.stream().allMatch(String::isEmpty)) {
                homeoFuncDto.setTos(-1);
                homeoFuncDto.setS(-1);
                homeoFuncDto.setM(-1);
                homeoFuncDto.setTotalThermalResistance_base(-1);
                homeoFuncDto.setTotalThermalResistance_weight(-1);
            } else {
                if ((thirdStr.stream().noneMatch(String::isEmpty) && thirdExpStr.stream().anyMatch(el -> !el.isEmpty())) && (thirdStr.stream().anyMatch(el -> !el.isEmpty()) && thirdExpStr.stream().allMatch(String::isEmpty))) {
                    throw new NoSuchElementException();
                } else if (thirdStr.stream().noneMatch(ValidationUtils::isValid) || thirdExpStr.stream().noneMatch(ValidationUtils::isValid)) {
                    throw new IllegalArgumentException();
                } else {
                    homeoFuncDto.setTos(Double.parseDouble(tos.getText()));
                    homeoFuncDto.setS(Double.parseDouble(s.getText()));
                    homeoFuncDto.setM(Double.parseDouble(m.getText()));
                    homeoFuncDto.setTotalThermalResistance_base(Double.parseDouble(totalThermalResistance_base.getText()));
                    homeoFuncDto.setTotalThermalResistance_weight(Double.parseDouble(totalThermalResistance_weight.getText()));
                }
            }

            //conds
            ArrayList<String> conds = new ArrayList<>(List.of(
                    comfTempInsideClothes.getText(),
                    comfHumidityInsideClothes.getText(),
                    minOutdoorHumidity.getText(),
                    minOutdoorTemp.getText(),
                    maxOutdoorTemp.getText(),
                    maxOutdoorHumidity.getText(),
                    avgOutdoorAirSpeed.getText(),
                    sampleSurfaceArea.getText()
            ));
            if (conds.stream().noneMatch(String::isEmpty)) {
                throw new NoSuchElementException();
            } else if (conds.stream().noneMatch(ValidationUtils::isValid)) {
                throw new IllegalArgumentException();
            } else {
                homeoFuncDto.setComfTempInsideClothes(Double.parseDouble(comfTempInsideClothes.getText()));
                homeoFuncDto.setComfHumidityInsideClothes(Double.parseDouble(comfHumidityInsideClothes.getText()));
                homeoFuncDto.setMinOutdoorHumidity(Double.parseDouble(minOutdoorHumidity.getText()));
                homeoFuncDto.setMinOutdoorTemp(Double.parseDouble(minOutdoorTemp.getText()));
                homeoFuncDto.setMaxOutdoorTemp(Double.parseDouble(maxOutdoorTemp.getText()));
                homeoFuncDto.setMaxOutdoorHumidity(Double.parseDouble(maxOutdoorHumidity.getText()));
                homeoFuncDto.setAvgOutdoorAirSpeed(Double.parseDouble(avgOutdoorAirSpeed.getText()));
                homeoFuncDto.setSampleSurfaceArea(Double.parseDouble(sampleSurfaceArea.getText()));
            }

            //equipment
            if(equipment.getText() != null) {
                homeoFuncDto.setEquipment(equipment.getText());
            } else {
                throw new NoSuchElementException();
            }

            //проверка суммы весомостей
            ArrayList<Double> weights = new ArrayList<>(List.of(
                    homeoFuncDto.getWaterPermeability_weight(),
                    homeoFuncDto.getWaterPermeabilityDynamicCriteria_weight(),
                    homeoFuncDto.getTotalThermalResistance_weight()
            ));
            Optional<Double> sumWeights = weights.stream().filter(el -> el != -1).reduce(Double::sum);
            if(sumWeights.isPresent()) {
                if(sumWeights.get() != 1) {
                    throw new IllegalArgumentException();
                }
            }

            allValues.getCreateMaterialDto().setHomeostasisFunction(homeoFuncDto);

            return true;
        } catch (NoSuchElementException e) {
            AlertUtil.show("Вы не заполнили все поля", "Закройте это окно и дозаполните всё необходимые поля", allValues.getRootStage());
        } catch (IllegalArgumentException e) {
            AlertUtil.show("Вы ввели некорректные значения", "Закройте это окно и проверьте правильность введенных значений", allValues.getRootStage());
        }

        return false;
    }

    @FXML
    void btn_clearFields_clicked(MouseEvent event) {
        boolean isTrue = AlertUtil.showConfirmation("Очистить все поля на этой стадии?", "Все поля в этой стадии создания будут полностью очищены", allValues.getRootStage());
        if (isTrue) {
            allValues.getCreateMaterialDto().setHomeostasisFunction(null);
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
        allValues.setLastCreateMaterialComponent(Component.WATERPROOF_TABLE);
        ComponentUtil.mount(Component.WATERPROOF_TABLE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @FXML
    @SneakyThrows()
    void next_btn_clicked(MouseEvent event) {
        if (validateAndSetData()) {
            allValues.setLastCreateMaterialComponent(Component.RELIABILITY_TABLE);
            ComponentUtil.mount(Component.RELIABILITY_TABLE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        экспериментальные значения
        m1s.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        m2s.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        s0_1.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        t_1.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));

        m1min.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        m2min.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        m1max.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        m2max.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        s0_2.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        t_2.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));

        tos.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        s.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        m.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));

        comfTempInsideClothes.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        comfHumidityInsideClothes.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        minOutdoorHumidity.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        minOutdoorTemp.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        maxOutdoorTemp.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        maxOutdoorHumidity.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        avgOutdoorAirSpeed.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        sampleSurfaceArea.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));

        //базовые значения
        waterPermeability_base.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        waterPermeabilityDynamicCriteria_base.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        totalThermalResistance_base.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));

        //значения весомости
        waterPermeability_weight.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        waterPermeabilityDynamicCriteria_weight.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        totalThermalResistance_weight.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
    }
}
