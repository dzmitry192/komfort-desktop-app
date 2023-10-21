package com.example.project_for_university.controllers.user;

import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.service.SignupService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.Data;

import java.io.IOException;

@Data
public class SignupController extends Node {

    private AllValues allValues = new AllValues();
    private SignupService signupService = new SignupService();

    @FXML
    private Button btn_back_reg;

    @FXML
    private Button btn_signup_reg;

    @FXML
    private Label label_signup;

    @FXML
    private TextField text_field_email_reg;

    @FXML
    private TextField text_field_fio;

    @FXML
    private TextField text_field_pass_reg;

    @FXML
    void btn_back_reg_click(MouseEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("/com/example/project_for_university/fxml/user/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        ((LoginController) fxmlLoader.getController()).setData(allValues);
    }

    @FXML
    void btn_signup_reg_click(MouseEvent event) throws IOException {
        String fio = text_field_fio.getText();
        String email = text_field_email_reg.getText();
        String password = text_field_pass_reg.getText();

        signupService.signup(fio, email, password, label_signup, allValues);
    }
}

