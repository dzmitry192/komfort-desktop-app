package com.example.project_for_university.dto.forBackend.create;

import com.example.project_for_university.dto.forBackend.MaterialInfoDto;
import com.example.project_for_university.dto.forBackend.calculate.CalculateEstimationDto;
import com.example.project_for_university.dto.forBackend.calculate.CalculateHomeostasisFunctionDto;
import com.example.project_for_university.dto.forBackend.calculate.CalculateReliabilityFunctionDto;
import com.example.project_for_university.dto.forBackend.calculate.CalculateWaterproofFunctionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMaterialDto {
    private File[] images = new File[]{};
    private MaterialInfoDto material = new MaterialInfoDto();
    private CreateConditionDto condition = new CreateConditionDto();
    private CalculateWaterproofFunctionDto waterproofFunction = new CalculateWaterproofFunctionDto();
    private CalculateHomeostasisFunctionDto homeostasisFunction = new CalculateHomeostasisFunctionDto();
    private CalculateReliabilityFunctionDto reliabilityFunction = new CalculateReliabilityFunctionDto();
    private CalculateEstimationDto estimation = new CalculateEstimationDto();
}
