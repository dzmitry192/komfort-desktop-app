package com.example.project_for_university.dto.forBackend.calculate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculateReliabilityFunctionDto {
    private int maxWaterResistanceLvl;
    private int relativeBlottingPressureAfterLoad_experimental_1;
    private int relativeBlottingPressureAfterLoad_calculated;
    private int relativeBlottingPressureAfterLoad_base;
    private int relativeBlottingPressureAfterLoad_weight;
    private int relativeWaterResistanceAfterLoad_experimental_1;
    private int relativeWaterResistanceAfterLoad_calculated;
    private int relativeWaterResistanceAfterLoad_base;
    private int relativeWaterResistanceAfterLoad_weight;
    private int relativeBlottingTimeAfterLoad_experimental_1;
    private int relativeBlottingTimeAfterLoad_calculated;
    private int relativeBlottingTimeAfterLoad_base;
    private int relativeBlottingTimeAfterLoad_weight;
    private int waterproofRealizationCriteriaAfterLoad_experimental_1;
    private int waterproofRealizationCriteriaAfterLoad_experimental_2;
    private int waterproofRealizationCriteriaAfterLoad_weight;
    private int waterproofFunctionResource_base;
    private int waterproofFunctionResource_weight;
    private int impactCyclesCnt;
    private String equipment;
}
