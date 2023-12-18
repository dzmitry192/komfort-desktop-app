package com.example.project_for_university.exception;

public enum ExceptionType {
    INVALID_INPUT_TYPE("Незаполнены необходимые поля"),
    INVALID_VALUE_TYPE("Некорректные введённые значения"),
    INVALID_WEIGHTS_SUM_TYPE("Некорректная сумма весомостей"),
    INVALID_CONDITIONS_TYPE("Незаполнены поля условий"),
    INVALID_CONDITIONS_VALUE_TYPE("Некорректные значения условий"),
    INVALID_EQUIPMENT_TYPE("Некорректное значение оборудования");

    private final String type;
    ExceptionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
