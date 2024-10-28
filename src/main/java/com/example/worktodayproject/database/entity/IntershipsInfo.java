package com.example.worktodayproject.database.entity;

import com.example.worktodayproject.database.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="IntershipsInfo", schema="public")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
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

    @ManyToMany
    @JoinTable(
            name = "tag_intership_info",
            joinColumns = {
                    @JoinColumn(name = "intership_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "tag_id")
            }
    )
    Set<Tags> tags = new HashSet<>();
}
