package com.example.project_for_university.dto.forBackend.calculate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculateWaterproofFunctionDto {
    private double materialBlottingPressure_experimental_1 = -1;
    private double materialBlottingPressure_calculated = -1;
    private double materialBlottingPressure_base = -1;
    private double materialBlottingPressure_weight = -1;
    private double waterproof_experimental_1 = -1;
    private double waterproof_calculated = -1;
    private double waterproof_weight = -1;
    private double waterproof_base = -1;
    private double materialBlottingTime_experimental_1 = -1;
    private double materialBlottingTime_calculated = -1;
    private double materialBlottingTime_base = -1;
    private double materialBlottingTime_weight = -1;
    private double waterproofRealizationCriteria_experimental_1 = -1;
    private double waterproofRealizationCriteria_experimental_2 = -1;
    private double waterproofRealizationCriteria_base = -1;
    private double waterproofRealizationCriteria_weight = -1;
    private double dynamicWaterproofCriteria_experimental_1 = -1;
    private double dynamicWaterproofCriteria_experimental_2 = -1;
    private double dynamicWaterproofCriteria_experimental_3 = -1;
    private double dynamicWaterproofCriteria_experimental_4 = -1;
    private double dynamicWaterproofCriteria_base = -1;
    private double dynamicWaterproofCriteria_weight = -1;
    private double hydrostaticPressureIncreaseSpeed = -1;
    private double hydrostaticPressure = -1;
    private double waterproofTime = -1;
    private String equipment = null;
}
