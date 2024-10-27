package com.example.worktodayproject.database.entity;

import com.example.worktodayproject.database.entity.base.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name="IntershipsInfo", schema="public")

public class IntershipsInfo extends BaseEntity {
    @Column(name="title")
    String title;
    @Column(name="description")
    String description;
    @Column(name="fields")
    String fields;

    @ManyToOne()
    @JoinColumn(name = "creator_id")
    Users user;


}
