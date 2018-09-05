package com.demo.spring;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

	@RequestMapping(path = "/greet/{name}")
	public String welcome(@PathVariable("name") String name, HttpServletRequest request) {
		String city = request.getParameter("city");
		return "Welcome " + name + city;
	}

	@RequestMapping(path = "/getJson", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Employee getJson() {
		Employee emp = new Employee();
		emp.setCity("chennai");
		emp.setName("John");
		return emp;
	}

	@RequestMapping(path = "/getXML", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody Employee getEmployeeInXML() {
		Employee emp = new Employee();
		emp.setCity("chennai");
		emp.setName("John");
		return emp;
	}

	@PostMapping(path = "/saveEmployee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody Employee getEmployeeInXML(@RequestBody Employee emp) {
		return emp;
	}
}
