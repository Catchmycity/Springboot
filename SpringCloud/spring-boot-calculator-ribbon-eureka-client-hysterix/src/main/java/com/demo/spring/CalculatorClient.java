package com.demo.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class CalculatorClient {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private LoadBalancerClient loadBalancer;

	@HystrixCommand(fallbackMethod = "defaultaddvalue")
	@GetMapping(path = "/do/add1")
	public String add() {

		HttpHeaders header = new HttpHeaders();
		HttpEntity req = new HttpEntity(header);

//		/*Direct server instance url you can get via ServiceInstant*/		
		ServiceInstance serviceInstance = loadBalancer.choose("calculator-service");	
		System.out.println(serviceInstance.getUri());
		System.out.println(serviceInstance.getUri().toString());
		
		
		String baseUrl ="http://calculator-service";
		ResponseEntity<String> result = restTemplate.exchange(baseUrl+"/add/1/1", HttpMethod.GET, req,
				String.class);

		return result.getBody();

	}
	private String defaultaddvalue() {
        return "Service is down return default value 2";
    }

}
