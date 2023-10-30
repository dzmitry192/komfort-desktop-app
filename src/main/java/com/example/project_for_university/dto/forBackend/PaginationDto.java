package com.example.project_for_university.dto.forBackend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationDto {
    private int page;
    private int perPage;
}
