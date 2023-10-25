package com.example.project_for_university.dto;

import javafx.scene.layout.StackPane;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContentPanes {
    StackPane mainContentPane;

    StackPane loggedInStackPane;
}
