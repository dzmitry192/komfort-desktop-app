package com.example.project_for_university.controllers.material;

import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.forBackend.create.CreateMaterialDto;
import com.example.project_for_university.dto.forBackend.entity.*;
import com.example.project_for_university.dto.forBackend.entity.types.*;
import com.example.project_for_university.enums.Component;
import com.example.project_for_university.providers.DataProvider;
import com.example.project_for_university.utils.AlertUtil;
import com.example.project_for_university.utils.ComponentUtil;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
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
    private static final List<File> images = new ArrayList<>();
    @FXML
    private TextArea description;

    @FXML
    private TextField name;

    @FXML
    private HBox upload_photo_btn;

    @FXML
    private HBox back_btn;

    @FXML
    private HBox next_btn;

    @Override
    public void setData(AllValues allValues) {
        this.allValues = allValues;

        fillMaterialInfo();

        for (int i = 0; i < allValues.getSideBarButtonsEventHandlers().size(); i++) {
            allValues.getSideBarButtons().get(i).removeEventHandler(MouseEvent.MOUSE_CLICKED, allValues.getSideBarButtonsEventHandlers().get(i));
        }
        allValues.getSideBarButtonsEventHandlers().clear();

        for (var button : allValues.getSideBarButtons()) {
            EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println("createMaterial sideBartBtn");
                    validateAndSetData();
                }
            };
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, clickHandler);
            allValues.getSideBarButtonsEventHandlers().add(clickHandler);
        }
    }

    @SneakyThrows
    @FXML
    void back_btn_clicked(MouseEvent event) throws IOException {
        validateAndSetData();
        allValues.setLastCreateMaterialComponent(Component.ESTIMATION_TABLE);
        ComponentUtil.mount(Component.ESTIMATION_TABLE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    private void fillMaterialInfo() {
        CreateMaterialDto materialDto = allValues.getCreateMaterialDto();

        if(materialDto.getMaterial().getDescription() != null) {
            description.setText(materialDto.getMaterial().getDescription());
        }
        if(materialDto.getMaterial().getName() != null) {
            name.setText(materialDto.getMaterial().getName());
        }
        if(materialDto.getImages() != null) {
            images.addAll(List.of(materialDto.getImages()));
        }
    }

    private boolean validateAndSetData() {
        boolean isEmpty = false;

        if(name.getText().isEmpty()) {
            allValues.getCreateMaterialDto().getMaterial().setName(null);
            isEmpty = true;
        } else {
            allValues.getCreateMaterialDto().getMaterial().setName(name.getText());
        }
        if(description.getText().isEmpty()) {
            allValues.getCreateMaterialDto().getMaterial().setDescription(null);
            isEmpty = true;
        } else {
            allValues.getCreateMaterialDto().getMaterial().setDescription(description.getText());
        }
        if(images.isEmpty()) {
            allValues.getCreateMaterialDto().setImages(null);
            isEmpty = true;
        } else {
            allValues.getCreateMaterialDto().setImages(images.toArray(File[]::new));
        }

        return isEmpty;
    }

    @FXML
    @SneakyThrows()
    void next_btn_clicked(MouseEvent event) {
        if(validateAndSetData()) {
            AlertUtil.show("Заполните все поля", "Закройте это окно и дозаполните всё необходимые поля", allValues.getRootStage());
        } else {
            //запрос на сохранение материала

            //потом обнуление данных
            allValues.setCreateMaterialDto(new CreateMaterialDto());
            allValues.setLastCreateMaterialComponent(null);

            ConditionEntity condition = new ConditionEntity(1, true, 1, 1, 1, 1, 1, 1, 1, 1, null, new WashingEntity(1, 1, 1, 1, true, new WashingTypeEntity(1, "washing")), null, new PhysicalActivityTypeEntity(1, "act", "desc"));
            PartialMaterialEntity newMaterial = new PartialMaterialEntity(1, "newMaterial", "newMaterial desk description description description description description", "manufacturer", 10, condition, new LayerEntity[] {new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[] {"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false), new ProductionMethodEntity(1, "name"), new MembraneLayerPolymerTypeEntity(1, "name"), new GlueTypeEntity(1, "name"));

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
            images.addAll(selectedFiles);
            allValues.getCreateMaterialDto().setImages(images.toArray(File[]::new));
        }
    }

    @FXML
    void initialize() {
        name.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getControlNewText().length() >= 40) {
                AlertUtil.show("Превышен лимит", "Максимальная длинна названия - 40", allValues.getRootStage());
                return null;
            }
            return change;
        }));

        description.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getControlNewText().length() >= 800) {
                AlertUtil.show("Превышен лимит", "Максимальная примечания названия - 800", allValues.getRootStage());
                return null;
            }
            return change;
        }));
    }
}
