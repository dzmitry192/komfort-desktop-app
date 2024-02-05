package com.example.project_for_university.controllers;

import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.ContentPanes;
import com.example.project_for_university.dto.forBackend.ReturnAllTypesDto;
import com.example.project_for_university.dto.forBackend.entity.ProductionMethodEntity;
import com.example.project_for_university.dto.forBackend.entity.UserEntity;
import com.example.project_for_university.dto.forBackend.entity.types.MembraneLayerPolymerTypeEntity;
import com.example.project_for_university.enums.Component;
import com.example.project_for_university.providers.DataProvider;
import com.example.project_for_university.service.AllTypesService;
import com.example.project_for_university.service.admin.MembraneLayerPolymerTypeService;
import com.example.project_for_university.service.admin.ProductionMethodService;
import com.example.project_for_university.service.models.TypeResponse;
import com.example.project_for_university.service.models.TypesResponse;
import com.example.project_for_university.service.models.get.GetAllTypesResponse;
import com.example.project_for_university.utils.AlertUtil;
import com.example.project_for_university.utils.ComponentUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Data
public class LoggedInPageController implements Initializable, DataProvider {

    private AllValues allValues;
    private AllTypesService allTypesService = new AllTypesService();

    @FXML
    private Text userName_text;

    @FXML
    private HBox adminPanel_btn;

    @FXML
    private HBox createMaterial_btn;

    @FXML
    private StackPane loggedInContentPane;

    @FXML
    private HBox logout_btn;

    @FXML
    private HBox materialList_btn;

    private ArrayList<HBox> sideBarButtons = new ArrayList<>();

    @SneakyThrows
    @Override
    public void setData(AllValues allValues) throws IOException {
        this.allValues = allValues;
        UserEntity user = allValues.getUser();

        userName_text.setText(user.getFio());
        if (user.isAdmin()) {
            adminPanel_btn.setManaged(true);
            adminPanel_btn.setVisible(true);
        }

        allValues.setContentPanes(new ContentPanes(allValues.getContentPanes().getMainContentPane(), loggedInContentPane));
        allValues.setSideBarButtons(sideBarButtons);

        if (allValues.getLastCreateMaterialComponent() != null) {
            ComponentUtil.mount(allValues.getLastCreateMaterialComponent(), loggedInContentPane, allValues);
        } else {
            ComponentUtil.mount(Component.CONDITION_1, loggedInContentPane, allValues);
        }
    }

    private void toggleActiveButton(HBox curActiveButton) {
        for (HBox btn : sideBarButtons) {
            if (btn == curActiveButton) {
                btn.getStyleClass().add("active");
            } else {
                btn.getStyleClass().removeAll("active");
            }
            btn.applyCss();
        }
    }

    @SneakyThrows
    @FXML
    void adminPanel_btn_click(MouseEvent event) {
        toggleActiveButton(adminPanel_btn);
        GetAllTypesResponse getAllTypesResponse = AllTypesService.INSTANCE.getAllTypesThread(allValues.getUser().getEmail(), allValues.getUser().getPassword());
        if(getAllTypesResponse.isError()) {
            AlertUtil.show("Ошибка при получении типов", getAllTypesResponse.getMessage(), allValues.getRootStage());
        } else {
            allValues.getAdminPanelInfo().setReturnAllTypesDto(getAllTypesResponse.getReturnAllTypesDto());
            ComponentUtil.mount(Component.ADMIN_PANEL, loggedInContentPane, allValues);
        }
    }

    @SneakyThrows
    @FXML
    void createMaterial_btn_click(MouseEvent event) {
        toggleActiveButton(createMaterial_btn);

        allValues.getAdminPanelInfo().setReturnAllTypesDto(allTypesService.getAllTypesThread(allValues.getUser().getEmail(), allValues.getUser().getPassword()).getReturnAllTypesDto());
        if (allValues.getLastCreateMaterialComponent() != null) {
            ComponentUtil.mount(allValues.getLastCreateMaterialComponent(), loggedInContentPane, allValues);
        } else {
            ComponentUtil.mount(Component.CONDITION_1, loggedInContentPane, allValues);
        }
    }

    @SneakyThrows
    @FXML
    void materialList_btn_click(MouseEvent event) {
        toggleActiveButton(materialList_btn);

        TypesResponse<ProductionMethodEntity> prodMethodsResponse = ProductionMethodService.INSTANCE.getAll(allValues.getUser().getEmail(), allValues.getUser().getPassword());
        if(prodMethodsResponse.isError()) {
            AlertUtil.show("Ошибка при получении типа", prodMethodsResponse.getMessage(), allValues.getRootStage());
        } else {
            TypesResponse<MembraneLayerPolymerTypeEntity> membTypesResponse = MembraneLayerPolymerTypeService.INSTANCE.getAll(allValues.getUser().getEmail(), allValues.getUser().getPassword());
            if(membTypesResponse.isError()) {
                AlertUtil.show("Ошибка при получении типа", membTypesResponse.getMessage(), allValues.getRootStage());
            } else {
                allValues.getAdminPanelInfo().getReturnAllTypesDto().setMembraneLayerPolymerTypes(membTypesResponse.getTypes());
                allValues.getAdminPanelInfo().getReturnAllTypesDto().setProductionMethods(prodMethodsResponse.getTypes());
                ComponentUtil.mount(Component.FILTER, loggedInContentPane, allValues);
            }
        }
    }

    @SneakyThrows
    @FXML
    void logout_btn_click(MouseEvent event) {
        AllValues cleanAllValues = new AllValues();
        cleanAllValues.setRootStage(allValues.getRootStage());
        cleanAllValues.setContentPanes(allValues.getContentPanes());
        allValues = null;

        ComponentUtil.mount(Component.LOGIN, cleanAllValues.getContentPanes().getMainContentPane(), cleanAllValues);
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adminPanel_btn.setManaged(false);
        adminPanel_btn.setVisible(false);

        sideBarButtons.add(adminPanel_btn);
        sideBarButtons.add(createMaterial_btn);
        sideBarButtons.add(materialList_btn);
        sideBarButtons.add(logout_btn);
        toggleActiveButton(createMaterial_btn);
    }
}
