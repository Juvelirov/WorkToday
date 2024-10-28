package com.example.worktodayproject.database.entity;

import com.example.worktodayproject.database.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Table(name="portfolios")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class Portfolios extends BaseEntity {

    // Техническая информация по портфолио

    @Column(name="file_path")
    String filePath;
    @Column(name="url")
    String url;
    @Column(name="upload_date")
    Date uploadDate;

    @OneToOne
    @JoinColumn(name = "user_info_id")
    UsersInfo userInfo;
}
