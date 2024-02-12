package com.example.project_for_university.dto.forBackend;

import com.example.project_for_university.dto.forBackend.create.CreateLayerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialInfoDto {
    private String name;
    private String manufacturer;
    private String description;
    private double depth;
    private int productionMethod_id;
    private int membraneLayerPolymerType_id;
    private int glueType_id;
    private List<CreateLayerDto> layers = new ArrayList<>();
}
