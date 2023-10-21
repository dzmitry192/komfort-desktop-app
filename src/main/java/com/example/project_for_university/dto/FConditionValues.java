package com.example.project_for_university.dto;

import com.example.project_for_university.dto.forBackend.entity.WashingEntity;
import com.example.project_for_university.dto.forBackend.entity.types.AbrasionTypeEntity;
import com.example.project_for_university.dto.forBackend.entity.types.BendingTypeEntity;
import com.example.project_for_university.dto.forBackend.entity.types.PhysicalActivityTypeEntity;
import lombok.Data;

@Data
public class FConditionValues {
    private boolean isPositive = false;
    private int minAirTemp = 0;
    private int maxAirTemp = 0;
    private int minAirHumidity = 0;
    private int maxAirHumidity = 0;
    private int avgAirSpeed = 0;
    private int residenceTime = 0;
    private int torsionAngle = 0;
    private int stretchingCompression = 0;
    private AbrasionTypeEntity abrasionType = null;
    private WashingEntity washing = null;
    private BendingTypeEntity bendingType = null;
    private PhysicalActivityTypeEntity physicalActivityType = null;
}
