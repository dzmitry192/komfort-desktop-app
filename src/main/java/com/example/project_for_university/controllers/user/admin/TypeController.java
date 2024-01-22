package com.example.project_for_university.controllers.user.admin;

import com.example.project_for_university.controllers.user.admin.models.AbstractType;
import com.example.project_for_university.controllers.user.admin.models.AdminPanelInfo;
import com.example.project_for_university.controllers.user.admin.models.PhType;
import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.enums.ActionType;
import com.example.project_for_university.enums.AdminPanelType;
import com.example.project_for_university.enums.Component;
import com.example.project_for_university.providers.DataProvider;
import com.example.project_for_university.utils.AlertUtil;
import com.example.project_for_university.utils.ComponentUtil;
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
import java.util.ResourceBundle;

public class TypeController implements DataProvider, Initializable {

    private AllValues allValues;

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
        return switch (allValues.getAdminPanelInfo().getCurAdminPanelType().getName()) {
            case "Тип клея" ->
                    FXCollections.observableArrayList(java.util.Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getGlueTypes()).map(type -> new PhType(type.getId(), type.getName(), "")).toList());
            case "Тип слоя" ->
                    FXCollections.observableArrayList(java.util.Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getLayerTypes()).map(type -> new PhType(type.getId(), type.getName(), "")).toList());
            case "Тип стирки" ->
                    FXCollections.observableArrayList(java.util.Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getWashingTypes()).map(type -> new PhType(type.getId(), type.getName(), "")).toList());
            case "Тип изгиба" ->
                    FXCollections.observableArrayList(java.util.Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getBendingTypes()).map(type -> new PhType(type.getId(), type.getName(), "")).toList());
            case "Тип истирания" ->
                    FXCollections.observableArrayList(java.util.Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getAbrasionTypes()).map(type -> new PhType(type.getId(), type.getName(), "")).toList());
            case "Способ производства" ->
                    FXCollections.observableArrayList(java.util.Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getProductionMethods()).map(type -> new PhType(type.getId(), type.getName(), "")).toList());
            case "Тип полимера мембранного слоя" ->
                    FXCollections.observableArrayList(java.util.Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getMembraneLayerPolymerTypes()).map(type -> new PhType(type.getId(), type.getName(), "")).toList());
            case "Уровень физической активности" ->
                    FXCollections.observableArrayList(java.util.Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getPhysicalActivityTypes()).map(type -> new PhType(type.getId(), type.getName(), type.getDescription())).toList());
            default -> null;
        };
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
            AlertUtil.show("Выберете элемент", "Выберете элемент из таблицы, затем попробуйте изменить", allValues.getRootStage());
        } else {
            adminPanelInfo.setActionType(ActionType.UPDATE);
            allValues.setAdminPanelInfo(adminPanelInfo);

            ComponentUtil.mountUpdateType(allValues.getContentPanes().getLoggedInStackPane(), allValues, selectedItem);
        }
    }

    @FXML
    void btn_delete_clicked(MouseEvent event) {
        if (selectedItem == null) {
            AlertUtil.show("Выберете элемент", "Выберете элемент из таблицы, затем попробуйте удалить", allValues.getRootStage());
        } else {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}