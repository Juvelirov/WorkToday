package com.example.worktodayproject.database.entity;

import com.example.worktodayproject.database.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * Сущность для записи на стажировку
 */
@Entity
@Table(name = "enrollments")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Enrollment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    Users users;

    @ManyToOne
    @JoinColumn(name = "intership_id")
    IntershipsInfo intershipsInfo;
}
