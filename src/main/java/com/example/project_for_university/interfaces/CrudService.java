package com.example.project_for_university.interfaces;

import com.example.project_for_university.controllers.user.admin.models.PhType;

public interface CrudService<T> {
    T[] getAll(String email, String password);
    T getById(int id, String email, String password);
    T create(PhType phType, String email, String password);
    T update(PhType phType, String email, String password);
    T delete(int id, String email, String password);
}
