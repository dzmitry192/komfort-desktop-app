package com.example.project_for_university.controllers.user.admin;

import com.example.project_for_university.controllers.user.admin.models.PhType;
import com.example.project_for_university.controllers.user.admin.models.Type;
import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.utils.ControllerUtils;
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
import java.util.Arrays;
import java.util.List;

public class TypeController {

    private AllValues allValues;
    private List<String> typesNames;

    @FXML
    private Button btn_back;

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_change;

    @FXML
    private Button btn_delete;

    @FXML
    private TableColumn<Type, String> col_name;

    @FXML
    private TableView<Type> table_types;

    @FXML
    void btn_back_clicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdminController.class.getResource("/com/example/project_for_university/fxml/user/admin/admin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        ((AdminController) fxmlLoader.getController()).setData(allValues);

        Stage window = (Stage) btn_add.getScene().getWindow();
        window.setTitle("Администратор");
        window.setScene(scene);
        window.show();
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