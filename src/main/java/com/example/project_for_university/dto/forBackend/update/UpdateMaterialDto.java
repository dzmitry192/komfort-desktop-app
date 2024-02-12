package com.example.project_for_university.dto.forBackend.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMaterialDto {
    private String name;
    private String description;
    private String manufacturer;
}
