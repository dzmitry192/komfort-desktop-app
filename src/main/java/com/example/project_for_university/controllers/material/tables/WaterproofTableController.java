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
                    validateAndSetData();
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
            if (waterproofFunctionDto.getMaterialBlottingPressure_experimental_1() != 0) {
                materialBlottingPressure_experimental_1.setText(String.valueOf(waterproofFunctionDto.getMaterialBlottingPressure_experimental_1()));
            }
            if (waterproofFunctionDto.getWaterproof_experimental_1() != 0) {
                waterproof_experimental_1.setText(String.valueOf(waterproofFunctionDto.getWaterproof_experimental_1()));
            }
            if (waterproofFunctionDto.getMaterialBlottingTime_experimental_1() != 0) {
                materialBlottingTime_experimental_1.setText(String.valueOf(waterproofFunctionDto.getMaterialBlottingTime_experimental_1()));
            }
            if (waterproofFunctionDto.getWaterproofRealizationCriteria_experimental_1() != 0) {
                waterproofRealizationCriteria_experimental_1.setText(String.valueOf(waterproofFunctionDto.getWaterproofRealizationCriteria_experimental_1()));
            }
            if (waterproofFunctionDto.getWaterproofRealizationCriteria_experimental_2() != 0) {
                waterproofRealizationCriteria_experimental_2.setText(String.valueOf(waterproofFunctionDto.getWaterproofRealizationCriteria_experimental_2()));
            }
            if (waterproofFunctionDto.getDynamicWaterproofCriteria_experimental_1() != 0) {
                dynamicWaterproofCriteria_experimental_1.setText(String.valueOf(waterproofFunctionDto.getDynamicWaterproofCriteria_experimental_1()));
            }
            if (waterproofFunctionDto.getDynamicWaterproofCriteria_experimental_2() != 0) {
                dynamicWaterproofCriteria_experimental_2.setText(String.valueOf(waterproofFunctionDto.getDynamicWaterproofCriteria_experimental_2()));
            }
            if (waterproofFunctionDto.getDynamicWaterproofCriteria_experimental_3() != 0) {
                dynamicWaterproofCriteria_experimental_3.setText(String.valueOf(waterproofFunctionDto.getDynamicWaterproofCriteria_experimental_3()));
            }
            if (waterproofFunctionDto.getDynamicWaterproofCriteria_experimental_4() != 0) {
                dynamicWaterproofCriteria_experimental_4.setText(String.valueOf(waterproofFunctionDto.getDynamicWaterproofCriteria_experimental_4()));
            }
            if (waterproofFunctionDto.getHydrostaticPressureIncreaseSpeed() != 0) {
                hydrostaticPressureIncreaseSpeed.setText(String.valueOf(waterproofFunctionDto.getHydrostaticPressureIncreaseSpeed()));
            }
            if (waterproofFunctionDto.getHydrostaticPressure() != 0) {
                hydrostaticPressure.setText(String.valueOf(waterproofFunctionDto.getHydrostaticPressure()));
            }
            if (waterproofFunctionDto.getWaterproofTime() != 0) {
                waterproofTime.setText(String.valueOf(waterproofFunctionDto.getWaterproofTime()));
            }

            //2 column
            if (waterproofFunctionDto.getMaterialBlottingPressure_calculated() != 0) {
                materialBlottingPressure_calculated.setText(String.valueOf(waterproofFunctionDto.getMaterialBlottingPressure_calculated()));
            }
            if (waterproofFunctionDto.getWaterproof_calculated() != 0) {
                waterproof_calculated.setText(String.valueOf(waterproofFunctionDto.getWaterproof_calculated()));
            }
            if (waterproofFunctionDto.getMaterialBlottingTime_calculated() != 0) {
                materialBlottingTime_calculated.setText(String.valueOf(waterproofFunctionDto.getMaterialBlottingTime_calculated()));
            }

            //3 column
            if (waterproofFunctionDto.getMaterialBlottingPressure_base() != 0) {
                materialBlottingPressure_base.setText(String.valueOf(waterproofFunctionDto.getMaterialBlottingPressure_base()));
            }
            if (waterproofFunctionDto.getWaterproof_base() != 0) {
                waterproof_base.setText(String.valueOf(waterproofFunctionDto.getWaterproof_base()));
            }
            if (waterproofFunctionDto.getMaterialBlottingTime_base() != 0) {
                materialBlottingTime_base.setText(String.valueOf(waterproofFunctionDto.getMaterialBlottingTime_base()));
            }
            if (waterproofFunctionDto.getWaterproofRealizationCriteria_base() != 0) {
                waterproofRealizationCriteria_base.setText(String.valueOf(waterproofFunctionDto.getWaterproofRealizationCriteria_base()));
            }
            if (waterproofFunctionDto.getDynamicWaterproofCriteria_base() != 0) {
                dynamicWaterproofCriteria_base.setText(String.valueOf(waterproofFunctionDto.getDynamicWaterproofCriteria_base()));
            }

            //4 column
            if (waterproofFunctionDto.getMaterialBlottingPressure_weight() != 0) {
                materialBlottingPressure_weight.setText(String.valueOf(waterproofFunctionDto.getMaterialBlottingPressure_weight()));
            }
            if (waterproofFunctionDto.getWaterproof_weight() != 0) {
                waterproof_weight.setText(String.valueOf(waterproofFunctionDto.getWaterproof_weight()));
            }
            if (waterproofFunctionDto.getMaterialBlottingTime_weight() != 0) {
                materialBlottingTime_weight.setText(String.valueOf(waterproofFunctionDto.getMaterialBlottingTime_weight()));
            }
            if (waterproofFunctionDto.getWaterproofRealizationCriteria_weight() != 0) {
                waterproofRealizationCriteria_weight.setText(String.valueOf(waterproofFunctionDto.getWaterproofRealizationCriteria_weight()));
            }
            if (waterproofFunctionDto.getDynamicWaterproofCriteria_weight() != 0) {
                dynamicWaterproofCriteria_weight.setText(String.valueOf(waterproofFunctionDto.getDynamicWaterproofCriteria_weight()));
            }

            //equipment
            if (waterproofFunctionDto.getEquipment() != null) {
                equipment.setText(waterproofFunctionDto.getEquipment());
            }
        }
    }

    private boolean[] validateAndSetData() {
        boolean isEmpty = false;
        boolean isValid = true;

        //col 1
        if (!materialBlottingPressure_experimental_1.getText().isEmpty()) {
            if (materialBlottingPressure_experimental_1.getText().equals(".") || Double.parseDouble(materialBlottingPressure_experimental_1.getText()) == 0) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingPressure_experimental_1(Double.parseDouble(materialBlottingPressure_experimental_1.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingPressure_experimental_1(0);
            isEmpty = true;
        }
        if (!waterproof_experimental_1.getText().isEmpty()) {
            if (waterproof_experimental_1.getText().equals(".") || Double.parseDouble(waterproof_experimental_1.getText()) == 0) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproof_experimental_1(Double.parseDouble(waterproof_experimental_1.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproof_experimental_1(0);
            isEmpty = true;
        }
        if (!materialBlottingTime_experimental_1.getText().isEmpty()) {
            if (materialBlottingTime_experimental_1.getText().equals(".") || Double.parseDouble(materialBlottingTime_experimental_1.getText()) == 0) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingTime_experimental_1(Double.parseDouble(materialBlottingTime_experimental_1.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingTime_experimental_1(0);
            isEmpty = true;
        }
        if (!waterproofRealizationCriteria_experimental_1.getText().isEmpty()) {
            if (waterproofRealizationCriteria_experimental_1.getText().equals(".") || Double.parseDouble(waterproofRealizationCriteria_experimental_1.getText()) == 0) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproofRealizationCriteria_experimental_1(Double.parseDouble(waterproofRealizationCriteria_experimental_1.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproofRealizationCriteria_experimental_1(0);
            isEmpty = true;
        }
        if (!waterproofRealizationCriteria_experimental_2.getText().isEmpty()) {
            if (waterproofRealizationCriteria_experimental_2.getText().equals(".") || Double.parseDouble(waterproofRealizationCriteria_experimental_2.getText()) == 0) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproofRealizationCriteria_experimental_2(Double.parseDouble(waterproofRealizationCriteria_experimental_2.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproofRealizationCriteria_experimental_2(0);
            isEmpty = true;
        }
        if (!dynamicWaterproofCriteria_experimental_1.getText().isEmpty()) {
            if (dynamicWaterproofCriteria_experimental_1.getText().equals(".") || Double.parseDouble(dynamicWaterproofCriteria_experimental_1.getText()) == 0) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getWaterproofFunction().setDynamicWaterproofCriteria_experimental_1(Double.parseDouble(dynamicWaterproofCriteria_experimental_1.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setDynamicWaterproofCriteria_experimental_1(0);
            isEmpty = true;
        }
        if (!dynamicWaterproofCriteria_experimental_2.getText().isEmpty()) {
            if (dynamicWaterproofCriteria_experimental_2.getText().equals(".") || Double.parseDouble(dynamicWaterproofCriteria_experimental_2.getText()) == 0) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getWaterproofFunction().setDynamicWaterproofCriteria_experimental_2(Double.parseDouble(dynamicWaterproofCriteria_experimental_2.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setDynamicWaterproofCriteria_experimental_2(0);
            isEmpty = true;
        }
        if (!dynamicWaterproofCriteria_experimental_3.getText().isEmpty()) {
            if (dynamicWaterproofCriteria_experimental_3.getText().equals(".") || Double.parseDouble(dynamicWaterproofCriteria_experimental_3.getText()) == 0) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getWaterproofFunction().setDynamicWaterproofCriteria_experimental_3(Double.parseDouble(dynamicWaterproofCriteria_experimental_3.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setDynamicWaterproofCriteria_experimental_3(0);
            isEmpty = true;
        }
        if (!dynamicWaterproofCriteria_experimental_4.getText().isEmpty()) {
            if (dynamicWaterproofCriteria_experimental_4.getText().equals(".") || Double.parseDouble(dynamicWaterproofCriteria_experimental_4.getText()) == 0) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getWaterproofFunction().setDynamicWaterproofCriteria_experimental_4(Double.parseDouble(dynamicWaterproofCriteria_experimental_4.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setDynamicWaterproofCriteria_experimental_4(0);
            isEmpty = true;
        }
        if (!hydrostaticPressureIncreaseSpeed.getText().isEmpty()) {
            if (hydrostaticPressureIncreaseSpeed.getText().equals(".") || Double.parseDouble(hydrostaticPressureIncreaseSpeed.getText()) == 0) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getWaterproofFunction().setHydrostaticPressureIncreaseSpeed(Double.parseDouble(hydrostaticPressureIncreaseSpeed.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setHydrostaticPressureIncreaseSpeed(0);
            isEmpty = true;
        }
        if (!hydrostaticPressure.getText().isEmpty()) {
            if (hydrostaticPressure.getText().equals(".") || Double.parseDouble(hydrostaticPressure.getText()) == 0) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getWaterproofFunction().setHydrostaticPressure(Double.parseDouble(hydrostaticPressure.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setHydrostaticPressure(0);
            isEmpty = true;
        }
        if (!waterproofTime.getText().isEmpty()) {
            if (waterproofTime.getText().equals(".") || Double.parseDouble(waterproofTime.getText()) == 0) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproofTime(Double.parseDouble(waterproofTime.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproofTime(0);
            isEmpty = true;
        }

        //col 2
        if (!materialBlottingPressure_calculated.getText().isEmpty()) {
            if (materialBlottingPressure_calculated.getText().equals(".") || Double.parseDouble(materialBlottingPressure_calculated.getText()) == 0) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingPressure_calculated(Double.parseDouble(materialBlottingPressure_calculated.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingPressure_calculated(0);
            isEmpty = true;
        }
        if (!waterproof_calculated.getText().isEmpty()) {
            if (waterproof_calculated.getText().equals(".") || Double.parseDouble(waterproof_calculated.getText()) == 0) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproof_calculated(Double.parseDouble(waterproof_calculated.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproof_calculated(0);
            isEmpty = true;
        }
        if (!materialBlottingTime_calculated.getText().isEmpty()) {
            if (materialBlottingTime_calculated.getText().equals(".") || Double.parseDouble(materialBlottingTime_calculated.getText()) == 0) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingTime_calculated(Double.parseDouble(materialBlottingTime_calculated.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingTime_calculated(0);
            isEmpty = true;
        }

        //col 3
        if (!materialBlottingPressure_base.getText().isEmpty()) {
            if (materialBlottingPressure_base.getText().equals(".") || Double.parseDouble(materialBlottingPressure_base.getText()) == 0) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingPressure_base(Double.parseDouble(materialBlottingPressure_base.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingPressure_base(0);
            isEmpty = true;
        }
        if (!waterproof_base.getText().isEmpty()) {
            if (waterproof_base.getText().equals(".") || Double.parseDouble(waterproof_base.getText()) == 0) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproof_base(Double.parseDouble(waterproof_base.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproof_base(0);
            isEmpty = true;
        }

        if (!materialBlottingTime_base.getText().isEmpty()) {
            if (materialBlottingTime_base.getText().equals(".") || Double.parseDouble(materialBlottingTime_base.getText()) == 0) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingTime_base(Double.parseDouble(materialBlottingTime_base.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingTime_base(0);
            isEmpty = true;
        }

        if (!waterproofRealizationCriteria_base.getText().isEmpty()) {
            if (waterproofRealizationCriteria_base.getText().equals(".") || Double.parseDouble(waterproofRealizationCriteria_base.getText()) == 0) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproofRealizationCriteria_base(Double.parseDouble(waterproofRealizationCriteria_base.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproofRealizationCriteria_base(0);
            isEmpty = true;
        }

        if (!dynamicWaterproofCriteria_base.getText().isEmpty()) {
            if (dynamicWaterproofCriteria_base.getText().equals(".") || Double.parseDouble(dynamicWaterproofCriteria_base.getText()) == 0) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getWaterproofFunction().setDynamicWaterproofCriteria_base(Double.parseDouble(dynamicWaterproofCriteria_base.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setDynamicWaterproofCriteria_base(0);
            isEmpty = true;
        }

        //col 4
        if (!materialBlottingPressure_weight.getText().isEmpty()) {
            if(materialBlottingPressure_weight.getText().equals(".") || Double.parseDouble(materialBlottingPressure_weight.getText()) == 0) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingPressure_weight(Double.parseDouble(materialBlottingPressure_weight.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingPressure_weight(0);
            isEmpty = true;
        }

        if (!waterproof_weight.getText().isEmpty()) {
            if(waterproof_weight.getText().equals(".") || Double.parseDouble(waterproof_weight.getText()) == 0) {
              isValid = false;
            } else {
                allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproof_weight(Double.parseDouble(waterproof_weight.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproof_weight(0);
            isEmpty = true;
        }

        if (!materialBlottingTime_weight.getText().isEmpty()) {
            if(materialBlottingTime_weight.getText().equals(".") || Double.parseDouble(materialBlottingTime_weight.getText()) == 0) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingTime_weight(Double.parseDouble(materialBlottingTime_weight.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setMaterialBlottingTime_weight(0);
            isEmpty = true;
        }

        if (!waterproofRealizationCriteria_weight.getText().isEmpty()) {
            if(waterproofRealizationCriteria_weight.getText().equals(".") || Double.parseDouble(waterproofRealizationCriteria_weight.getText()) == 0) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproofRealizationCriteria_weight(Double.parseDouble(waterproofRealizationCriteria_weight.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setWaterproofRealizationCriteria_weight(0);
            isEmpty = true;
        }

        if (!dynamicWaterproofCriteria_weight.getText().isEmpty()) {
            if(dynamicWaterproofCriteria_weight.getText().equals(".") || Double.parseDouble(dynamicWaterproofCriteria_weight.getText()) == 0) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getWaterproofFunction().setDynamicWaterproofCriteria_weight(Double.parseDouble(dynamicWaterproofCriteria_weight.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setDynamicWaterproofCriteria_weight(0);
            isEmpty = true;
        }

        //equipment
        if (equipment.getText() != null) {
            allValues.getCreateMaterialDto().getWaterproofFunction().setEquipment(equipment.getText());
        } else {
            allValues.getCreateMaterialDto().getWaterproofFunction().setEquipment(null);
            isEmpty = true;
        }

        return new boolean[]{isEmpty, isValid};
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
        boolean[] checkErrors = validateAndSetData();
        if (checkErrors[0]) {
            allValues.setLastCreateMaterialComponent(Component.WATERPROOF_TABLE);
            AlertUtil.show("Вы не заполнили все поля", "Закройте это окно и дозаполните всё необходимые поля", allValues.getRootStage());
        } else if (!checkErrors[1]) {
            allValues.setLastCreateMaterialComponent(Component.WATERPROOF_TABLE);
            AlertUtil.show("Вы ввели некорректные значения", "Закройте это окно и проверьте правильность введенных значений", allValues.getRootStage());
        } else {
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
