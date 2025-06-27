package com.jasminegadelhak.groupexpensetracker.model;

import java.util.*;

public class OwingCalculator {

    public Map<Member, Map<Member, Float>> calculateOwings(List<Member> members, List<Expense> expenses) {
        Map<Member, Float> totalPaid = getAllMembersTotalPaid(members, expenses);

        Map<Member, Map<Member, Float>> owings = new HashMap<>();

        Member member;
        for (int i = 0; i < members.size() - 1; i++) {
            member = members.get(i);

            for (int j = i + 1; j < members.size(); j++) {
                Member otherMember = members.get(j);

                float net = calculatedOwedAmount(totalPaid.get(member), totalPaid.get(otherMember), members.size());
                if (net != 0) {
                    Member ower = net < 0 ? member : otherMember;
                    Member owed = net < 0 ? otherMember : member;

                    if (! owings.containsKey(ower)) {
                        owings.put(ower, new HashMap<>());
                    }
                    owings.get(ower).put(owed, Math.abs(net));
                }

            }
        }
        return owings;
    }

    private float calculatedOwedAmount(float amount1, float amount2, int split){
        return (amount1 - amount2) / split; // assuming even split, TODO account for other splits
    }

    public Map<Member, Float> getAllMembersTotalPaid(List<Member> members, List<Expense> expenses){
        Map<Member, Float> totalPaid = new HashMap<>(members.size());

        for (Member member : members) {
            // for now assume we're only dealing with CAD,
            totalPaid.put(member, calculateTotalPaid(member, Currency.CAD, expenses)); // TODO adjust to calc for each currency...
        }
        return totalPaid;
    }

    public float calculateTotalPaid(Member member, Currency currency, List<Expense> expenses){
        float total = 0;

        for (Expense expense : expenses){
            if (expense.getCurrency().equals(currency) && expense.getPaidBy().equals(member)){
                total += expense.getAmount();
            }
        }
        return total;
    }
}
