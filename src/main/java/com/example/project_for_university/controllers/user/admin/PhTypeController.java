package com.example.project_for_university.controllers.user.admin;

import com.example.project_for_university.controllers.user.admin.models.PhType;
import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.forBackend.entity.types.PhysicalActivityTypeEntity;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PhTypeController {

    private AllValues allValues;
    private PhysicalActivityTypeEntity[] physicalActivityTypes;
    @FXML
    private Button btn_add;

    @FXML
    private Button btn_back;

    @FXML
    private Button btn_change;

    @FXML
    private Button btn_delete;

    @FXML
    private TableColumn<PhType, String> col_desc;

    @FXML
    private TableColumn<PhType, String> col_name;

    @FXML
    private TableView<PhType> table_types;

    @FXML
    void btn_add_clicked(MouseEvent event) {

    }

    @FXML
    void btn_back_clicked(MouseEvent event) throws IOException {

    }

    @FXML
    void btn_change_clicked(MouseEvent event) {

    }

    @FXML
    void btn_delete_clicked(MouseEvent event) {

    }

    private void setTableData() {
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_desc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        List<PhType> list = new ArrayList<>();
        for(int i = 0; i < physicalActivityTypes.length; i++) {
            list.add(new PhType(physicalActivityTypes[i].getName(), physicalActivityTypes[i].getDescription()));
        }
        table_types.setItems(FXCollections.observableArrayList(list));
    }

    public <T> void setData(AllValues allValues) {
        this.allValues = allValues;
        this.physicalActivityTypes = allValues.getReturnAllTypesDto().getPhysicalActivityTypes();
        setTableData();
    }
}
