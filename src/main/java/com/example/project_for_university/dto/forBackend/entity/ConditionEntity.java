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
    private boolean isPositive; //табл4
    private int minAirTemp; //табл4
    private int maxAirTemp; //табл4
    private int minAirHumidity; //табл4
    private int maxAirHumidity; //табл4
    private int avgAirSpeed; //табл4
    private int residenceTime; //табл4
    private int torsionAngle; //табл3
    private int stretchingCompression; //табл3
    private AbrasionTypeEntity abrasionType; //табл3
    private WashingEntity washing; //табл3
    private BendingTypeEntity bendingType; //табл3
    private PhysicalActivityTypeEntity physicalActivityType; //табл3
}
