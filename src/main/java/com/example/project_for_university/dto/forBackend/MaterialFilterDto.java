package com.example.project_for_university.dto.forBackend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialFilterDto {
    private String name;
    private int userId;
    private int layersCnt;
    private int membraneLayerPolymerType_id;
    private int productionMethod_id;
    private double depth_min;
    private double depth_max;
    private double materialBlottingPressure_calculated_min;
    private double materialBlottingPressure_calculated_max;
    private double materialBlottingTime_calculated_min;
    private double materialBlottingTime_calculated_max;
    private double waterPermeability_calculated_min;
    private double waterPermeability_calculated_max;
    private double totalThermalResistance_calculated_min;
    private double totalThermalResistance_calculated_max;
    private double relativeBlottingPressureAfterLoad_relativeValuation_min;
    private double relativeBlottingPressureAfterLoad_relativeValuation_max;
}
