package com.jasminegadelhak.groupexpensetracker.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BalanceCalculator {
    public static Map<Long, Float> calculateBalances(List<Debt> debtList){
        Map<Long, Float> balances = new HashMap<>();
        for (Debt debt: debtList){
            if (debt.currency().equals(Currency.CAD)) {// TODO, make it work for all currencies
                Long payerId = debt.payer().getId();
                Long payeeId = debt.payee().getId();
                if (!balances.containsKey(payerId)) balances.put(payerId, 0f);
                if (!balances.containsKey(payeeId)) balances.put(payeeId, 0f);
                balances.put(payerId, balances.get(payerId) - debt.amount());
                balances.put(payeeId, balances.get(payeeId) + debt.amount());
            }
        }
        return balances;
    }
}
