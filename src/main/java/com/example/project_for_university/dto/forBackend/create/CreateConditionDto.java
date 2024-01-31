package com.example.project_for_university.dto.forBackend.create;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateConditionDto {
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
    private CreateWashingDto washing;
    private int bendingType_id;
    private int abrasionType_id;
    private int physicalActivityType_id;
}
