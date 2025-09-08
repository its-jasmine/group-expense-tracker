package com.jasminegadelhak.groupexpensetracker.model;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DebtCalculatorTest {
    private static Member John = new Member("John");
    private static Member Bob = new Member("Bob");
    private static Member Rick = new Member("Rick");
    private static List<Member> memberList = new ArrayList<>(List.of(John, Bob, Rick));
    private static Expense expense1 = new Expense("Dinner in Florence",3 ,Currency.CAD, John, null, null); // TODO update to account for 'members'
    private static Expense expense2 = new Expense("Train to Rome",9 ,Currency.CAD, Bob, null, null);
    private static Expense expense3 = new Expense("Hotel in Milan",18 ,Currency.CAD, Rick, null, null);
    private static List<Expense> expenseList = new ArrayList<>(List.of(expense1, expense2, expense3));

    /*
    Test case:
    John paid $3. -> $1/person
    Bob paid $9 -> $3/person
    Rick paid $18 ->$6/person

    John & Bob
        Bob owes John $1
        John owes Bob $3...
        NET OWING: John owes Bob $2.

    Bob & Rick
        Bob owes Rick $6
        Rick owes Bob $3
        NET OWING: Bob owes Rick $3

    Rick & John
        Rick owes John $1
        John owes Rick $6
        NET OWING: John owes Rick $5

     */

    @Test
    void calculateOwings() {
        /*
        Expected result:
        {
            'John': {'Bob': 2, 'Rick': 5}, // John owes Bob $2 and Rick $5
            'Bob': {'Rick': 3}, // Bob owes Rick $3
            'Rick': {} // Rick doesn't owe anything
        }
         */

        List<Debt> actualOwing = DebtCalculator.calculateAllDebt(memberList, expenseList);
        assertEquals(3, actualOwing.size());
        assertTrue(actualOwing.contains(new Debt(John, Bob, (float)2, Currency.CAD)));
        assertTrue(actualOwing.contains(new Debt(John, Rick, (float)5, Currency.CAD)));
        assertTrue(actualOwing.contains(new Debt(Bob, Rick, (float)3, Currency.CAD)));
    }

    @Test
    void calculateTotalPaid() {
        assertEquals(3, DebtCalculator.calculateTotalPaid(John, Currency.CAD, expenseList));
        assertEquals(9, DebtCalculator.calculateTotalPaid(Bob, Currency.CAD, expenseList));
        assertEquals(18, DebtCalculator.calculateTotalPaid(Rick, Currency.CAD, expenseList));

        expenseList.add(new Expense("test4",20 ,Currency.CAD, Rick, null, null));

        assertEquals(38, DebtCalculator.calculateTotalPaid(Rick, Currency.CAD, expenseList));
    }
}