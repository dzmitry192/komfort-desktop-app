package com.example.project_for_university.enums;

public enum Component {
    MAIN ("/com/example/project_for_university/fxml/main.fxml"),
    LOGIN ("/com/example/project_for_university/fxml/user/login.fxml"),
    SIGNUP ("/com/example/project_for_university/fxml/user/signup.fxml"),
    LOGGED_IN ("/com/example/project_for_university/fxml/loggedInPage/logged-in-page.fxml"),

    CONDITION_1 ("/com/example/project_for_university/fxml/cond/condition-1.fxml"),
    CONDITION_2 ("/com/example/project_for_university/fxml/cond/condition-2.fxml"),
    WATERPROOF_TABLE ("/com/example/project_for_university/fxml/cond/tables/waterproof-table.fxml"),
    HOMEOSTASIS_TABLE ("/com/example/project_for_university/fxml/cond/tables/homeostasis-table.fxml"),
    RELIABILITY_TABLE ("/com/example/project_for_university/fxml/cond/tables/reliability-table.fxml"),
    ESTIMATION_TABLE ("/com/example/project_for_university/fxml/cond/tables/estimation-table.fxml"),
    MATERIAL_INFO ("/com/example/project_for_university/fxml/cond/material-info.fxml"),

    FILTER ("/com/example/project_for_university/fxml/filter/filter.fxml"),
    MATERIAL_ITEM ("/com/example/project_for_university/fxml/filter/material_item/filter-material.fxml"),
    MATERIAL_DETAILS ("/com/example/project_for_university/fxml/filter/material_details/filter-material-details.fxml"),

    ADMIN_PANEL ("/com/example/project_for_university/fxml/user/admin/admin.fxml"),
    TYPE ("/com/example/project_for_university/fxml/user/admin/type.fxml"),

    LOADER ("/com/example/project_for_university/fxml/loader/loader.fxml");

    private String path;

    Component(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
