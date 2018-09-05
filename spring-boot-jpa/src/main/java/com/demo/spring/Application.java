package com.demo.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication

public class Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);

		EmpService empService = context.getBean(EmpService.class);
		Emp e = empService.findById(101);
		System.out.println("Employee fetched" + e);

		List<Emp> empList = empService.getEmpList();
		System.out.println("Emp List" + empList);

		int result = empService.delete(103);

		System.out.println("Deleted count" + result);

		String saveResult = empService.save(new Emp(102, "Peter", "Indiana", 60000));
		System.out.println("Save result" + saveResult);
	}
}
