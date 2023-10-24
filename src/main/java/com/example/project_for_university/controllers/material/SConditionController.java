package com.example.project_for_university.controllers.material;

import com.example.project_for_university.controllers.user.LoginController;
import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.utils.ControllerUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SConditionController extends Node {

    private AllValues allValues;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_back;

    @FXML
    private Button btn_next;

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

    @FXML
    void btn_back_clicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource(ControllerUtils.firstCondRoute));
        Scene scene = new Scene(fxmlLoader.load());

        ((FConditionController) fxmlLoader.getController()).setData(allValues);

        Stage window = (Stage) btn_back.getScene().getWindow();
        window.setScene(scene);
        window.show();
        ((SConditionController) fxmlLoader.getController()).setData(allValues);
    }

    @FXML
    void btn_next_clicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource(ControllerUtils.firstCondRoute));
        Scene scene = new Scene(fxmlLoader.load());

        ((ResultTableController) fxmlLoader.getController()).setData(allValues);

        Stage window = (Stage) btn_back.getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    void homeo_scroll_dragged(MouseEvent event) {
        homeo_scroll_inp.setText(String.valueOf((int) homeo_scroll.getValue()));
    }

    @FXML
    void water_scroll_dragged(MouseEvent event) {
        water_scroll_inp.setText(String.valueOf((int) water_scroll.getValue()));
    }

    public void setData(AllValues allValues) {
        this.allValues = allValues;
    }
}
