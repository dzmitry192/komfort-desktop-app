package com.example.project_for_university.dto.forBackend.calculate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculateWaterproofFunctionDto {
    private double materialBlottingPressure_experimental_1;
    private double materialBlottingPressure_calculated;
    private double materialBlottingPressure_base;
    private double materialBlottingPressure_weight;
    private double waterproof_experimental_1;
    private double waterproof_calculated;
    private double waterproof_weight;
    private double waterproof_base;
    private double materialBlottingTime_experimental_1;
    private double materialBlottingTime_calculated;
    private double materialBlottingTime_base;
    private double materialBlottingTime_weight;
    private double waterproofRealizationCriteria_experimental_1;
    private double waterproofRealizationCriteria_experimental_2;
    private double waterproofRealizationCriteria_base;
    private double waterproofRealizationCriteria_weight;
    private double dynamicWaterproofCriteria_experimental_1;
    private double dynamicWaterproofCriteria_experimental_2;
    private double dynamicWaterproofCriteria_experimental_3;
    private double dynamicWaterproofCriteria_experimental_4;
    private double dynamicWaterproofCriteria_base;
    private double dynamicWaterproofCriteria_weight;
    private double hydrostaticPressureIncreaseSpeed;
    private double hydrostaticPressure;
    private double waterproofTime;
    private String equipment;
}
