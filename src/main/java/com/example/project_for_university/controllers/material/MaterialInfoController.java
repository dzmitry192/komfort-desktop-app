package com.example.project_for_university.controllers.material;

import com.example.project_for_university.controllers.user.ChooseOpController;
import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.MaterialInformationDto;
import com.example.project_for_university.dto.forBackend.MaterialInfoDto;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MaterialInfoController {
    private AllValues allValues;
    private static List<File> files = new ArrayList<>();
    private MaterialInformationDto materialInformationDto = new MaterialInformationDto();
    @FXML
    private TextArea comments_area;

    @FXML
    private TextField name_field;

    @FXML
    private ListView<String> photos_names_list;

    @FXML
    private Button upload_photo_btn;

    @FXML
    private Button back_btn;

    @FXML
    private Button next_btn;

    @FXML
    void back_btn_clicked(MouseEvent event) throws IOException {
        Stage stage = (Stage) back_btn.getScene().getWindow();
        stage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/project_for_university/fxml/user/choose-operation.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        ((ChooseOpController) fxmlLoader.getController()).setData(allValues);

        Stage window = (Stage) back_btn.getScene().getWindow();
        window.setScene(scene);
        window.setFullScreen(true);
        window.show();
    }

    @FXML
    void next_btn_clicked(MouseEvent event) throws IOException {
        if(name_field.getText().isEmpty() || comments_area.getText().isEmpty()) {
            throwAlert(4);
        } else {
            materialInformationDto.setName(name_field.getText());
            materialInformationDto.setDescription(comments_area.getText());
            this.allValues.setMaterialInformationDto(materialInformationDto);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/project_for_university/fxml/cond/condition-1.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            ((FConditionController) fxmlLoader.getController()).setData(allValues);
            ((FConditionController) fxmlLoader.getController()).getValuesForCB();

            Stage window = (Stage) back_btn.getScene().getWindow();
            window.setScene(scene);
            window.setFullScreen(true);
            window.show();
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
            throwAlert(1);
        } else if ((5 - photos_names_list.getItems().size()) >= selectedFiles.size()) {
            files.addAll(selectedFiles);
            ObservableList<String> filesNames = photos_names_list.getItems();
            filesNames.addAll(selectedFiles.stream().map(File::getName).toList());
            photos_names_list.setItems(filesNames);
//            materialInformationDto.setImages(files);
        } else {
            throwAlert(1);
        }
    }

    private Change throwAlert(int num) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Предупреждение");
        switch (num) {
            case 1: alert.setContentText("Максимальное количетсов фоток - 5!"); break;
            case 2: alert.setContentText("Максимальная длинна примечания - 800!"); break;
            case 3: alert.setContentText("Максимальная длинна названия - 40!"); break;
            case 4: alert.setContentText("Вы не заполнили все нужные поля!");
        }
        alert.showAndWait();
        return null;
    }

    public void setData(AllValues allValues) {
        this.allValues = allValues;
    }

    @FXML
    void initialize() {
        comments_area.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= 800 ? change : throwAlert(2)));
        name_field.setTextFormatter(new TextFormatter<String>(change ->
                change.getControlNewText().length() <= 40 ? change : throwAlert(3)));
    }
}
