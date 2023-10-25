package com.example.project_for_university.controllers.user;

import java.io.IOException;

import com.example.project_for_university.controllers.filter.FilterController;
import com.example.project_for_university.controllers.material.FConditionController;
import com.example.project_for_university.controllers.material.MaterialInfoController;
import com.example.project_for_university.controllers.material.SConditionController;
import com.example.project_for_university.controllers.user.admin.AdminController;
import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.enums.Page;
import com.example.project_for_university.utils.ControllerUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ChooseOpController extends Node {

    private AllValues allValues;

    @FXML
    private Button admin_btn;

    @FXML
    private Button back_btn;

    @FXML
    private Button calculation_btn;

    @FXML
    private Button exit_btn;

    @FXML
    private Button search_btn;

    @FXML
    private Text user_fio_lbl;

    @FXML
    void calculation_btn_clicked(MouseEvent event) throws IOException {
    }

    @FXML
    void admin_btn_clicked(MouseEvent event) throws IOException {
    }

    @FXML
    void exit_btn_clicked(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void search_btn_clicked(MouseEvent event) throws IOException {
    }

    @FXML
    void back_btn_clicked(MouseEvent event) throws IOException {
    }

    public void setData(AllValues allValues) throws IOException {
        this.allValues = allValues;
        if(allValues.getUser().isAdmin()) {
            admin_btn.setVisible(true);
            new AdminController().setData(allValues);
        }
    }
}