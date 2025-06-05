package com.jasminegadelhak.groupexpensetracker;

import java.util.List;

public class Expense {
    private String name;
    private float amount;
    private Currency currency;
    private Member paidBy;
    private List<Member> members;

    public Expense(String name, float amount, Currency currency, Member paidBy, List<Member> members) {
        this.name = name;
        this.amount = amount;
        this.currency = currency;
        this.paidBy = paidBy;
        this.members = members;
    }
}
