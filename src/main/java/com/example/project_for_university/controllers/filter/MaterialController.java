package com.example.project_for_university.controllers.filter;

import com.example.project_for_university.dto.AllValues;
import com.example.project_for_university.dto.Material;
import com.example.project_for_university.dto.forBackend.entity.types.PartialMaterialEntity;
import com.example.project_for_university.enums.Component;
import com.example.project_for_university.providers.DataProvider;
import com.example.project_for_university.utils.ComponentUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MaterialController implements Initializable, DataProvider {

    private AllValues allValues;
    private PartialMaterialEntity partialMaterialEntity;

    @FXML
    private HBox btn_moreDetails;

    @FXML
    private Label desc;

    @FXML
    private HBox hbox;

    @FXML
    private ImageView image;

    @FXML
    private Label name;

    @Override
    public void setData(AllValues allValues) {
        this.allValues = allValues;
    }

    @FXML
    void btn_moreDetails_clicked(MouseEvent event) throws IOException {
        ComponentUtil.mountMaterialDetails(allValues.getContentPanes().getLoggedInStackPane(), allValues, partialMaterialEntity);
    }

    public void setMaterial(PartialMaterialEntity partialMaterialEntity) {
        this.partialMaterialEntity = partialMaterialEntity;
        Image img;
//        if (partialMaterialEntity.getImages().length > 0) {
//            img = new Image(getClass().getResourceAsStream(partialMaterialEntity.getImages()[1]));
//            image.setImage(img);
//        }
        name.setText(partialMaterialEntity.getName());
        desc.setText(partialMaterialEntity.getDescription());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
