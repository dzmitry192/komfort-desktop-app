package com.example.project_for_university.enums;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    DEFAULT("Непредвиденная ошибка"),
    ENTITY_NOT_FOUND("То, что вы пытаетесь удалить уже не существует"),
    SERVER_ERROR("С серваком писец, хз чё делать, попробуй ещё раз позже"),
    SIGNUP_400_EMAIL("Некорректный email"),
    SIGNUP_400_PASSWORD("Разрешенные символы пароля: 0-9, A-z, !@#$%^&*"),
    SIGNUP_401("Пользователь с таким email уже существует"),
    SIGNUP_404("Пользователь с таким email не найден"),
    LOGIN_400("Некорректный email"),
    LOGIN_401("Неверный пароль"),
    LOGIN_404("Пользователь с таким email не найден"),
    TYPE_400("Тип с таким названием уже существует"),
    TYPE_500("Невозможно удалить тип, т.к. он используется"),
    REPORT_NOT_FOUND("Отчёта по такому материалу нет"),
    MATERIAL_DELETE_403("Вы можете удалять только свои материалы"),
    MATERIAL_PATCH_403("Вы можете удалять только свои материалы");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

}
