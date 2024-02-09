package com.example.project_for_university.service.models;

import com.example.project_for_university.dto.forBackend.entity.DesktopEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAppLatestVersionResponse extends AbstractResponse {
    private DesktopEntity desktop;
}
