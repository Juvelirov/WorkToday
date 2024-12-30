package com.example.worktodayproject.exception.custom;

import com.example.worktodayproject.database.enums.ResultStatus;

/**
 * Ошибка, которая появляется когда нельзя поставить оценку
 */
public class GradeNotSetException extends RuntimeException {

    public static final String CODE = "400";

    public GradeNotSetException(ResultStatus resultStatus) {
        super("Задание нельзя оценить, так как оно в статусе " + resultStatus.name());
    }
}
