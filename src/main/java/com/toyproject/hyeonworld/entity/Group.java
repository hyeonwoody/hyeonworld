package com.toyproject.hyeonworld.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Table (name="group_table")
public class Group {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id", unique = true, nullable = false)
    private Long id;

    @Column(name="group_type", nullable = false)
    private Integer groupType;

    @Column(name="persons", nullable = false)
    private Integer persons;

    public void setGroup(Integer group) {
        this.groupType = groupType;
    }

//    public void setGroup(Integer group) {
//        this.groupType
//    }
}
