package com.example.project_for_university.controllers.user.admin;

import com.example.project_for_university.controllers.user.admin.models.PhType;
import com.example.project_for_university.controllers.user.admin.models.Type;
import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.forBackend.entity.types.PhysicalActivityTypeEntity;
import com.example.project_for_university.utils.ControllerUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
        Stage stage = (Stage) btn_add.getScene().getWindow();
        stage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(AdminController.class.getResource("/com/example/project_for_university/fxml/user/admin/admin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        ((AdminController) fxmlLoader.getController()).setData(allValues);

        Stage window = new Stage();
        window.setTitle("Администратор");
        window.setScene(scene);
        window.show();
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
