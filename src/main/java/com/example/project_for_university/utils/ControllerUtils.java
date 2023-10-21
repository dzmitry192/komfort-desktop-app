package com.example.project_for_university.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ControllerUtils {

    private static final String loginRoute = "/com/example/project_for_university/fxml/user/login.fxml";
    private static final String signupRoute = "/com/example/project_for_university/fxml/user/signup.fxml";
    private static final String welcomeRoute = "/com/example/project_for_university/fxml/user/choose-operation.fxml";
    private static final String filterRoute = "/com/example/project_for_university/fxml/filter/filter.fxml";
    private static final String firstCondRoute = "/com/example/project_for_university/fxml/cond/condition-1.fxml";
    private static final String secCondRoute = "/com/example/project_for_university/fxml/cond/condition-2.fxml";
    private static final String tableRoute = "/com/example/project_for_university/fxml/cond/result-table.fxml";
    private static final String adminRoute = "/com/example/project_for_university/fxml/user/admin/admin.fxml";
    private static final String typeRoute = "/com/example/project_for_university/fxml/user/admin/type.fxml";
    private static final String phTypeRoute = "/com/example/project_for_university/fxml/user/admin/physical-type.fxml";

    private static String getRoute(int num) {
        switch (num) {
            case 1:
                return loginRoute;
            case 2:
                return signupRoute;
            case 3:
                return welcomeRoute;
            case 4:
                return filterRoute;
            case 5:
                return firstCondRoute;
            case 6:
                return secCondRoute;
            case 7:
                return tableRoute;
            case 8:
                return adminRoute;
            case 9:
                return typeRoute;
            case 10:
                return phTypeRoute;
        }
        return null;
    }

    public static void changeWindow(Object obj, int num, MouseEvent event, String title) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(obj.getClass().getResource(Objects.requireNonNull(getRoute(num))));
        Scene scene = new Scene(fxmlLoader.load());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setTitle(title);
        window.setScene(scene);
        window.show();
    }
}