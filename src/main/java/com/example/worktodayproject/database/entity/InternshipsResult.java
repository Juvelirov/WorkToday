package com.example.worktodayproject.database.entity;

import com.example.worktodayproject.database.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Table(name="internships_result")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class InternshipsResult extends BaseEntity {
    @Column(name="mark")
    double mark;
    @Column(name="recomendation")
    Boolean recomendation;
    @Column(name="final_date")
    Date finalDate;

    @OneToOne
    @JoinColumn(name = "report_id")
    Reports report;
}
