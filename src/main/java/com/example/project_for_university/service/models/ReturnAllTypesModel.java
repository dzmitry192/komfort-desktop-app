package com.example.project_for_university.service.models;

import com.example.project_for_university.dto.forBackend.ReturnAllTypesDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnAllTypesModel {
    private ReturnAllTypesDto returnAllTypesDto;
    private boolean isError;
}
