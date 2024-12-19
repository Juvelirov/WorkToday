package com.example.worktodayproject.exception.custom;

/**
 * Ошибка не нахождения роли
 */
public class RoleNotFoundException extends RuntimeException {

    public static final String CODE = "400";

    /**
     * Конструктор RoleNotFoundException, который исползует родительский класс
     * @param roleName
     */
    public RoleNotFoundException(String roleName) {
        super("Role " + roleName + "not found");
    }
}
