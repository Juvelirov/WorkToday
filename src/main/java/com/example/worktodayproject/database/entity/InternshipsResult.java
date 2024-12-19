package com.example.worktodayproject.database.entity;

import com.example.worktodayproject.database.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name="internships_result")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class InternshipsResult extends BaseEntity {
    @Column(name="mark")
    double mark;
    @Column(name="recomendation")
    Boolean recomendation;
    @Column(name="final_date")
    LocalDateTime finalDate;

    @OneToOne
    @JoinColumn(name = "report_id")
    Reports report;
}
