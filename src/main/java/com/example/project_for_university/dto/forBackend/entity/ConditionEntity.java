package com.example.project_for_university.dto.forBackend.entity;

import com.example.project_for_university.dto.forBackend.entity.types.AbrasionTypeEntity;
import com.example.project_for_university.dto.forBackend.entity.types.BendingTypeEntity;
import com.example.project_for_university.dto.forBackend.entity.types.PhysicalActivityTypeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConditionEntity {
    private int id;
    private boolean isPositive;
    private int minAirTemp;
    private int minAirHumidity;
    private int avgAirSpeed;
    private int residenceTime;
    private int torsionAngle;
    private int stretchingCompression;
    private AbrasionTypeEntity abrasionType;
    private WashingEntity washing;
    private BendingTypeEntity bendingType;
    private PhysicalActivityTypeEntity physicalActivityType;
}
