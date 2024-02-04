package com.example.project_for_university.enums;

public enum ErrorMessage {
    NOT_FOUND("Что-то там не найдено короче"),
    SERVER_ERROR ("С серваком писец, хз чё делать, попробуй ещё раз позже"),
    KOGDA_NE_ZNAESH_CHTO_KIDATb("Полная жопа, ничё не понятно");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
