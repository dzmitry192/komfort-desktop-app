package com.example.project_for_university.controllers.user.admin;

import com.example.project_for_university.controllers.user.admin.models.Type;
import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.enums.Component;
import com.example.project_for_university.providers.DataProvider;
import com.example.project_for_university.utils.ComponentUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TypeController implements DataProvider {

    private AllValues allValues;
    private List<String> typesNames;

    @FXML
    private HBox btn_add;

    @FXML
    private HBox btn_change;

    @FXML
    private HBox btn_delete;

    @FXML
    private TableColumn<Type, String> col_name;

    @FXML
    private TableView<Type> table_types;

    @Override
    public void setData(AllValues allValues) {
        this.allValues = allValues;
    }

    @FXML
    void btn_back_clicked(MouseEvent event) throws IOException {
        ComponentUtil.mount(Component.ADMIN_PANEL, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }


    @FXML
    void btn_add_clicked(MouseEvent event) {

    }

    @FXML
    void btn_change_clicked(MouseEvent event) {

    }

    @FXML
    void btn_delete_clicked(MouseEvent event) {

    }

    private void setTableData() {
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        List<Type> list = new ArrayList<>();
        for(int i = 0; i < typesNames.size(); i++) {
            list.add(new Type(typesNames.get(i)));
        }
        table_types.setItems(FXCollections.observableArrayList(list));
    }

    public void setData(AllValues allValues, List<String> typesNames) {
        this.allValues = allValues;
        this.typesNames = typesNames;
        setTableData();
    }
}