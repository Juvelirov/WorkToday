package com.example.worktodayproject.database.entity;

import com.example.worktodayproject.database.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Table(name="resumes")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class Resumes extends BaseEntity {

    // Техническая информация по резюме

    @Column(name="url")
    String url;
    @Column(name="file_path")
    String filePath;
    @Column(name="upload_date")
    Date uploadDate;

    @OneToOne
    @JoinColumn(name = "user_info_id")
    UsersInfo userInfo;

}
