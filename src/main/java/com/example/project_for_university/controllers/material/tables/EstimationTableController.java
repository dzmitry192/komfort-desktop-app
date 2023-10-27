package com.example.project_for_university.controllers.material.tables;

import com.example.project_for_university.enums.Component;
import com.example.project_for_university.utils.ComponentUtil;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.providers.DataProvider;
import lombok.SneakyThrows;

public class EstimationTableController implements DataProvider {
    private AllValues allValues;

    @FXML
    private HBox back_btn;

    @FXML
    private HBox next_btn;

    @Override
    public void setData(AllValues allValues) {
        this.allValues = allValues;
    }

    @FXML
    @SneakyThrows()
    void back_btn_clicked(MouseEvent event) {
        allValues.setLastCreateMaterialComponent(Component.RELIABILITY_TABLE);
        ComponentUtil.mount(Component.RELIABILITY_TABLE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @FXML
    @SneakyThrows()
    void next_btn_clicked(MouseEvent event) {
        allValues.setLastCreateMaterialComponent(Component.MATERIAL_INFO);
        ComponentUtil.mount(Component.MATERIAL_INFO, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

}
