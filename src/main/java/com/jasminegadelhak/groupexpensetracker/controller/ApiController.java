package com.jasminegadelhak.groupexpensetracker.controller;

import com.jasminegadelhak.groupexpensetracker.model.*;
import com.jasminegadelhak.groupexpensetracker.repositories.ExpenseRepository;
import com.jasminegadelhak.groupexpensetracker.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final MemberRepository memberRepository;
    private final ExpenseRepository expenseRepository;

    @Autowired
    public ApiController(MemberRepository memberRepository, ExpenseRepository expenseRepository) {
        this.memberRepository = memberRepository;
        this.expenseRepository = expenseRepository;
    }

    // Members
    @GetMapping("/members")
    public List<Member> listMembers() {
        return memberRepository.findAll();
    }

    @PostMapping("/members")
    public Member createMember(@RequestBody Member member) {
        return memberRepository.save(member);
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<Object> deleteMember(@PathVariable Long id) {
        return memberRepository.findById(id)
                .map(m -> {
                    memberRepository.delete(m);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Expenses
    @GetMapping("/expenses")
    public List<Expense> listExpenses() {
        return expenseRepository.findAll();
    }

    @PostMapping("/expenses")
    public Expense createExpense(@RequestBody Expense expense) {
        return expenseRepository.save(expense);
    }

    @DeleteMapping("/expenses/{id}")
    public ResponseEntity<Object> deleteExpense(@PathVariable Long id) {
        return expenseRepository.findById(id)
                .map(e -> {
                    expenseRepository.delete(e);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Derived data: debts and balances
    @GetMapping("/summary")
    public Map<String, Object> getSummary() {
        List<Member> members = memberRepository.findAll();
        List<Expense> expenses = expenseRepository.findAll();

        List<Debt> debts = DebtCalculator.calculateAllDebt(members, expenses);
        Map<Member, Float> balances = BalanceCalculator.calculateBalances(debts);

        Map<String, Object> payload = new HashMap<>();
        payload.put("members", members);
        payload.put("expenses", expenses);
        payload.put("debts", debts);
        payload.put("balances", balances);
        payload.put("expenseSum", expenseRepository.sumAllExpenses());
        payload.put("expenseCount", expenseRepository.count());
        payload.put("memberCount", memberRepository.count());
        payload.put("currencies", Currency.values());
        payload.put("categories", Expense.Category.values());
        return payload;
    }
}


