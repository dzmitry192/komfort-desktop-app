package com.example.project_for_university.controllers.material;

import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.MaterialInformationDto;
import com.example.project_for_university.dto.forBackend.create.CreateMaterialDto;
import com.example.project_for_university.dto.forBackend.entity.ConditionEntity;
import com.example.project_for_university.dto.forBackend.entity.LayerEntity;
import com.example.project_for_university.dto.forBackend.entity.UserEntity;
import com.example.project_for_university.dto.forBackend.entity.WashingEntity;
import com.example.project_for_university.dto.forBackend.entity.types.LayerTypeEntity;
import com.example.project_for_university.dto.forBackend.entity.types.PartialMaterialEntity;
import com.example.project_for_university.dto.forBackend.entity.types.PhysicalActivityTypeEntity;
import com.example.project_for_university.dto.forBackend.entity.types.WashingTypeEntity;
import com.example.project_for_university.enums.Component;
import com.example.project_for_university.providers.DataProvider;
import com.example.project_for_university.utils.AlertUtil;
import com.example.project_for_university.utils.ComponentUtil;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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
    private static final List<File> files = new ArrayList<>();
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

        for (int i = 0; i < allValues.getSideBarButtonsEventHandlers().size(); i++) {
            allValues.getSideBarButtons().get(i).removeEventHandler(MouseEvent.MOUSE_CLICKED, allValues.getSideBarButtonsEventHandlers().get(i));
        }
        allValues.getSideBarButtonsEventHandlers().clear();

        for (var button : allValues.getSideBarButtons()) {
            EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("createMaterial sideBartBtn");
                    // тут сетишь значения условий в allValues
                }
            };
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, clickHandler);
            allValues.getSideBarButtonsEventHandlers().add(clickHandler);
        }
    }

    @SneakyThrows
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
            allValues.setCreateMaterialDto(new CreateMaterialDto());

            allValues.setLastCreateMaterialComponent(null);

            ConditionEntity condition = new ConditionEntity(1, true, 1, 1, 1, 1, 1, 1, 1, 1, null, new WashingEntity(1, 1, 1, 1, true, new WashingTypeEntity(1, "washing")), null, new PhysicalActivityTypeEntity(1, "act", "desc"));
            PartialMaterialEntity newMaterial = new PartialMaterialEntity(1, "newMaterial", "newMaterial desk description description description description description", "manufacturer", 10, condition, new LayerEntity[] {new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[] {"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false));

            ComponentUtil.mountMaterialDetails(allValues.getContentPanes().getLoggedInStackPane(), allValues, newMaterial);
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
