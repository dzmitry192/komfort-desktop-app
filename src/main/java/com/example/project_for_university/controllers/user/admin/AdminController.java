package com.example.project_for_university.controllers.user.admin;

import com.example.project_for_university.controllers.user.admin.models.AdminPanelInfo;
import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.enums.AdminPanelType;
import com.example.project_for_university.enums.Component;
import com.example.project_for_university.providers.DataProvider;
import com.example.project_for_university.utils.ComponentUtil;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class AdminController implements DataProvider {
    private AllValues allValues;

    private static final AdminPanelInfo adminPanelInfo = new AdminPanelInfo();

    @FXML
    private HBox btn_abrasion_type;

    @FXML
    private HBox btn_production_type;

    @FXML
    private HBox btn_bend_type;

    @FXML
    private HBox btn_glue_type;

    @FXML
    private HBox btn_lay_type;

    @FXML
    private HBox btn_lev_activ_type;

    @FXML
    private HBox btn_membr_lay_type;

    @FXML
    private HBox btn_washing_type;

    @Override
    public void setData(AllValues allValues) {
        this.allValues = allValues;
    }

    @FXML
    void btn_abrasion_type_clicked(MouseEvent event) throws IOException, ExecutionException, InterruptedException {
        adminPanelInfo.setCurAdminPanelType(AdminPanelType.ABRASION);
        allValues.setAdminPanelInfo(adminPanelInfo);
        ComponentUtil.mount(Component.TYPE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @FXML
    void btn_bend_type_clicked(MouseEvent event) throws IOException, ExecutionException, InterruptedException {
        adminPanelInfo.setCurAdminPanelType(AdminPanelType.BENDING);
        adminPanelInfo.setReturnAllTypesDto(allValues.getAdminPanelInfo().getReturnAllTypesDto());
        allValues.setAdminPanelInfo(adminPanelInfo);
        ComponentUtil.mount(Component.TYPE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @SneakyThrows
    @FXML
    void btn_production_type_clicked(MouseEvent event) throws IOException {
        adminPanelInfo.setCurAdminPanelType(AdminPanelType.PRODUCTION_METHOD);
        adminPanelInfo.setReturnAllTypesDto(allValues.getAdminPanelInfo().getReturnAllTypesDto());
        allValues.setAdminPanelInfo(adminPanelInfo);
        ComponentUtil.mount(Component.TYPE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @FXML
    void btn_glue_type_clicked(MouseEvent event) throws IOException, ExecutionException, InterruptedException {
        adminPanelInfo.setCurAdminPanelType(AdminPanelType.GLUE);
        adminPanelInfo.setReturnAllTypesDto(allValues.getAdminPanelInfo().getReturnAllTypesDto());
        allValues.setAdminPanelInfo(adminPanelInfo);
        ComponentUtil.mount(Component.TYPE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @SneakyThrows
    @FXML
    void btn_lay_type_clicked(MouseEvent event) throws IOException {
        adminPanelInfo.setCurAdminPanelType(AdminPanelType.LAYER);
        adminPanelInfo.setReturnAllTypesDto(allValues.getAdminPanelInfo().getReturnAllTypesDto());
        allValues.setAdminPanelInfo(adminPanelInfo);
        ComponentUtil.mount(Component.TYPE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @SneakyThrows
    @FXML
    void btn_lev_activ_type_clicked(MouseEvent event) throws IOException {
        adminPanelInfo.setCurAdminPanelType(AdminPanelType.PHYSICAL_ACTIVITY);
        adminPanelInfo.setReturnAllTypesDto(allValues.getAdminPanelInfo().getReturnAllTypesDto());
        allValues.setAdminPanelInfo(adminPanelInfo);
        ComponentUtil.mount(Component.TYPE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @SneakyThrows
    @FXML
    void btn_membr_lay_type_clicked(MouseEvent event) throws IOException {
        adminPanelInfo.setCurAdminPanelType(AdminPanelType.MEMBRANE_LAYER_POLYMER);
        adminPanelInfo.setReturnAllTypesDto(allValues.getAdminPanelInfo().getReturnAllTypesDto());
        allValues.setAdminPanelInfo(adminPanelInfo);
        ComponentUtil.mount(Component.TYPE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }

    @SneakyThrows
    @FXML
    void btn_washing_type_clicked(MouseEvent event) throws IOException {
        adminPanelInfo.setCurAdminPanelType(AdminPanelType.WASHING);
        adminPanelInfo.setReturnAllTypesDto(allValues.getAdminPanelInfo().getReturnAllTypesDto());
        allValues.setAdminPanelInfo(adminPanelInfo);
        ComponentUtil.mount(Component.TYPE, allValues.getContentPanes().getLoggedInStackPane(), allValues);
    }
}
