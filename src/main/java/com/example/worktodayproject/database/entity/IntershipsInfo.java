package com.example.worktodayproject.database.entity;

import com.example.worktodayproject.database.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="interships_info", schema="public")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class IntershipsInfo extends BaseEntity {

    @Column(name = "company")
    String company;
    @Column(name="title")
    String title;
    @Column(name = "duties")
    String duties;
    @Column(name = "requirements")
    String requirements;
    @Column(name = "task")
    String task;
    @Column(name="fields")
    String fields;
    @Column(name = "town")
    String town;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    Users user;

    @OneToMany(mappedBy = "intershipsInfo")
    List<Tasks> tasks;

    @OneToMany(mappedBy = "intershipsInfo")
    List<Reports> reports;

    @OneToMany(mappedBy = "intershipsInfo")
    List<Enrollment> enrollments = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "tag_interships_info",
            joinColumns = {
                    @JoinColumn(name = "interships_info_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "tag_id")
            }
    )
    Set<Tags> tags = new HashSet<>();
}
