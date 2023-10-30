package com.example.project_for_university.controllers.material;

import com.example.project_for_university.controllers.material.models.TableType;
import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.FConditionValues;
import com.example.project_for_university.dto.forBackend.entity.WashingEntity;
import com.example.project_for_university.dto.forBackend.entity.types.*;
import com.example.project_for_university.enums.Component;
import com.example.project_for_university.providers.DataProvider;
import com.example.project_for_university.utils.AlertUtil;
import com.example.project_for_university.utils.ComponentUtil;
import com.example.project_for_university.utils.ValidationUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Data
public class FConditionController implements Initializable, DataProvider {
    private AllValues allValues;
    private FConditionValues FConditionValues = new FConditionValues();
    private int cnt_type_of_load = 0;
    private boolean isReadyToChange;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<TableType> layers_table;

    @FXML
    private TableColumn<TableType, String> position_column;

    @FXML
    private TableColumn<TableType, String> layerName_column;

    @FXML
    private HBox btn_add_layer;

    @FXML
    private HBox btn_cond_next;

    @FXML
    private HBox remove_layer_btn;

    @FXML
    private CheckBox check_abrasion;

    @FXML
    private CheckBox check_bend;

    @FXML
    private CheckBox check_stretch_compress;

    @FXML
    private CheckBox check_torsion;

    @FXML
    private CheckBox check_wash;

    @FXML
    private TextField inp_av_speed;

    @FXML
    private TextField inp_max_air_one;

    @FXML
    private TextField inp_cycles_cnt;

    @FXML
    private TextField inp_max_air_sec;

    @FXML
    private TextField inp_min_temp_one;

    @FXML
    private TextField inp_min_temp_sec;

    @FXML
    private TextField inp_stretching;

    @FXML
    private TextField inp_temp_washing;

    @FXML
    private TextField inp_time_washing;

    @FXML
    private TextField inp_torsion_angle;

    @FXML
    private Label lab_spin;

    @FXML
    private Label lab_temp;

    @FXML
    private Label lab_wash_time;

    @FXML
    private ComboBox<String> lev_phys;

    @FXML
    private ComboBox<String> abrasion_type;

    @FXML
    private ComboBox<String> bend_type;

    @FXML
    private ComboBox<String> choose_layer_cb;

    @FXML
    private ComboBox<Integer> time_cond;

    @FXML
    private ComboBox<String> wash_type;

    @FXML
    private RadioButton rad_btn_minus;

    @FXML
    private RadioButton rad_btn_no;

    @FXML
    private RadioButton rad_btn_plus;

    @FXML
    private RadioButton rad_btn_yes;

    @FXML
    private Slider scroll_av_speed;

    @FXML
    private Slider scroll_max_air_one;

    @FXML
    private Slider scroll_max_air_sec;

    @FXML
    private Slider scroll_min_temp_one;

    @FXML
    private Slider scroll_min_temp_sec;

    @FXML
    private Slider scroll_stretching;

    @FXML
    private Slider scroll_temp_washing;

    @FXML
    private Slider scroll_time_washing;

    @FXML
    private Slider scroll_torsion_angle;

    @Override
    public void setData(AllValues allValues) {
        this.allValues = allValues;
        setValuesToCB();
    }

    private void setValuesToCB() {
        wash_type.setItems(FXCollections.observableArrayList(Arrays.stream(allValues.getReturnAllTypesDto().getWashingTypes()).map(WashingTypeEntity::getName).toList()));
        choose_layer_cb.setItems(FXCollections.observableArrayList(Arrays.stream(allValues.getReturnAllTypesDto().getLayerTypes()).map(LayerTypeEntity::getName).toList()));
        bend_type.setItems(FXCollections.observableArrayList(Arrays.stream(allValues.getReturnAllTypesDto().getBendingTypes()).map(BendingTypeEntity::getName).toList()));
        abrasion_type.setItems(FXCollections.observableArrayList(Arrays.stream(allValues.getReturnAllTypesDto().getAbrasionTypes()).map(AbrasionTypeEntity::getName).toList()));
        lev_phys.setItems(FXCollections.observableArrayList(Arrays.stream(allValues.getReturnAllTypesDto().getPhysicalActivityTypes()).map(PhysicalActivityTypeEntity::getName).toList()));
        time_cond.setItems(FXCollections.observableArrayList(2, 4));
    }

    @FXML
    void btn_add_layer_clicked(MouseEvent event) {
        List<TableType> layers = layers_table.getItems();
        if(layers.size() < 6 && choose_layer_cb.getSelectionModel().getSelectedIndex() != -1) {
            layers.add(new TableType(String.valueOf(layers.size() + 1), choose_layer_cb.getSelectionModel().getSelectedItem()));
            layers_table.setItems(FXCollections.observableList(layers));
        }
    }

    @FXML
    void remove_layer_btn_clicked(MouseEvent event) {
        List<TableType> layers = layers_table.getItems();
        if(!layers.isEmpty() && choose_layer_cb.getSelectionModel().getSelectedIndex() != -1) {
            int removeIndex = layers_table.getSelectionModel().getSelectedIndex();
            layers.remove(removeIndex);
            for(int i = removeIndex; i < layers.size(); i++) {
                layers.get(i).setFirstCol(String.valueOf(Integer.parseInt(layers.get(i).getFirstCol()) - 1));
            }
        }
    }

    @FXML
    void check_abrasion_clicked(MouseEvent event) {
        if (!check_abrasion.isSelected()) {
            cnt_type_of_load--;
            abrasion_type.setDisable(true);
        } else {
            if (cnt_type_of_load == 2) {
                check_abrasion.setSelected(false);
            } else {
                cnt_type_of_load++;
                abrasion_type.setDisable(false);
            }
        }
    }

    @FXML
    void check_bend_clicked(MouseEvent event) {
        if (!check_bend.isSelected()) {
            cnt_type_of_load--;
            bend_type.setDisable(true);
        } else {
            if (cnt_type_of_load == 2) {
                check_bend.setSelected(false);
            } else {
                cnt_type_of_load++;
                bend_type.setDisable(false);
            }
        }
    }

    @FXML
    void check_stretch_compress_clicked(MouseEvent event) {
        if (!check_stretch_compress.isSelected()) {
            cnt_type_of_load--;
            scroll_stretching.setDisable(true);
            inp_stretching.setDisable(true);
        } else {
            if (cnt_type_of_load == 2) {
                check_stretch_compress.setSelected(false);
            } else {
                cnt_type_of_load++;
                scroll_stretching.setDisable(false);
                inp_stretching.setDisable(false);
            }
        }
    }

    @FXML
    void check_torsion_clicked(MouseEvent event) {
        if (!check_torsion.isSelected()) {
            cnt_type_of_load--;
            scroll_torsion_angle.setDisable(true);
            inp_torsion_angle.setDisable(true);
        } else {
            if (cnt_type_of_load == 2) {
                check_torsion.setSelected(false);
            } else {
                cnt_type_of_load++;
                scroll_torsion_angle.setDisable(false);
                inp_torsion_angle.setDisable(false);
            }
        }
    }

    @FXML
    void check_wash_clicked(MouseEvent event) {
        if (!check_wash.isSelected()) {
            cnt_type_of_load--;
            wash_type.setDisable(true);
            scroll_temp_washing.setDisable(true);
            scroll_time_washing.setDisable(true);
            inp_temp_washing.setDisable(true);
            inp_time_washing.setDisable(true);
            rad_btn_yes.setDisable(true);
            rad_btn_no.setDisable(true);
            inp_cycles_cnt.setDisable(true);
        } else {
            if (cnt_type_of_load == 2) {
                check_wash.setSelected(false);
            } else {
                cnt_type_of_load++;
                wash_type.setDisable(false);
                scroll_temp_washing.setDisable(false);
                scroll_time_washing.setDisable(false);
                inp_temp_washing.setDisable(false);
                inp_time_washing.setDisable(false);
                rad_btn_yes.setDisable(false);
                rad_btn_no.setDisable(false);
                inp_cycles_cnt.setDisable(false);
            }
        }
    }

    @FXML
    void rad_btn_minus_clicked(MouseEvent event) {
        rad_btn_plus.setSelected(false);
    }

    @FXML
    void rad_btn_no_clicked(MouseEvent event) {
        rad_btn_yes.setSelected(false);
    }

    @FXML
    void rad_btn_plus_clicked(MouseEvent event) {
        rad_btn_minus.setSelected(false);
    }

    @FXML
    void rad_btn_yes_clicked(MouseEvent event) {
        rad_btn_no.setSelected(false);
    }

    @SneakyThrows
    @FXML
    void btn_cond_next_clicked(MouseEvent event) throws IOException {
        try {
            FConditionValues.setPositive(rad_btn_plus.isSelected());
            FConditionValues.setMinAirTemp((int) scroll_min_temp_one.getValue());
            FConditionValues.setMaxAirTemp((int) scroll_min_temp_sec.getValue());
            FConditionValues.setMinAirHumidity((int) scroll_max_air_one.getValue());
            FConditionValues.setMaxAirHumidity((int) scroll_max_air_sec.getValue());
            FConditionValues.setAvgAirSpeed((int) scroll_av_speed.getValue());
            FConditionValues.setResidenceTime(time_cond.getValue());

            if (check_bend.isSelected()) {
                FConditionValues.setBendingType(Arrays.stream(allValues.getReturnAllTypesDto().getBendingTypes()).filter(el -> el.getName().equals(bend_type.getValue())).findFirst().get());
            }
            if (check_abrasion.isSelected()) {
                FConditionValues.setAbrasionType(Arrays.stream(allValues.getReturnAllTypesDto().getAbrasionTypes()).filter(el -> el.getName().equals(abrasion_type.getValue())).findFirst().get());
            }
            if (check_stretch_compress.isSelected()) {
                FConditionValues.setStretchingCompression((int) scroll_stretching.getValue());
            }
            if (check_torsion.isSelected()) {
                FConditionValues.setTorsionAngle((int) scroll_torsion_angle.getValue());
            }
            if (check_wash.isSelected()) {
                FConditionValues.setWashing(new WashingEntity(0,
                        (int) scroll_temp_washing.getValue(),
                        Integer.parseInt(inp_cycles_cnt.getText()),
                        (int) scroll_time_washing.getValue(),
                        rad_btn_yes.isPressed(),
                        Arrays.stream(allValues.getReturnAllTypesDto().getWashingTypes()).filter(el -> el.getName().equals(wash_type.getValue())).findFirst().get()));
            }

//            allValues.setFConditionValues(FConditionValues);
            allValues.setLastCreateMaterialComponent(Component.CONDITION_2);
            ComponentUtil.mount(Component.CONDITION_2, allValues.getContentPanes().getLoggedInStackPane(), allValues);
        } catch (NullPointerException e) {
//            allValues.setLastCreateMaterialComponent(Component.CONDITION_1);
//            ComponentUtil.mount(Component.LOGGED_IN, allValues.getContentPanes().getMainContentPane(), allValues);
            AlertUtil.show("Вы не заполнили все поля", "Закройте это окно и дозаполните всё необходимые поля", allValues.getRootStage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scroll_av_speed.valueProperty().addListener((observable, oldValue, newValue) -> {
            inp_av_speed.setText(String.valueOf(newValue.intValue()));
        });

        scroll_max_air_one.valueProperty().addListener((observable, oldValue, newValue) -> {
            inp_max_air_one.setText(String.valueOf((int) scroll_max_air_one.getValue()));
        });

        scroll_max_air_sec.valueProperty().addListener((observable, oldValue, newValue) -> {
            inp_max_air_sec.setText(String.valueOf((int) scroll_max_air_sec.getValue()));
        });

        scroll_min_temp_one.valueProperty().addListener((observable, oldValue, newValue) -> {
            inp_min_temp_one.setText(String.valueOf((int) scroll_min_temp_one.getValue()));
        });

        scroll_min_temp_sec.valueProperty().addListener((observable, oldValue, newValue) -> {
            inp_min_temp_sec.setText(String.valueOf((int) scroll_min_temp_sec.getValue()));
        });

        scroll_stretching.valueProperty().addListener((observable, oldValue, newValue) -> {
            inp_stretching.setText(String.valueOf((int) scroll_stretching.getValue()));
        });

        scroll_torsion_angle.valueProperty().addListener((observable, oldValue, newValue) -> {
            inp_torsion_angle.setText(String.valueOf((int) scroll_torsion_angle.getValue()));
        });

        scroll_temp_washing.valueProperty().addListener((observable, oldValue, newValue) -> {
            inp_temp_washing.setText(String.valueOf((int) scroll_temp_washing.getValue()));
        });

        scroll_time_washing.valueProperty().addListener((observable, oldValue, newValue) -> {
            inp_time_washing.setText(String.valueOf((int) scroll_time_washing.getValue()));
        });

        inp_cycles_cnt.setTextFormatter(new TextFormatter<>(ValidationUtils.integerFilter));

        position_column.setCellValueFactory(new PropertyValueFactory<>("firstCol"));
        layerName_column.setCellValueFactory(new PropertyValueFactory<>("secondCol"));
    }
}