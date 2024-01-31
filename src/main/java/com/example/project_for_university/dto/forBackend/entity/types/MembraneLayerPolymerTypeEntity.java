package com.example.project_for_university.dto.forBackend.entity.types;

import com.example.project_for_university.controllers.user.admin.models.AbstractType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MembraneLayerPolymerTypeEntity extends AbstractType {
    public MembraneLayerPolymerTypeEntity(int id, String name) {
        super();
    }
}
