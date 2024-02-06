package com.example.project_for_university.controllers.user.admin;

import com.example.project_for_university.controllers.user.admin.models.AbstractType;
import com.example.project_for_university.controllers.user.admin.models.AdminPanelInfo;
import com.example.project_for_university.controllers.user.admin.models.PhType;
import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.forBackend.entity.ProductionMethodEntity;
import com.example.project_for_university.dto.forBackend.entity.types.*;
import com.example.project_for_university.enums.ActionType;
import com.example.project_for_university.enums.AdminPanelType;
import com.example.project_for_university.enums.Component;
import com.example.project_for_university.factory.ServiceFactory;
import com.example.project_for_university.providers.DataProvider;
import com.example.project_for_university.service.admin.*;
import com.example.project_for_university.service.models.TypeResponse;
import com.example.project_for_university.service.models.TypesResponse;
import com.example.project_for_university.utils.AlertUtil;
import com.example.project_for_university.utils.ComponentUtil;
import com.example.project_for_university.utils.ExceptionMessageUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import lombok.SneakyThrows;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.example.project_for_university.utils.AlertUtil.*;
import static com.example.project_for_university.utils.ExceptionMessageUtil.*;

public class TypeController implements DataProvider, Initializable {

    private AllValues allValues;
    private final ServiceFactory serviceFactory = new ServiceFactory();

    private AdminPanelInfo adminPanelInfo;

    private List<String> typesNames;

    private AbstractType selectedItem;

    @FXML
    private Text typeName_text;

    @FXML
    private HBox btn_add;

    @FXML
    private HBox btn_change;

    @FXML
    private HBox btn_delete;

    @FXML
    private TableColumn<AbstractType, String> col_name;

    @FXML
    private TableView<AbstractType> table_types;

    private TableColumn<AbstractType, String> name_col;

    private TableColumn<AbstractType, String> description_col;

    @Override
    public void setData(AllValues allValues) {
        this.allValues = allValues;
        this.adminPanelInfo = allValues.getAdminPanelInfo();

        AdminPanelType curAdminPanelType = adminPanelInfo.getCurAdminPanelType();

        typeName_text.setText(curAdminPanelType.getName());

        TableColumn<AbstractType, String> name_col = new TableColumn<>("тип");
        this.name_col = name_col;
        table_types.getColumns().add(name_col);

        if (curAdminPanelType == AdminPanelType.PHYSICAL_ACTIVITY) {
            TableColumn<AbstractType, String> description_col = new TableColumn<>("Описание");
            this.description_col = description_col;

            description_col.setCellValueFactory(new PropertyValueFactory<>("description"));
            table_types.getColumns().add(description_col);
        }

        name_col.setCellValueFactory(new PropertyValueFactory<>("name"));

        table_types.setItems(getTypesList());
    }

    private ObservableList<AbstractType> getTypesList() {
        switch (allValues.getAdminPanelInfo().getCurAdminPanelType()) {
            case GLUE: {
                TypesResponse<GlueTypeEntity> typesResponse = GlueTypeService.INSTANCE.getAll(allValues.getUser().getEmail(), allValues.getUser().getPassword());
                if (typesResponse.isError()) {
                    show(typeTitleErrorMessage, typesResponse.getMessage(), allValues.getRootStage());
                } else {
                    return FXCollections.observableArrayList(typesResponse.getTypes());
                }
            }
            case LAYER: {
                TypesResponse<LayerTypeEntity> typesResponse = LayerTypeService.INSTANCE.getAll(allValues.getUser().getEmail(), allValues.getUser().getPassword());
                if (typesResponse.isError()) {
                    show(typeTitleErrorMessage, typesResponse.getMessage(), allValues.getRootStage());
                } else {
                    return FXCollections.observableArrayList(typesResponse.getTypes());
                }
            }
            case WASHING: {
                TypesResponse<WashingTypeEntity> typesResponse = WashingTypeService.INSTANCE.getAll(allValues.getUser().getEmail(), allValues.getUser().getPassword());
                if (typesResponse.isError()) {
                    show(typeTitleErrorMessage, typesResponse.getMessage(), allValues.getRootStage());
                } else {
                    return FXCollections.observableArrayList(typesResponse.getTypes());
                }
            }
            case BENDING: {
                TypesResponse<BendingTypeEntity> typesResponse = BendingTypeService.INSTANCE.getAll(allValues.getUser().getEmail(), allValues.getUser().getPassword());
                if (typesResponse.isError()) {
                    show(typeTitleErrorMessage, typesResponse.getMessage(), allValues.getRootStage());
                } else {
                    return FXCollections.observableArrayList(typesResponse.getTypes());
                }
            }
            case ABRASION: {
                TypesResponse<AbrasionTypeEntity> typesResponse = AbrasionTypeService.INSTANCE.getAll(allValues.getUser().getEmail(), allValues.getUser().getPassword());
                if (typesResponse.isError()) {
                    show(typeTitleErrorMessage, typesResponse.getMessage(), allValues.getRootStage());
                } else {
                    return FXCollections.observableArrayList(typesResponse.getTypes());
                }
            }
            case PRODUCTION_METHOD: {
                TypesResponse<ProductionMethodEntity> typesResponse = ProductionMethodService.INSTANCE.getAll(allValues.getUser().getEmail(), allValues.getUser().getPassword());
                if (typesResponse.isError()) {
                    show(typeTitleErrorMessage, typesResponse.getMessage(), allValues.getRootStage());
                } else {
                    return FXCollections.observableArrayList(typesResponse.getTypes());
                }
            }
            case MEMBRANE_LAYER_POLYMER: {
                TypesResponse<MembraneLayerPolymerTypeEntity> typesResponse = MembraneLayerPolymerTypeService.INSTANCE.getAll(allValues.getUser().getEmail(), allValues.getUser().getPassword());
                if (typesResponse.isError()) {
                    show(typeTitleErrorMessage, typesResponse.getMessage(), allValues.getRootStage());
                } else {
                    return FXCollections.observableArrayList(typesResponse.getTypes());
                }
            }
            case PHYSICAL_ACTIVITY: {
                TypesResponse<PhysicalActivityTypeEntity> typesResponse = PhysicalActivityTypeService.INSTANCE.getAll(allValues.getUser().getEmail(), allValues.getUser().getPassword());
                if (typesResponse.isError()) {
                    show(typeTitleErrorMessage, typesResponse.getMessage(), allValues.getRootStage());
                } else {
                    return FXCollections.observableArrayList(typesResponse.getTypes());
                }
            }
        }
        return FXCollections.observableArrayList();
    }

    @SneakyThrows
    @FXML
    void btn_back_clicked(MouseEvent event) {
        ComponentUtil.mount(Component.ADMIN_PANEL, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @FXML
    void getSelectedItem(MouseEvent event) {
        this.selectedItem = table_types.getSelectionModel().getSelectedItem();
    }

    @SneakyThrows
    @FXML
    void btn_add_clicked(MouseEvent event) {
        adminPanelInfo.setActionType(ActionType.CREATE);
        allValues.setAdminPanelInfo(adminPanelInfo);
        ComponentUtil.mount(Component.CREATE_TYPE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @FXML
    @SneakyThrows
    void btn_change_clicked(MouseEvent event) {
        if (selectedItem == null) {
            show("Выберете элемент", "Выберете элемент из таблицы, затем попробуйте изменить", allValues.getRootStage());
        } else {
            adminPanelInfo.setActionType(ActionType.UPDATE);
            allValues.setAdminPanelInfo(adminPanelInfo);
            ComponentUtil.mountUpdateType(allValues.getContentPanes().getLoggedInStackPane(), allValues, selectedItem);
        }
    }

    @FXML
    void btn_delete_clicked(MouseEvent event) {
        if (selectedItem == null) {
            show("Выберете элемент", "Выберете элемент из таблицы, затем попробуйте удалить", allValues.getRootStage());
        } else {
            TypeResponse<AbstractType> typeResponse = serviceFactory.createService(adminPanelInfo.getCurAdminPanelType()).delete(selectedItem.getId(), allValues.getUser().getEmail(), allValues.getUser().getPassword());
            if (typeResponse.isError()) {
                show("Ошибка при удалении элемента", typeResponse.getMessage(), allValues.getRootStage());
            }

            List<AbstractType> types = table_types.getItems();
            types.remove(selectedItem);
            table_types.setItems(FXCollections.observableArrayList(types));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}