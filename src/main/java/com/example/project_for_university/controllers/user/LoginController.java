package com.example.project_for_university.controllers.user;

import com.example.project_for_university.Main;
import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.service.LoginService;
import com.example.project_for_university.utils.ControllerUtils;
import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import javafx.scene.Node;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


@Data
public class LoginController extends Node {

    private AllValues allValues = new AllValues();
    private LoginService loginService = new LoginService();

    @FXML
    private Label lab_info;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField inp_email;

    @FXML
    private TextField inp_pass;

    @FXML
    private Button btn_login;

    @FXML
    private Button btn_signup;

    @FXML
    void btn_login_click(MouseEvent event) throws IOException {
        String email = inp_email.getText();
        String password = inp_pass.getText();

        loginService.login(email, password, lab_info, event, allValues);
    }

    @FXML
    void btn_signup_click(MouseEvent event) throws IOException {
        ControllerUtils.changeWindow(this, 2, event, "Регистрация");
    }

    public void setData(AllValues allValues) {
        this.allValues = allValues;
    }
}