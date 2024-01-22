package com.example.project_for_university.service.models;

import com.example.project_for_university.dto.forBackend.entity.ProductionMethodEntity;
import com.example.project_for_university.dto.forBackend.entity.types.MembraneLayerPolymerTypeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterServiceModel {
    private MembraneLayerPolymerTypeEntity[] membraneLayerPolymerTypes;
    private ProductionMethodEntity[] productionMethods;
}
