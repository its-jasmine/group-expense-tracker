package com.jasminegadelhak.groupexpensetracker.controller;

import com.jasminegadelhak.groupexpensetracker.model.Expense;
import com.jasminegadelhak.groupexpensetracker.model.Member;
import com.jasminegadelhak.groupexpensetracker.repositories.ExpenseRepository;
import com.jasminegadelhak.groupexpensetracker.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GroupExpenseTrackerController {
    private MemberRepository memberRepo;
    private ExpenseRepository expenseRepo;

    @Autowired
    GroupExpenseTrackerController(MemberRepository memberRepo, ExpenseRepository expenseRepo){
        this.memberRepo = memberRepo;
        this.expenseRepo = expenseRepo;
    }

    @GetMapping
    public String home(Model model){
        model.addAttribute("member", new Member());
        model.addAttribute("expense", new Expense());
        List<Member> members =  memberRepo.findAll();
        if (!members.isEmpty()) { model.addAttribute("members", members); }

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
}
