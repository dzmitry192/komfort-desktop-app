package com.example.project_for_university.controllers.filter;

import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.forBackend.MaterialFilterDto;
import com.example.project_for_university.dto.forBackend.PaginationDto;
import com.example.project_for_university.dto.forBackend.entity.*;
import com.example.project_for_university.dto.forBackend.entity.types.*;
import com.example.project_for_university.enums.Component;
import com.example.project_for_university.providers.DataProvider;
import com.example.project_for_university.service.FilterSearchService;
import com.example.project_for_university.service.models.FilterMaterialsModel;
import com.example.project_for_university.utils.ValidationUtils;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class FilterController implements Initializable, DataProvider {

    private AllValues allValues;
    private HashMap<String, String> queryParams;
    private final FilterSearchService filterSearchService = new FilterSearchService();
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
    private ComboBox<String> num_layers_cb;

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
    public void setData(AllValues allValues) throws ExecutionException, InterruptedException {
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
        num_layers_cb.setItems(FXCollections.observableArrayList("Не выбрано", "1", "2", "3", "4", "5","6"));
        ObservableList<String> membraneLayerPolymerTypes = FXCollections.observableArrayList("Не выбрано");
        membraneLayerPolymerTypes.addAll(Arrays.stream(allValues.getReturnAllTypesDto().getMembraneLayerPolymerTypes()).map(MembraneLayerPolymerTypeEntity::getName).toList());
        typeMemb_cb.setItems(membraneLayerPolymerTypes);
        ObservableList<String> productionMethods = FXCollections.observableArrayList("Не выбрано");
        productionMethods.addAll(Arrays.stream(allValues.getReturnAllTypesDto().getProductionMethods()).map(ProductionMethodEntity::getName).toList());
        way_prod_cb.setItems(productionMethods);
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
                typeMemb_cb.getSelectionModel().select(allValues.getMaterialFilterDto().getMembraneLayerPolymerType_id());
            }
            if (allValues.getMaterialFilterDto().getProductionMethod_id() != 0) {
                way_prod_cb.getSelectionModel().select(allValues.getMaterialFilterDto().getProductionMethod_id());
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
    void btn_search_clicked(MouseEvent event) throws ExecutionException, InterruptedException {
        MaterialFilterDto curFilterDto = allValues.getMaterialFilterDto();

        queryParams = new HashMap<>();

        if (check_own_materials.isSelected()) {
            curFilterDto.setUserId(allValues.getUser().getId());
            queryParams.put("userId", String.valueOf(curFilterDto.getUserId()));
        }


        if (!name_inp.getText().isEmpty()) {
            curFilterDto.setName(name_inp.getText());
            queryParams.put("name", curFilterDto.getName());
        }
        if (typeMemb_cb.getSelectionModel().getSelectedItem() != null && !typeMemb_cb.getSelectionModel().getSelectedItem().equals("Не выбрано")) {
            curFilterDto.setMembraneLayerPolymerType_id(way_prod_cb.getSelectionModel().getSelectedIndex());
            queryParams.put("membraneLayerPolymerType_id", String.valueOf(Arrays.stream(allValues.getReturnAllTypesDto().getMembraneLayerPolymerTypes()).filter(type -> type.getName().equals(typeMemb_cb.getSelectionModel().getSelectedItem())).findFirst().get().getId()));
        }
        if (way_prod_cb.getSelectionModel().getSelectedItem() != null && !typeMemb_cb.getSelectionModel().getSelectedItem().equals("Не выбрано")) {
            curFilterDto.setProductionMethod_id(way_prod_cb.getSelectionModel().getSelectedIndex());
            queryParams.put("productionMethod_id", String.valueOf(Arrays.stream(allValues.getReturnAllTypesDto().getProductionMethods()).filter(method -> method.getName().equals(way_prod_cb.getSelectionModel().getSelectedItem())).findFirst().get().getId()));
        }
        if (num_layers_cb.getSelectionModel().getSelectedItem() != null && !num_layers_cb.getSelectionModel().getSelectedItem().equals("Не выбрано")) {
            curFilterDto.setLayersCnt(Integer.parseInt(num_layers_cb.getSelectionModel().getSelectedItem()));
            queryParams.put("layersCnt", String.valueOf(curFilterDto.getLayersCnt()));
        }

        if (check_depth.isSelected()) {
            if (!depth_inp_1.getText().isEmpty()) {
                curFilterDto.setDepth_min(Double.parseDouble(depth_inp_1.getText()));
                queryParams.put("depth_min", String.valueOf(curFilterDto.getDepth_min()));
            }
            if (!depth_inp_2.getText().isEmpty()) {
                curFilterDto.setDepth_max(Double.parseDouble(depth_inp_2.getText()));
                queryParams.put("depth_max", String.valueOf(curFilterDto.getDepth_max()));
            }
        }
        if (check_blotting_pressure.isSelected()) {
            if (!blotting_pressure_inp_1.getText().isEmpty()) {
                curFilterDto.setMaterialBlottingPressure_calculated_min(Double.parseDouble(blotting_pressure_inp_1.getText()));
                queryParams.put("materialBlottingPressure_calculated_min", String.valueOf(curFilterDto.getMaterialBlottingTime_calculated_min()));
            }
            if (!blotting_pressure_inp_2.getText().isEmpty()) {
                curFilterDto.setMaterialBlottingPressure_calculated_max(Double.parseDouble(blotting_pressure_inp_2.getText()));
                queryParams.put("materialBlottingPressure_calculated_max", String.valueOf(curFilterDto.getMaterialBlottingPressure_calculated_max()));
            }
        }
        if (check_time.isSelected()) {
            if (!time_inp_1.getText().isEmpty()) {
                curFilterDto.setMaterialBlottingTime_calculated_min(Double.parseDouble(time_inp_1.getText()));
                queryParams.put("materialBlottingTime_calculated_min", String.valueOf(curFilterDto.getMaterialBlottingTime_calculated_min()));
            }
            if (!time_inp_2.getText().isEmpty()) {
                curFilterDto.setMaterialBlottingTime_calculated_max(Double.parseDouble(time_inp_2.getText()));
                queryParams.put("materialBlottingTime_calculated_max", String.valueOf(curFilterDto.getMaterialBlottingTime_calculated_max()));
            }
        }
        if (check_water_vapor_perm.isSelected()) {
            if (!water_vapor_perm_inp_1.getText().isEmpty()) {
                curFilterDto.setWaterPermeability_calculated_min(Double.parseDouble(water_vapor_perm_inp_1.getText()));
                queryParams.put("waterPermeability_calculated_min", String.valueOf(curFilterDto.getWaterPermeability_calculated_min()));
            }
            if (!water_vapor_perm_inp_2.getText().isEmpty()) {
                curFilterDto.setWaterPermeability_calculated_max(Double.parseDouble(water_vapor_perm_inp_2.getText()));
                queryParams.put("waterPermeability_calculated_max", String.valueOf(curFilterDto.getWaterPermeability_calculated_max()));
            }
        }
        if (check_resistance.isSelected()) {
            if (!resistance_inp_1.getText().isEmpty()) {
                curFilterDto.setTotalThermalResistance_calculated_min(Double.parseDouble(resistance_inp_1.getText()));
                queryParams.put("totalThermalResistance_calculated_min", String.valueOf(curFilterDto.getTotalThermalResistance_calculated_min()));
            }
            if (!resistance_inp_2.getText().isEmpty()) {
                curFilterDto.setTotalThermalResistance_calculated_max(Double.parseDouble(resistance_inp_2.getText()));
                queryParams.put("totalThermalResistance_calculated_max", String.valueOf(curFilterDto.getTotalThermalResistance_calculated_max()));
            }
        }
        if (check_relative_pressure.isSelected()) {
            if (!relative_pressure_inp_1.getText().isEmpty()) {
                curFilterDto.setRelativeBlottingPressureAfterLoad_relativeValuation_min(Double.parseDouble(relative_pressure_inp_1.getText()));
                queryParams.put("relativeBlottingPressureAfterLoad_relativeValuation_min", String.valueOf(curFilterDto.getRelativeBlottingPressureAfterLoad_relativeValuation_min()));
            }
            if (!relative_pressure_inp_2.getText().isEmpty()) {
                curFilterDto.setRelativeBlottingPressureAfterLoad_relativeValuation_max(Double.parseDouble(relative_pressure_inp_2.getText()));
                queryParams.put("relativeBlottingPressureAfterLoad_relativeValuation_max", String.valueOf(curFilterDto.getRelativeBlottingPressureAfterLoad_relativeValuation_max()));
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
    void prevPage_btn_clicked(MouseEvent event) throws ExecutionException, InterruptedException {
        int page = allValues.getPaginationDto().getPage();
        if (page > 1) {
            allValues.getPaginationDto().setPage(page - 1);
        }
        mountMaterials(getMaterials());
    }

    @FXML
    void firstPage_btn_clicked(MouseEvent event) throws ExecutionException, InterruptedException {
        allValues.getPaginationDto().setPage(1);
        mountMaterials(getMaterials());
    }

    @FXML
    void page1_btn_clicked(MouseEvent event) throws ExecutionException, InterruptedException {
        allValues.getPaginationDto().setPage(Integer.valueOf(page1_btnText.getText()));
        mountMaterials(getMaterials());
    }

    @FXML
    void page2_btn_clicked(MouseEvent event) throws ExecutionException, InterruptedException {
        allValues.getPaginationDto().setPage(Integer.valueOf(page2_btnText.getText()));
        mountMaterials(getMaterials());
    }

    @FXML
    void curPage_btn_clicked(MouseEvent event) {
    }

    @FXML
    void page4_btn_clicked(MouseEvent event) throws ExecutionException, InterruptedException {
        allValues.getPaginationDto().setPage(Integer.valueOf(page4_btnText.getText()));
        mountMaterials(getMaterials());
    }

    @FXML
    void page5_btn_clicked(MouseEvent event) throws ExecutionException, InterruptedException {
        allValues.getPaginationDto().setPage(Integer.valueOf(page5_btnText.getText()));
        mountMaterials(getMaterials());
    }

    @FXML
    void lastPage_btn_clicked(MouseEvent event) throws ExecutionException, InterruptedException {
        allValues.getPaginationDto().setPage(lastPage);
        mountMaterials(getMaterials());
    }

    @FXML
    void nextPage_btn_clicked(MouseEvent event) throws ExecutionException, InterruptedException {
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


    //тут будет делаться запрос на сервер на получение материалов с фильтрами и page, perPage
    private ArrayList<PartialMaterialEntity> getMaterials() throws ExecutionException, InterruptedException {
        if (allValues.getLoadedMaterials() == null) {
            allValues.setLoadedMaterials(new ArrayList<>());
        }

        ArrayList<PartialMaterialEntity> materials = new ArrayList<>();
        if(allValues.getMaterialFilterDto().getName() != null) {
            FilterMaterialsModel filterMaterialsModel = filterSearchService.getFilterMaterialsThread(allValues, queryParams);
            if(!filterMaterialsModel.isError()) {
                materials.addAll(Arrays.asList(filterMaterialsModel.getPartialMaterials()));
            } else {
                System.out.println("НЕ УДАЛОСЬ ПОЛУЧИТЬ МАТЕРИАЛЫ ИЗ БД");
            }
        }
//        //тестовые данные
//        ArrayList<PartialMaterialEntity> testMaterials = new ArrayList<>();
//        ConditionEntity condition = new ConditionEntity(1, true, 1, 1, 1, 1, 1, 1, 1, 1, null, new WashingEntity(1, 1, 1, 1, true, new WashingTypeEntity(1, "washing")), null, new PhysicalActivityTypeEntity(1, "act", "desc"));
//
//        int page = allValues.getPaginationDto().getPage();
//        if (page == 1) {
//            testMaterials.add(new PartialMaterialEntity(1, "name1", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name2", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name3", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name4", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name5", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU", "https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name6", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name7", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name8", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU", "https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name9", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name10", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
//        } else if (page == 2) {
//            testMaterials.add(new PartialMaterialEntity(1, "name11", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name12", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name13", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name14", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name15", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU", "https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name16", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name17", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name18", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU", "https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name19", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name20", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
//        } else if (page == 3) {
//            testMaterials.add(new PartialMaterialEntity(1, "name21", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name22", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name23", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name24", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name25", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU", "https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name26", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name27", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name28", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU", "https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name29", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name30", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
//        } else if (page > 3) {
//            testMaterials.add(new PartialMaterialEntity(1, "name31", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name32", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name33", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name34", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name35", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU", "https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name36", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name37", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name38", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU", "https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name39", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{}, new UserEntity(1, "userName", "email", "pass", false)));
//            testMaterials.add(new PartialMaterialEntity(1, "name40", "description description description description description description", "manufacturer", 10, condition, new LayerEntity[]{new LayerEntity(1, 1, new LayerTypeEntity(1, "fdsafsf"))}, new String[]{"https://avatars.githubusercontent.com/u/95999531?v=4", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfiAsmz9QJAl1zQuMB98yf3rje25gDaZbZyZ3VpaDl1-yZwfd3nWfW918AvHR449ePXKM&usqp=CAU", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQm8nQdinoQx9ed3qju0E6e-C4ve5eDbZhRm-SqGchXgaI72-Y2oC7tpzRr4tFmYvfMxU4&usqp=CAU"}, new UserEntity(1, "userName", "email", "pass", false)));
//        }
//        //--------

        allValues.setLoadedMaterials(materials);

        return allValues.getLoadedMaterials();
    }

    // фильтр для валидации инпутов


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        depth_inp_1.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        depth_inp_2.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));

        blotting_pressure_inp_1.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        blotting_pressure_inp_2.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));

        time_inp_1.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        time_inp_2.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));

        water_vapor_perm_inp_1.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        water_vapor_perm_inp_2.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));

        resistance_inp_1.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        resistance_inp_2.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));

        relative_pressure_inp_1.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));
        relative_pressure_inp_2.setTextFormatter(new TextFormatter<>(ValidationUtils.doubleFilter));


    }
}
