package com.example.project_for_university.enums;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    DEFAULT("Непредвиденная ошибка"),
    ENTITY_NOT_FOUND("Что-то там не найдено короче"),
    SERVER_ERROR("С серваком писец, хз чё делать, попробуй ещё раз позже"),
    SIGNUP_400("Некорректный email"),
    SIGNUP_401("Пользователь с таким email уже существует"),
    SIGNUP_404("Пользователь с таким email не найден"),
    LOGIN_400("Некорректный email"),
    LOGIN_401("Неверный пароль"),
    LOGIN_404("Пользователь с таким email не найден"),
    TYPE_400("Тип с таким названием уже существует"),
    TYPE_500("Невозможно удалить тип, т.к. он используется");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

}
