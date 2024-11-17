package com.example.worktodayproject.database.entity;

import com.example.worktodayproject.database.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="resumes")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Resumes extends BaseEntity {

    // Техническая информация по резюме

    @Column(name="url")
    String url;
    @Column(name="file_path")
    String filePath;
    @Column(name="upload_date")
    LocalDateTime uploadDate;

    @OneToOne
    @JoinColumn(name = "user_info_id")
    UsersInfo userInfo;

}
