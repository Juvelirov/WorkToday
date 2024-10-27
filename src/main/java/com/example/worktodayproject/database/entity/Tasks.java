package com.example.worktodayproject.database.entity;

import com.example.worktodayproject.database.entity.base.BaseEntity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="Tasks")
public class Tasks extends BaseEntity {
    @Column(name="title")
    String title;
    @Column(name="info")
    String info;
    @Column(name="deadline")
    Date deadline;

    @ManyToOne()
    @JoinColumn(name="task_id")
    UsersInfo usersInfo;

}
