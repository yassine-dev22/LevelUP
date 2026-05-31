package com.ehtp.kanban_backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.Data;

@Entity
@Data
public class AiInsight extends BaseAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 4000)
    private String explanation;

    @Column(nullable = false)
    private Integer confidenceScore;

    @Column(nullable = false)
    private Boolean acknowledged;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InsightType insightType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sprint_id", nullable = false)
    private Sprint sprint;

    public enum InsightType {
        RISK_ALERT,
        REASSIGNMENT_SUGGESTION,
        PATTERN_ANALYSIS
    }
}
