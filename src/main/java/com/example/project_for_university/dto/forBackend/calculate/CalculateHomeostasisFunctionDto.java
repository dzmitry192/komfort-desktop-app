package com.example.project_for_university.dto.forBackend.calculate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculateHomeostasisFunctionDto {
    private int sampleSurfaceArea;
    private int m1s;
    private int m2s;
    private int s0_1;
    private int t_1;
    private int waterPermeability_base;
    private int waterPermeability_weight;
    private int m1min;
    private int m2min;
    private int m1max;
    private int m2max;
    private int s0_2;
    private int t_2;
    private int waterPermeabilityDynamicCriteria_base;
    private int waterPermeabilityDynamicCriteria_weight;
    private int tos;
    private int s;
    private int m;
    private int totalThermalResistance_base;
    private int totalThermalResistance_weight;
    private int comfTempInsideClothes;
    private int comfHumidityInsideClothes;
    private int minOutdoorTemp;
    private int maxOutdoorTemp;
    private int minOutdoorHumidity;
    private int maxOutdoorHumidity;
    private int avgOutdoorAirSpeed;
    private String equipment;
}
