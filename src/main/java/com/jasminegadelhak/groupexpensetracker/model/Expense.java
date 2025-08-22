package com.jasminegadelhak.groupexpensetracker.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Expense {

    public enum Category {
        FOOD,
        ACCOMMODATION,
        ACTIVITY,
        TRANSPORTATION
    }
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private float amount; // TODO use BigDecimal, maybe create custom Amount class
    private Currency currency;
    private Category category;
    @ManyToOne // TODO future iteration: change to list, @OneToMany
    private Member paidBy;
    @OneToMany
    private List<Member> members;
    public Expense(){
        this.members = new ArrayList<>();
    }

    public Expense(String name, float amount, Currency currency, Member paidBy, List<Member> members, Category category) {
        this.name = name;
        this.amount = amount;
        this.currency = currency;
        this.paidBy = paidBy;
        this.members = members;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
}
