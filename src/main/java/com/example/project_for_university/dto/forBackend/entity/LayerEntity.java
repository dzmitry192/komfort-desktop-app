package com.example.project_for_university.dto.forBackend.entity;

import com.example.project_for_university.dto.forBackend.entity.types.LayerTypeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LayerEntity {
    private int id;
    private int indexNum;
    private LayerTypeEntity layerType;
}
