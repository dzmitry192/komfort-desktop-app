package com.example.project_for_university.dto.forBackend.entity;

import com.example.project_for_university.dto.forBackend.entity.types.WashingTypeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WashingEntity {
    private int id;
    private int temperature;
    private int cyclesCnt;
    private int duration;
    private boolean press;
    private WashingTypeEntity washingType;
}
