package com.example.project_for_university.service.models.material;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateConditionRequestDto {
    @JsonProperty("isPositive")
    private boolean isPositive;
    private int minAirTemp;
    private int maxAirTemp;
    private int minAirHumidity;
    private int maxAirHumidity;
    private int avgAirSpeed;
    private int residenceTime;
    private int torsionAngle;
    private int stretchingCompression;
    private CreateWashingRequestDto washing = new CreateWashingRequestDto();
    private Integer bendingType_id;
    private Integer abrasionType_id;
    private Integer physicalActivityType_id;
}
