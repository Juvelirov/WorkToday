package com.example.worktodayproject.database.entity;

import com.example.worktodayproject.database.entity.base.BaseEntity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="Reports")
public class Reports extends BaseEntity {
    @Column(name="title")
    String title;
    @Column(name="description")
    String description;

    @ManyToOne
    @JoinColumn(name="report_id")
    UsersInfo userInfo;

    @OneToOne(mappedBy = "report")
    InternshipsResult result;
}
