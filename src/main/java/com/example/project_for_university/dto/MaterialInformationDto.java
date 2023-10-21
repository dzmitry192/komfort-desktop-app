package com.example.project_for_university.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialInformationDto {
    private String[] images;
    private String name;
    private String description;
}
