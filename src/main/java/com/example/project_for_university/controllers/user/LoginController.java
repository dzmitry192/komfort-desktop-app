package com.example.project_for_university.controllers.user;

import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.forBackend.LoginDto;
import com.example.project_for_university.enums.Component;
import com.example.project_for_university.providers.DataProvider;
import com.example.project_for_university.service.AuthService;
import com.example.project_for_university.service.ReturnAllTypesService;
import com.example.project_for_university.service.models.ReturnAllTypesModel;
import com.example.project_for_university.service.models.UserModel;
import com.example.project_for_university.utils.AlertUtil;
import com.example.project_for_university.utils.ComponentUtil;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lombok.Data;

@Data
public class LoginController implements DataProvider {
    private AllValues allValues = new AllValues();
    private AuthService authService = new AuthService();
    private ReturnAllTypesService allTypesService = new ReturnAllTypesService();

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

    @Override
    public void setData(AllValues allValues) {
        this.allValues = allValues;
    }

    @FXML
    void login_btn_clicked(MouseEvent event) throws IOException, ExecutionException, InterruptedException {
        String email = email_tf.getText();
        String password = password_tf.getText();

//        allValues = LoaderUtil.showModal(allValues.getRootStage(), allValues);

        if (email.isEmpty() || password.isEmpty()) {
            status_lbl.setText("Все поля должны быть заполнены");
        } else {
            UserModel userModel = authService.loginThread(new LoginDto(email, password));
            if(userModel.isError()) {
                status_lbl.setText(userModel.getErrorMessage());
            } else {
                allValues.setUser(userModel.getUser());
                ReturnAllTypesModel allTypesModel = allTypesService.getAllTypesThread(email, password);
                if(allTypesModel.isError()) {
                    status_lbl.setText("Сервер временно недоступен, повторите попытку позже");
                } else {
                    allValues.setReturnAllTypesDto(allTypesModel.getReturnAllTypesDto());
                    ComponentUtil.mount(Component.LOGGED_IN, allValues.getContentPanes().getMainContentPane(), allValues);
                }
            }
//            LoaderUtil.closeModal(allValues.getLoaderStage());
        }
    }

    @FXML
    void signup_btn_clicked(MouseEvent event) throws IOException {
        ComponentUtil.mount(Component.SIGNUP, allValues.getContentPanes().getMainContentPane(), allValues);
    }
}