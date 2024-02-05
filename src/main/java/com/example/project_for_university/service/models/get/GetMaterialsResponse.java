package com.example.project_for_university.service.models.get;

import com.example.project_for_university.dto.forBackend.entity.types.PartialMaterialEntity;
import com.example.project_for_university.service.models.AbstractResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetMaterialsResponse extends AbstractResponse {
    private PartialMaterialEntity[] partialMaterials;
    private int totalCount;
}
