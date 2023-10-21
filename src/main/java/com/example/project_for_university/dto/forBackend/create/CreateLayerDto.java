package com.example.project_for_university.dto.forBackend.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateLayerDto {
    private int indexNum;
    private int layerType_id;
}
