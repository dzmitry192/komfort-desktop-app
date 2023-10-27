package com.example.project_for_university.dto;

import com.example.project_for_university.controllers.user.admin.models.AdminPanelInfo;
import com.example.project_for_university.dto.forBackend.ReturnAllTypesDto;
import com.example.project_for_university.dto.forBackend.calculate.CalculateEstimationDto;
import com.example.project_for_university.dto.forBackend.calculate.CalculateHomeostasisFunctionDto;
import com.example.project_for_university.dto.forBackend.calculate.CalculateReliabilityFunctionDto;
import com.example.project_for_university.dto.forBackend.calculate.CalculateWaterproofFunctionDto;
import com.example.project_for_university.dto.forBackend.create.CreateMaterialDto;
import com.example.project_for_university.dto.forBackend.entity.MaterialEntity;
import com.example.project_for_university.dto.forBackend.entity.UserEntity;
import com.example.project_for_university.enums.Component;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllValues {
    private AdminPanelInfo adminPanelInfo;
    private Stage rootStage;
    private Stage loaderStage;
    private ContentPanes contentPanes;
    private Component lastCreateMaterialComponent;

    private UserEntity user;
    private ReturnAllTypesDto returnAllTypesDto;
    private CreateMaterialDto createMaterialDto;
}
