package com.tester.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@OpenAPIDefinition(info = @Info(title = "Api" , version = "2.0" , description = "api test"))
public class RestApiApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}

}
