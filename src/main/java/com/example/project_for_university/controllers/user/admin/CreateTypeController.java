package com.example.project_for_university.controllers.user.admin;

import com.example.project_for_university.controllers.user.admin.models.AbstractType;
import com.example.project_for_university.controllers.user.admin.models.AdminPanelInfo;
import com.example.project_for_university.controllers.user.admin.models.PhType;
import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.enums.ActionType;
import com.example.project_for_university.enums.AdminPanelType;
import com.example.project_for_university.enums.Component;
import com.example.project_for_university.factory.ServiceFactory;
import com.example.project_for_university.providers.DataProvider;
import com.example.project_for_university.service.ReturnAllTypesService;
import com.example.project_for_university.utils.AlertUtil;
import com.example.project_for_university.utils.ComponentUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.IOException;

public class CreateTypeController implements DataProvider {

    private AllValues allValues;
    private final ServiceFactory serviceFactory = new ServiceFactory();
    private final ReturnAllTypesService returnAllTypesService = new ReturnAllTypesService();

    private AdminPanelInfo adminPanelInfo;

    @FXML
    private Text typeName_text;

    @FXML
    private Text actionType_text;

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

    @Setter
    private AbstractType typeForUpdate;

    @Override
    public void setData(AllValues allValues) {
        this.allValues = allValues;
        this.adminPanelInfo = allValues.getAdminPanelInfo();

        if (adminPanelInfo.getActionType() == ActionType.CREATE) {
            actionType_text.setText(ActionType.CREATE.getName() + " ");
        } else {
            actionType_text.setText(ActionType.UPDATE.getName() + " ");
            name_field.setText(typeForUpdate.getName());

            if (typeForUpdate instanceof PhType) {
                description_textArea.setText(((PhType) typeForUpdate).getDescription());
            }
        }

        typeName_text.setText("(" + allValues.getAdminPanelInfo().getCurAdminPanelType().getName() + ")");

        if (adminPanelInfo.getCurAdminPanelType() != AdminPanelType.PHYSICAL_ACTIVITY) {
            description_vbox.setVisible(false);
            description_vbox.setManaged(false);
        }
    }

    @SneakyThrows
    @FXML
    void ok_btn_clicked(MouseEvent event) throws IOException {
        if (adminPanelInfo.getCurAdminPanelType().getName().equals("Уровень физической активности")) {
            if (name_field.getText().isEmpty()) {
                AlertUtil.show("Вы не заполнили название типа", "Закройте окно и заполните нужное поле", allValues.getRootStage());
            } else if (description_textArea.getText().isEmpty()) {
                AlertUtil.show("Вы не заполнили описание типа", "Закройте окно и заполните нужное поле", allValues.getRootStage());
            } else {
                if(adminPanelInfo.getActionType().name().equals(ActionType.CREATE.name())) {
                    serviceFactory.createService(adminPanelInfo.getCurAdminPanelType()).create(new PhType(0, name_field.getText(), description_textArea.getText()), allValues.getUser().getEmail(), allValues.getUser().getPassword());
                } else {
                    serviceFactory.createService(adminPanelInfo.getCurAdminPanelType()).update(new PhType(typeForUpdate.getId(), name_field.getText(), description_textArea.getText()), allValues.getUser().getEmail(), allValues.getUser().getPassword());
                }
                allValues.getAdminPanelInfo().setReturnAllTypesDto(returnAllTypesService.getAllTypesThread(allValues.getUser().getEmail(), allValues.getUser().getPassword()).getReturnAllTypesDto());
                ComponentUtil.mount(Component.TYPE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
            }
        } else {
            if (name_field.getText().isEmpty()) {
                AlertUtil.show("Вы не заполнили название типа", "Закройте окно и заполните нужное поле", allValues.getRootStage());
            } else {
                if(adminPanelInfo.getActionType().name().equals(ActionType.CREATE.name())) {
                    serviceFactory.createService(adminPanelInfo.getCurAdminPanelType()).create(new PhType(0, name_field.getText(), ""), allValues.getUser().getEmail(), allValues.getUser().getPassword());
                } else {
                    serviceFactory.createService(adminPanelInfo.getCurAdminPanelType()).update(new PhType(typeForUpdate.getId(), name_field.getText(), ""), allValues.getUser().getEmail(), allValues.getUser().getPassword());
                }
                allValues.getAdminPanelInfo().setReturnAllTypesDto(returnAllTypesService.getAllTypesThread(allValues.getUser().getEmail(), allValues.getUser().getPassword()).getReturnAllTypesDto());
                ComponentUtil.mount(Component.TYPE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
            }
        }
    }

    @SneakyThrows
    @FXML
    void cancel_btn_clicked(MouseEvent event) throws IOException {
        ComponentUtil.mount(Component.TYPE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }
}
