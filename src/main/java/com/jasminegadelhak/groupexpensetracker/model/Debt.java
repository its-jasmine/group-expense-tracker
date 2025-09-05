package com.jasminegadelhak.groupexpensetracker.model;

public record Debt(Member payer, Member payee, Float amount, Currency currency) {
    public String toString(){
        return payer.getName() + " owes " + payee.getName() + ": " + currency.name() + String.format("%.2f");
    }
}
