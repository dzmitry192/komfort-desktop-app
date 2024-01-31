package com.example.project_for_university.controllers.user.admin.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PhType extends AbstractType {
    private String description;

    public PhType(int id, String name, String description) {
        super(id, name);
        this.description = description;
    }

}
