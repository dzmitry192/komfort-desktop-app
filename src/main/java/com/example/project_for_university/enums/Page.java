package com.example.project_for_university.enums;

public enum Page {
    LOGIN ("/com/example/project_for_university/fxml/user/login.fxml"),
    SIGNUP ("/com/example/project_for_university/fxml/user/signup.fxml"),
    CHOOSE_OPERATION ("/com/example/project_for_university/fxml/user/choose-operation.fxml"),
    FILTER ("/com/example/project_for_university/fxml/filter/filter.fxml"),
    CONDITION_1 ("/com/example/project_for_university/fxml/cond/condition-1.fxml"),
    CONDITION_2 ("/com/example/project_for_university/fxml/cond/condition-2.fxml"),
    TABLES ("/com/example/project_for_university/fxml/cond/result-table.fxml"),
    ADMIN_PANEL ("/com/example/project_for_university/fxml/user/admin/admin.fxml"),
    TYPE ("/com/example/project_for_university/fxml/user/admin/type.fxml"),
    LOADER ("/com/example/project_for_university/fxml/loader/loader.fxml"),
    MATERIAL_DETAILS ("/com/example/project_for_university/fxml/filter/material_details/filter-material-details.fxml");

    private String path;

    Page(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
