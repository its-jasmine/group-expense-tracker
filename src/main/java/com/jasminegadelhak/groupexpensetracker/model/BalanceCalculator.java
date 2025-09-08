package com.jasminegadelhak.groupexpensetracker.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BalanceCalculator {
    public static Map<Member, Float> calculateBalances(List<Debt> debtList){
        Map<Member, Float> balances = new HashMap<>();
        for (Debt debt: debtList){
            if (debt.currency().equals(Currency.CAD)) {// TODO, make it work for all currencies
                if (!balances.containsKey(debt.payer())) balances.put(debt.payer(), 0f);
                if (!balances.containsKey(debt.payee())) balances.put(debt.payee(), 0f);
                balances.put(debt.payer(), balances.get(debt.payer()) - debt.amount());
                balances.put(debt.payee(), balances.get(debt.payee()) + debt.amount());
            }
        }
        return balances;
    }
}
