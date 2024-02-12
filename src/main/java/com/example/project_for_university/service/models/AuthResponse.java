package com.example.project_for_university.service.models;

import com.example.project_for_university.dto.forBackend.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse extends AbstractResponse {
    private UserEntity user;
}
