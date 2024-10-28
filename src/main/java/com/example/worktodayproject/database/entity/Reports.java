package com.example.worktodayproject.database.entity;

import com.example.worktodayproject.database.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Table(name="reports")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class Reports extends BaseEntity {
    @Column(name="title")
    String title;
    @Column(name="description")
    String description;

    @ManyToOne
    @JoinColumn(name="user_info_id")
    UsersInfo userInfo;

    @OneToOne(mappedBy = "report")
    InternshipsResult result;
}
