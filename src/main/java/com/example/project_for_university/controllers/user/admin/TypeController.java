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

import java.io.IOException;
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

    ObservableList<AbstractType> typeList = FXCollections.observableArrayList(
            new PhType(1, "name1", "description1"),
            new PhType(2, "name2", "description2"),
            new PhType(3, "name3", "description3")
    );

    @Override
    public void setData(AllValues allValues) {
        this.allValues = allValues;
        this.adminPanelInfo = allValues.getAdminPanelInfo();

        AdminPanelType curAdminPanelType = adminPanelInfo.getCurAdminPanelType();

        typeName_text.setText(curAdminPanelType.getName());

        table_types.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
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

        table_types.setItems(typeList);
    }

    @FXML
    void btn_back_clicked(MouseEvent event) throws IOException {
        ComponentUtil.mount(Component.ADMIN_PANEL, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @FXML
    void getSelectedItem(MouseEvent event) {
        this.selectedItem = table_types.getSelectionModel().getSelectedItem();
    }

    @FXML
    void btn_add_clicked(MouseEvent event) throws IOException {
        adminPanelInfo.setActionType(ActionType.CREATE);
        allValues.setAdminPanelInfo(adminPanelInfo);
        ComponentUtil.mount(Component.CREATE_TYPE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @FXML
    void btn_change_clicked(MouseEvent event) throws IOException {
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