package com.example.project_for_university.controllers.material;

import com.example.project_for_university.controllers.material.models.TableType;
import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.forBackend.entity.types.*;
import com.example.project_for_university.enums.Component;
import com.example.project_for_university.providers.DataProvider;
import com.example.project_for_university.utils.ComponentUtil;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.SneakyThrows;

import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class MaterialDetailsController implements Initializable, DataProvider {
    private AllValues allValues;
    private int curIndex = 0;
    private Image[] images;
    private PartialMaterialEntity partialMaterialEntity;

    @FXML
    private ImageView image_view;

    @FXML
    private HBox back_btn;

    @FXML
    private HBox download_report_btn;

    @FXML
    private Hyperlink email_lbl;

    @FXML
    private Text engineer_name_text;

    @FXML
    private HBox image_back_btn;

    @FXML
    private HBox image_next_btn;

    @FXML
    private Text material_desc_text;

    @FXML
    private Label material_name_lbl;

    //---layer-table---
    @FXML
    private TableView<TableType> layers_table;

    @FXML
    private TableColumn<TableType, String> index_layer_column;

    @FXML
    private TableColumn<TableType, String> type_layer_column;

    //---condition-table---
    @FXML
    private TableView<TableType> condition_params_table;

    @FXML
    private TableColumn<TableType, String> name_condition_param_column;

    @FXML
    private TableColumn<TableType, String> value_condition_param_column;

    //---load-type-table---
    @FXML
    private TableView<TableType> load_type_table;

    @FXML
    private TableColumn<TableType, String> type_name_column;

    @FXML
    private TableColumn<TableType, String> values_type_column;

    //---material-details-table---
    @FXML
    private TableView<TableType> material_details_table;

    @FXML
    private TableColumn<TableType, String> name_material_details_column;

    @FXML
    private TableColumn<TableType, String> value_material_details_column;

    @Override
    public void setData(AllValues allValues) {
        this.allValues = allValues;
    }

    public void setPartialMaterialEntity(PartialMaterialEntity partialMaterialEntity) {
        this.partialMaterialEntity = partialMaterialEntity;
        this.images = new Image[]{
                new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU"),
                new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"),
                new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU"),
        };

        image_view.setImage(images[0]);
//
//        material_name_lbl.setText(partialMaterialEntity.getName());
//        material_desc_text.setText(partialMaterialEntity.getDescription());
//        engineer_name_text.setText(partialMaterialEntity.getUser().getFio());
//        email_lbl.setText(partialMaterialEntity.getUser().getEmail());
//
//        //и тут заполняешь таблицы, мне самому лень просто
//        ObservableList<TableType> layerEntities = FXCollections.observableArrayList();
//        for (LayerEntity layer : partialMaterialEntity.getLayers()) {
//            layerEntities.add(new TableType(String.valueOf(layer.getIndexNum()), layer.getLayerType().getName()));
//        }
//        layers_table.setItems(layerEntities);
//
//        material_details_table.setItems(FXCollections.observableArrayList(
//                new TableType("Толщина", String.valueOf(partialMaterialEntity.getDepth())),
//                new TableType("Способ производства", partialMaterialEntity.getManufacturer())
//        ));
//
//        load_type_table.setItems(FXCollections.observableArrayList(
//                new TableType("Изгиб", partialMaterialEntity.getCondition().getBendingType().getName()),
//                new TableType("Истирание", partialMaterialEntity.getCondition().getAbrasionType().getName()),
//                new TableType("Растяжение-сжатие", partialMaterialEntity.getCondition().getStretchingCompression() + "%"),
//                new TableType("Кручение", partialMaterialEntity.getCondition().getTorsionAngle() + "°"),
//                new TableType("Стирка", partialMaterialEntity.getName() + ", "
//                        + partialMaterialEntity.getCondition().getWashing().getCyclesCnt() + "циклов, "
//                        + partialMaterialEntity.getCondition().getWashing().getTemperature() + "°С, "
//                        + partialMaterialEntity.getCondition().getWashing().getDuration() + " минут, "
//                        + "отжим - " + (partialMaterialEntity.getCondition().getWashing().isPress() ? "да" : "нет")
//                )
//        ));
//
//        condition_params_table.setItems(FXCollections.observableArrayList(
//                new TableType("Знак при минимальной температуре воздуха", partialMaterialEntity.getCondition().isPositive() ? "Плюс" : "Минус"),
//                new TableType("Минимальная температура воздуха", partialMaterialEntity.getCondition().getMinAirTemp() + "-" + partialMaterialEntity.getCondition().getMaxAirTemp() + "°С"),
//                new TableType("Максимальная влажность воздуха", partialMaterialEntity.getCondition().getMinAirHumidity() + "-" + partialMaterialEntity.getCondition().getMaxAirHumidity() + "%"),
//                new TableType("Средняя скорость движения воздуха", partialMaterialEntity.getCondition().getAvgAirSpeed() + "м/с"),
//                new TableType("Время неприрывного пребывания в заданных условиях", partialMaterialEntity.getCondition().getResidenceTime() + " часа")
//        ));
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
        if (images.length != 0 && images[curIndex - 1] != null) {
            image_view.setImage(images[curIndex - 1]);
            curIndex -= 1;
            System.out.println("back: curIndex = " + curIndex);
        }
    }

    @FXML
    void image_next_btn_clicked(MouseEvent event) {
        if (images.length != 0 && images[curIndex + 1] != null) {
            image_view.setImage(images[curIndex + 1]);
            curIndex += 1;
            System.out.println("next: curIndex = " + curIndex);

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        index_layer_column.setCellValueFactory(new PropertyValueFactory<>("firstCol"));
        type_layer_column.setCellValueFactory(new PropertyValueFactory<>("secondCol"));

        name_material_details_column.setCellValueFactory(new PropertyValueFactory<>("firstCol"));
        value_material_details_column.setCellValueFactory(new PropertyValueFactory<>("secondCol"));

        type_name_column.setCellValueFactory(new PropertyValueFactory<>("firstCol"));
        values_type_column.setCellValueFactory(new PropertyValueFactory<>("secondCol"));

        name_condition_param_column.setCellValueFactory(new PropertyValueFactory<>("firstCol"));
        value_condition_param_column.setCellValueFactory(new PropertyValueFactory<>("secondCol"));
    }
}
