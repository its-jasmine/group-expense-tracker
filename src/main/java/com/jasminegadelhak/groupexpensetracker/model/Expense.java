package com.jasminegadelhak.groupexpensetracker.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Expense {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private float amount; // TODO use BigDecimal, maybe create custom Amount class
    private Currency currency;
    @ManyToOne // TODO future iteration: change to list, @OneToMany
    private Member paidBy;
    @OneToMany
    private List<Member> members;
    public Expense(){
        this.members = new ArrayList<>();
    }

    public Expense(String name, float amount, Currency currency, Member paidBy, List<Member> members) {
        this.name = name;
        this.amount = amount;
        this.currency = currency;
        this.paidBy = paidBy;
        this.members = members;
    }

    /* Getters & Setters */
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Member getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(Member paidBy) {
        this.paidBy = paidBy;
    }

    public List<Member> getMembers() {
        return members;
    }
    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
