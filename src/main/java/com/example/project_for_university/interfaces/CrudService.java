package com.example.project_for_university.interfaces;

import com.example.project_for_university.controllers.user.admin.models.PhType;

public interface CrudService<T> {
    T[] getAll();
    T getById(int id);
    T create(PhType phType);
    T update(PhType phType);
    T delete(int id);
}
