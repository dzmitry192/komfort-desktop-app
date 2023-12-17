package com.example.project_for_university.dto.forBackend.calculate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculateHomeostasisFunctionDto {
    private double sampleSurfaceArea = -1;
    private double m1s = -1;
    private double m2s = -1;
    private double s0_1 = -1;
    private double t_1 = -1;
    private double waterPermeability_base = -1;
    private double waterPermeability_weight = -1;
    private double m1min = -1;
    private double m2min = -1;
    private double m1max = -1;
    private double m2max = -1;
    private double s0_2 = -1;
    private double t_2 = -1;
    private double waterPermeabilityDynamicCriteria_base = -1;
    private double waterPermeabilityDynamicCriteria_weight = -1;
    private double tos = -1;
    private double s = -1;
    private double m = -1;
    private double totalThermalResistance_base = -1;
    private double totalThermalResistance_weight = -1;
    private double comfTempInsideClothes = -1;
    private double comfHumidityInsideClothes = -1;
    private double minOutdoorTemp = -1;
    private double maxOutdoorTemp = -1;
    private double minOutdoorHumidity = -1;
    private double maxOutdoorHumidity = -1;
    private double avgOutdoorAirSpeed = -1;
    private String equipment = null;
}
