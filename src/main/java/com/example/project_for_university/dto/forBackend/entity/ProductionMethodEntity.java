package com.example.project_for_university.dto.forBackend.entity;

import com.example.project_for_university.controllers.user.admin.models.AbstractType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ProductionMethodEntity extends AbstractType {
    public ProductionMethodEntity(int id, String name) {
        super();
    }
}
