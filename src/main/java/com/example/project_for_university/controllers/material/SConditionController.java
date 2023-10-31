package com.example.project_for_university.controllers.material;

import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.enums.Component;
import com.example.project_for_university.providers.DataProvider;
import com.example.project_for_university.utils.ComponentUtil;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class SConditionController implements DataProvider, Initializable {

    private AllValues allValues;

    @FXML
    private HBox btn_back;

    @FXML
    private HBox btn_next;

    @FXML
    private TextField homeo_capacity_inp;

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
    private Slider hydrostaticPressureIncreaseSpeed_slider;

    @FXML
    private TextField hydrostaticPressureIncreaseSpeed_inp;

    @FXML
    private Slider avgAirSpeed_slider;

    @FXML
    private TextField avgAirSpeed_inp;


    @Override
    public void setData(AllValues allValues) throws IOException {
        this.allValues = allValues;

        for (int i = 0; i < allValues.getSideBarButtonsEventHandlers().size(); i++) {
            allValues.getSideBarButtons().get(i).removeEventHandler(MouseEvent.MOUSE_CLICKED, allValues.getSideBarButtonsEventHandlers().get(i));
        }
        allValues.getSideBarButtonsEventHandlers().clear();

        for (var button : allValues.getSideBarButtons()) {
            EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("secondCondition sideBartBtn");
                    // тут сетишь значения условий в allValues
                }
            };
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, clickHandler);
            allValues.getSideBarButtonsEventHandlers().add(clickHandler);
        }
    }

    @FXML
    void btn_back_clicked(MouseEvent event) throws IOException, ExecutionException, InterruptedException {
        allValues.setLastCreateMaterialComponent(Component.CONDITION_1);
        ComponentUtil.mount(Component.CONDITION_1, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @FXML
    void btn_next_clicked(MouseEvent event) throws IOException, ExecutionException, InterruptedException {
        allValues.setLastCreateMaterialComponent(Component.WATERPROOF_TABLE);
        ComponentUtil.mount(Component.WATERPROOF_TABLE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        avgAirSpeed_slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            avgAirSpeed_inp.setText(String.valueOf((int) avgAirSpeed_slider.getValue()));
        });

        hydrostaticPressureIncreaseSpeed_slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            hydrostaticPressureIncreaseSpeed_inp.setText(String.valueOf((int) hydrostaticPressureIncreaseSpeed_slider.getValue()));
        });
    }
}
