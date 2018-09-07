package com.demo.spring;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Employee", description = "REST API for Employee", tags = {"Employee"})
public class EmployeeController {

    @GetMapping(path = "/emp/find")
    @ApiOperation(value = "Get Employee", tags = {"Employee"})
    @ApiResponses(value = {
        @ApiResponse(code = 500, message = "Server error")
        ,
		         @ApiResponse(code = 404, message = "Service not found")
        ,
		        @ApiResponse(code = 200, message = "Successful retrieval",
                response = String.class, responseContainer = "String")})
    public String emp(@PathVariable("name") String name, HttpServletRequest request) {
        return "Welcome " + name;
    }

    @PostMapping(path = "/emp/save", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save Employee", tags = {"Employee"})
    public Map getJson() {
        Map map = new HashMap();
        map.put("city", "test");
        return map;
    }

}
