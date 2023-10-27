package com.example.project_for_university.controllers.material;

import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.enums.Component;
import com.example.project_for_university.providers.DataProvider;
import com.example.project_for_university.utils.ComponentUtil;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SConditionController implements DataProvider {

    private AllValues allValues;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private HBox btn_back;

    @FXML
    private HBox btn_next;

    @FXML
    private TextField homeo_capacity_inp;

    @FXML
    private Slider homeo_scroll;

    @FXML
    private TextField homeo_scroll_inp;

    @FXML
    private TextField homeo_square_inp;

    @FXML
    private TextField homeo_weight_inp;

    @FXML
    private TextField rel_implCriteria_inp;

    @FXML
    private TextField rel_pressure_inp;

    @FXML
    private TextField rel_time_inp;

    @FXML
    private TextField rel_watRes_inp;

    @FXML
    private ComboBox<String> water_combo;

    @FXML
    private TextField water_inp_press;

    @FXML
    private Slider water_scroll;

    @FXML
    private TextField water_scroll_inp;

    @FXML
    private TextField water_time_protect;

    @Override
    public void setData(AllValues allValues) {
        this.allValues = allValues;
    }

    @FXML
    void btn_back_clicked(MouseEvent event) throws IOException {
        ComponentUtil.mount(Component.CONDITION_1, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @FXML
    void btn_next_clicked(MouseEvent event) throws IOException {
        ComponentUtil.mount(Component.WATERPROOF_TABLE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @FXML
    void homeo_scroll_dragged(MouseEvent event) {
        homeo_scroll_inp.setText(String.valueOf((int) homeo_scroll.getValue()));
    }

    @FXML
    void water_scroll_dragged(MouseEvent event) {
        water_scroll_inp.setText(String.valueOf((int) water_scroll.getValue()));
    }
}
