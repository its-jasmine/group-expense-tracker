package com.jasminegadelhak.groupexpensetracker.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BalanceCalculatorTest {
    private Member m1 = new Member("Bill");
    private Member m2 = new Member("Bob");

    private Member m3 = new Member("Jane");
    private Member m4 = new Member("Jill");


    @Test
    void NoDebts() {
        // Simple two-person scenario
        Map<Member, Float> balances = BalanceCalculator.calculateBalances(Collections.emptyList());
        assertTrue(balances.isEmpty());
    }

    @Test
    void OneDebt() {
        // Simple two-person scenario
        List<Debt> debtList = List.of(new Debt(m1, m2, 25.50f, Currency.CAD)); // bill owes bob CAD$25.50

        Map<Member, Float> balances = BalanceCalculator.calculateBalances(debtList);

        assertEquals(2, balances.size());
        assertTrue(balances.containsKey(m1));
        assertTrue(balances.containsKey(m2));
        assertEquals(-25.50f,balances.get(m1));
        assertEquals(25.50f,balances.get(m2));
        verifyBalanceSum(balances.values());

    }

    @Test
    void ChainOfDebt() {
        // Simple linear chain A->B->C->D
        List<Debt> debtList = List.of(
                new Debt(m1, m2, 100f, Currency.CAD), // bill owes bob CAD$100.00
                new Debt(m2,m3, 50f, Currency.CAD), // bob owes jane CAD$50.00
                new Debt(m3,m4, 25f, Currency.CAD) // jane owes jill CAD$25.00
                );

        Map<Member, Float> balances = BalanceCalculator.calculateBalances(debtList);

        assertEquals(4, balances.size());
        assertTrue(balances.containsKey(m1));
        assertTrue(balances.containsKey(m2));
        assertTrue(balances.containsKey(m3));
        assertTrue(balances.containsKey(m4));
        assertEquals(-100f,balances.get(m1));
        assertEquals(50f,balances.get(m2));
        assertEquals(25f,balances.get(m3));
        assertEquals(25f,balances.get(m4));
        verifyBalanceSum(balances.values());


    }

    @Test
    void OnePaidForAll(){
        // One person paid for everything, so everyone else owes them
        List<Debt> debtList = List.of(
                new Debt(m2, m1, 100f, Currency.CAD), // bob owes bill CAD$100.00
                new Debt(m3,m1, 75f, Currency.CAD), // jane owes bill CAD$75.00
                new Debt(m4,m1, 50f, Currency.CAD) // jill owes bill CAD$50.00
        );

        Map<Member, Float> balances = BalanceCalculator.calculateBalances(debtList);

        assertEquals(4, balances.size());
        assertTrue(balances.containsKey(m1));
        assertTrue(balances.containsKey(m2));
        assertTrue(balances.containsKey(m3));
        assertTrue(balances.containsKey(m4));
        assertEquals(225f,balances.get(m1));
        assertEquals(-100f,balances.get(m2));
        assertEquals(-75f,balances.get(m3));
        assertEquals(-50f,balances.get(m4));
        verifyBalanceSum(balances.values());

    }

    @Test
    void CircularDebt() {
        // Simple linear chain A->B->C->D
        List<Debt> debtList = List.of(
                new Debt(m1, m2, 60f, Currency.CAD), // bill owes bob CAD$60.00
                new Debt(m2,m3, 40f, Currency.CAD), // bob owes jane CAD$40.00
                new Debt(m3,m4, 30f, Currency.CAD), // jane owes jill CAD$30.00
                new Debt(m4,m1, 20f, Currency.CAD) // jill owes bill CAD$20.00
        );

        Map<Member, Float> balances = BalanceCalculator.calculateBalances(debtList);

        assertEquals(4, balances.size());
        assertTrue(balances.containsKey(m1));
        assertTrue(balances.containsKey(m2));
        assertTrue(balances.containsKey(m3));
        assertTrue(balances.containsKey(m4));
        assertEquals(-40f,balances.get(m1));
        assertEquals(20f,balances.get(m2));
        assertEquals(10f,balances.get(m3));
        assertEquals(10f,balances.get(m4));
        verifyBalanceSum(balances.values());

    }
    private static void verifyBalanceSum(Collection<Float> balances){
        Float sum = 0f;
        for (Float val : balances){
            sum+= val;

        }

        assertEquals(0f, sum);
    }


}