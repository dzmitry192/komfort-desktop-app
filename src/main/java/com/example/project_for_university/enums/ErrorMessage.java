package com.example.project_for_university.enums;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    DEFAULT("Непредвиденная ошибка"),
    ENTITY_NOT_FOUND("То, что вы пытаетесь удалить уже не существует"),
    SERVER_ERROR("Ошибка соеденения с сервером, попробуйте позже"),
    SIGNUP_400_EMAIL("Некорректный email"),
    SIGNUP_400_PASSWORD("Разрешенные символы пароля: 0-9, A-z, !@#$%^&*"),
    SIGNUP_401("Пользователь с таким email уже существует"),
    SIGNUP_404("Пользователь с таким email не найден"),
    LOGIN_400("Некорректный email"),
    LOGIN_401("Неверный пароль"),
    LOGIN_404("Пользователь с таким email не найден"),
    TYPE_400("Тип с таким названием уже существует"),
    TYPE_404("Выбранного типа уже не существует"),
    TYPE_500("Невозможно удалить тип, т.к. он используется"),
    MATERIAL_400("Артикул с таким названием уже существует"),
    MATERIAL_SIZE_LIMIT("Максимальный размер изображения составляет 5мб, удалите изображения превышающие лимит и попробуйте снова"),
    MATERIAL_NOT_FOUND("Данного артикула уже не существует"),
    MATERIAL_PATCH_403("Вы можете удалять только свои материалы"),
    MATERIAL_DELETE_403("Вы можете удалять только свои материалы");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

}
