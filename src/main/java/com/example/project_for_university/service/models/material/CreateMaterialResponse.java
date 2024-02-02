package com.example.project_for_university.service.models.material;

import com.example.project_for_university.dto.forBackend.entity.types.PartialMaterialEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMaterialResponse {
    private PartialMaterialEntity material;
    private boolean isError;
    private String errorMessage;
}
