package com.example.project_for_university.controllers.user;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.project_for_university.controllers.filter.MaterialController;
import com.example.project_for_university.controllers.material.FConditionController;
import com.example.project_for_university.controllers.material.MaterialInfoController;
import com.example.project_for_university.controllers.user.admin.AdminController;
import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.Material;
import com.example.project_for_university.utils.ControllerUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ChooseOpController extends Node implements Initializable {

    private AllValues allValues;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_calc;

    @FXML
    private Button btn_exit;

    @FXML
    private Button btn_back;

    @FXML
    private Button btn_search;

    @FXML
    private Button btn_admin;

    @FXML
    private Text user_fio_lbl;

    @FXML
    void btn_calc_clicked(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/project_for_university/fxml/cond/material-info.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        ((MaterialInfoController) fxmlLoader.getController()).setData(allValues);

        Stage window = new Stage();
        window.setTitle("Ифнформация о материале");
        window.setScene(scene);
        window.show();
    }

    @FXML
    void btn_admin_clicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/project_for_university/fxml/user/admin/admin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        ((AdminController) fxmlLoader.getController()).setData(allValues);

        Stage window = new Stage();
        window.setTitle("Администратор");
        window.setScene(scene);
        window.show();
    }

    @FXML
    void btn_exit_clicked(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void btn_search_clicked(MouseEvent event) throws IOException {
        ControllerUtils.changeWindow(this, 4 , event, "Поиск материалов");
    }

    @FXML
    void btn_back_clicked(MouseEvent event) throws IOException {
        ControllerUtils.changeWindow(this, 1, event, "Вход");
    }

    @Override()
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        user_fio_lbl.setText(allValues.getUser().getFio());
    }

// admin@yandex.by
// adminDlyaDimki9182_gay

    public void setData(AllValues allValues) throws IOException {
        this.allValues = allValues;
        if(allValues.getUser().isAdmin()) {
            btn_admin.setVisible(true);
            new AdminController().setData(allValues);
        }
    }
}