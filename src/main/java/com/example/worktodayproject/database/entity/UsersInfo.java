package com.example.worktodayproject.database.entity;

import com.example.worktodayproject.database.entity.base.BaseEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="UsersInfo")
public class UsersInfo extends BaseEntity {

    // Информация по персональным данным User

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

    @OneToOne(mappedBy = "userInfo")
    @JoinColumn(name = "portfolio_id")
    Portfolios portfolio;

    @OneToOne(mappedBy = "userInfo")
    @JoinColumn(name = "resume_id")
    Resumes resume;

    @OneToMany(mappedBy = "userInfo")
    List<Reports> reports = new ArrayList<>();

    @OneToMany(mappedBy = "usersInfo")
    List<Tasks> tasks = new ArrayList<>();
}
