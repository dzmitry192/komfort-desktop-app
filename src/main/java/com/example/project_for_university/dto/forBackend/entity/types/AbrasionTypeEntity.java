package com.example.project_for_university.dto.forBackend.entity.types;

import com.example.project_for_university.controllers.user.admin.models.AbstractType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AbrasionTypeEntity extends AbstractType {
    public AbrasionTypeEntity(int id, String name) {
        super();
    }
}
