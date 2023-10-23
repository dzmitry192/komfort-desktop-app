package com.example.project_for_university.controllers.filter;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class MaterialDetailsController implements Initializable {
    @FXML
    private Button back_btn;

    @FXML
    private TableView<?> condition_params_table;

    @FXML
    private Button download_report_btn;

    @FXML
    private Hyperlink email_label;

    @FXML
    private Label engineer_name_label;

    @FXML
    private Button image_back_btn;

    @FXML
    private Button image_next_btn;

    @FXML
    private ImageView image_view;

    @FXML
    private TableColumn<?, ?> index_layer_column;

    @FXML
    private TableView<?> layers_table;

    @FXML
    private TableView<?> load_type_table;

    @FXML
    private Text material_desc_label;

    @FXML
    private Label material_name_label;

    @FXML
    private TableView<?> material_params_table;

    @FXML
    private TableColumn<?, ?> name_condition_param_column;

    @FXML
    private TableColumn<?, ?> name_material_param_column;

    @FXML
    private TableColumn<?, ?> type_layer_column;

    @FXML
    private TableColumn<?, ?> type_name_column;

    @FXML
    private TableColumn<?, ?> value_condition_param_column;

    @FXML
    private TableColumn<?, ?> value_material_param_column;

    @FXML
    private TableColumn<?, ?> values_type_column;

    @FXML
    void back_btn_clicked(MouseEvent event) {

    }

    @FXML
    void download_report_btn_clicked(MouseEvent event) {

    }

    @FXML
    void image_back_btn_clicked(MouseEvent event) {

    }

    @FXML
    void image_next_btn_clicked(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
