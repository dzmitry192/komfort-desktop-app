package com.example.project_for_university.controllers.user;

import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.forBackend.create.CreateUserDto;
import com.example.project_for_university.dto.forBackend.entity.UserEntity;
import com.example.project_for_university.enums.Component;
import com.example.project_for_university.providers.DataProvider;
import com.example.project_for_university.service.AuthService;
import com.example.project_for_university.service.models.UserModel;
import com.example.project_for_university.utils.ComponentUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import lombok.Data;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Data
public class SignupController implements DataProvider {

    private AllValues allValues = new AllValues();
    private AuthService authService = new AuthService();

    @FXML
    private Button login_btn;

    @FXML
    private TextField email_tf;

    @FXML
    private TextField fio_tf;

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
    void signup_btn_clicked(MouseEvent event) throws IOException, ExecutionException, InterruptedException {
        String fio = fio_tf.getText();
        String email = email_tf.getText();
        String password = password_tf.getText();

        status_lbl.setTextFill(Color.RED);
        if(fio.isEmpty() || email.isEmpty() || password.isEmpty()) {
            status_lbl.setText("Ошибка! Вы не заполнили все поля");
        } else if(password.length() < 4) {
            status_lbl.setText("Минимальная длина пароля 6 символов");
        } else {
            UserModel userModel = authService.signupThread(new CreateUserDto(fio, email, password));
            if(userModel.isError()) {
                status_lbl.setText(userModel.getErrorMessage());
            } else {
                allValues.setUser(userModel.getUser());
                status_lbl.setTextFill(Color.GREEN);
                status_lbl.setText("Регистрация прошла успешно");
            }
        }
    }

    @FXML
    void login_btn_clicked(MouseEvent event) throws IOException {
        ComponentUtil.mount(Component.LOGIN, allValues.getContentPanes().getMainContentPane(), allValues);
    }
}

