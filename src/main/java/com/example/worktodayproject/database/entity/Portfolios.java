package com.example.worktodayproject.database.entity;

import com.example.worktodayproject.database.entity.base.BaseEntity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="Portfolios")
public class Portfolios extends BaseEntity {

    // Техническая информация по портфолио

    @Column(name="file_path")
    String filePath;
    @Column(name="url")
    String url;
    @Column(name="upload_date")
    Date uploadDate;


    @OneToOne
    UsersInfo userInfo;
}
