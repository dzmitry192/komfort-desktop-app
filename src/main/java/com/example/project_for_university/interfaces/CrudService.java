package com.example.project_for_university.interfaces;

import com.example.project_for_university.controllers.user.admin.models.PhType;
import com.example.project_for_university.service.models.TypeResponse;
import com.example.project_for_university.service.models.TypesResponse;

public interface CrudService<T> {
    TypesResponse<T> getAll(String email, String password);
    TypeResponse<T> getById(int id, String email, String password);
    TypeResponse<T> create(PhType phType, String email, String password);
    TypeResponse<T> update(PhType phType, String email, String password);
    TypeResponse<T> delete(int id, String email, String password);
}
