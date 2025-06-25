package com.jasminegadelhak.groupexpensetracker.controller;

import com.jasminegadelhak.groupexpensetracker.model.*;
import com.jasminegadelhak.groupexpensetracker.repositories.*;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GroupExpenseTrackerController.class)
@ActiveProfiles("test") // applied to test class to declare which bean defintions to activate
class GroupExpenseTrackerControllerTest {

    @Autowired
    private MockMvc mockMvc; // simulates HTTP requests
    @MockBean
    private ExpenseRepository expenseRepository;
    @MockBean
    private MemberRepository memberRepository;

    @Test
    void home() throws Exception {
        // no members or expenses added
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().size(3))
                .andExpect(model().attributeExists("member"))
                .andExpect(model().attributeExists("expense"))
                .andExpect(model().attributeExists("currencies"))
                .andExpect(view().name("home"));

        // add a member & expense
        Member m1 = new Member("memberTest");
        when(memberRepository.findAll()).thenReturn(List.of(m1));
        when(expenseRepository.findAll()).thenReturn(List.of(new Expense("expenseTest", 5, Currency.CAD, m1, null)));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attributeExists("member"))
                .andExpect(model().attributeExists("expense"))
                .andExpect(model().attributeExists("members"))
                .andExpect(model().attributeExists("expenses"))
                .andExpect(model().attributeExists("currencies"));
    }

    @Test
    void addMember() throws Exception {
        mockMvc.perform(post("/members/create")
                        .param("name", "test"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(memberRepository).save(any(Member.class));
    }

    @Test
    void removeMember() throws Exception {
        mockMvc.perform(post("/members/delete")
                        .param("id", "5"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(memberRepository).deleteById(any(Long.class));
    }

    @Test
    void addExpense() throws Exception {
        mockMvc.perform(post("/expenses/create")
                        .param("title", "Lunch with team")
                        .param("amount", "42.50")
                        .param("currency", "USD")
                        .param("paidBy", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(expenseRepository).save(any(Expense.class));
    }

    @Test
    void removeExpense() throws Exception{
        mockMvc.perform(post("/expenses/delete")
                        .param("id", "5"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        verify(expenseRepository).deleteById(any(Long.class));
    }
}