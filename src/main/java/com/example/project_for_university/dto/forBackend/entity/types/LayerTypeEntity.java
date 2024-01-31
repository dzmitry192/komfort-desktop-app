package com.example.project_for_university.dto.forBackend.entity.types;

import com.example.project_for_university.controllers.user.admin.models.AbstractType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class LayerTypeEntity extends AbstractType {
    public LayerTypeEntity(int id, String name) {
        super();
    }
}
