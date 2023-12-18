package com.example.project_for_university.controllers.material.tables;

import com.example.project_for_university.dto.forBackend.calculate.CalculateEstimationDto;
import com.example.project_for_university.dto.forBackend.create.CreateMaterialDto;
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

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutionException;

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
            EventHandler<MouseEvent> clickHandler = event -> {
                System.out.println("estimation sideBartBtn");
                saveDataFromInputs();
            };
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, clickHandler);
            allValues.getSideBarButtonsEventHandlers().add(clickHandler);
        }
    }

    private void fillEstimationTable() {
        CalculateEstimationDto estimationDto = allValues.getCreateMaterialDto().getEstimation();

        if (estimationDto != null) {
            if (estimationDto.getWaterproofFunction_weight() != -1) {
                waterproofFunction_weight.setText(String.valueOf(estimationDto.getWaterproofFunction_weight()));
            }
            if (estimationDto.getHomeostasisFunction_weight() != -1) {
                homeostasisFunction_weight.setText(String.valueOf(estimationDto.getHomeostasisFunction_weight()));
            }
            if (estimationDto.getReliabilityFunction_weight() != -1) {
                reliabilityFunction_weight.setText(String.valueOf(estimationDto.getReliabilityFunction_weight()));
            }
        }
    }

    private void saveDataFromInputs() {
        if (!waterproofFunction_weight.getText().isEmpty() && ValidationUtils.isValid(waterproofFunction_weight.getText())) {
            allValues.getCreateMaterialDto().getEstimation().setWaterproofFunction_weight(Double.parseDouble(waterproofFunction_weight.getText()));
        } else {
            allValues.getCreateMaterialDto().getEstimation().setWaterproofFunction_weight(-1);
        }

        if (!homeostasisFunction_weight.getText().isEmpty() && ValidationUtils.isValid(homeostasisFunction_weight.getText())) {
            allValues.getCreateMaterialDto().getEstimation().setHomeostasisFunction_weight(Double.parseDouble(homeostasisFunction_weight.getText()));
        } else {
            allValues.getCreateMaterialDto().getEstimation().setHomeostasisFunction_weight(-1);
        }

        if (!reliabilityFunction_weight.getText().isEmpty() && ValidationUtils.isValid(reliabilityFunction_weight.getText())) {
            allValues.getCreateMaterialDto().getEstimation().setReliabilityFunction_weight(Double.parseDouble(reliabilityFunction_weight.getText()));
        } else {
            allValues.getCreateMaterialDto().getEstimation().setReliabilityFunction_weight(-1);
        }
    }

    private boolean validateAndSetData() {
        CalculateEstimationDto estimationDto = allValues.getCreateMaterialDto().getEstimation();

        try {
            //проверка весомостей
            ArrayList<String> inpWeights = new ArrayList<>(List.of(
                    waterproofFunction_weight.getText(),
                    homeostasisFunction_weight.getText(),
                    reliabilityFunction_weight.getText()
            ));
            if (inpWeights.stream().anyMatch(String::isEmpty)) {
                throw new NoSuchElementException();
            } else if (inpWeights.stream().noneMatch(ValidationUtils::isValid)) {
                throw new IllegalArgumentException();
            } else {
                estimationDto.setWaterproofFunction_weight(Double.parseDouble(waterproofFunction_weight.getText()));
                estimationDto.setHomeostasisFunction_weight(Double.parseDouble(homeostasisFunction_weight.getText()));
                estimationDto.setReliabilityFunction_weight(Double.parseDouble(reliabilityFunction_weight.getText()));
            }

            //проверка суммы весомостей
            ArrayList<Double> weights = new ArrayList<>(List.of(
                    estimationDto.getWaterproofFunction_weight(),
                    estimationDto.getHomeostasisFunction_weight(),
                    estimationDto.getReliabilityFunction_weight()
            ));
            Optional<Double> sumWeights = weights.stream().reduce(Double::sum);
            if(sumWeights.isPresent()) {
                if(sumWeights.get() != 1) {
                    throw new IllegalArgumentException();
                }
            }

            allValues.getCreateMaterialDto().setEstimation(estimationDto);

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
            allValues.getCreateMaterialDto().setEstimation(null);
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
        allValues.setLastCreateMaterialComponent(Component.RELIABILITY_TABLE);
        ComponentUtil.mount(Component.RELIABILITY_TABLE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @FXML
    @SneakyThrows()
    void next_btn_clicked(MouseEvent event) {
        if (validateAndSetData()) {
            allValues.setLastCreateMaterialComponent(Component.MATERIAL_INFO);
            ComponentUtil.mount(Component.MATERIAL_INFO, allValues.getContentPanes().getLoggedInStackPane(), allValues);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        waterproofFunction_weight.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        homeostasisFunction_weight.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        reliabilityFunction_weight.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
    }
}