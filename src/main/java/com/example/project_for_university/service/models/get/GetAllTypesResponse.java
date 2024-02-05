package com.example.project_for_university.service.models.get;

import com.example.project_for_university.dto.forBackend.ReturnAllTypesDto;
import com.example.project_for_university.service.models.AbstractResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllTypesResponse extends AbstractResponse {
    private ReturnAllTypesDto returnAllTypesDto;
}
