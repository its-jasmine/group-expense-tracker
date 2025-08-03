package com.jasminegadelhak.groupexpensetracker.controller;

import com.jasminegadelhak.groupexpensetracker.model.*;
import com.jasminegadelhak.groupexpensetracker.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GroupExpenseTrackerController {
    private MemberRepository memberRepo;
    private ExpenseRepository expenseRepo;

    private OwingCalculator owingCalculator;

    @Autowired
    GroupExpenseTrackerController(MemberRepository memberRepo, ExpenseRepository expenseRepo){
        this.memberRepo = memberRepo;
        this.expenseRepo = expenseRepo;
        this.owingCalculator = new OwingCalculator();
    }

    @GetMapping
    public String home(Model model){
        model.addAttribute("member", new Member());
        model.addAttribute("expense", new Expense());
        List<Member> members =  memberRepo.findAll();
        if (!members.isEmpty()) { model.addAttribute("members", members); }

        List<Expense> expenses =  expenseRepo.findAll();
        if (!expenses.isEmpty()) { model.addAttribute("expenses",expenses);}

        // TODO total expenses

        model.addAttribute("currencies", Currency.values());

        OwingCalculator owingCalculator = new OwingCalculator();
        model.addAttribute("owings", owingCalculator.calculateOwings(members, expenses).entrySet().stream().toArray());


        return "home";
    }

    @PostMapping("/members/create")
    public String addMember(@ModelAttribute Member member){
        memberRepo.save(member);
        return "redirect:/";
    }
    @PostMapping("/members/delete")
    public String removeMember(@RequestParam("id") Long id){
        memberRepo.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/members/edit")
    public String editMember(@RequestParam("id") Long id,  @RequestParam("name")String newName){
        Member m = memberRepo.findById(id).get();
        m.setName(newName);
        memberRepo.save(m);

        return "redirect:/";
    }

    @PostMapping("/expenses/create")
    public String addExpense(@ModelAttribute Expense expense){
        expenseRepo.save(expense);
        return "redirect:/";
    }
    @PostMapping("/expenses/delete")
    public String removeExpense(@RequestParam("id") Long id){
        expenseRepo.deleteById(id);
        return "redirect:/";
    }
    @PostMapping("/expenses/edit")
    public String editExpense(@ModelAttribute Expense expense){
        expenseRepo.save(expense);
        return "redirect:/";
    }
}
