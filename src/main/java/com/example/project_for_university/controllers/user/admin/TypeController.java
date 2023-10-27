package com.example.project_for_university.controllers.user.admin;

import com.example.project_for_university.controllers.user.admin.models.AbstractType;
import com.example.project_for_university.controllers.user.admin.models.AdminPanelInfo;
import com.example.project_for_university.controllers.user.admin.models.PhType;
import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.enums.ActionType;
import com.example.project_for_university.enums.AdminPanelType;
import com.example.project_for_university.enums.Component;
import com.example.project_for_university.providers.DataProvider;
import com.example.project_for_university.utils.ComponentUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private TableColumn<AbstractType, Integer> id_col;

    private TableColumn<AbstractType, String> name_col;

    private TableColumn<AbstractType, String> description_col;

    ObservableList<AbstractType> typeList = FXCollections.observableArrayList(
            new PhType(1, "name", "desc")
    );

    @Override
    public void setData(AllValues allValues) {
        this.allValues = allValues;
        this.adminPanelInfo = allValues.getAdminPanelInfo();

        AdminPanelType curAdminPanelType = adminPanelInfo.getCurAdminPanelType();

        typeName_text.setText(curAdminPanelType.getName());

        table_types.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<AbstractType, Integer> id_col = new TableColumn<>("id");
        TableColumn<AbstractType, String> name_col = new TableColumn<>("Название типа");
        this.id_col = id_col;
        this.name_col = name_col;

        if (curAdminPanelType == AdminPanelType.PHYSICAL_ACTIVITY) {
            TableColumn<AbstractType, String> description_col = new TableColumn<>("Описание");
            table_types.getColumns().addAll(id_col, name_col, description_col);
            this.description_col = description_col;
        } else  {
            table_types.getColumns().addAll(id_col, name_col);
        }
//
//        name_col.setCellValueFactory(new PropertyValueFactory<AbstractType, Integer>("id"));
//        name_col.setCellValueFactory(new PropertyValueFactory<AbstractType, Integer>("name"));
//        name_col.setCellValueFactory(new PropertyValueFactory<AbstractType, Integer>("description"));

        table_types.setItems(typeList);
    }

    @FXML
    void btn_back_clicked(MouseEvent event) throws IOException {
        ComponentUtil.mount(Component.ADMIN_PANEL, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @FXML
    void btn_add_clicked(MouseEvent event) throws IOException {
        adminPanelInfo.setActionType(ActionType.CREATE);
        allValues.setAdminPanelInfo(adminPanelInfo);
        ComponentUtil.mount(Component.CREATE_TYPE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @FXML
    void btn_change_clicked(MouseEvent event) throws IOException {
        adminPanelInfo.setActionType(ActionType.UPDATE);
        allValues.setAdminPanelInfo(adminPanelInfo);

        AbstractType abstractType = new AbstractType(1, "type_name");

        ComponentUtil.mountUpdateType(allValues.getContentPanes().getLoggedInStackPane(), allValues, abstractType);
    }

    @FXML
    void btn_delete_clicked(MouseEvent event) {

    }

//    private void setTableData() {
//        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
//        List<Type> list = new ArrayList<>();
//        for(int i = 0; i < typesNames.size(); i++) {
//            list.add(new Type(typesNames.get(i)));
//        }
//        table_types.setItems(FXCollections.observableArrayList(list));
//    }
//
//    public void setData(AllValues allValues, List<String> typesNames) {
//        this.allValues = allValues;
//        this.typesNames = typesNames;
//        setTableData();
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}