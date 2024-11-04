package com.example.worktodayproject.database.entity;

import com.example.worktodayproject.database.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Table(name="tasks")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class Tasks extends BaseEntity {
    @Column(name="title")
    String title;
    @Column(name="info")
    String info;
    @Column(name="deadline")
    Date deadline;

    @ManyToOne
    @JoinColumn(name="users_info_id")
    UsersInfo usersInfo;
}
