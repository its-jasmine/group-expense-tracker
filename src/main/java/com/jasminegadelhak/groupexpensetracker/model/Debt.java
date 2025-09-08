package com.jasminegadelhak.groupexpensetracker.model;

import java.util.Objects;

public record Debt(Member payer, Member payee, Float amount, Currency currency) {
    @Override
    public String toString(){
        return payer.getName() + " owes " + payee.getName() + ": " + currency.name() + String.format("%.2f", amount);
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (! (o instanceof Debt)) return false;
        Debt debt = (Debt) o;
        return this.payer.equals(debt.payer) && this.payee.equals(debt.payee) && this.amount.equals(debt.amount) && this.currency.equals(debt.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payer, payee, amount, currency);
    }
}
