package com.example.project_for_university.service.models;

import com.example.project_for_university.service.models.AbstractResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeResponse<T> extends AbstractResponse {
    private T type;
}
