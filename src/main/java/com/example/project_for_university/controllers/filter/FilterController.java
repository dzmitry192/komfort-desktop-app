package com.example.project_for_university.controllers.filter;

import com.example.project_for_university.controllers.user.ChooseOpController;
import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.FilterValues;
import com.example.project_for_university.dto.Material;
import com.example.project_for_university.utils.ControllerUtils;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FilterController implements Initializable {

    private AllValues allValues;
    private FilterValues filterValues;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CheckBox check_own_materials;

    @FXML
    private TextField blotting_pressure_inp_1;
    @FXML
    private TextField blotting_pressure_inp_2;

    @FXML
    private Button btn_search;

    @FXML
    private CheckBox check_blotting_pressure;

    @FXML
    private CheckBox check_depth;

    @FXML
    private CheckBox check_relative_pressure;

    @FXML
    private CheckBox check_resistance;

    @FXML
    private CheckBox check_time;

    @FXML
    private CheckBox check_water_vapor_perm;

    @FXML
    private TextField depth_inp_1;

    @FXML
    private TextField depth_inp_2;

    @FXML
    private VBox list_materials;

    @FXML
    private TextField name_inp;

    @FXML
    private ComboBox<?> num_layers_cb;

    @FXML
    private TextField relative_pressure_inp_1;

    @FXML
    private TextField relative_pressure_inp_2;

    @FXML
    private Button btn_reset_filters;

    @FXML
    private TextField resistance_inp_1;

    @FXML
    private TextField resistance_inp_2;

    @FXML
    private TextField time_inp_1;

    @FXML
    private TextField time_inp_2;

    @FXML
    private ComboBox<?> typeMemb_cb;

    @FXML
    private TextField water_vapor_perm_inp_1;

    @FXML
    private TextField water_vapor_perm_inp_2;

    @FXML
    private ComboBox<?> way_prod_cb;

    @FXML
    void check_own_materials(MouseEvent event) {

    }

    @FXML
    void check_blotting_pressure_clicked(MouseEvent event) {
        if(check_blotting_pressure.isSelected()) {
            blotting_pressure_inp_1.setDisable(false);
            blotting_pressure_inp_2.setDisable(false);
        } else {
            blotting_pressure_inp_1.setDisable(true);
            blotting_pressure_inp_2.setDisable(true);
            blotting_pressure_inp_1.setText("");
            blotting_pressure_inp_2.setText("");
        }
    }

    @FXML
    void check_depth_clicked(MouseEvent event) {
        if(check_depth.isSelected()) {
            depth_inp_1.setDisable(false);
            depth_inp_2.setDisable(false);
        } else {
            depth_inp_1.setDisable(true);
            depth_inp_2.setDisable(true);
            depth_inp_1.setText("");
            depth_inp_2.setText("");
        }
    }

    @FXML
    void check_relative_pressure_clicked(MouseEvent event) {
        if(check_relative_pressure.isSelected()) {
            relative_pressure_inp_1.setDisable(false);
            relative_pressure_inp_2.setDisable(false);
        } else {
            relative_pressure_inp_1.setDisable(true);
            relative_pressure_inp_2.setDisable(true);
            relative_pressure_inp_1.setText("");
            relative_pressure_inp_2.setText("");
        }
    }

    @FXML
    void check_resistance_clicked(MouseEvent event) {
        if(check_resistance.isSelected()) {
            resistance_inp_1.setDisable(false);
            resistance_inp_2.setDisable(false);
        } else {
            resistance_inp_1.setDisable(true);
            resistance_inp_2.setDisable(true);
            resistance_inp_1.setText("");
            resistance_inp_2.setText("");
        }
    }

    @FXML
    void check_time_clicked(MouseEvent event) {
        if(check_time.isSelected()) {
            time_inp_1.setDisable(false);
            time_inp_2.setDisable(false);
        } else {
            time_inp_1.setDisable(true);
            time_inp_2.setDisable(true);
            time_inp_1.setText("");
            time_inp_2.setText("");
        }
    }

    @FXML
    void check_water_vapor_perm_clicked(MouseEvent event) {
        if(check_water_vapor_perm.isSelected()) {
            water_vapor_perm_inp_1.setDisable(false);
            water_vapor_perm_inp_2.setDisable(false);
        } else {
            water_vapor_perm_inp_1.setDisable(true);
            water_vapor_perm_inp_2.setDisable(true);
            water_vapor_perm_inp_1.setText("");
            water_vapor_perm_inp_2.setText("");
        }
    }

    @FXML
    void btn_search_clicked(MouseEvent event) {

    }

    @FXML
    void btn_reset_filters_clicked(MouseEvent event) {
        check_time.setSelected(false);
        check_resistance.setSelected(false);
        check_water_vapor_perm.setSelected(false);
        check_relative_pressure.setSelected(false);
        check_depth.setSelected(false);
        check_blotting_pressure.setSelected(false);

        time_inp_1.setText("");
        time_inp_2.setText("");
        resistance_inp_1.setText("");
        resistance_inp_2.setText("");
        name_inp.setText("");
        water_vapor_perm_inp_1.setText("");
        water_vapor_perm_inp_2.setText("");
        relative_pressure_inp_1.setText("");
        relative_pressure_inp_2.setText("");
        depth_inp_1.setText("");
        depth_inp_2.setText("");
        blotting_pressure_inp_1.setText("");
        blotting_pressure_inp_2.setText("");

        way_prod_cb.setDisable(true);
        typeMemb_cb.setDisable(true);
        time_inp_1.setDisable(true);
        time_inp_2.setDisable(true);
        resistance_inp_1.setDisable(true);
        resistance_inp_2.setDisable(true);
        water_vapor_perm_inp_1.setDisable(true);
        water_vapor_perm_inp_2.setDisable(true);
        relative_pressure_inp_1.setDisable(true);
        relative_pressure_inp_2.setDisable(true);
        num_layers_cb.setDisable(true);
        depth_inp_1.setDisable(true);
        depth_inp_2.setDisable(true);
        blotting_pressure_inp_1.setDisable(true);
        blotting_pressure_inp_2.setDisable(true);
    }

    @FXML
    void btn_back_clicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource(ControllerUtils.welcomeRoute));
        Scene scene = new Scene(fxmlLoader.load());

        ((ChooseOpController) fxmlLoader.getController()).setData(allValues);

        Stage window = (Stage) btn_search.getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Material> materials = new ArrayList<>(materials());
        for(int i = 0; i < materials.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/project_for_university/fxml/filter/material_item/filter-material.fxml"));

            try {
                HBox materialItem = fxmlLoader.load();
                MaterialController materialController = fxmlLoader.getController();
                materialController.setMaterial(materials.get(i));
                list_materials.getChildren().add(materialItem);
//                anchorPane.maxWidthProperty().bind(list_materials.maxWidthProperty());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private List<Material> materials() {
        List<Material> materials = new ArrayList<>();

        materials.add(new Material("/com/example/project_for_university/img/image1.jpg", "Material1", "ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫы"));
        materials.add(new Material("/com/example/project_for_university/img/image1.jpg", "Material1", "ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫы"));
        materials.add(new Material("/com/example/project_for_university/img/image1.jpg", "Material1", "ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫы"));
        materials.add(new Material("/com/example/project_for_university/img/image1.jpg", "Material1", "ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫы"));
        materials.add(new Material("/com/example/project_for_university/img/image1.jpg", "Material1", "ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫ ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫы ЫЫЫЫЫЫЫЫЫЫЫЫЫ ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫ ЫЫЫЫЫЫЫЫЫ ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫы"));
        materials.add(new Material("/com/example/project_for_university/img/image1.jpg", "Material1", "ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫы"));
        materials.add(new Material("/com/example/project_for_university/img/image1.jpg", "Material1", "ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫы"));
        materials.add(new Material("/com/example/project_for_university/img/image1.jpg", "Material1", "ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫы"));
        materials.add(new Material("/com/example/project_for_university/img/image1.jpg", "Material1", "ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫы"));
        materials.add(new Material("/com/example/project_for_university/img/image1.jpg", "Material1", "ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫы"));
        materials.add(new Material("/com/example/project_for_university/img/image1.jpg", "Material1", "ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫы"));
        materials.add(new Material("/com/example/project_for_university/img/image1.jpg", "Material1", "ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫы"));
        materials.add(new Material("/com/example/project_for_university/img/image1.jpg", "Material1", "ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫы"));
        materials.add(new Material("/com/example/project_for_university/img/image1.jpg", "Material1", "ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫы"));
        materials.add(new Material("/com/example/project_for_university/img/image1.jpg", "Material1", "ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫы"));

        return materials;
    }

    public void setData(AllValues allValues) {
        this.allValues = allValues;
    }
}
