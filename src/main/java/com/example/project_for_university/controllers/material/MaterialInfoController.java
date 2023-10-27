package com.example.project_for_university.controllers.material;

import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.MaterialInformationDto;
import com.example.project_for_university.enums.Component;
import com.example.project_for_university.providers.DataProvider;
import com.example.project_for_university.utils.AlertUtil;
import com.example.project_for_university.utils.ComponentUtil;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MaterialInfoController implements DataProvider {
    private AllValues allValues;
    private static List<File> files = new ArrayList<>();
    @FXML
    private TextArea comments_area;

    @FXML
    private TextField name_field;

    @FXML
    private HBox upload_photo_btn;

    @FXML
    private HBox back_btn;

    @FXML
    private HBox next_btn;

    @Override
    public void setData(AllValues allValues) {
        this.allValues = allValues;
    }

    @FXML
    void back_btn_clicked(MouseEvent event) throws IOException {
        allValues.setLastCreateMaterialComponent(Component.ESTIMATION_TABLE);
        ComponentUtil.mount(Component.ESTIMATION_TABLE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @FXML
    @SneakyThrows()
    void next_btn_clicked(MouseEvent event) {
        if(name_field.getText().isEmpty() || comments_area.getText().isEmpty()) {
            AlertUtil.show("Заполните все поля", "Закройте это окно и дозаполните всё необходимые поля", allValues.getRootStage());
        } else {
            allValues.setLastCreateMaterialComponent(null);
            ComponentUtil.mount(Component.MATERIAL_DETAILS, allValues.getContentPanes().getLoggedInStackPane(), allValues);
        }
    }

    @FXML
    void upload_photo_btn_clicked(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файлы");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))); // начальная директория
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.tif"));
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(stage); // stage - текущее окно приложения
        if (selectedFiles.size() > 5) {
            AlertUtil.show("Превышен лимит", "Максимальное количетсов фотографий - 5", allValues.getRootStage());
        } else {

        }
    }

    @FXML
    void initialize() {

        name_field.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getControlNewText().length() >= 40) {
                AlertUtil.show("Превышен лимит", "Максимальная длинна названия - 40", allValues.getRootStage());
                return null;
            }
            return change;
        }));

        comments_area.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getControlNewText().length() >= 800) {
                AlertUtil.show("Превышен лимит", "Максимальная примечания названия - 800", allValues.getRootStage());
                return null;
            }
            return change;
        }));
    }
}
