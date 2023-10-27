package com.example.project_for_university.utils;
import com.example.project_for_university.controllers.material.MaterialDetailsController;
import com.example.project_for_university.controllers.user.admin.CreateTypeController;
import com.example.project_for_university.controllers.user.admin.TypeController;
import com.example.project_for_university.controllers.user.admin.models.AbstractType;
import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.forBackend.entity.types.PartialMaterialEntity;
import com.example.project_for_university.enums.Component;
import com.example.project_for_university.providers.DataProvider;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Objects;

public class ComponentUtil {

    public static void mount(Component component, StackPane contentArea, AllValues allValues) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ComponentUtil.class.getResource(Objects.requireNonNull(component.getPath())));
        Parent parent = fxmlLoader.load();

        Object controller = fxmlLoader.getController();

        if (controller instanceof DataProvider) {
            DataProvider dataProvider = (DataProvider) controller;
            dataProvider.setData(allValues);
        }

        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(parent);
    }

    public static void mountMaterialDetails(StackPane contentArea, AllValues allValues, PartialMaterialEntity partialMaterialEntity) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ComponentUtil.class.getResource(Objects.requireNonNull(Component.MATERIAL_DETAILS.getPath())));
        Parent parent = fxmlLoader.load();

        Object controller = fxmlLoader.getController();

        if (controller instanceof MaterialDetailsController) {
            MaterialDetailsController materialDetailsController = (MaterialDetailsController) controller;
            materialDetailsController.setData(allValues);
            materialDetailsController.setPartialMaterialEntity(partialMaterialEntity);
        }

        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(parent);
    }

    public static void mountUpdateType(StackPane contentArea, AllValues allValues, AbstractType typeForUpdate) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ComponentUtil.class.getResource(Objects.requireNonNull(Component.CREATE_TYPE.getPath())));
        Parent parent = fxmlLoader.load();

        Object controller = fxmlLoader.getController();

        if (controller instanceof CreateTypeController) {
            CreateTypeController createTypeController = (CreateTypeController) controller;
            createTypeController.setTypeForUpdate(typeForUpdate);
            createTypeController.setData(allValues);
        }

        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(parent);
    }
}