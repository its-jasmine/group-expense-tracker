package com.jasminegadelhak.groupexpensetracker.repositories;

import com.jasminegadelhak.groupexpensetracker.model.Expense;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Long> {
    public List<Expense> findAll();
    @Query("SELECT SUM(e.amount) FROM Expense e")
    public Float sumAllExpenses();
}
