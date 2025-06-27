package com.jasminegadelhak.groupexpensetracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    //private float expensesPaid; TODO look into efficiency advantages of keeping a running tally, decide on best design

    public Member(String name) {
        this.name = name;
    }

    public Member() {}

    /* Getters & Setters */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
