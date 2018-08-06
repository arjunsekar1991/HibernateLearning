package com.example.hibernatelearning;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HibernateLearningApplication {
	@Autowired
    private SessionFactory sessionFactory;
	@Autowired
	private Employee employee;
	public static void main(String[] args) {
		SpringApplication.run(HibernateLearningApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner(){
		return args -> {
			System.out.println("Starting command Line runner");
			Session session = sessionFactory.openSession();
			String SQL = "select version()";
		String result =	(String)session.createNativeQuery(SQL).getSingleResult();
		System.out.println("My SQL Version ::"+result);
		System.out.println(session.isConnected());
	//	Employee employee = new Employee();
		employee.setDoj(new Date());
		employee.setEmployeeName("Arjun");
		employee.setSalary(2.00);
		employee.setEmail("arjun@gmail.com");
		session.beginTransaction();
		session.save(employee);
		session.getTransaction().commit();
		session.close();
		System.out.println(session.isConnected());
		};
	}
}
