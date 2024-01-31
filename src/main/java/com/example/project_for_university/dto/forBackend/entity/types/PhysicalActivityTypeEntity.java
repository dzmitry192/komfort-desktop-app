package com.example.project_for_university.dto.forBackend.entity.types;

import com.example.project_for_university.controllers.user.admin.models.AbstractType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhysicalActivityTypeEntity extends AbstractType {
    private String description;

    public PhysicalActivityTypeEntity(int id, String name, String description) {
        super();
        this.description = description;
    }
}
