package com.demo.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CalculatorController {

	@Autowired
	private Environment environment;

	@GetMapping(path = "/add/{a}/{b}")
	public String add(@PathVariable("a") int a, @PathVariable("b") int b) {
		String port = environment.getProperty("server.port");
		return "Response from Port=" + port + " Addition result = " + (a + b);
	}

}
