package com.example.project_for_university.dto.forBackend.entity.types;

import com.example.project_for_university.controllers.user.admin.models.AbstractType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WashingTypeEntity extends AbstractType {
    public WashingTypeEntity(int id, String name) {
        super();
    }
}
