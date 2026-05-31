package com.ehtp.kanban_backend.model;

import jakarta.persistence.*;
import lombok.Data;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User extends BaseAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false)
    private String fullName;

    @Enumerated(EnumType.STRING)
    private Role role;
    public enum Role { Owner ,Member, Viewer }
    private Integer capacityPoints;

    @ManyToMany(mappedBy = "members")
    private List<Projet> projects = new ArrayList<>();

    @OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    public User() {
    }

    public User(String email, String fullName, Role role, Integer capacityPoints) {
        this.email = email;
        this.fullName = fullName;
        this.role = role;
        this.capacityPoints = capacityPoints;
    }
}