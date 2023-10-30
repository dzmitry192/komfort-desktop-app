package com.example.project_for_university.service.models;

import com.example.project_for_university.dto.forBackend.entity.MaterialEntity;
import com.example.project_for_university.dto.forBackend.entity.types.PartialMaterialEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterMaterialsModel {
    private PartialMaterialEntity[] partialMaterials;
    private boolean isError;
}
