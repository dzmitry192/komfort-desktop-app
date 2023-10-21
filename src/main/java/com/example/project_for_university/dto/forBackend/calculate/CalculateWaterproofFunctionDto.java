package com.example.project_for_university.dto.forBackend.calculate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculateWaterproofFunctionDto {
    private int materialBlottingPressure_experimental_1;
    private int materialBlottingPressure_calculated;
    private int materialBlottingPressure_base;
    private int materialBlottingPressure_weight;
    private int waterproof_experimental_1;
    private int waterproof_calculated;
    private int waterproof_base;
    private int waterproof_weight;
    private int materialBlottingTime_experimental_1;
    private int materialBlottingTime_calculated;
    private int materialBlottingTime_base;
    private int materialBlottingTime_weight;
    private int waterproofRealizationCriteria_experimental_1;
    private int waterproofRealizationCriteria_experimental_2;
    private int waterproofRealizationCriteria_base;
    private int waterproofRealizationCriteria_weight;
    private int dynamicWaterproofCriteria_experimental_1;
    private int dynamicWaterproofCriteria_experimental_2;
    private int dynamicWaterproofCriteria_experimental_3;
    private int dynamicWaterproofCriteria_experimental_4;
    private int dynamicWaterproofCriteria_base;
    private int dynamicWaterproofCriteria_weight;
    private int hydrostaticPressureIncreaseSpeed;
    private int hydrostaticPressure;
    private int waterproofTime;
    private String equipment;
}
