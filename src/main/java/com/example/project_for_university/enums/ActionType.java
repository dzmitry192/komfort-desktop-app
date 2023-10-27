package com.example.project_for_university.enums;

public enum ActionType {
    CREATE ("Создание"),
    UPDATE ("Изменение");

    private String name;

    ActionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
