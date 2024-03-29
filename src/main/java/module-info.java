module com.example.project_for_university {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;



    requires static lombok;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires com.google.gson;
    requires json.lib;
    requires de.jensd.fx.glyphs.fontawesome;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires org.apache.httpcomponents.httpmime;

    exports com.example.project_for_university;
    exports com.example.project_for_university.controllers;
    opens com.example.project_for_university.controllers to javafx.fxml;
    exports com.example.project_for_university.controllers.loader;
    opens com.example.project_for_university.controllers.loader to javafx.fxml;
    exports com.example.project_for_university.controllers.user;
    opens com.example.project_for_university.controllers.user to javafx.fxml;
    exports com.example.project_for_university.http to com.fasterxml.jackson.databind;
    exports com.example.project_for_university.utils;
    opens com.example.project_for_university.utils to javafx.fxml;
    exports com.example.project_for_university.controllers.filter;
    opens com.example.project_for_university.controllers.filter to javafx.fxml;
    opens com.example.project_for_university.dto to javafx.fxml;
    opens com.example.project_for_university.controllers.user.admin to javafx.fxml;
    opens com.example.project_for_university.controllers.user.admin.models to javafx.base, com.google.gson;
    exports com.example.project_for_university.controllers.material;
    opens com.example.project_for_university.controllers.material to javafx.fxml;
    exports com.example.project_for_university.controllers.material.tables;
    opens com.example.project_for_university.controllers.material.tables to javafx.fxml;
    exports com.example.project_for_university.dto.forBackend.entity to com.google.gson, json.lib;
    opens com.example.project_for_university.dto.forBackend.entity to com.google.gson;
    opens com.example.project_for_university.dto.forBackend.entity.types to com.google.gson, javafx.base;
    opens com.example.project_for_university.controllers.material.models to javafx.base;
    opens com.example.project_for_university.dto.forBackend to com.google.gson;
    exports com.example.project_for_university.dto.forBackend.create to com.fasterxml.jackson.databind;
    exports com.example.project_for_university.dto.forBackend to com.fasterxml.jackson.databind;
    exports com.example.project_for_university.dto.forBackend.calculate to com.fasterxml.jackson.databind;
    exports com.example.project_for_university.service.models.material to com.fasterxml.jackson.databind;
    opens com.example.project_for_university.service.models.material to com.fasterxml.jackson.databind;
    exports com.example.project_for_university.service.models to com.fasterxml.jackson.databind;
    opens com.example.project_for_university.service.models to com.fasterxml.jackson.databind;
    exports com.example.project_for_university.service.models.get to com.fasterxml.jackson.databind;
    opens com.example.project_for_university.service.models.get to com.fasterxml.jackson.databind;

    opens com.example.project_for_university;
}