package com.example.project_for_university.controllers.user;

import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.service.LoginService;
import com.example.project_for_university.utils.ControllerUtils;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Data;

@Data
public class LoginController extends Node {

    private AllValues allValues = new AllValues();
    private LoginService loginService = new LoginService();

    @FXML
    private TextField email_tf;

    @FXML
    private VBox main_box;

    @FXML
    private VBox sec_main_box;

    @FXML
    private Button login_btn;

    @FXML
    private TextField password_tf;

    @FXML
    private Button signup_btn;

    @FXML
    private Label status_lbl;

    @FXML
    void login_btn_clicked(MouseEvent event) throws IOException {
        String email = email_tf.getText();
        String password = password_tf.getText();

        if (email.isEmpty() || password.isEmpty()) {
            status_lbl.setText("Ошибка! Вы не заполнили все поля");
        } else {
            if(loginService.login(email, password, status_lbl, allValues)) {
                FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/com/example/project_for_university/fxml/user/choose-operation.fxml"));
                Scene scene = new Scene(fxmlLoader.load());

                ((ChooseOpController) fxmlLoader.getController()).setData(allValues);

                Stage window = (Stage) login_btn.getScene().getWindow();
                window.setScene(scene);
                window.setFullScreen(true);
                window.show();
            }
        }
    }

    @FXML
    void signup_btn_clicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource(ControllerUtils.signupRoute));
        Scene scene = new Scene(fxmlLoader.load());

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle("Регистрация");
        window.setScene(scene);
        window.setFullScreen(true);
        window.show();
    }

    public void setData(AllValues allValues) {
        this.allValues = allValues;
    }
}