package com.example.project_for_university.dto.forBackend.calculate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculateEstimationDto {
    private double waterproofFunction_weight = -1;
    private double homeostasisFunction_weight = -1;
    private double reliabilityFunction_weight = -1;
}
