package com.example.project_for_university.dto.forBackend.create;

import com.example.project_for_university.dto.forBackend.MaterialInfoDto;
import com.example.project_for_university.dto.forBackend.calculate.CalculateEstimationDto;
import com.example.project_for_university.dto.forBackend.calculate.CalculateHomeostasisFunctionDto;
import com.example.project_for_university.dto.forBackend.calculate.CalculateReliabilityFunctionDto;
import com.example.project_for_university.dto.forBackend.calculate.CalculateWaterproofFunctionDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMaterialDto {
    private File[] images;
    private MaterialInfoDto material;
    private CreateConditionDto condition;
    private CalculateWaterproofFunctionDto waterproofFunction;
    private CalculateHomeostasisFunctionDto homeostasisFunction;
    private CalculateReliabilityFunctionDto reliabilityFunction;
    private CalculateEstimationDto estimation;
}
