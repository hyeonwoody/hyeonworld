package com.toyproject.hyeonworld.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="submission")
public class Submission {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "submission_id", unique = true)
    private Long id;
}