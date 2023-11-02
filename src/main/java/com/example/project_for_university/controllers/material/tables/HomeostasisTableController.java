package com.example.project_for_university.controllers.material.tables;

import com.example.project_for_university.dto.forBackend.calculate.CalculateHomeostasisFunctionDto;
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
import java.util.ResourceBundle;

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
                    validateAndSetData();
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
            if (homeostasisFunctionDto.getM1s() != 0) {
                m1s.setText(String.valueOf(homeostasisFunctionDto.getM1s()));
            }
            if (homeostasisFunctionDto.getM2s() != 0) {
                m2s.setText(String.valueOf(homeostasisFunctionDto.getM2s()));
            }
            if (homeostasisFunctionDto.getS0_1() != 0) {
                s0_1.setText(String.valueOf(homeostasisFunctionDto.getS0_1()));
            }
            if (homeostasisFunctionDto.getT_1() != 0) {
                t_1.setText(String.valueOf(homeostasisFunctionDto.getT_1()));
            }
            if (homeostasisFunctionDto.getM1min() != 0) {
                m1min.setText(String.valueOf(homeostasisFunctionDto.getM1min()));
            }
            if (homeostasisFunctionDto.getM2min() != 0) {
                m2min.setText(String.valueOf(homeostasisFunctionDto.getM2min()));
            }
            if (homeostasisFunctionDto.getM1max() != 0) {
                m1max.setText(String.valueOf(homeostasisFunctionDto.getM1max()));
            }
            if (homeostasisFunctionDto.getM2max() != 0) {
                m2max.setText(String.valueOf(homeostasisFunctionDto.getM2max()));
            }
            if (homeostasisFunctionDto.getS0_2() != 0) {
                s0_2.setText(String.valueOf(homeostasisFunctionDto.getS0_2()));
            }
            if (homeostasisFunctionDto.getT_2() != 0) {
                t_2.setText(String.valueOf(homeostasisFunctionDto.getT_2()));
            }
            if (homeostasisFunctionDto.getTos() != 0) {
                tos.setText(String.valueOf(homeostasisFunctionDto.getTos()));
            }
            if (homeostasisFunctionDto.getS() != 0) {
                s.setText(String.valueOf(homeostasisFunctionDto.getS()));
            }
            if (homeostasisFunctionDto.getM() != 0) {
                m.setText(String.valueOf(homeostasisFunctionDto.getM()));
            }
            if (homeostasisFunctionDto.getComfTempInsideClothes() != 0) {
                comfTempInsideClothes.setText(String.valueOf(homeostasisFunctionDto.getComfTempInsideClothes()));
            }
            if (homeostasisFunctionDto.getComfHumidityInsideClothes() != 0) {
                comfHumidityInsideClothes.setText(String.valueOf(homeostasisFunctionDto.getComfHumidityInsideClothes()));
            }
            if (homeostasisFunctionDto.getMinOutdoorHumidity() != 0) {
                minOutdoorHumidity.setText(String.valueOf(homeostasisFunctionDto.getMinOutdoorHumidity()));
            }
            if (homeostasisFunctionDto.getMinOutdoorTemp() != 0) {
                minOutdoorTemp.setText(String.valueOf(homeostasisFunctionDto.getMinOutdoorTemp()));
            }
            if (homeostasisFunctionDto.getMaxOutdoorTemp() != 0) {
                maxOutdoorTemp.setText(String.valueOf(homeostasisFunctionDto.getMaxOutdoorTemp()));
            }
            if (homeostasisFunctionDto.getMaxOutdoorHumidity() != 0) {
                maxOutdoorHumidity.setText(String.valueOf(homeostasisFunctionDto.getMaxOutdoorHumidity()));
            }
            if (homeostasisFunctionDto.getAvgOutdoorAirSpeed() != 0) {
                avgOutdoorAirSpeed.setText(String.valueOf(homeostasisFunctionDto.getAvgOutdoorAirSpeed()));
            }
            if (homeostasisFunctionDto.getSampleSurfaceArea() != 0) {
                sampleSurfaceArea.setText(String.valueOf(homeostasisFunctionDto.getSampleSurfaceArea()));
            }

            //col 2
            if (homeostasisFunctionDto.getWaterPermeability_base() != 0) {
                waterPermeability_base.setText(String.valueOf(homeostasisFunctionDto.getWaterPermeability_base()));
            }
            if (homeostasisFunctionDto.getWaterPermeabilityDynamicCriteria_base() != 0) {
                waterPermeabilityDynamicCriteria_base.setText(String.valueOf(homeostasisFunctionDto.getWaterPermeabilityDynamicCriteria_base()));
            }
            if (homeostasisFunctionDto.getTotalThermalResistance_base() != 0) {
                totalThermalResistance_base.setText(String.valueOf(homeostasisFunctionDto.getTotalThermalResistance_base()));
            }

            //col 3
            if (homeostasisFunctionDto.getWaterPermeability_weight() != 0) {
                waterPermeability_weight.setText(String.valueOf(homeostasisFunctionDto.getWaterPermeability_weight()));
            }
            if (homeostasisFunctionDto.getWaterPermeabilityDynamicCriteria_weight() != 0) {
                waterPermeabilityDynamicCriteria_weight.setText(String.valueOf(homeostasisFunctionDto.getWaterPermeabilityDynamicCriteria_weight()));
            }
            if (homeostasisFunctionDto.getTotalThermalResistance_weight() != 0) {
                totalThermalResistance_weight.setText(String.valueOf(homeostasisFunctionDto.getTotalThermalResistance_weight()));
            }

            //equipment
            if (homeostasisFunctionDto.getEquipment() != null) {
                equipment.setText(homeostasisFunctionDto.getEquipment());
            }
        }
    }

    private boolean[] validateAndSetData() {
        boolean isEmpty = false;
        boolean isValid = true;

        //col 1
        if (!m1s.getText().isEmpty()) {
            if (m1s.getText().equals(".") || Double.parseDouble(m1s.getText()) == 0) {
                isValid = false;
                System.out.println("1_1");
            } else {
                allValues.getCreateMaterialDto().getHomeostasisFunction().setM1s(Integer.parseInt(m1s.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setM1s(0);
            isEmpty = true;
            System.out.println("1_2");
        }

        if (!m2s.getText().isEmpty()) {
            if (m2s.getText().equals(".") || Double.parseDouble(m2s.getText()) == 0) {
                isValid = false;
                System.out.println("2_1");
            } else {
                allValues.getCreateMaterialDto().getHomeostasisFunction().setM2s(Integer.parseInt(m2s.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setM2s(0);
            isEmpty = true;
            System.out.println("2_2");
        }

        if (!s0_1.getText().isEmpty()) {
            if (s0_1.getText().equals(".") || Double.parseDouble(s0_1.getText()) == 0) {
                isValid = false;
                System.out.println("3_1");
            } else {
                allValues.getCreateMaterialDto().getHomeostasisFunction().setS0_1(Integer.parseInt(s0_1.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setS0_1(0);
            isEmpty = true;
            System.out.println("3_2");
        }

        if (!t_1.getText().isEmpty()) {
            if (t_1.getText().equals(".") || Double.parseDouble(t_1.getText()) == 0) {
                isValid = false;
                System.out.println("4_1");
            } else {
                allValues.getCreateMaterialDto().getHomeostasisFunction().setT_1(Integer.parseInt(t_1.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setT_1(0);
            isEmpty = true;
            System.out.println("4_2");
        }

        if (!m1min.getText().isEmpty()) {
            if (m1min.getText().equals(".") || Double.parseDouble(m1min.getText()) == 0) {
                isValid = false;
                System.out.println("5_1");
            } else {
                allValues.getCreateMaterialDto().getHomeostasisFunction().setM1min(Integer.parseInt(m1min.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setM1min(0);
            isEmpty = true;
            System.out.println("5_2");
        }

        if (!m2min.getText().isEmpty()) {
            if (m2min.getText().equals(".") || Double.parseDouble(m2min.getText()) == 0) {
                isValid = false;
                System.out.println("6_1");
            } else {
                allValues.getCreateMaterialDto().getHomeostasisFunction().setM2min(Integer.parseInt(m2min.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setM2min(0);
            isEmpty = true;
            System.out.println("6_2");
        }

        if (!m1max.getText().isEmpty()) {
            if (m1max.getText().equals(".") || Double.parseDouble(m1max.getText()) == 0) {
                isValid = false;
                System.out.println("7_1");
            } else {
                allValues.getCreateMaterialDto().getHomeostasisFunction().setM1max(Integer.parseInt(m1max.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setM1max(0);
            isEmpty = true;
            System.out.println("7_2");
        }

        if (!m2max.getText().isEmpty()) {
            if (m2max.getText().equals(".") || Double.parseDouble(m2max.getText()) == 0) {
                isValid = false;
                System.out.println("8_1");
            } else {
                allValues.getCreateMaterialDto().getHomeostasisFunction().setM2max(Integer.parseInt(m2max.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setM2max(0);
            isEmpty = true;
            System.out.println("8_2");
        }

        if (!s0_2.getText().isEmpty()) {
            if (s0_2.getText().equals(".") || Double.parseDouble(s0_2.getText()) == 0) {
                isValid = false;
                System.out.println("9_1");
            } else {
                allValues.getCreateMaterialDto().getHomeostasisFunction().setS0_2(Integer.parseInt(s0_2.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setS0_2(0);
            isEmpty = true;
            System.out.println("9_2");
        }

        if (!t_2.getText().isEmpty()) {
            if (t_2.getText().equals(".") || Double.parseDouble(t_2.getText()) == 0) {
                isValid = false;
                System.out.println("10_1");
            } else {
                allValues.getCreateMaterialDto().getHomeostasisFunction().setT_2(Integer.parseInt(t_2.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setT_2(0);
            isEmpty = true;
            System.out.println("10_2");
        }

        if (!tos.getText().isEmpty()) {
            if (tos.getText().equals(".") || Double.parseDouble(tos.getText()) == 0) {
                isValid = false;
                System.out.println("11_1");
            } else {
                allValues.getCreateMaterialDto().getHomeostasisFunction().setTos(Integer.parseInt(tos.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setTos(0);
            isEmpty = true;
            System.out.println("11_2");
        }

        if (!s.getText().isEmpty()) {
            if (s.getText().equals(".") || Double.parseDouble(s.getText()) == 0) {
                isValid = false;
                System.out.println("12_1");
            } else {
                allValues.getCreateMaterialDto().getHomeostasisFunction().setS(Integer.parseInt(s.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setS(0);
            isEmpty = true;
            System.out.println("12_2");
        }

        if (!m.getText().isEmpty()) {
            if (m.getText().equals(".") || Double.parseDouble(m.getText()) == 0) {
                isValid = false;
                System.out.println("13_1");
            } else {
                allValues.getCreateMaterialDto().getHomeostasisFunction().setM(Integer.parseInt(m.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setM(0);
            isEmpty = true;
            System.out.println("13_2");
        }

        if (!m.getText().isEmpty()) {
            if (m.getText().equals(".") || Double.parseDouble(m.getText()) == 0) {
                isValid = false;
                System.out.println("13_1");
            } else {
                allValues.getCreateMaterialDto().getHomeostasisFunction().setM(Integer.parseInt(m.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setM(0);
            isEmpty = true;
            System.out.println("13_2");
        }

        if (!comfTempInsideClothes.getText().isEmpty()) {
            if (comfTempInsideClothes.getText().equals(".") || Double.parseDouble(comfTempInsideClothes.getText()) == 0) {
                isValid = false;
                System.out.println("14_1");
            } else {
                allValues.getCreateMaterialDto().getHomeostasisFunction().setComfTempInsideClothes(Integer.parseInt(comfTempInsideClothes.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setComfTempInsideClothes(0);
            isEmpty = true;
            System.out.println("14_2");
        }

        if (!comfHumidityInsideClothes.getText().isEmpty()) {
            if (comfHumidityInsideClothes.getText().equals(".") || Double.parseDouble(comfHumidityInsideClothes.getText()) == 0) {
                isValid = false;
                System.out.println("15_1");
            } else {
                allValues.getCreateMaterialDto().getHomeostasisFunction().setComfHumidityInsideClothes(Integer.parseInt(comfHumidityInsideClothes.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setComfHumidityInsideClothes(0);
            isEmpty = true;
            System.out.println("15_2");
        }

        if (!minOutdoorHumidity.getText().isEmpty()) {
            if (minOutdoorHumidity.getText().equals(".") || Double.parseDouble(minOutdoorHumidity.getText()) == 0) {
                isValid = false;
                System.out.println("16_1");
            } else {
                allValues.getCreateMaterialDto().getHomeostasisFunction().setMinOutdoorHumidity(Integer.parseInt(minOutdoorHumidity.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setMinOutdoorHumidity(0);
            isEmpty = true;
            System.out.println("16_2");
        }

        if (!minOutdoorTemp.getText().isEmpty()) {
            if (minOutdoorTemp.getText().equals(".") || Double.parseDouble(minOutdoorTemp.getText()) == 0) {
                isValid = false;
                System.out.println("17_1");
            } else {
                allValues.getCreateMaterialDto().getHomeostasisFunction().setMinOutdoorTemp(Integer.parseInt(minOutdoorTemp.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setMinOutdoorTemp(0);
            isEmpty = true;
            System.out.println("17_2");
        }

        if (!maxOutdoorTemp.getText().isEmpty()) {
            if (maxOutdoorTemp.getText().equals(".") || Double.parseDouble(maxOutdoorTemp.getText()) == 0) {
                isValid = false;
                System.out.println("18_1");
            } else {
                allValues.getCreateMaterialDto().getHomeostasisFunction().setMaxOutdoorTemp(Integer.parseInt(maxOutdoorTemp.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setMaxOutdoorTemp(0);
            isEmpty = true;
            System.out.println("18_2");
        }

        if (!maxOutdoorHumidity.getText().isEmpty()) {
            if (maxOutdoorHumidity.getText().equals(".") || Double.parseDouble(maxOutdoorHumidity.getText()) == 0) {
                isValid = false;
                System.out.println("19_1");
            } else {
                allValues.getCreateMaterialDto().getHomeostasisFunction().setMaxOutdoorHumidity(Integer.parseInt(maxOutdoorHumidity.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setMaxOutdoorHumidity(0);
            isEmpty = true;
            System.out.println("19_2");
        }

        if (!avgOutdoorAirSpeed.getText().isEmpty()) {
            if (avgOutdoorAirSpeed.getText().equals(".") || Double.parseDouble(avgOutdoorAirSpeed.getText()) == 0) {
                isValid = false;
                System.out.println("20_1");
            } else {
                allValues.getCreateMaterialDto().getHomeostasisFunction().setAvgOutdoorAirSpeed(Integer.parseInt(avgOutdoorAirSpeed.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setAvgOutdoorAirSpeed(0);
            isEmpty = true;
            System.out.println("20_2");
        }

        if (!sampleSurfaceArea.getText().isEmpty()) {
            if (sampleSurfaceArea.getText().equals(".") || Double.parseDouble(sampleSurfaceArea.getText()) == 0) {
                isValid = false;
                System.out.println("21_1");
            } else {
                allValues.getCreateMaterialDto().getHomeostasisFunction().setSampleSurfaceArea(Integer.parseInt(sampleSurfaceArea.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setSampleSurfaceArea(0);
            isEmpty = true;
            System.out.println("21_2");
        }

        //col 2
        if (!waterPermeability_base.getText().isEmpty()) {
            if (waterPermeability_base.getText().equals(".") || Double.parseDouble(waterPermeability_base.getText()) == 0) {
                isValid = false;
                System.out.println("22_1");
            } else {
                allValues.getCreateMaterialDto().getHomeostasisFunction().setWaterPermeability_base(Integer.parseInt(waterPermeability_base.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setWaterPermeability_base(0);
            isEmpty = true;
            System.out.println("22_2");
        }

        if (!waterPermeabilityDynamicCriteria_base.getText().isEmpty()) {
            if (waterPermeabilityDynamicCriteria_base.getText().equals(".") || Double.parseDouble(waterPermeabilityDynamicCriteria_base.getText()) == 0) {
                isValid = false;
                System.out.println("23_1");
            } else {
                allValues.getCreateMaterialDto().getHomeostasisFunction().setWaterPermeabilityDynamicCriteria_base(Integer.parseInt(waterPermeabilityDynamicCriteria_base.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setWaterPermeabilityDynamicCriteria_base(0);
            isEmpty = true;
            System.out.println("23_2");
        }

        if (!totalThermalResistance_base.getText().isEmpty()) {
            if (totalThermalResistance_base.getText().equals(".") || Double.parseDouble(totalThermalResistance_base.getText()) == 0) {
                isValid = false;
                System.out.println("24_1");
            } else {
                allValues.getCreateMaterialDto().getHomeostasisFunction().setTotalThermalResistance_base(Integer.parseInt(totalThermalResistance_base.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setTotalThermalResistance_base(0);
            isEmpty = true;
            System.out.println("24_2");
        }

        //col 3
        if (!waterPermeability_weight.getText().isEmpty()) {
            if (waterPermeability_weight.getText().equals(".") || Double.parseDouble(waterPermeability_weight.getText()) == 0) {
                isValid = false;
                System.out.println("25_1");
            } else {
                allValues.getCreateMaterialDto().getHomeostasisFunction().setWaterPermeability_weight(Integer.parseInt(waterPermeability_weight.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setWaterPermeability_weight(0);
            isEmpty = true;
            System.out.println("25_2");
        }

        if (!waterPermeabilityDynamicCriteria_weight.getText().isEmpty()) {
            if (waterPermeabilityDynamicCriteria_weight.getText().equals(".") || Double.parseDouble(waterPermeabilityDynamicCriteria_weight.getText()) == 0) {
                isValid = false;
                System.out.println("26_1");
            } else {
                allValues.getCreateMaterialDto().getHomeostasisFunction().setWaterPermeabilityDynamicCriteria_weight(Integer.parseInt(waterPermeabilityDynamicCriteria_weight.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setWaterPermeabilityDynamicCriteria_weight(0);
            isEmpty = true;
            System.out.println("26_2");
        }

        if (!totalThermalResistance_weight.getText().isEmpty()) {
            if (totalThermalResistance_weight.getText().equals(".") || Double.parseDouble(totalThermalResistance_weight.getText()) == 0) {
                isValid = false;
                System.out.println("27_1");
            } else {
                allValues.getCreateMaterialDto().getHomeostasisFunction().setTotalThermalResistance_weight(Integer.parseInt(totalThermalResistance_weight.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setTotalThermalResistance_weight(0);
            isEmpty = true;
            System.out.println("27_2");
        }

        //equipment
        if (equipment.getText() != null) {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setEquipment(equipment.getText());
        } else {
            allValues.getCreateMaterialDto().getHomeostasisFunction().setEquipment(null);
            isEmpty = true;
            System.out.println("28");
        }

        return new boolean[]{isEmpty, isValid};
    }

    @FXML
    @SneakyThrows()
    void back_btn_clicked(MouseEvent event) {
        validateAndSetData();
        allValues.setLastCreateMaterialComponent(Component.WATERPROOF_TABLE);
        ComponentUtil.mount(Component.WATERPROOF_TABLE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @FXML
    @SneakyThrows()
    void next_btn_clicked(MouseEvent event) {
        boolean[] checkErrors = validateAndSetData();

        if(checkErrors[0]) {
            allValues.setLastCreateMaterialComponent(Component.HOMEOSTASIS_TABLE);
            AlertUtil.show("Вы не заполнили все поля", "Закройте это окно и дозаполните всё необходимые поля", allValues.getRootStage());
        } else if(!checkErrors[1]) {
            allValues.setLastCreateMaterialComponent(Component.HOMEOSTASIS_TABLE);
            AlertUtil.show("Вы ввели некорректные значения", "Закройте это окно и проверьте правильность введенных значений", allValues.getRootStage());
        } else {
            allValues.setLastCreateMaterialComponent(Component.RELIABILITY_TABLE);
            ComponentUtil.mount(Component.RELIABILITY_TABLE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //экспериментальные значения
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
