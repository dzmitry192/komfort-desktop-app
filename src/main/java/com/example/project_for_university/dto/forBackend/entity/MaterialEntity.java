package com.example.project_for_university.dto.forBackend.entity;

import com.example.project_for_university.dto.forBackend.entity.types.GlueTypeEntity;
import com.example.project_for_university.dto.forBackend.entity.types.MembraneLayerPolymerTypeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialEntity {
    private int id;
    private String name;
    private String description;
    private String manufacturer; //табл 2
    private int depth; //табл 2
    private ConditionEntity condition; //табл 3 - параметры, табл 4 - перечисления типов условий
    private LayerEntity[] layers; //табл 1
    private String[] images;
    private UserEntity user;
    private ProductionMethodEntity productionMethod; //табл 2
    private MembraneLayerPolymerTypeEntity membraneLayerPolymerType; //табл 2
    private GlueTypeEntity glueType; //табл 2
}
