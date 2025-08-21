package com.jasminegadelhak.groupexpensetracker.repositories;

import com.jasminegadelhak.groupexpensetracker.model.Expense;
import com.jasminegadelhak.groupexpensetracker.model.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Long> {
    public List<Expense> findAll();


    List<Expense> findByPaidBy(Member member);
    @Query("SELECT SUM(e.amount) FROM Expense e")
    public Float sumAllExpenses();
}
