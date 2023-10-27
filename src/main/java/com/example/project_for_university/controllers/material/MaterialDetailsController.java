package com.example.project_for_university.controllers.material;

import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.forBackend.entity.types.PartialMaterialEntity;
import com.example.project_for_university.enums.Component;
import com.example.project_for_university.providers.DataProvider;
import com.example.project_for_university.utils.ComponentUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MaterialDetailsController implements Initializable, DataProvider {
    private AllValues allValues;

    private PartialMaterialEntity partialMaterialEntity;

    @FXML
    private HBox back_btn;

    @FXML
    private TableView<?> condition_params_table;

    @FXML
    private HBox download_report_btn;

    @FXML
    private Hyperlink email_label;

    @FXML
    private Text engineer_name_text;

    @FXML
    private HBox image_back_btn;

    @FXML
    private HBox image_next_btn;

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

    @Override
    public void setData(AllValues allValues) {
        this.allValues = allValues;
    }

    public void setPartialMaterialEntity(PartialMaterialEntity partialMaterialEntity) {
        this.partialMaterialEntity = partialMaterialEntity;

        material_name_label.setText(partialMaterialEntity.getName());
        material_desc_label.setText(partialMaterialEntity.getDescription());
        engineer_name_text.setText(partialMaterialEntity.getUser().getFio());
        email_label.setText(partialMaterialEntity.getUser().getEmail());

        //и тут заполняешь таблицы, мне самому лень просто
    }

    @FXML
    @SneakyThrows()
    void back_btn_clicked(MouseEvent event) {
        ComponentUtil.mount(Component.FILTER, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @FXML
    void download_report_btn_clicked(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Загрузка отчета");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.setInitialFileName(partialMaterialEntity.getName() + ".xlsx");
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            System.out.println("Selected file: " + file.getAbsolutePath());
        } else {
            System.out.println("Operation canceled");
        }
        stage.show();
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
