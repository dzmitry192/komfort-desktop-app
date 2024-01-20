package com.example.project_for_university.dto.forBackend.calculate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculateReliabilityFunctionDto {
    private double maxWaterResistanceLvl = -1;
    private double relativeBlottingPressureAfterLoad_experimental_1 = -1;
    private double relativeBlottingPressureAfterLoad_calculated = -1;
    private double relativeBlottingPressureAfterLoad_base = -1;
    private double relativeBlottingPressureAfterLoad_weight = -1;
    private double relativeWaterResistanceAfterLoad_experimental_1 = -1;
    private double relativeWaterResistanceAfterLoad_calculated = -1;
    private double relativeWaterResistanceAfterLoad_base = -1;
    private double relativeWaterResistanceAfterLoad_weight = -1;
    private double relativeBlottingTimeAfterLoad_experimental_1 = -1;
    private double relativeBlottingTimeAfterLoad_calculated = -1;
    private double relativeBlottingTimeAfterLoad_base = -1;
    private double relativeBlottingTimeAfterLoad_weight = -1;
    private double waterproofRealizationCriteriaAfterLoad_experimental_1 = -1;
    private double waterproofRealizationCriteriaAfterLoad_experimental_2 = -1;
    private double waterproofRealizationCriteriaAfterLoad_weight = -1;
    private double waterproofFunctionResource_base = -1;
    private double waterproofFunctionResource_weight = -1;
    private double impactCyclesCnt = -1;
    private String equipment = null;
}
