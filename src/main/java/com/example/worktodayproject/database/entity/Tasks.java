package com.example.worktodayproject.database.entity;

import com.example.worktodayproject.database.entity.base.BaseEntity;
import com.example.worktodayproject.database.enums.TaskGrade;
import com.example.worktodayproject.database.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Table(name="tasks")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Tasks extends BaseEntity {
    @Column(name="title")
    String title;
    @Column(name="info")
    String info;
    @Column(name="deadline")
    Date deadline;
    @Column(name = "url")
    String url;
    @Column(name = "file_path")
    String filePath;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    TaskStatus status;
    @Enumerated(EnumType.STRING)
    @Column(name = "grade")
    TaskGrade grade;
    @Column(name = "result")
    String result;

    @ManyToOne
    @JoinColumn(name = "internship_info_id")
    IntershipsInfo intershipsInfo;
}
