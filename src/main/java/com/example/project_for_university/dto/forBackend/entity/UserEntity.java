package com.example.project_for_university.dto.forBackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    private int id;
    private String fio;
    private String email;
    private String password;
    private boolean isAdmin;
}