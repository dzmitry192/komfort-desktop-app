package com.example.project_for_university.exception;

public enum ExceptionMessage {
    INVALID_INPUT("Вы не заполнили все необходимые значения"),
    INVALID_VALUE("Вы ввели некорректные значения"),
    INVALID_WEIGHTS_SUM("Сумма весомостей должна быть равна 1"),
    INVALID_CONDITIONS("Все поля условий должны быть заполнены"),
    INVALID_CONDITIONS_VALUE("Вы ввели некорректные значения условий"),
    INVALID_EQUIPMENT("Вы не заполнили поле оборудования");

    private final String message;
    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
