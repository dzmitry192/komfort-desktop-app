package com.example.project_for_university.service.models.material;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateWashingRequestDto {
    private int temperature;
    private int cyclesCnt;
    private int duration;
    private boolean press;
    private Integer washingType_id;
}
