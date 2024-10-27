package com.example.worktodayproject.database.entity;

import com.example.worktodayproject.database.entity.base.BaseEntity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="InternshipsResult")
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
