package com.demo.spring;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.demo.spring.Emp;
import com.demo.spring.EmpService;

@Component
public class RepoRunner implements CommandLineRunner {

	@Autowired
	EmpService empService;

	@Override
	public void run(String... args) throws Exception {
		
	}

}
