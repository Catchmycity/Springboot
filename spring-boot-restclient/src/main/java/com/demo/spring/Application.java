package com.demo.spring;

import java.util.ArrayList;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        String stringResponse = getStringResponse();

        LOGGER.info(stringResponse);

        Employee emp = getEmployeeObjectFromJSON();

        LOGGER.info("Name:" + emp.getName());
        LOGGER.info("City:" + emp.getCity());

        Employee empXmp = getEmployeeObjectFromXML();
        LOGGER.info("Name:" + empXmp.getName());
        LOGGER.info("City:" + empXmp.getCity());

        Employee postEmp = postJsonAndGetXMLEmployeeObject(new Employee("Peter", "New York"));
        LOGGER.info("Name:" + postEmp.getName());
        LOGGER.info("City:" + postEmp.getCity());

    }

    public static String getStringResponse() {
        HttpHeaders header = new HttpHeaders();
        header.set("Accept", "application/json");
        HttpEntity req = new HttpEntity(header);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/getJson", HttpMethod.GET, req,
                String.class);

        return response.getBody();
    }

    public static Employee getEmployeeObjectFromJSON() {
        HttpHeaders header = new HttpHeaders();
        header.set("Accept", "application/json");
        HttpEntity req = new HttpEntity(header);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Employee> empResponse = restTemplate.exchange("http://localhost:8080/getJson", HttpMethod.GET,
                req, Employee.class);

        Employee emp = empResponse.getBody();
        return emp;

    }

    public static Employee getEmployeeObjectFromXML() {
        HttpHeaders header = new HttpHeaders();
        header.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
        HttpEntity req = new HttpEntity(header);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Employee> empResponse = restTemplate.exchange("http://localhost:8080/getXML", HttpMethod.GET,
                req, Employee.class);

        Employee emp = empResponse.getBody();
        return emp;

    }

    public static Employee postJsonAndGetXMLEmployeeObject(Employee empEntity) {
        HttpHeaders header = new HttpHeaders();
        header.setAccept(Arrays.asList(MediaType.APPLICATION_XML));  // Spring understand the response is xml and JAXB convert to employee object
        header.setContentType((MediaType.APPLICATION_JSON));
        HttpEntity req = new HttpEntity(empEntity, header);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Employee> empResponse = restTemplate.exchange("http://localhost:8080/saveEmployee",
                HttpMethod.POST, req, Employee.class);

        Employee emp = empResponse.getBody();
        return emp;

    }

}
