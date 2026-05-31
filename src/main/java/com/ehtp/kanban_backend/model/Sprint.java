package com.ehtp.kanban_backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Data
public class Sprint extends BaseAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sprintId;
    @Column(nullable=false, unique=true)
    private String objective;
    @Column(nullable=false)
    private LocalDate startDate;
    @Column(nullable=false)
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private SprintStatus sprintStatus;
    public enum SprintStatus { PLANNED, onProgress, DONE };
    @Column(nullable=false)
    private Integer pointsPlanned;
    // add velocity attribute

    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private Projet project;

    @OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    @OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AiInsight> aiInsights = new ArrayList<>();
}
