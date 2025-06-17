package com.jasminegadelhak.groupexpensetracker;


import com.jasminegadelhak.groupexpensetracker.model.Member;
import com.jasminegadelhak.groupexpensetracker.repositories.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GroupExpenseTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroupExpenseTrackerApplication.class, args);
	}

	@Bean
	public CommandLineRunner initSampleData(MemberRepository memberRepo){
		/*
		Note to self:
		The CommandLineRunner is an interface in Spring Boot. When a class implements this interface,
		Spring Boot will automatically run its run method after loading the application context.
		Usually, we use this CommandLineRunner to perform startup tasks like user or database initialization, seeding,
		or other startup activities.
		https://mkyong.com/spring-boot/spring-boot-commandlinerunner-example/
		 */
		return (args) -> {
			Member m = null;
			for (int i  = 1; i <= 3; i++){
				m = new Member("Member " + i);
				memberRepo.save(m);
			}
		};
	}


}
