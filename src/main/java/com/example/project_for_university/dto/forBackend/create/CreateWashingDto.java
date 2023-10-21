package com.example.project_for_university.dto.forBackend.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateWashingDto {
    private int temperature;
    private int cyclesCnt;
    private int duration;
    private boolean press;
    private int washingType_id;
}
