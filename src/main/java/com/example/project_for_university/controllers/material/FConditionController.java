package com.example.project_for_university.controllers.material;

import com.example.project_for_university.controllers.material.models.TableType;
import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.forBackend.MaterialInfoDto;
import com.example.project_for_university.dto.forBackend.create.CreateConditionDto;
import com.example.project_for_university.dto.forBackend.create.CreateLayerDto;
import com.example.project_for_university.dto.forBackend.create.CreateMaterialDto;
import com.example.project_for_university.dto.forBackend.create.CreateWashingDto;
import com.example.project_for_university.dto.forBackend.entity.ProductionMethodEntity;
import com.example.project_for_university.dto.forBackend.entity.types.*;
import com.example.project_for_university.enums.Component;
import com.example.project_for_university.providers.DataProvider;
import com.example.project_for_university.utils.AlertUtil;
import com.example.project_for_university.utils.ComponentUtil;
import com.example.project_for_university.utils.ValidationUtils;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import java.util.*;

@Data
public class FConditionController implements Initializable, DataProvider {
    private AllValues allValues;

    private boolean isError = false;

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
    private TextField manufacturer_inp;

    @FXML
    private TextField depth_inp;

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
    private ComboBox<String> production_way_cb;

    @FXML
    private ComboBox<String> membrane_type_cb;

    @FXML
    private ComboBox<String> glue_type_cb;

    @FXML
    private ComboBox<String> abrasion_type;

    @FXML
    private ComboBox<String> bend_type;

    @FXML
    private ComboBox<String> choose_layer_cb;

    @FXML
    private ComboBox<String> time_cond;

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
        if (allValues.getCreateMaterialDto().getCondition() == null && allValues.getCreateMaterialDto().getMaterial() == null) {
            CreateConditionDto conditionDto = new CreateConditionDto();
            conditionDto.setWashing(new CreateWashingDto());
            allValues.getCreateMaterialDto().setCondition(conditionDto);
            if (allValues.getCreateMaterialDto().getMaterial() == null) {
                allValues.getCreateMaterialDto().setMaterial(new MaterialInfoDto());
            }
        }
        setValuesToCB();

        fillFCondition();

        if (allValues.getSideBarButtonsEventHandlers() == null) {
            allValues.setSideBarButtonsEventHandlers(new ArrayList<>());
        }

        for (int i = 0; i < allValues.getSideBarButtonsEventHandlers().size(); i++) {
            allValues.getSideBarButtons().get(i).removeEventHandler(MouseEvent.MOUSE_CLICKED, allValues.getSideBarButtonsEventHandlers().get(i));
        }
        allValues.getSideBarButtonsEventHandlers().clear();

        for (var button : allValues.getSideBarButtons()) {
            EventHandler<MouseEvent> clickHandler = event -> {
                fillFCondition();
            };
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, clickHandler);
            allValues.getSideBarButtonsEventHandlers().add(clickHandler);
        }
    }

    private void setValuesToCB() {
        ObservableList<String> prodWay = FXCollections.observableArrayList("Не выбрано");
        prodWay.addAll(Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getProductionMethods()).map(ProductionMethodEntity::getName).toList());
        production_way_cb.setItems(prodWay);

        ObservableList<String> membraneTypes = FXCollections.observableArrayList("Не выбрано");
        membraneTypes.addAll(Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getMembraneLayerPolymerTypes()).map(MembraneLayerPolymerTypeEntity::getName).toList());
        membrane_type_cb.setItems(membraneTypes);

        ObservableList<String> glueTypes = FXCollections.observableArrayList("Не выбрано", "нет");
        glueTypes.addAll(Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getGlueTypes()).map(GlueTypeEntity::getName).toList());
        glue_type_cb.setItems(glueTypes);

        ObservableList<String> washTypes = FXCollections.observableArrayList("Не выбрано");
        washTypes.addAll(Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getWashingTypes()).map(WashingTypeEntity::getName).toList());
        wash_type.setItems(washTypes);

        ObservableList<String> layerTypes = FXCollections.observableArrayList("Не выбрано");
        layerTypes.addAll(Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getLayerTypes()).map(LayerTypeEntity::getName).toList());
        choose_layer_cb.setItems(layerTypes);

        ObservableList<String> bendingTypes = FXCollections.observableArrayList("Не выбрано");
        bendingTypes.addAll(Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getBendingTypes()).map(BendingTypeEntity::getName).toList());
        bend_type.setItems(bendingTypes);

        ObservableList<String> abrasionTypes = FXCollections.observableArrayList("Не выбрано");
        abrasionTypes.addAll(Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getAbrasionTypes()).map(AbrasionTypeEntity::getName).toList());
        abrasion_type.setItems(abrasionTypes);

        ObservableList<String> physicalTypes = FXCollections.observableArrayList("Не выбрано");
        physicalTypes.addAll(Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getPhysicalActivityTypes()).map(PhysicalActivityTypeEntity::getName).toList());
        lev_phys.setItems(physicalTypes);

        time_cond.setItems(FXCollections.observableArrayList("Не выбрано", "2", "4"));
    }

    @FXML
    void btn_add_layer_clicked(MouseEvent event) {
        List<TableType> layers = new ArrayList<>(layers_table.getItems());
        if (layers.size() < 6) {
            if (choose_layer_cb.getSelectionModel().getSelectedIndex() != -1 && choose_layer_cb.getSelectionModel().getSelectedIndex() != 0) {
                layers.add(new TableType(String.valueOf(layers.size() + 1), choose_layer_cb.getSelectionModel().getSelectedItem()));
                layers_table.setItems(FXCollections.observableList(layers));
                allValues.getCreateMaterialDto().getMaterial().setLayers(layers_table.getItems().stream().map(layer -> new CreateLayerDto(Integer.parseInt(layer.getFirstCol()), Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getLayerTypes()).filter(type -> type.getName().equals(layer.getSecondCol())).findFirst().get().getId())).toList());
            } else {
                AlertUtil.show("Выберете элемент", "Выберете нужный элемент, затем попробуйте добавить", allValues.getRootStage());
            }
        } else {
            AlertUtil.show("Ошибка добавления слоя", "Невозможно добавить слой, тк максильманое число слоев равно 6", allValues.getRootStage());
        }
    }

    @FXML
    void remove_layer_btn_clicked(MouseEvent event) {
        List<TableType> layers = new ArrayList<>(layers_table.getItems());
        if (!layers.isEmpty()) {
            if (layers_table.getSelectionModel().getSelectedIndex() != -1) {
                int removeIndex = layers_table.getSelectionModel().getSelectedIndex();
                layers.remove(removeIndex);
                for (int i = removeIndex; i < layers.size(); i++) {
                    layers.get(i).setFirstCol(String.valueOf(Integer.parseInt(layers.get(i).getFirstCol()) - 1));
                }
                layers_table.setItems(FXCollections.observableList(layers));
                allValues.getCreateMaterialDto().getMaterial().setLayers(layers_table.getItems().stream().map(layer -> new CreateLayerDto(Integer.parseInt(layer.getFirstCol()), Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getLayerTypes()).filter(type -> type.getName().equals(layer.getSecondCol())).findFirst().get().getId())).toList());
            } else {
                AlertUtil.show("Выберете элемент", "Выберете элемент из таблицы, затем попробуйте удалить", allValues.getRootStage());
            }
        } else {
            AlertUtil.show("Ошибка удаления слоя", "Невозможно удалить слой, тк таблица пустая", allValues.getRootStage());
        }
    }

    private void fillFCondition() {
        CreateConditionDto conditionDto = allValues.getCreateMaterialDto().getCondition();
        MaterialInfoDto materialInfo = allValues.getCreateMaterialDto().getMaterial();

        if (conditionDto != null) {
            if (conditionDto.isPositive()) {
                rad_btn_plus.setSelected(true);
            }
            if (conditionDto.getPhysicalActivityType_id() != 0 && Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getPhysicalActivityTypes()).anyMatch(type -> type.getId() == conditionDto.getPhysicalActivityType_id())) {
                lev_phys.setValue(Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getPhysicalActivityTypes()).filter(type -> type.getId() == conditionDto.getPhysicalActivityType_id()).findFirst().get().getName());
            }
            if (conditionDto.getResidenceTime() != 0) {
                time_cond.setValue(String.valueOf(conditionDto.getResidenceTime()));
            }
            if (conditionDto.getAvgAirSpeed() != 0) {
                scroll_av_speed.setValue(conditionDto.getAvgAirSpeed());
                inp_av_speed.setText(String.valueOf(conditionDto.getAvgAirSpeed()));
            }
            if (conditionDto.getMinAirTemp() != 0) {
                scroll_min_temp_one.setValue(conditionDto.getMinAirTemp());
                inp_min_temp_one.setText(String.valueOf(conditionDto.getMinAirTemp()));
            }
            if (conditionDto.getMaxAirTemp() != 0) {
                scroll_min_temp_sec.setValue(conditionDto.getMaxAirTemp());
                inp_min_temp_sec.setText(String.valueOf(conditionDto.getMaxAirTemp()));
            }
            if (conditionDto.getMinAirHumidity() != 0) {
                scroll_max_air_one.setValue(conditionDto.getMinAirHumidity());
                inp_max_air_one.setText(String.valueOf(conditionDto.getMinAirHumidity()));
            }
            if (conditionDto.getMaxAirTemp() != 0) {
                scroll_max_air_sec.setValue(conditionDto.getMaxAirHumidity());
                inp_max_air_sec.setText(String.valueOf(conditionDto.getMaxAirHumidity()));
            }
            if (conditionDto.getBendingType_id() != 0) {
                check_bend.setSelected(true);
                bend_type.setDisable(false);
                bend_type.setValue(Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getBendingTypes()).filter(type -> type.getId() == conditionDto.getBendingType_id()).findFirst().get().getName());
            }
            if (conditionDto.getAbrasionType_id() != 0) {
                check_abrasion.setSelected(true);
                abrasion_type.setDisable(false);
                abrasion_type.setValue(Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getAbrasionTypes()).filter(type -> type.getId() == conditionDto.getAbrasionType_id()).findFirst().get().getName());
            }
            if (conditionDto.getStretchingCompression() != 0) {
                check_stretch_compress.setSelected(true);
                scroll_stretching.setDisable(false);
                inp_stretching.setDisable(false);
                scroll_stretching.setValue(conditionDto.getStretchingCompression());
                inp_stretching.setText(String.valueOf(conditionDto.getStretchingCompression()));
            }
            if (conditionDto.getTorsionAngle() != 0) {
                check_torsion.setSelected(true);
                scroll_torsion_angle.setDisable(false);
                inp_torsion_angle.setDisable(false);
                scroll_torsion_angle.setValue(conditionDto.getTorsionAngle());
                inp_torsion_angle.setText(String.valueOf(conditionDto.getTorsionAngle()));
            }
            if (conditionDto.getWashing().getWashingType_id() != 0) {
                check_wash.setSelected(true);
                wash_type.setDisable(false);
                scroll_temp_washing.setDisable(false);
                inp_temp_washing.setDisable(false);
                scroll_time_washing.setDisable(false);
                inp_time_washing.setDisable(false);
                inp_cycles_cnt.setDisable(false);
                rad_btn_yes.setDisable(false);
                rad_btn_no.setDisable(false);

                if (conditionDto.getWashing().getWashingType_id() != 0) {
                    allValues.getCreateMaterialDto().getCondition().getWashing().setWashingType_id(conditionDto.getWashing().getWashingType_id());
                    wash_type.setValue(Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getWashingTypes()).filter(type -> type.getId() == conditionDto.getWashing().getWashingType_id()).findFirst().get().getName());
                }
                if (conditionDto.getWashing().getTemperature() != 0) {
                    scroll_temp_washing.setValue(conditionDto.getWashing().getTemperature());
                    inp_temp_washing.setText(String.valueOf(conditionDto.getWashing().getTemperature()));
                }
                if (conditionDto.getWashing().getDuration() != 0) {
                    scroll_time_washing.setValue(conditionDto.getWashing().getDuration());
                    inp_time_washing.setText(String.valueOf(conditionDto.getWashing().getDuration()));
                }
                if (conditionDto.getWashing().getCyclesCnt() != 0) {
                    inp_cycles_cnt.setText(String.valueOf(conditionDto.getWashing().getCyclesCnt()));
                }
                if (conditionDto.getWashing().isPress()) {
                    allValues.getCreateMaterialDto().getCondition().getWashing().setPress(true);
                    rad_btn_yes.setSelected(true);
                } else {
                    allValues.getCreateMaterialDto().getCondition().getWashing().setPress(false);
                    rad_btn_no.setSelected(true);
                }
            }
        }
        if (materialInfo != null) {
            if (materialInfo.getManufacturer() != null) {
                allValues.getCreateMaterialDto().getMaterial().setManufacturer(materialInfo.getManufacturer());
                manufacturer_inp.setText(materialInfo.getManufacturer());
            }
            if (materialInfo.getDepth() != 0) {
                allValues.getCreateMaterialDto().getMaterial().setDepth(materialInfo.getDepth());
                depth_inp.setText(String.valueOf(materialInfo.getDepth()));
            }
            if (materialInfo.getLayers() != null && !materialInfo.getLayers().isEmpty()) {
                layers_table.setItems(FXCollections.observableList(materialInfo.getLayers().stream().map(layer -> new TableType(String.valueOf(layer.getIndexNum()), Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getLayerTypes()).filter(layerType -> layerType.getId() == layer.getLayerType_id()).findFirst().get().getName())).toList()));
            }
            if (materialInfo.getProductionMethod_id() != 0) {
                allValues.getCreateMaterialDto().getMaterial().setProductionMethod_id(materialInfo.getProductionMethod_id());
                production_way_cb.setValue(Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getProductionMethods()).filter(method -> method.getId() == materialInfo.getProductionMethod_id()).findFirst().get().getName());
            }
            if (materialInfo.getMembraneLayerPolymerType_id() != 0) {
                allValues.getCreateMaterialDto().getMaterial().setMembraneLayerPolymerType_id(materialInfo.getMembraneLayerPolymerType_id());
                membrane_type_cb.setValue(Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getMembraneLayerPolymerTypes()).filter(type -> type.getId() == materialInfo.getMembraneLayerPolymerType_id()).findFirst().get().getName());
            }
            if (materialInfo.getGlueType_id() != 0) {
                allValues.getCreateMaterialDto().getMaterial().setGlueType_id(materialInfo.getGlueType_id());
                glue_type_cb.setValue(Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getGlueTypes()).filter(type -> type.getId() == materialInfo.getGlueType_id()).findFirst().get().getName());
            }
        }
    }

    private void clearFields() {
        //check buttons
        check_abrasion.setSelected(false);
        abrasion_type.setDisable(true);

        check_bend.setSelected(false);
        bend_type.setDisable(true);

        check_stretch_compress.setSelected(false);
        scroll_stretching.setDisable(true);
        inp_stretching.setDisable(true);

        check_torsion.setSelected(false);
        scroll_torsion_angle.setDisable(true);
        inp_torsion_angle.setDisable(true);

        check_wash.setSelected(false);
        allValues.getCreateMaterialDto().getCondition().getWashing().setPress(false);
        allValues.getCreateMaterialDto().getCondition().getWashing().setWashingType_id(0);
        allValues.getCreateMaterialDto().getCondition().getWashing().setTemperature(0);
        allValues.getCreateMaterialDto().getCondition().getWashing().setDuration(0);
        allValues.getCreateMaterialDto().getCondition().getWashing().setCyclesCnt(0);
        wash_type.setDisable(true);

        scroll_temp_washing.setDisable(true);
        inp_temp_washing.setDisable(true);
        scroll_time_washing.setDisable(true);
        inp_time_washing.setDisable(true);
        inp_cycles_cnt.setDisable(true);
        rad_btn_yes.setDisable(true);
        rad_btn_no.setDisable(true);

        //text fields
        manufacturer_inp.setText("");
        depth_inp.setText("");
        inp_av_speed.setText("0");
        inp_max_air_one.setText("20");
        inp_cycles_cnt.setText("");
        inp_max_air_sec.setText("20");
        inp_min_temp_sec.setText("0");
        inp_min_temp_one.setText("0");
        inp_stretching.setText("5");
        inp_temp_washing.setText("30");
        inp_time_washing.setText("20");
        inp_torsion_angle.setText("5");

        //table
        layers_table.setItems(FXCollections.observableArrayList());

        isError = false;

        //combo boxes
        lev_phys.setValue("Не выбрано");
        production_way_cb.setValue("Не выбрано");
        membrane_type_cb.setValue("Не выбрано");
        glue_type_cb.setValue("Не выбрано");
        abrasion_type.setValue("Не выбрано");
        bend_type.setValue("Не выбрано");
        choose_layer_cb.setValue("Не выбрано");
        time_cond.setValue("Не выбрано");
        wash_type.setValue("Не выбрано");

        //radio buttons
        rad_btn_minus.setSelected(false);
        rad_btn_no.setSelected(false);
        rad_btn_plus.setSelected(false);
        rad_btn_yes.setSelected(false);

        //sliders
        scroll_av_speed.setValue(0);
        scroll_max_air_one.setValue(20);
        scroll_max_air_sec.setValue(20);
        scroll_min_temp_one.setValue(0);
        scroll_min_temp_sec.setValue(0);
        scroll_stretching.setValue(5);
        scroll_temp_washing.setValue(30);
        scroll_time_washing.setValue(20);
        scroll_torsion_angle.setValue(5);
    }

    @FXML
    void btn_clearFields_clicked(MouseEvent event) {
        boolean isTrue = AlertUtil.showConfirmation("Очистить все поля на этой стадии?", "Все поля в этой стадии создания будут полностью очищены", allValues.getRootStage());
        if (isTrue) {
            allValues.getCreateMaterialDto().setCondition(new CreateConditionDto());
            clearFields();
        }
    }

    @SneakyThrows
    @FXML
    void btn_reset_clicked(MouseEvent event) {
        boolean isTrue = AlertUtil.showConfirmation("Полностью сбросить прогресс?", "Весть прогресс создания будет полностью очищен, создание начнется сначала", allValues.getRootStage());
        if (isTrue) {
            allValues.setCreateMaterialDto(new CreateMaterialDto());
            allValues.setLastCreateMaterialComponent(null);
            ComponentUtil.mount(Component.CONDITION_1, allValues.getContentPanes().getLoggedInStackPane(), allValues);
        }
    }

    @FXML
    void check_abrasion_clicked(MouseEvent event) {
        if (check_abrasion.isSelected()) {
            abrasion_type.setDisable(false);
        } else {
            abrasion_type.setDisable(true);
            abrasion_type.setValue("Не выбрано");
        }
    }

    @FXML
    void check_bend_clicked(MouseEvent event) {
        if (check_bend.isSelected()) {
            bend_type.setDisable(false);
        } else {
            bend_type.setDisable(true);
            bend_type.setValue("Не выбрано");
        }
    }

    @FXML
    void check_stretch_compress_clicked(MouseEvent event) {
        if (check_stretch_compress.isSelected()) {
            scroll_stretching.setDisable(false);
            inp_stretching.setDisable(false);

            scroll_stretching.setValue(5);
            inp_stretching.setText("5");
            allValues.getCreateMaterialDto().getCondition().setStretchingCompression(5);
        } else {
            scroll_stretching.setDisable(true);
            scroll_stretching.setValue(0);
            inp_stretching.setText("");

            allValues.getCreateMaterialDto().getCondition().setStretchingCompression(0);

            inp_stretching.setDisable(true);
            inp_stretching.setText(null);
        }
    }

    @FXML
    void check_torsion_clicked(MouseEvent event) {
        if (check_torsion.isSelected()) {
            scroll_torsion_angle.setDisable(false);
            inp_torsion_angle.setDisable(false);

            scroll_torsion_angle.setValue(5);
            inp_torsion_angle.setText("5");
            allValues.getCreateMaterialDto().getCondition().setTorsionAngle(5);
        } else {
            scroll_torsion_angle.setDisable(true);
            scroll_torsion_angle.setValue(0);
            inp_torsion_angle.setText("");
            allValues.getCreateMaterialDto().getCondition().setTorsionAngle(0);

            inp_torsion_angle.setDisable(true);
            inp_torsion_angle.setText(null);
        }
    }

    @FXML
    void check_wash_clicked(MouseEvent event) {
        if (check_wash.isSelected()) {
            wash_type.setDisable(false);
            scroll_temp_washing.setDisable(false);
            scroll_time_washing.setDisable(false);
            inp_temp_washing.setDisable(false);
            inp_time_washing.setDisable(false);
            rad_btn_yes.setDisable(false);
            rad_btn_no.setDisable(false);
            inp_cycles_cnt.setDisable(false);

            scroll_temp_washing.setValue(30);
            allValues.getCreateMaterialDto().getCondition().getWashing().setTemperature(30);
            inp_temp_washing.setText("30");
            scroll_time_washing.setValue(20);
            allValues.getCreateMaterialDto().getCondition().getWashing().setDuration(20);
            inp_time_washing.setText("20");
            wash_type.setValue("Не выбрано");
        } else {
            wash_type.setDisable(true);
            wash_type.setValue(null);

            scroll_temp_washing.setDisable(true);
            scroll_temp_washing.setValue(0);

            allValues.getCreateMaterialDto().getCondition().getWashing().setDuration(0);
            allValues.getCreateMaterialDto().getCondition().getWashing().setTemperature(0);

            scroll_time_washing.setDisable(true);
            scroll_time_washing.setValue(0);

            inp_temp_washing.setDisable(true);
            inp_temp_washing.setText("");

            inp_time_washing.setDisable(true);
            inp_time_washing.setText("");

            rad_btn_yes.setDisable(true);
            rad_btn_no.setDisable(true);

            rad_btn_yes.setSelected(false);
            rad_btn_no.setSelected(false);

            allValues.getCreateMaterialDto().getCondition().setPositive(false);

            inp_cycles_cnt.setDisable(true);
            inp_cycles_cnt.setText(null);
        }
    }

    @FXML
    void rad_btn_plus_action(ActionEvent event) {
        allValues.getCreateMaterialDto().getCondition().setPositive(true);
        rad_btn_minus.setSelected(false);
    }

    @FXML
    void rad_btn_minus_action(ActionEvent event) {
        allValues.getCreateMaterialDto().getCondition().setPositive(false);
        rad_btn_plus.setSelected(false);
    }

    @FXML
    void production_way_cb_action(ActionEvent event) {
        if (production_way_cb.getSelectionModel().getSelectedItem() != null && production_way_cb.getSelectionModel().getSelectedIndex() != 0) {
            allValues.getCreateMaterialDto().getMaterial().setProductionMethod_id(Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getProductionMethods()).filter(type -> type.getName().equals(production_way_cb.getSelectionModel().getSelectedItem())).findFirst().get().getId());
        } else {
            allValues.getCreateMaterialDto().getMaterial().setProductionMethod_id(0);
        }
    }

    @FXML
    void membrane_type_cb_action(ActionEvent event) {
        if (membrane_type_cb.getSelectionModel().getSelectedItem() != null && membrane_type_cb.getSelectionModel().getSelectedIndex() != 0) {
            allValues.getCreateMaterialDto().getMaterial().setMembraneLayerPolymerType_id(Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getMembraneLayerPolymerTypes()).filter(type -> type.getName().equals(membrane_type_cb.getSelectionModel().getSelectedItem())).findFirst().get().getId());
        } else {
            allValues.getCreateMaterialDto().getMaterial().setMembraneLayerPolymerType_id(0);
        }
    }

    @FXML
    void glue_type_cb_action(ActionEvent event) {
        if (glue_type_cb.getSelectionModel().getSelectedItem() != null && glue_type_cb.getSelectionModel().getSelectedIndex() != 0) {
            allValues.getCreateMaterialDto().getMaterial().setGlueType_id(Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getGlueTypes()).filter(type -> type.getName().equals(glue_type_cb.getSelectionModel().getSelectedItem())).findFirst().get().getId());
        } else {
            allValues.getCreateMaterialDto().getMaterial().setGlueType_id(0);
        }
    }

    @FXML
    void lev_phys_action(ActionEvent event) {
        if (lev_phys.getSelectionModel().getSelectedItem() != null && lev_phys.getSelectionModel().getSelectedIndex() != 0) {
            allValues.getCreateMaterialDto().getCondition().setPhysicalActivityType_id(Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getPhysicalActivityTypes()).filter(type -> type.getName().equals(lev_phys.getSelectionModel().getSelectedItem())).findFirst().get().getId());
        } else {
            allValues.getCreateMaterialDto().getCondition().setPhysicalActivityType_id(0);
        }
    }

    @FXML
    void time_cond_action(ActionEvent event) {
        if (time_cond.getSelectionModel().getSelectedItem() != null && time_cond.getSelectionModel().getSelectedIndex() != 0) {
            allValues.getCreateMaterialDto().getCondition().setResidenceTime(Integer.parseInt(time_cond.getSelectionModel().getSelectedItem()));
        } else {
            allValues.getCreateMaterialDto().getCondition().setResidenceTime(0);
        }
    }

    @FXML
    void bend_type_action(ActionEvent event) {
        if (bend_type.getSelectionModel().getSelectedItem() != null && bend_type.getSelectionModel().getSelectedIndex() != 0) {
            allValues.getCreateMaterialDto().getCondition().setBendingType_id(Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getBendingTypes()).filter(type -> type.getName().equals(bend_type.getSelectionModel().getSelectedItem())).findFirst().get().getId());
        } else {
            allValues.getCreateMaterialDto().getCondition().setBendingType_id(0);
        }
    }

    @FXML
    void abrasion_type_action(ActionEvent event) {
        if (abrasion_type.getSelectionModel().getSelectedItem() != null && abrasion_type.getSelectionModel().getSelectedIndex() != 0) {
            allValues.getCreateMaterialDto().getCondition().setAbrasionType_id(Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getAbrasionTypes()).filter(type -> type.getName().equals(abrasion_type.getSelectionModel().getSelectedItem())).findFirst().get().getId());
        } else {
            allValues.getCreateMaterialDto().getCondition().setAbrasionType_id(0);
        }
    }

    @FXML
    void wash_type_action(ActionEvent event) {
        if (wash_type.getSelectionModel().getSelectedItem() != null && wash_type.getSelectionModel().getSelectedIndex() != 0) {
            allValues.getCreateMaterialDto().getCondition().getWashing().setWashingType_id(Arrays.stream(allValues.getAdminPanelInfo().getReturnAllTypesDto().getWashingTypes()).filter(type -> type.getName().equals(wash_type.getSelectionModel().getSelectedItem())).findFirst().get().getId());
        } else {
            allValues.getCreateMaterialDto().getCondition().getWashing().setWashingType_id(0);
        }
    }

    @FXML
    void rad_btn_yes_action(ActionEvent event) {
        allValues.getCreateMaterialDto().getCondition().getWashing().setPress(true);
        rad_btn_no.setSelected(false);
    }

    @FXML
    void rad_btn_no_action(ActionEvent event) {
        allValues.getCreateMaterialDto().getCondition().getWashing().setPress(false);
        rad_btn_yes.setSelected(false);
    }

    @SneakyThrows
    @FXML
    void btn_cond_next_clicked(MouseEvent event) throws IOException {
        if (manufacturer_inp.getText().isEmpty()) {
            isError = true;
        }
        if (depth_inp.getText().isEmpty()) {
            isError = true;
        }
        if (allValues.getCreateMaterialDto().getMaterial().getProductionMethod_id() == 0) {
            isError = true;
        }
        if (allValues.getCreateMaterialDto().getMaterial().getMembraneLayerPolymerType_id() == 0) {
            isError = true;
        }
        if (allValues.getCreateMaterialDto().getMaterial().getGlueType_id() == 0) {
            isError = true;
        }
        if (!rad_btn_plus.isSelected() && !rad_btn_minus.isSelected()) {
            isError = true;
        }
        if (allValues.getCreateMaterialDto().getCondition().getPhysicalActivityType_id() == 0) {
            isError = true;
        }
        if (allValues.getCreateMaterialDto().getCondition().getResidenceTime() == 0) {
            isError = true;
        }
        if (inp_av_speed.getText().isEmpty()) {
            isError = true;
        }
        if (inp_min_temp_one.getText().isEmpty() || inp_min_temp_sec.getText().isEmpty()) {
            isError = true;
        }
        if (inp_max_air_one.getText().isEmpty() || inp_max_air_sec.getText().isEmpty()) {
            isError = true;
        }
        if (check_bend.isSelected() && allValues.getCreateMaterialDto().getCondition().getBendingType_id() == 0) {
            isError = true;
        }
        if (check_abrasion.isSelected() && allValues.getCreateMaterialDto().getCondition().getAbrasionType_id() == 0) {
            isError = true;
        }
        if (check_stretch_compress.isSelected() && inp_stretching.getText().isEmpty()) {
            isError = true;
        }
        if (check_torsion.isSelected() && inp_torsion_angle.getText().isEmpty()) {
            isError = true;
        }
        if (check_wash.isSelected()) {
            if (allValues.getCreateMaterialDto().getCondition().getWashing() != null) {
                if (
                        allValues.getCreateMaterialDto().getCondition().getWashing().getWashingType_id() == 0 ||
                                inp_temp_washing.getText().isEmpty() ||
                                inp_time_washing.getText().isEmpty() ||
                                inp_cycles_cnt.getText().isBlank() ||
                                (!rad_btn_yes.isSelected() && !rad_btn_no.isSelected())
                ) {
                    isError = true;
                }
            } else {
                isError = true;
            }
        }
        if (allValues.getCreateMaterialDto().getMaterial().getLayers() == null || allValues.getCreateMaterialDto().getMaterial().getLayers().isEmpty()) {
            isError = true;
        }
        if (isError) {
            allValues.setLastCreateMaterialComponent(Component.CONDITION_1);
            AlertUtil.show("Вы не заполнили все поля", "Закройте это окно и дозаполните всё необходимые поля", allValues.getRootStage());
            isError = false;
        } else {
            allValues.setLastCreateMaterialComponent(Component.WATERPROOF_TABLE);
            ComponentUtil.mount(Component.WATERPROOF_TABLE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            scroll_av_speed.valueProperty().addListener((observable, oldValue, newValue) -> {
                allValues.getCreateMaterialDto().getCondition().setAvgAirSpeed((int) scroll_av_speed.getValue());
                inp_av_speed.setText(String.valueOf(newValue.intValue()));
            });

            scroll_max_air_one.valueProperty().addListener((observable, oldValue, newValue) -> {
                allValues.getCreateMaterialDto().getCondition().setMinAirHumidity((int) scroll_max_air_one.getValue());
                inp_max_air_one.setText(String.valueOf((int) scroll_max_air_one.getValue()));
            });

            scroll_max_air_sec.valueProperty().addListener((observable, oldValue, newValue) -> {
                allValues.getCreateMaterialDto().getCondition().setMaxAirHumidity((int) scroll_max_air_sec.getValue());
                inp_max_air_sec.setText(String.valueOf((int) scroll_max_air_sec.getValue()));
            });

            scroll_min_temp_one.valueProperty().addListener((observable, oldValue, newValue) -> {
                allValues.getCreateMaterialDto().getCondition().setMinAirTemp((int) scroll_min_temp_one.getValue());
                inp_min_temp_one.setText(String.valueOf((int) scroll_min_temp_one.getValue()));
            });

            scroll_min_temp_sec.valueProperty().addListener((observable, oldValue, newValue) -> {
                allValues.getCreateMaterialDto().getCondition().setMaxAirTemp((int) scroll_min_temp_sec.getValue());
                inp_min_temp_sec.setText(String.valueOf((int) scroll_min_temp_sec.getValue()));
            });

            scroll_stretching.valueProperty().addListener((observable, oldValue, newValue) -> {
                allValues.getCreateMaterialDto().getCondition().setStretchingCompression((int) scroll_stretching.getValue());
                inp_stretching.setText(String.valueOf((int) scroll_stretching.getValue()));
            });

            scroll_torsion_angle.valueProperty().addListener((observable, oldValue, newValue) -> {
                allValues.getCreateMaterialDto().getCondition().setTorsionAngle((int) scroll_torsion_angle.getValue());
                inp_torsion_angle.setText(String.valueOf((int) scroll_torsion_angle.getValue()));
            });

            scroll_temp_washing.valueProperty().addListener((observable, oldValue, newValue) -> {
                allValues.getCreateMaterialDto().getCondition().getWashing().setTemperature((int) scroll_temp_washing.getValue());
                inp_temp_washing.setText(String.valueOf((int) scroll_temp_washing.getValue()));
            });

            scroll_time_washing.valueProperty().addListener((observable, oldValue, newValue) -> {
                allValues.getCreateMaterialDto().getCondition().getWashing().setDuration((int) scroll_time_washing.getValue());
                inp_time_washing.setText(String.valueOf((int) scroll_time_washing.getValue()));
            });

            inp_cycles_cnt.setTextFormatter(new TextFormatter<>(ValidationUtils.integerFilter));
            inp_cycles_cnt.textProperty().addListener((observable, oldValue, newValue) -> allValues.getCreateMaterialDto().getCondition().getWashing().setCyclesCnt(newValue == null ? 0 : Integer.parseInt(newValue)));

            manufacturer_inp.textProperty().addListener((observable, oldValue, newValue) -> allValues.getCreateMaterialDto().getMaterial().setManufacturer(newValue));

            depth_inp.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
            depth_inp.textProperty().addListener((observable, oldValue, newValue) -> allValues.getCreateMaterialDto().getMaterial().setDepth(Double.valueOf(newValue)));
        });

        position_column.setCellValueFactory(new PropertyValueFactory<>("firstCol"));
        layerName_column.setCellValueFactory(new PropertyValueFactory<>("secondCol"));
    }
}