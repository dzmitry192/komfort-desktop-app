package com.example.project_for_university.controllers.material;

import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.forBackend.entity.types.PartialMaterialEntity;
import com.example.project_for_university.dto.forBackend.update.UpdateMaterialDto;
import com.example.project_for_university.factory.ServiceFactory;
import com.example.project_for_university.providers.DataProvider;
import com.example.project_for_university.service.AllTypesService;
import com.example.project_for_university.service.MaterialService;
import com.example.project_for_university.service.models.UpdateMaterialResponse;
import com.example.project_for_university.utils.AlertUtil;
import com.example.project_for_university.utils.ComponentUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lombok.SneakyThrows;

import java.io.IOException;

public class UpdateMaterialController implements DataProvider {

    private AllValues allValues;

    private PartialMaterialEntity partialMaterialEntity;
    private final ServiceFactory serviceFactory = new ServiceFactory();
    private final AllTypesService allTypesService = new AllTypesService();

    @FXML
    private Text typeName_text;

    @FXML
    private HBox ok_btn;

    @FXML
    private HBox cancel_btn;

    @FXML
    private TextArea description_textArea;

    @FXML
    private VBox description_vbox;

    @FXML
    private TextField name_field;

    @FXML
    private TextField manufacturer_field;

    @Override
    public void setData(AllValues allValues) {
        this.allValues = allValues;
    }

    public void setPartialMaterialEntity(PartialMaterialEntity partialMaterialEntity) {
        this.partialMaterialEntity = partialMaterialEntity;

        typeName_text.setText("(" + partialMaterialEntity.getName()+ ")");
        name_field.setText(partialMaterialEntity.getName());
        description_textArea.setText(partialMaterialEntity.getDescription());
        manufacturer_field.setText(partialMaterialEntity.getManufacturer());
    }

    @SneakyThrows
    @FXML
    void ok_btn_clicked(MouseEvent event) throws IOException {
        if (name_field.getText().isEmpty()) {
            AlertUtil.show("Вы не заполнили поле названия", "Закройте окно и заполните нужное поле", allValues.getRootStage());
        } else if (description_textArea.getText().isEmpty()) {
            AlertUtil.show("Вы не заполнили поле описания", "Закройте окно и заполните нужное поле", allValues.getRootStage());
        } else if (manufacturer_field.getText().isEmpty()) {
            AlertUtil.show("Вы не заполнили поле производителя", "Закройте окно и заполните нужное поле", allValues.getRootStage());
        } else {
            if (!partialMaterialEntity.getName().equals(name_field.getText())
                    || !partialMaterialEntity.getDescription().equals(description_textArea.getText())
                    || !partialMaterialEntity.getManufacturer().equals(manufacturer_field.getText())
            ) {
                UpdateMaterialResponse updateMaterialResponse = MaterialService.INSTANCE.update(new UpdateMaterialDto(name_field.getText().trim(), description_textArea.getText().trim(), manufacturer_field.getText().trim()), partialMaterialEntity.getId(), allValues.getUser().getEmail(), allValues.getUser().getPassword());
                if(updateMaterialResponse.isError()) {
                    AlertUtil.show("Ошибка изменения материала", updateMaterialResponse.getMessage(), allValues.getRootStage());
                } else {
                    this.partialMaterialEntity = updateMaterialResponse.getMaterial();
                }
            }

            ComponentUtil.mountMaterialDetails(allValues.getContentPanes().getLoggedInStackPane(), allValues, partialMaterialEntity);
        }
    }

    @SneakyThrows
    @FXML
    void cancel_btn_clicked(MouseEvent event) throws IOException {
        ComponentUtil.mountMaterialDetails(allValues.getContentPanes().getLoggedInStackPane(), allValues, partialMaterialEntity);
    }
}
