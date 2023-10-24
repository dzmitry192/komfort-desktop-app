package com.example.project_for_university.controllers.user;

import java.io.IOException;

import com.example.project_for_university.controllers.filter.FilterController;
import com.example.project_for_university.controllers.material.FConditionController;
import com.example.project_for_university.controllers.material.MaterialInfoController;
import com.example.project_for_university.controllers.user.admin.AdminController;
import com.example.project_for_university.dto.AllValues;
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ControllerUtils.firstCondRoute));
        Scene scene = new Scene(fxmlLoader.load());

        ((FConditionController) fxmlLoader.getController()).setData(allValues);
        ((FConditionController) fxmlLoader.getController()).getValuesForCB();

        Stage window = (Stage) back_btn.getScene().getWindow();
        window.setTitle("Ввод первых условий");
        window.setScene(scene);
        window.setFullScreen(true);
        window.show();
    }

    @FXML
    void admin_btn_clicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ControllerUtils.adminRoute));
        Scene scene = new Scene(fxmlLoader.load());

        ((AdminController) fxmlLoader.getController()).setData(allValues);

        Stage window = (Stage) back_btn.getScene().getWindow();
        window.setTitle("Администратор");
        window.setScene(scene);
        window.setFullScreen(true);
        window.show();
    }

    @FXML
    void exit_btn_clicked(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void search_btn_clicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(ChooseOpController.class.getResource(ControllerUtils.filterRoute));
        Scene scene = new Scene(loader.load());

        ((FilterController) loader.getController()).setData(allValues);

        Stage window = (Stage) back_btn.getScene().getWindow();
        window.setScene(scene);
        window.setFullScreen(true);
        window.show();
    }

    @FXML
    void back_btn_clicked(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(ChooseOpController.class.getResource(ControllerUtils.loginRoute));
        Scene scene = new Scene(loader.load());

        ((LoginController) loader.getController()).setData(allValues);

        Stage window = (Stage) back_btn.getScene().getWindow();
        window.setScene(scene);
        window.setFullScreen(true);
        window.show();
    }

    public void setData(AllValues allValues) throws IOException {
        this.allValues = allValues;
        if(allValues.getUser().isAdmin()) {
            admin_btn.setVisible(true);
            new AdminController().setData(allValues);
        }
    }
}