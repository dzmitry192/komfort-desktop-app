package com.example.project_for_university.controllers;

import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.ContentPanes;
import com.example.project_for_university.dto.forBackend.entity.UserEntity;
import com.example.project_for_university.enums.Component;
import com.example.project_for_university.providers.DataProvider;
import com.example.project_for_university.utils.ComponentUtil;
import javafx.application.Platform;
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
        ComponentUtil.mount(Component.ADMIN_PANEL, loggedInContentPane, allValues);
    }

    @SneakyThrows
    @FXML
    void createMaterial_btn_click(MouseEvent event) {
        toggleActiveButton(createMaterial_btn);

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
        ComponentUtil.mount(Component.FILTER, loggedInContentPane, allValues);
    }

    @SneakyThrows
    @FXML
    void logout_btn_click(MouseEvent event) {
        ComponentUtil.mount(Component.LOGIN, allValues.getContentPanes().getMainContentPane(), allValues);
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
