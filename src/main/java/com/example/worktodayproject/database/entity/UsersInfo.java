package com.example.worktodayproject.database.entity;

import com.example.worktodayproject.database.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users_info")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class UsersInfo extends BaseEntity {

    @Column(name="name")
    String name;
    @Column(name="surname")
    String surname;
    @Column(name="patronymic")
    String patronymic;
    @Column(name="recomendation_flag")
    Boolean recomendationFlag;
    @Column(name="phone_number")
    String phoneNumber;
    @Column(name="town")
    String town;
    @Column(name = "user_photo")
    String userPhoto;

    @OneToOne
    @JoinColumn(name = "user_id")
    Users users;

    @OneToOne(mappedBy = "userInfo")
    Portfolios portfolio;

    @OneToOne(mappedBy = "userInfo")
    Resumes resume;

    @OneToMany(mappedBy = "userInfo")
    List<Reports> reports = new ArrayList<>();

    @OneToMany(mappedBy = "userInfo")
    List<IntershipsInfo> intershipsInfo = new ArrayList<>();

    @OneToMany(mappedBy = "userInfo")
    List<Enrollment> enrollments = new ArrayList<>();

    @OneToMany(mappedBy = "userInfo")
    List<InternshipsResult> results;
}
