package com.example.project_for_university.controllers.user;

import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.forBackend.LoginDto;
import com.example.project_for_university.dto.forBackend.MaterialInfoDto;
import com.example.project_for_university.dto.forBackend.calculate.CalculateEstimationDto;
import com.example.project_for_university.dto.forBackend.calculate.CalculateHomeostasisFunctionDto;
import com.example.project_for_university.dto.forBackend.calculate.CalculateReliabilityFunctionDto;
import com.example.project_for_university.dto.forBackend.calculate.CalculateWaterproofFunctionDto;
import com.example.project_for_university.dto.forBackend.create.CreateConditionDto;
import com.example.project_for_university.dto.forBackend.create.CreateLayerDto;
import com.example.project_for_university.dto.forBackend.create.CreateMaterialDto;
import com.example.project_for_university.dto.forBackend.create.CreateWashingDto;
import com.example.project_for_university.enums.Component;
import com.example.project_for_university.providers.DataProvider;
import com.example.project_for_university.service.AuthService;
import com.example.project_for_university.service.MaterialService;
import com.example.project_for_university.service.ReturnAllTypesService;
import com.example.project_for_university.service.models.UserModel;
import com.example.project_for_university.utils.ComponentUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Data
public class LoginController implements DataProvider {
    private AllValues allValues = new AllValues();
    private AuthService authService = new AuthService();
    private ReturnAllTypesService returnAllTypesService = new ReturnAllTypesService();

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
            if (userModel.isError()) {
                status_lbl.setText(userModel.getErrorMessage());
            } else {
                allValues.setUser(userModel.getUser());
                allValues.getAdminPanelInfo().setReturnAllTypesDto(returnAllTypesService.getAllTypesThread(allValues.getUser().getEmail(), allValues.getUser().getPassword()).getReturnAllTypesDto());
                ComponentUtil.mount(Component.LOGGED_IN, allValues.getContentPanes().getMainContentPane(), allValues);
            }
//            LoaderUtil.closeModal(allValues.getLoaderStage());
        }
    }

    @SneakyThrows
    @FXML
    void signup_btn_clicked(MouseEvent event) throws IOException {
        ComponentUtil.mount(Component.SIGNUP, allValues.getContentPanes().getMainContentPane(), allValues);
    }
}