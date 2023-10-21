package com.example.project_for_university.dto;

import javafx.scene.control.TextField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterValues {
    private String name;
    private String blotting_pressure_inp_1;
    private String blotting_pressure_inp_2;
    private String depth_inp_1;
    private String depth_inp_2;
    private String relative_pressure_inp_1;
    private String relative_pressure_inp_2;
    private String resistance_inp_1;
    private String resistance_inp_2;
    private String time_inp_1;
    private String time_inp_2;
    private String water_vapor_perm_inp_1;
    private String water_vapor_perm_inp_2;
    private boolean onlyOwnerMaterials;
}
