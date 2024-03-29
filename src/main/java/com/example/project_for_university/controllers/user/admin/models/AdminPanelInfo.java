package com.example.project_for_university.controllers.user.admin.models;

import com.example.project_for_university.dto.forBackend.ReturnAllTypesDto;
import com.example.project_for_university.enums.ActionType;
import com.example.project_for_university.enums.AdminPanelType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminPanelInfo {
    private ActionType actionType;
    private ReturnAllTypesDto returnAllTypesDto = new ReturnAllTypesDto();
    private AdminPanelType curAdminPanelType;
}
