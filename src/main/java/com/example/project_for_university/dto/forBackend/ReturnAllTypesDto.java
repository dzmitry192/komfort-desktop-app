package com.example.project_for_university.dto.forBackend;

import com.example.project_for_university.dto.forBackend.entity.ProductionMethodEntity;
import com.example.project_for_university.dto.forBackend.entity.types.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnAllTypesDto {
    private AbrasionTypeEntity[] abrasionTypes;
    private BendingTypeEntity[] bendingTypes;
    private GlueTypeEntity[] glueTypes;
    private LayerTypeEntity[] layerTypes;
    private MembraneLayerPolymerTypeEntity[] membraneLayerPolymerTypes;
    private PhysicalActivityTypeEntity[] physicalActivityTypes;
    private ProductionMethodEntity[] productionMethods;
    private WashingTypeEntity[] washingTypes;
}
