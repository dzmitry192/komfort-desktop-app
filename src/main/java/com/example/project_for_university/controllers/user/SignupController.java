package com.example.project_for_university.controllers.user;

import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.forBackend.create.CreateUserDto;
import com.example.project_for_university.enums.Component;
import com.example.project_for_university.providers.DataProvider;
import com.example.project_for_university.service.AuthService;
import com.example.project_for_university.service.models.AuthResponse;
import com.example.project_for_university.utils.ComponentUtil;
import com.example.project_for_university.utils.NodeUtils;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import lombok.Data;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

@Data
public class SignupController implements DataProvider, Initializable {

    private AllValues allValues = new AllValues();
    private AuthService authService = new AuthService();

    private boolean isPasswordShows = false;

    @FXML
    private Button login_btn;

    @FXML
    private TextField email_tf;

    @FXML
    private TextField fio_tf;

    @FXML
    private TextField password_tf;

    @FXML
    private PasswordField password_pf;

    @FXML
    private Button btn_showPassword;

    @FXML
    private FontAwesomeIconView icon_showPassword;

    @FXML
    private Button signup_btn;

    @FXML
    private VBox error_section;

    @FXML
    private Text error_text;

    @FXML
    private VBox success_section;

    @FXML
    private Text success_text;

    private void showSuccessSection(String message) {
        success_text.setText(message);
        NodeUtils.displayNode(success_section, true);
        NodeUtils.displayNode(error_section, false);
    }

    private void showErrorSection(String message) {
        error_text.setText(message);
        NodeUtils.displayNode(success_section, false);
        NodeUtils.displayNode(error_section, true);
    }

    private void hideStatusSections() {
        NodeUtils.displayNode(success_section, false);
        NodeUtils.displayNode(error_section, false);
    }

    @Override
    public void setData(AllValues allValues) {
        hideStatusSections();

        this.allValues = allValues;
    }

    @FXML
    void signup_btn_clicked(MouseEvent event) throws IOException, ExecutionException, InterruptedException {
        hideStatusSections();

        String fio = fio_tf.getText();
        String email = email_tf.getText();
        String password;
        if (isPasswordShows) {
            password = password_tf.getText();
        } else {
            password = password_pf.getText();
        }

        if(fio.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showErrorSection("Ошибка! Вы не заполнили все поля");
        } else if(password.length() < 8) {
            showErrorSection("Минимальная длина пароля 8 символов");
        } else {
            AuthResponse userModel = authService.signupThread(new CreateUserDto(fio, email, password));
            if(userModel.isError()) {
                showErrorSection(userModel.getMessage());
            } else {
                allValues.setUser(userModel.getUser());
                showSuccessSection("Регистрация завершена успешно");
            }
        }
    }

    @FXML
    void btn_showPassword_clicked(MouseEvent e) {
        if (this.isPasswordShows) {
            this.isPasswordShows = false;
            icon_showPassword.setGlyphName(FontAwesomeIcon.EYE.name());
            password_pf.setText(password_tf.getText());
            NodeUtils.displayNode(password_tf, false);
            NodeUtils.displayNode(password_pf, true);
        } else {
            this.isPasswordShows = true;
            icon_showPassword.setGlyphName(FontAwesomeIcon.EYE_SLASH.name());
            password_tf.setText(password_pf.getText());
            NodeUtils.displayNode(password_tf, true);
            NodeUtils.displayNode(password_pf, false);
        }
    }

    @FXML
    void login_btn_clicked(MouseEvent event) throws IOException, ExecutionException, InterruptedException {
        ComponentUtil.mount(Component.LOGIN, allValues.getContentPanes().getMainContentPane(), allValues);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NodeUtils.displayNode(password_tf, false);
    }
}

