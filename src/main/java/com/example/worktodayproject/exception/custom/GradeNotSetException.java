package com.example.worktodayproject.exception.custom;

import com.example.worktodayproject.database.enums.TaskStatus;

/**
 * Ошибка, которая появляется когда нельзя поставить оценку
 */
public class GradeNotSetException extends RuntimeException {

    public static final String CODE = "400";

    public GradeNotSetException(TaskStatus taskStatus) {
        super("Задание нельзя оценить, так как оно в статусе " + taskStatus.name());
    }
}
