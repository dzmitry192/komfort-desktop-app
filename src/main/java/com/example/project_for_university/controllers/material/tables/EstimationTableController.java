package com.example.project_for_university.controllers.material.tables;

import com.example.project_for_university.dto.forBackend.calculate.CalculateEstimationDto;
import com.example.project_for_university.enums.Component;
import com.example.project_for_university.utils.AlertUtil;
import com.example.project_for_university.utils.ComponentUtil;
import com.example.project_for_university.utils.ValidationUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.providers.DataProvider;
import lombok.SneakyThrows;

import java.net.URL;
import java.util.ResourceBundle;

public class EstimationTableController implements DataProvider, Initializable {
    private AllValues allValues;

    @FXML
    private HBox back_btn;

    @FXML
    private HBox next_btn;

    @FXML
    private TextField homeostasisFunction_weight;

    @FXML
    private TextField reliabilityFunction_weight;

    @FXML
    private TextField waterproofFunction_weight;

    @Override
    public void setData(AllValues allValues) {
        this.allValues = allValues;

        if (allValues.getCreateMaterialDto().getEstimation() == null) {
            allValues.getCreateMaterialDto().setEstimation(new CalculateEstimationDto());
        }

        fillEstimationTable();

        for (int i = 0; i < allValues.getSideBarButtonsEventHandlers().size(); i++) {
            allValues.getSideBarButtons().get(i).removeEventHandler(MouseEvent.MOUSE_CLICKED, allValues.getSideBarButtonsEventHandlers().get(i));
        }
        allValues.getSideBarButtonsEventHandlers().clear();

        for (var button : allValues.getSideBarButtons()) {
            EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("estimation sideBartBtn");
                    validateAndSetData();
                }
            };
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, clickHandler);
            allValues.getSideBarButtonsEventHandlers().add(clickHandler);
        }
    }

    private void fillEstimationTable() {
        CalculateEstimationDto estimationDto = allValues.getCreateMaterialDto().getEstimation();

        if (estimationDto != null) {
            if (estimationDto.getWaterproofFunction_weight() != 0) {
                waterproofFunction_weight.setText(String.valueOf(estimationDto.getWaterproofFunction_weight()));
            }
            if (estimationDto.getHomeostasisFunction_weight() != 0) {
                homeostasisFunction_weight.setText(String.valueOf(estimationDto.getHomeostasisFunction_weight()));
            }
            if (estimationDto.getReliabilityFunction_weight() != 0) {
                reliabilityFunction_weight.setText(String.valueOf(estimationDto.getReliabilityFunction_weight()));
            }
        }
    }

    private boolean[] validateAndSetData() {
        boolean isEmpty = false;
        boolean isValid = true;

        if(!waterproofFunction_weight.getText().isEmpty()) {
            if(!ValidationUtils.isValid(waterproofFunction_weight.getText())) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getEstimation().setWaterproofFunction_weight(Integer.parseInt(waterproofFunction_weight.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getEstimation().setWaterproofFunction_weight(0);
            isEmpty = true;
        }

        if(!homeostasisFunction_weight.getText().isEmpty()) {
            if(!ValidationUtils.isValid(homeostasisFunction_weight.getText())) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getEstimation().setHomeostasisFunction_weight(Integer.parseInt(homeostasisFunction_weight.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getEstimation().setHomeostasisFunction_weight(0);
            isEmpty = true;
        }

        if(!reliabilityFunction_weight.getText().isEmpty()) {
            if(!ValidationUtils.isValid(reliabilityFunction_weight.getText())) {
                isValid = false;
            } else {
                allValues.getCreateMaterialDto().getEstimation().setReliabilityFunction_weight(Integer.parseInt(reliabilityFunction_weight.getText()));
            }
        } else {
            allValues.getCreateMaterialDto().getEstimation().setReliabilityFunction_weight(0);
            isEmpty = true;
        }

        return new boolean[]{isEmpty, isValid};
    }

    @FXML
    @SneakyThrows()
    void back_btn_clicked(MouseEvent event) {
        validateAndSetData();
        allValues.setLastCreateMaterialComponent(Component.RELIABILITY_TABLE);
        ComponentUtil.mount(Component.RELIABILITY_TABLE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @FXML
    @SneakyThrows()
    void next_btn_clicked(MouseEvent event) {
        boolean[] checkErrors = validateAndSetData();
        if (checkErrors[0]) {
            allValues.setLastCreateMaterialComponent(Component.ESTIMATION_TABLE);
            AlertUtil.show("Вы не заполнили все поля", "Закройте это окно и дозаполните всё необходимые поля", allValues.getRootStage());
        } else if (!checkErrors[1]) {
            allValues.setLastCreateMaterialComponent(Component.ESTIMATION_TABLE);
            AlertUtil.show("Вы ввели некорректные значения", "Закройте это окно и проверьте правильность введенных значений", allValues.getRootStage());
        } else {
            allValues.setLastCreateMaterialComponent(Component.MATERIAL_INFO);
            ComponentUtil.mount(Component.MATERIAL_INFO, allValues.getContentPanes().getLoggedInStackPane(), allValues);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        waterproofFunction_weight.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
//        homeostasisFunction_weight.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
//        reliabilityFunction_weight.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
    }
}