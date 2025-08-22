package com.jasminegadelhak.groupexpensetracker;


import com.jasminegadelhak.groupexpensetracker.model.Currency;
import com.jasminegadelhak.groupexpensetracker.model.Expense;
import com.jasminegadelhak.groupexpensetracker.model.Member;
import com.jasminegadelhak.groupexpensetracker.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class GroupExpenseTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroupExpenseTrackerApplication.class, args);
	}

	@Bean
	@Profile("!test") // won't run this bean when test profile is active
	public CommandLineRunner initSampleData(MemberRepository memberRepo, ExpenseRepository expenseRepository){
		/*
		Note to self:
		The CommandLineRunner is an interface in Spring Boot. When a class implements this interface,
		Spring Boot will automatically run its run method after loading the application context.
		Usually, we use this CommandLineRunner to perform startup tasks like user or database initialization, seeding,
		or other startup activities.
		https://mkyong.com/spring-boot/spring-boot-commandlinerunner-example/
		 */

		return (args) -> {
			Member alice = new Member("Alice");
			Member bob = new Member("Bob");
			Member charlie = new Member("Charlie");

			memberRepo.save(alice);
			memberRepo.save(bob);
			memberRepo.save(charlie);

			// Sample expenses for the trip
			List<Expense> expenses = Arrays.asList(
					new Expense("Rome Hotel (3 nights)", 550, Currency.CAD, alice, null, Expense.Category.ACCOMMODATION),
					new Expense("Dinner in Milan", 120, Currency.CAD, bob, null, Expense.Category.FOOD),
					new Expense("Train from Rome to Florence", 180, Currency.CAD, charlie, null, Expense.Category.TRANSPORTATION),
					new Expense("Cooking Class in Florence", 200, Currency.CAD, alice, null, Expense.Category.ACTIVITY),
					new Expense("Gelato in Florence", 15, Currency.CAD, charlie, null, Expense.Category.FOOD),
					new Expense("Vatican Museum Tickets", 90, Currency.CAD, bob, null, Expense.Category.ACTIVITY),
					new Expense("Taxi to Airport", 60, Currency.CAD, alice, null, Expense.Category.TRANSPORTATION)
			);

			for (Expense e : expenses) {
				expenseRepository.save(e);
			}
		};
	}


}
