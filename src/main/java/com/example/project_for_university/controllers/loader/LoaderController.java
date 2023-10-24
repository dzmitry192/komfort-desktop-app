package com.example.project_for_university.controllers.loader;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lombok.Data;

import java.net.URL;
import java.util.ResourceBundle;

@Data
public class LoaderController extends Node implements Initializable {

    @FXML
    private AnchorPane loader;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(loader);
        rotate.setDuration(Duration.millis(850));
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setByAngle(360);
        rotate.play();
    }
}
