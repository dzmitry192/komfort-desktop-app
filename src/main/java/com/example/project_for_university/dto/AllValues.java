package com.example.project_for_university.dto;

import com.example.project_for_university.controllers.user.admin.models.AdminPanelInfo;
import com.example.project_for_university.dto.forBackend.MaterialFilterDto;
import com.example.project_for_university.dto.forBackend.PaginationDto;
import com.example.project_for_university.dto.forBackend.create.CreateMaterialDto;
import com.example.project_for_university.dto.forBackend.entity.UserEntity;
import com.example.project_for_university.dto.forBackend.entity.types.PartialMaterialEntity;
import com.example.project_for_university.enums.Component;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllValues {
    private String appVersion = "1.0";
    private AdminPanelInfo adminPanelInfo = new AdminPanelInfo();
    private Stage rootStage;
    private Stage loaderStage;
    private ContentPanes contentPanes;
    private Component lastCreateMaterialComponent;
    private ArrayList<HBox> sideBarButtons;
    private ArrayList<EventHandler<MouseEvent>> sideBarButtonsEventHandlers;

    private UserEntity user;

    private CreateMaterialDto createMaterialDto = new CreateMaterialDto();
    private MaterialFilterDto materialFilterDto;
    private PaginationDto paginationDto;
    private int totalMaterialsCnt;
    private ArrayList<PartialMaterialEntity> loadedMaterials;
}
