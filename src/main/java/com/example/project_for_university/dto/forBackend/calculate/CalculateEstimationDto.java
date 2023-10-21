package com.example.project_for_university.dto.forBackend.calculate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculateEstimationDto {
    private int waterproofFunction_weight;
    private int homeostasisFunction_weight;
    private int reliabilityFunction_weight;
}
