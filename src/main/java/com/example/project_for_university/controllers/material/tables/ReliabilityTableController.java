package com.example.project_for_university.controllers.material.tables;

import com.example.project_for_university.dto.forBackend.calculate.CalculateReliabilityFunctionDto;
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

import java.net.URL;
import java.util.ResourceBundle;

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
            EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("reliability sideBartBtn");
                    validateAndSetData();
                }
            };
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, clickHandler);
            allValues.getSideBarButtonsEventHandlers().add(clickHandler);
        }
    }

    private void fillReliabilityTable() {
        CalculateReliabilityFunctionDto reliabilityFunctionDto = allValues.getCreateMaterialDto().getReliabilityFunction();

        if (reliabilityFunctionDto != null) {
            //col 1
            if (reliabilityFunctionDto.getRelativeBlottingPressureAfterLoad_experimental_1() != 0) {
                relativeBlottingPressureAfterLoad_experimental_1.setText(String.valueOf(reliabilityFunctionDto.getRelativeBlottingPressureAfterLoad_experimental_1()));
            }
            if (reliabilityFunctionDto.getRelativeWaterResistanceAfterLoad_experimental_1() != 0) {
                relativeWaterResistanceAfterLoad_experimental_1.setText(String.valueOf(reliabilityFunctionDto.getRelativeWaterResistanceAfterLoad_experimental_1()));
            }
            if (reliabilityFunctionDto.getRelativeBlottingTimeAfterLoad_experimental_1() != 0) {
                relativeBlottingTimeAfterLoad_experimental_1.setText(String.valueOf(reliabilityFunctionDto.getRelativeBlottingTimeAfterLoad_experimental_1()));
            }
            if (reliabilityFunctionDto.getWaterproofRealizationCriteriaAfterLoad_experimental_1() != 0) {
                waterproofRealizationCriteriaAfterLoad_experimental_1.setText(String.valueOf(reliabilityFunctionDto.getWaterproofRealizationCriteriaAfterLoad_experimental_1()));
            }
            if (reliabilityFunctionDto.getWaterproofRealizationCriteriaAfterLoad_experimental_2() != 0) {
                waterproofRealizationCriteriaAfterLoad_experimental_2.setText(String.valueOf(reliabilityFunctionDto.getWaterproofRealizationCriteriaAfterLoad_experimental_2()));
            }
            if (reliabilityFunctionDto.getMaxWaterResistanceLvl() != 0) {
                maxWaterResistanceLvl.setText(String.valueOf(reliabilityFunctionDto.getMaxWaterResistanceLvl()));
            }
            if (reliabilityFunctionDto.getImpactCyclesCnt() != 0) {
                impactCyclesCnt.setText(String.valueOf(reliabilityFunctionDto.getImpactCyclesCnt()));
            }

            //col 2
            if (reliabilityFunctionDto.getRelativeBlottingPressureAfterLoad_calculated() != 0) {
                relativeBlottingPressureAfterLoad_calculated.setText(String.valueOf(reliabilityFunctionDto.getRelativeBlottingPressureAfterLoad_calculated()));
            }
            if (reliabilityFunctionDto.getRelativeWaterResistanceAfterLoad_calculated() != 0) {
                relativeWaterResistanceAfterLoad_calculated.setText(String.valueOf(reliabilityFunctionDto.getRelativeWaterResistanceAfterLoad_calculated()));
            }
            if (reliabilityFunctionDto.getRelativeBlottingTimeAfterLoad_calculated() != 0) {
                relativeBlottingTimeAfterLoad_calculated.setText(String.valueOf(reliabilityFunctionDto.getRelativeBlottingTimeAfterLoad_calculated()));
            }

            //col 3
            if (reliabilityFunctionDto.getRelativeBlottingPressureAfterLoad_base() != 0) {
                relativeBlottingPressureAfterLoad_base.setText(String.valueOf(reliabilityFunctionDto.getRelativeBlottingPressureAfterLoad_base()));
            }
            if (reliabilityFunctionDto.getRelativeWaterResistanceAfterLoad_base() != 0) {
                relativeWaterResistanceAfterLoad_base.setText(String.valueOf(reliabilityFunctionDto.getRelativeWaterResistanceAfterLoad_base()));
            }
            if (reliabilityFunctionDto.getRelativeBlottingTimeAfterLoad_base() != 0) {
                relativeBlottingTimeAfterLoad_base.setText(String.valueOf(reliabilityFunctionDto.getRelativeBlottingTimeAfterLoad_base()));
            }
            if (reliabilityFunctionDto.getWaterproofFunctionResource_base() != 0) {
                waterproofFunctionResource_base.setText(String.valueOf(reliabilityFunctionDto.getWaterproofFunctionResource_base()));
            }

            //col 4
            if (reliabilityFunctionDto.getRelativeBlottingPressureAfterLoad_weight() != 0) {
                relativeBlottingPressureAfterLoad_weight.setText(String.valueOf(reliabilityFunctionDto.getRelativeBlottingPressureAfterLoad_weight()));
            }
            if (reliabilityFunctionDto.getRelativeWaterResistanceAfterLoad_weight() != 0) {
                relativeWaterResistanceAfterLoad_weight.setText(String.valueOf(reliabilityFunctionDto.getRelativeWaterResistanceAfterLoad_weight()));
            }
            if (reliabilityFunctionDto.getRelativeBlottingTimeAfterLoad_weight() != 0) {
                relativeBlottingTimeAfterLoad_weight.setText(String.valueOf(reliabilityFunctionDto.getRelativeBlottingTimeAfterLoad_weight()));
            }
            if (reliabilityFunctionDto.getWaterproofRealizationCriteriaAfterLoad_weight() != 0) {
                waterproofRealizationCriteriaAfterLoad_weight.setText(String.valueOf(reliabilityFunctionDto.getWaterproofRealizationCriteriaAfterLoad_weight()));
            }
            if (reliabilityFunctionDto.getWaterproofFunctionResource_weight() != 0) {
                waterproofFunctionResource_weight.setText(String.valueOf(reliabilityFunctionDto.getWaterproofFunctionResource_weight()));
            }

            //equipment
            if (reliabilityFunctionDto.getEquipment() != null) {
                equipment.setText(reliabilityFunctionDto.getEquipment());
            }
        }
    }

    private boolean[] validateAndSetData() {
        boolean isEmpty = false;
        boolean isValid = true;

        //col 1
        if (!relativeBlottingPressureAfterLoad_experimental_1.getText().isEmpty()) {
            if (!ValidationUtils.isValid(relativeBlottingPressureAfterLoad_experimental_1.getText())) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeBlottingPressureAfterLoad_experimental_1(Integer.parseInt(relativeBlottingPressureAfterLoad_experimental_1.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeBlottingPressureAfterLoad_experimental_1(0);
            isEmpty = true;
        }

        if (!relativeWaterResistanceAfterLoad_experimental_1.getText().isEmpty()) {
            if (!ValidationUtils.isValid(relativeWaterResistanceAfterLoad_experimental_1.getText())) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeWaterResistanceAfterLoad_experimental_1(Integer.parseInt(relativeWaterResistanceAfterLoad_experimental_1.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeWaterResistanceAfterLoad_experimental_1(0);
            isEmpty = true;
        }

        if (!relativeBlottingTimeAfterLoad_experimental_1.getText().isEmpty()) {
            if (!ValidationUtils.isValid(relativeBlottingTimeAfterLoad_experimental_1.getText())) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeBlottingTimeAfterLoad_experimental_1(Integer.parseInt(relativeBlottingTimeAfterLoad_experimental_1.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeBlottingTimeAfterLoad_experimental_1(0);
            isEmpty = true;
        }

        if (!waterproofRealizationCriteriaAfterLoad_experimental_1.getText().isEmpty()) {
            if (!ValidationUtils.isValid(waterproofRealizationCriteriaAfterLoad_experimental_1.getText())) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getReliabilityFunction().setWaterproofRealizationCriteriaAfterLoad_experimental_1(Integer.parseInt(waterproofRealizationCriteriaAfterLoad_experimental_1.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setWaterproofRealizationCriteriaAfterLoad_experimental_1(0);
            isEmpty = true;
        }

        if (!waterproofRealizationCriteriaAfterLoad_experimental_2.getText().isEmpty()) {
            if (!ValidationUtils.isValid(waterproofRealizationCriteriaAfterLoad_experimental_2.getText())) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getReliabilityFunction().setWaterproofRealizationCriteriaAfterLoad_experimental_2(Integer.parseInt(waterproofRealizationCriteriaAfterLoad_experimental_2.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setWaterproofRealizationCriteriaAfterLoad_experimental_2(0);
            isEmpty = true;
        }

        if (!maxWaterResistanceLvl.getText().isEmpty()) {
            if (!ValidationUtils.isValid(maxWaterResistanceLvl.getText())) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getReliabilityFunction().setMaxWaterResistanceLvl(Integer.parseInt(maxWaterResistanceLvl.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setMaxWaterResistanceLvl(0);
            isEmpty = true;
        }

        if (!impactCyclesCnt.getText().isEmpty()) {
            if (!ValidationUtils.isValid(impactCyclesCnt.getText())) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getReliabilityFunction().setImpactCyclesCnt(Integer.parseInt(impactCyclesCnt.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setImpactCyclesCnt(0);
            isEmpty = true;
        }

        //col 2
        if (!relativeBlottingPressureAfterLoad_calculated.getText().isEmpty()) {
            if (!ValidationUtils.isValid(relativeBlottingPressureAfterLoad_calculated.getText())) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeBlottingPressureAfterLoad_calculated(Integer.parseInt(relativeBlottingPressureAfterLoad_calculated.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeBlottingPressureAfterLoad_calculated(0);
            isEmpty = true;
        }

        if (!relativeWaterResistanceAfterLoad_calculated.getText().isEmpty()) {
            if (!ValidationUtils.isValid(relativeWaterResistanceAfterLoad_calculated.getText())) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeWaterResistanceAfterLoad_calculated(Integer.parseInt(relativeWaterResistanceAfterLoad_calculated.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeWaterResistanceAfterLoad_calculated(0);
            isEmpty = true;
        }

        if (!relativeBlottingTimeAfterLoad_calculated.getText().isEmpty()) {
            if (!ValidationUtils.isValid(relativeBlottingTimeAfterLoad_calculated.getText())) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeBlottingTimeAfterLoad_calculated(Integer.parseInt(relativeBlottingTimeAfterLoad_calculated.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeBlottingTimeAfterLoad_calculated(0);
            isEmpty = true;
        }

        //col 3
        if (!relativeBlottingPressureAfterLoad_base.getText().isEmpty()) {
            if (!ValidationUtils.isValid(relativeBlottingPressureAfterLoad_base.getText())) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeBlottingPressureAfterLoad_base(Integer.parseInt(relativeBlottingPressureAfterLoad_base.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeBlottingPressureAfterLoad_base(0);
            isEmpty = true;
        }

        if (!relativeWaterResistanceAfterLoad_base.getText().isEmpty()) {
            if (!ValidationUtils.isValid(relativeWaterResistanceAfterLoad_base.getText())) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeWaterResistanceAfterLoad_base(Integer.parseInt(relativeWaterResistanceAfterLoad_base.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeWaterResistanceAfterLoad_base(0);
            isEmpty = true;
        }

        if (!relativeBlottingTimeAfterLoad_base.getText().isEmpty()) {
            if (!ValidationUtils.isValid(relativeBlottingTimeAfterLoad_base.getText())) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeBlottingTimeAfterLoad_base(Integer.parseInt(relativeBlottingTimeAfterLoad_base.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeBlottingTimeAfterLoad_base(0);
            isEmpty = true;
        }

        if (!waterproofFunctionResource_base.getText().isEmpty()) {
            if (!ValidationUtils.isValid(waterproofFunctionResource_base.getText())) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getReliabilityFunction().setWaterproofFunctionResource_base(Integer.parseInt(waterproofFunctionResource_base.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setWaterproofFunctionResource_base(0);
            isEmpty = true;
        }

        //col 4
        if (!relativeBlottingPressureAfterLoad_weight.getText().isEmpty()) {
            if (!ValidationUtils.isValid(relativeBlottingPressureAfterLoad_weight.getText())) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeBlottingPressureAfterLoad_weight(Integer.parseInt(relativeBlottingPressureAfterLoad_weight.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeBlottingPressureAfterLoad_weight(0);
            isEmpty = true;
        }

        if (!relativeWaterResistanceAfterLoad_weight.getText().isEmpty()) {
            if (!ValidationUtils.isValid(relativeWaterResistanceAfterLoad_weight.getText())) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeWaterResistanceAfterLoad_weight(Integer.parseInt(relativeWaterResistanceAfterLoad_weight.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeWaterResistanceAfterLoad_weight(0);
            isEmpty = true;
        }

        if (!relativeBlottingTimeAfterLoad_weight.getText().isEmpty()) {
            if (!ValidationUtils.isValid(relativeBlottingTimeAfterLoad_weight.getText())) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeBlottingTimeAfterLoad_weight(Integer.parseInt(relativeBlottingTimeAfterLoad_weight.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setRelativeBlottingTimeAfterLoad_weight(0);
            isEmpty = true;
        }

        if (!waterproofRealizationCriteriaAfterLoad_weight.getText().isEmpty()) {
            if (!ValidationUtils.isValid(waterproofRealizationCriteriaAfterLoad_weight.getText())) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getReliabilityFunction().setWaterproofRealizationCriteriaAfterLoad_weight(Integer.parseInt(waterproofRealizationCriteriaAfterLoad_weight.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setWaterproofRealizationCriteriaAfterLoad_weight(0);
            isEmpty = true;
        }

        if (!waterproofFunctionResource_weight.getText().isEmpty()) {
            if (!ValidationUtils.isValid(waterproofFunctionResource_weight.getText())) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getReliabilityFunction().setWaterproofFunctionResource_weight(Integer.parseInt(waterproofFunctionResource_weight.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setWaterproofFunctionResource_weight(0);
            isEmpty = true;
        }

        //equipment
        if (equipment.getText() != null) {
            allValues.getCreateMaterialDto().getReliabilityFunction().setEquipment(equipment.getText());
        } else {
            allValues.getCreateMaterialDto().getReliabilityFunction().setEquipment(null);
            isEmpty = true;

        }

        return new boolean[]{isEmpty, isValid};
    }

    @FXML
    @SneakyThrows()
    void back_btn_clicked(MouseEvent event) {
        validateAndSetData();
        allValues.setLastCreateMaterialComponent(Component.HOMEOSTASIS_TABLE);
        ComponentUtil.mount(Component.HOMEOSTASIS_TABLE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @FXML
    @SneakyThrows()
    void next_btn_clicked(MouseEvent event) {
        boolean[] checkErrors = validateAndSetData();
        if (checkErrors[0]) {
            allValues.setLastCreateMaterialComponent(Component.RELIABILITY_TABLE);
            AlertUtil.show("Вы не заполнили все поля", "Закройте это окно и дозаполните всё необходимые поля", allValues.getRootStage());
        } else if (!checkErrors[1]) {
            allValues.setLastCreateMaterialComponent(Component.RELIABILITY_TABLE);
            AlertUtil.show("Вы ввели некорректные значения", "Закройте это окно и проверьте правильность введенных значений", allValues.getRootStage());
        } else {
            allValues.setLastCreateMaterialComponent(Component.ESTIMATION_TABLE);
            ComponentUtil.mount(Component.ESTIMATION_TABLE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //экспериментальные значения
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
