package com.example.project_for_university.controllers.filter;

import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.forBackend.MaterialFilterDto;
import com.example.project_for_university.dto.forBackend.PaginationDto;
import com.example.project_for_university.dto.forBackend.entity.*;
import com.example.project_for_university.dto.forBackend.entity.types.*;
import com.example.project_for_university.enums.Component;
import com.example.project_for_university.providers.DataProvider;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class FilterController implements Initializable, DataProvider {

    private AllValues allValues;

    private int lastPage = 1;

    @FXML
    private ScrollPane materialList_scrollPane;

    @FXML
    private StackPane loader_contentPane;

    @FXML
    private HBox firstPage_btn;

    @FXML
    private HBox prevPage_btn;


    @FXML
    private HBox page1_btn;

    @FXML
    private Label page1_btnText;

    @FXML
    private HBox page2_btn;

    @FXML
    private Label page2_btnText;

    @FXML
    private HBox curPage_btn;

    @FXML
    private Label curPage_btnText;

    @FXML
    private HBox page4_btn;

    @FXML
    private Label page4_btnText;

    @FXML
    private HBox page5_btn;

    @FXML
    private Label page5_btnText;

    @FXML
    private HBox nextPage_btn;

    @FXML
    private HBox lastPage_btn;

    @FXML
    private CheckBox check_own_materials;

    @FXML
    private TextField blotting_pressure_inp_1;
    @FXML
    private TextField blotting_pressure_inp_2;

    @FXML
    private HBox btn_search;

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
    private ComboBox<Integer> num_layers_cb;

    @FXML
    private TextField relative_pressure_inp_1;

    @FXML
    private TextField relative_pressure_inp_2;

    @FXML
    private HBox btn_reset_filters;

    @FXML
    private TextField resistance_inp_1;

    @FXML
    private TextField resistance_inp_2;

    @FXML
    private TextField time_inp_1;

    @FXML
    private TextField time_inp_2;

    @FXML
    private ComboBox<String> typeMemb_cb;

    @FXML
    private TextField water_vapor_perm_inp_1;

    @FXML
    private TextField water_vapor_perm_inp_2;

    @FXML
    private ComboBox<String> way_prod_cb;

    @Override
    public void setData(AllValues allValues) {
        this.allValues = allValues;

        setDataToCb();

        if (allValues.getPaginationDto() == null) {
            allValues.setPaginationDto(new PaginationDto(1, 10));
        }
        allValues.setTotalMaterialsCnt(50);

        fillFilters();
        mountMaterials(getMaterials());
    }

    private void setDataToCb() {
        num_layers_cb.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6));
//        typeMemb_cb.setItems(Arrays.stream(allValues.getReturnAllTypesDto().getMembraneLayerPolymerTypes()).map(MembraneLayerPolymerTypeEntity::getName).collect(Collectors.toCollection(FXCollections::observableArrayList)));
//        way_prod_cb.setItems(Arrays.stream(allValues.getReturnAllTypesDto().getProductionMethods()).map(ProductionMethodEntity::getName).collect(Collectors.toCollection(FXCollections::observableArrayList)));
    }

    private void fillFilters() {
        if (allValues.getMaterialFilterDto() == null) {
            allValues.setMaterialFilterDto(new MaterialFilterDto());
        } else {
            if (allValues.getMaterialFilterDto().getUserId() != 0) {
                check_own_materials.setSelected(true);
            }
            if (allValues.getMaterialFilterDto().getName() != null) {
                name_inp.setText(allValues.getMaterialFilterDto().getName());
            }


            if (allValues.getMaterialFilterDto().getLayersCnt() != 0) {
                num_layers_cb.getSelectionModel().select(allValues.getMaterialFilterDto().getLayersCnt());
            }
            if (allValues.getMaterialFilterDto().getMembraneLayerPolymerType_id() != 0) {
//                typeMemb_cb.getSelectionModel().select();
            }
            if (allValues.getMaterialFilterDto().getProductionMethod_id() != 0) {
//                way_prod_cb.getSelectionModel().select();
            }


            if (allValues.getMaterialFilterDto().getDepth_min() != 0 || allValues.getMaterialFilterDto().getDepth_max() != 0) {
                check_depth.setSelected(true);
                depth_inp_1.setDisable(false);
                depth_inp_2.setDisable(false);
            }
            if (allValues.getMaterialFilterDto().getDepth_min() != 0) {
                depth_inp_1.setText(String.valueOf(allValues.getMaterialFilterDto().getDepth_min()));
            }
            if (allValues.getMaterialFilterDto().getDepth_max() != 0) {
                depth_inp_2.setText(String.valueOf(allValues.getMaterialFilterDto().getDepth_max()));
            }

            if (allValues.getMaterialFilterDto().getMaterialBlottingPressure_calculated_min() != 0 || allValues.getMaterialFilterDto().getMaterialBlottingPressure_calculated_max() != 0) {
                check_blotting_pressure.setSelected(true);
                blotting_pressure_inp_1.setDisable(false);
                blotting_pressure_inp_2.setDisable(false);
            }
            if (allValues.getMaterialFilterDto().getMaterialBlottingPressure_calculated_min() != 0) {
                blotting_pressure_inp_1.setText(String.valueOf(allValues.getMaterialFilterDto().getMaterialBlottingPressure_calculated_min()));
            }
            if (allValues.getMaterialFilterDto().getMaterialBlottingPressure_calculated_max() != 0) {
                blotting_pressure_inp_2.setText(String.valueOf(allValues.getMaterialFilterDto().getMaterialBlottingPressure_calculated_max()));
            }

            if (allValues.getMaterialFilterDto().getMaterialBlottingTime_calculated_min() != 0 || allValues.getMaterialFilterDto().getMaterialBlottingTime_calculated_max() != 0) {
                check_time.setSelected(true);
                time_inp_1.setDisable(false);
                time_inp_2.setDisable(false);
            }
            if (allValues.getMaterialFilterDto().getMaterialBlottingTime_calculated_min() != 0) {
                time_inp_1.setText(String.valueOf(allValues.getMaterialFilterDto().getMaterialBlottingTime_calculated_min()));
            }
            if (allValues.getMaterialFilterDto().getMaterialBlottingTime_calculated_max() != 0) {
                time_inp_2.setText(String.valueOf(allValues.getMaterialFilterDto().getMaterialBlottingTime_calculated_max()));
            }

            if (allValues.getMaterialFilterDto().getWaterPermeability_calculated_min() != 0 || allValues.getMaterialFilterDto().getWaterPermeability_calculated_max() != 0) {
                check_water_vapor_perm.setSelected(true);
                water_vapor_perm_inp_1.setDisable(false);
                water_vapor_perm_inp_2.setDisable(false);
            }
            if (allValues.getMaterialFilterDto().getWaterPermeability_calculated_min() != 0) {
                water_vapor_perm_inp_1.setText(String.valueOf(allValues.getMaterialFilterDto().getWaterPermeability_calculated_min()));
            }
            if (allValues.getMaterialFilterDto().getWaterPermeability_calculated_max() != 0) {
                water_vapor_perm_inp_2.setText(String.valueOf(allValues.getMaterialFilterDto().getWaterPermeability_calculated_max()));
            }

            if (allValues.getMaterialFilterDto().getTotalThermalResistance_calculated_min() != 0 || allValues.getMaterialFilterDto().getTotalThermalResistance_calculated_max() != 0) {
                check_resistance.setSelected(true);
                resistance_inp_1.setDisable(false);
                resistance_inp_2.setDisable(false);
            }
            if (allValues.getMaterialFilterDto().getTotalThermalResistance_calculated_min() != 0) {
                resistance_inp_1.setText(String.valueOf(allValues.getMaterialFilterDto().getTotalThermalResistance_calculated_min()));
            }
            if (allValues.getMaterialFilterDto().getTotalThermalResistance_calculated_max() != 0) {
                resistance_inp_2.setText(String.valueOf(allValues.getMaterialFilterDto().getTotalThermalResistance_calculated_max()));
            }

            if (allValues.getMaterialFilterDto().getRelativeBlottingPressureAfterLoad_relativeValuation_min() != 0 || allValues.getMaterialFilterDto().getRelativeBlottingPressureAfterLoad_relativeValuation_max() != 0) {
                check_relative_pressure.setSelected(true);
                relative_pressure_inp_1.setDisable(false);
                relative_pressure_inp_2.setDisable(false);
            }
            if (allValues.getMaterialFilterDto().getRelativeBlottingPressureAfterLoad_relativeValuation_min() != 0) {
                relative_pressure_inp_1.setText(String.valueOf(allValues.getMaterialFilterDto().getRelativeBlottingPressureAfterLoad_relativeValuation_min()));
            }
            if (allValues.getMaterialFilterDto().getRelativeBlottingPressureAfterLoad_relativeValuation_max() != 0) {
                relative_pressure_inp_2.setText(String.valueOf(allValues.getMaterialFilterDto().getRelativeBlottingPressureAfterLoad_relativeValuation_max()));
            }

        }
    }

    @FXML
    void check_own_materials(MouseEvent event) {
    }

    @FXML
    void check_blotting_pressure_clicked(MouseEvent event) {
        if (check_blotting_pressure.isSelected()) {
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
        if (check_depth.isSelected()) {
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
        if (check_relative_pressure.isSelected()) {
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
        if (check_resistance.isSelected()) {
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
        if (check_time.isSelected()) {
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
        if (check_water_vapor_perm.isSelected()) {
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
        MaterialFilterDto curFilterDto = allValues.getMaterialFilterDto();

        if (check_own_materials.isSelected()) {
            curFilterDto.setUserId(allValues.getUser().getId());
        }
        if (!name_inp.getText().isEmpty()) {
            curFilterDto.setName(name_inp.getText());
        }
        if (typeMemb_cb.getSelectionModel().getSelectedItem() != null) {
//            curFilterDto.setMembraneLayerPolymerType_id();
        }
        if (way_prod_cb.getSelectionModel().getSelectedItem() != null) {
//            curFilterDto.setProductionMethod_id();
        }
        if (num_layers_cb.getSelectionModel().getSelectedItem() != null) {
            curFilterDto.setLayersCnt(num_layers_cb.getSelectionModel().getSelectedItem());
        }

        if (check_depth.isSelected()) {
            if (depth_inp_1.getText().length() > 0) {
                curFilterDto.setDepth_min(Double.valueOf(depth_inp_1.getText()));
            }
            if (depth_inp_2.getText().length() > 0) {
                curFilterDto.setDepth_max(Double.valueOf(depth_inp_2.getText()));
            }
        }
        if (check_blotting_pressure.isSelected()) {
            if (blotting_pressure_inp_1.getText().length() > 0) {
                curFilterDto.setMaterialBlottingPressure_calculated_min(Double.valueOf(blotting_pressure_inp_1.getText()));
            }
            if (blotting_pressure_inp_2.getText().length() > 0) {
                curFilterDto.setMaterialBlottingPressure_calculated_max(Double.valueOf(blotting_pressure_inp_2.getText()));
            }
        }
        if (check_time.isSelected()) {
            if (time_inp_1.getText().length() > 0) {
                curFilterDto.setMaterialBlottingTime_calculated_min(Double.valueOf(time_inp_1.getText()));
            }
            if (time_inp_2.getText().length() > 0) {
                curFilterDto.setMaterialBlottingTime_calculated_max(Double.valueOf(time_inp_2.getText()));
            }
        }
        if (check_water_vapor_perm.isSelected()) {
            if (water_vapor_perm_inp_1.getText().length() > 0) {
                curFilterDto.setWaterPermeability_calculated_min(Double.valueOf(water_vapor_perm_inp_1.getText()));
            }
            if (water_vapor_perm_inp_2.getText().length() > 0) {
                curFilterDto.setWaterPermeability_calculated_max(Double.valueOf(water_vapor_perm_inp_2.getText()));
            }
        }
        if (check_resistance.isSelected()) {
            if (resistance_inp_1.getText().length() > 0) {
                curFilterDto.setTotalThermalResistance_calculated_min(Double.valueOf(resistance_inp_1.getText()));
            }
            if (resistance_inp_2.getText().length() > 0) {
                curFilterDto.setTotalThermalResistance_calculated_max(Double.valueOf(resistance_inp_2.getText()));
            }
        }
        if (check_relative_pressure.isSelected()) {
            if (relative_pressure_inp_1.getText().length() > 0) {
                curFilterDto.setRelativeBlottingPressureAfterLoad_relativeValuation_min(Double.valueOf(relative_pressure_inp_1.getText()));
            }
            if (relative_pressure_inp_2.getText().length() > 0) {
                curFilterDto.setRelativeBlottingPressureAfterLoad_relativeValuation_max(Double.valueOf(relative_pressure_inp_2.getText()));
            }
        }

        allValues.setMaterialFilterDto(curFilterDto);
        allValues.getPaginationDto().setPage(1);
        mountMaterials(getMaterials());
    }

    @FXML
    void btn_reset_filters_clicked(MouseEvent event) {
        allValues.getPaginationDto().setPage(1);
        allValues.setMaterialFilterDto(null);

        check_own_materials.setSelected(false);
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

        time_inp_1.setDisable(true);
        time_inp_2.setDisable(true);
        resistance_inp_1.setDisable(true);
        resistance_inp_2.setDisable(true);
        water_vapor_perm_inp_1.setDisable(true);
        water_vapor_perm_inp_2.setDisable(true);
        relative_pressure_inp_1.setDisable(true);
        relative_pressure_inp_2.setDisable(true);
        depth_inp_1.setDisable(true);
        depth_inp_2.setDisable(true);
        blotting_pressure_inp_1.setDisable(true);
        blotting_pressure_inp_2.setDisable(true);
    }

    @FXML
    void prevPage_btn_clicked(MouseEvent event) {
        int page = allValues.getPaginationDto().getPage();
        if (page > 1) {
            allValues.getPaginationDto().setPage(page - 1);
        }
        mountMaterials(getMaterials());
    }

    @FXML
    void firstPage_btn_clicked(MouseEvent event) {
        allValues.getPaginationDto().setPage(1);
        mountMaterials(getMaterials());
    }

    @FXML
    void page1_btn_clicked(MouseEvent event) {
        allValues.getPaginationDto().setPage(Integer.valueOf(page1_btnText.getText()));
        mountMaterials(getMaterials());
    }

    @FXML
    void page2_btn_clicked(MouseEvent event) {
        allValues.getPaginationDto().setPage(Integer.valueOf(page2_btnText.getText()));
        mountMaterials(getMaterials());
    }

    @FXML
    void curPage_btn_clicked(MouseEvent event) {
    }

    @FXML
    void page4_btn_clicked(MouseEvent event) {
        allValues.getPaginationDto().setPage(Integer.valueOf(page4_btnText.getText()));
        mountMaterials(getMaterials());
    }

    @FXML
    void page5_btn_clicked(MouseEvent event) {
        allValues.getPaginationDto().setPage(Integer.valueOf(page5_btnText.getText()));
        mountMaterials(getMaterials());
    }

    @FXML
    void lastPage_btn_clicked(MouseEvent event) {
        allValues.getPaginationDto().setPage(lastPage);
        mountMaterials(getMaterials());
    }

    @FXML
    void nextPage_btn_clicked(MouseEvent event) {
        allValues.getPaginationDto().setPage(allValues.getPaginationDto().getPage() + 1);
        mountMaterials(getMaterials());
    }

    private int calcLastPage() {
        if (allValues.getTotalMaterialsCnt() <= 0) {
            lastPage = 1;
        } else {
            lastPage = allValues.getTotalMaterialsCnt() / allValues.getPaginationDto().getPerPage();
        }
        return lastPage;
    }

    private void displayNode(Node node, boolean isDisplay) {
        node.setVisible(isDisplay);
        node.setManaged(isDisplay);
    }

    private void setPagination() {
        int page = allValues.getPaginationDto().getPage();

        if (page < 2) {
            displayNode(firstPage_btn, false);
            displayNode(prevPage_btn, false);
            displayNode(page1_btn, false);
            displayNode(page2_btn, false);
        } else if (page == 2) {
            displayNode(firstPage_btn, true);
            displayNode(prevPage_btn, true);
            displayNode(page1_btn, false);
            displayNode(page2_btn, true);
        } else {
            displayNode(firstPage_btn, true);
            displayNode(prevPage_btn, true);
            displayNode(page1_btn, true);
            displayNode(page2_btn, true);
        }

        if (page >= lastPage) {
            displayNode(lastPage_btn, false);
            displayNode(nextPage_btn, false);
            displayNode(page4_btn, false);
            displayNode(page5_btn, false);
        } else if (page == lastPage - 1) {
            displayNode(lastPage_btn, true);
            displayNode(nextPage_btn, true);
            displayNode(page4_btn, true);
            displayNode(page5_btn, false);
        } else {
            displayNode(lastPage_btn, true);
            displayNode(nextPage_btn, true);
            displayNode(page4_btn, true);
            displayNode(page5_btn, true);
        }

        page1_btnText.setText(String.valueOf(page - 2));
        page2_btnText.setText(String.valueOf(page - 1));
        curPage_btnText.setText(String.valueOf(page));
        page4_btnText.setText(String.valueOf(page + 1));
        page5_btnText.setText(String.valueOf(page + 2));
    }

    private void mountMaterials(ArrayList<PartialMaterialEntity> materials) {
        list_materials.getChildren().clear();
        materialList_scrollPane.setVvalue(0);

        calcLastPage();
        setPagination();

        Platform.runLater(() -> {
            for (int i = 0; i < materials.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource(Component.MATERIAL_ITEM.getPath()));

                try {
                    HBox materialItem = fxmlLoader.load();
                    MaterialController materialController = fxmlLoader.getController();

                    materialController.setMaterial(materials.get(i));
                    materialController.setData(allValues);

                    list_materials.getChildren().removeAll();
                    list_materials.getChildren().add(materialItem);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }


    //тут будет делаться запрос на сервер на получение материалов с фильтарми и page, perPage
    private ArrayList<PartialMaterialEntity> getMaterials() {
        if (allValues.getLoadedMaterials() == null) {
            allValues.setLoadedMaterials(new ArrayList<>());
        }

        //запрос на сервер


        //тестовые данные
        ArrayList<PartialMaterialEntity> testMaterials = new ArrayList<>();
        ConditionEntity condition = new ConditionEntity(1, true, 1, 1, 1, 1, 1, 1, 1, 1, null, new WashingEntity(1, 1, 1, 1, true, new WashingTypeEntity(1, "washing")), null, new PhysicalActivityTypeEntity(1, "act", "desc"));

        int page = allValues.getPaginationDto().getPage();
        if (page == 1) {
            testMaterials.add(new PartialMaterialEntity(1, "name1", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name2", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name3", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name4", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name5", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU", "https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name6", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name7", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name8", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU", "https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name9", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name10", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
        } else if (page == 2) {
            testMaterials.add(new PartialMaterialEntity(1, "name11", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name12", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name13", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name14", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name15", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU", "https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name16", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name17", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name18", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU", "https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name19", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name20", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
        } else if (page == 3) {
            testMaterials.add(new PartialMaterialEntity(1, "name21", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name22", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name23", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name24", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name25", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU", "https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name26", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name27", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name28", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU", "https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name29", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name30", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
        } else if (page > 3) {
            testMaterials.add(new PartialMaterialEntity(1, "name31", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name32", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name33", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name34", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name35", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU", "https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name36", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name37", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name38", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU", "https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name39", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{}, new UserEntity(1, "userName", "email", "pass", false)));
            testMaterials.add(new PartialMaterialEntity(1, "name40", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
        }
        //--------

        allValues.setLoadedMaterials(testMaterials);

        return allValues.getLoadedMaterials();
    }

    // фильтр для валидации инпутов
    private final UnaryOperator<TextFormatter.Change> filter = change -> {
        if (change.isReplaced()) {
            if (change.getText().matches("[^0-9]")) {
                change.setText(change.getControlText().substring(change.getRangeStart(), change.getRangeEnd()));
            }
        }
        if (change.isAdded()) {
            if (change.getControlText().contains(".")) {
                if (change.getText().matches("[^0-9]")) {
                    change.setText("");
                }
            } else if (change.getText().matches("[^0-9.]")) {
                change.setText("");
            }
        }
        return change;
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        depth_inp_1.setTextFormatter(new TextFormatter<>(filter));
        depth_inp_2.setTextFormatter(new TextFormatter<>(filter));

        blotting_pressure_inp_1.setTextFormatter(new TextFormatter<>(filter));
        blotting_pressure_inp_2.setTextFormatter(new TextFormatter<>(filter));

        time_inp_1.setTextFormatter(new TextFormatter<>(filter));
        time_inp_2.setTextFormatter(new TextFormatter<>(filter));

        water_vapor_perm_inp_1.setTextFormatter(new TextFormatter<>(filter));
        water_vapor_perm_inp_2.setTextFormatter(new TextFormatter<>(filter));

        resistance_inp_1.setTextFormatter(new TextFormatter<>(filter));
        resistance_inp_2.setTextFormatter(new TextFormatter<>(filter));

        relative_pressure_inp_1.setTextFormatter(new TextFormatter<>(filter));
        relative_pressure_inp_2.setTextFormatter(new TextFormatter<>(filter));
    }

}
