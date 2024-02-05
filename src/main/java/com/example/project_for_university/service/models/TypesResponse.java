package com.example.project_for_university.service.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypesResponse<T> extends AbstractResponse {
    private T[] types;
}
