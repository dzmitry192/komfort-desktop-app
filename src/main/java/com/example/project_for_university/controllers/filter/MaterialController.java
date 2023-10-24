package com.example.project_for_university.controllers.filter;

import com.example.project_for_university.dto.Material;
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

public class MaterialController implements Initializable {
    @FXML
    private Button btn_moreDetails;

    @FXML
    private Label desc;

    @FXML
    private HBox hbox;

    @FXML
    private ImageView image;

    @FXML
    private Label name;

    @FXML
    void btn_moreDetails_clicked(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MaterialController.class.getResource("/com/example/project_for_university/fxml/filter/material_details/filter-material-details.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage window = (Stage) btn_moreDetails.getScene().getWindow();
        window.setTitle("Подробная информация о материале");
        window.setScene(scene);
        window.setFullScreen(true);
        window.show();
    }

    public void setMaterial(Material material) {
        Image img = new Image(getClass().getResourceAsStream(material.getImageSrc()));

        image.setImage(img);
        name.setText(material.getName());
        desc.setText(material.getDesc());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
