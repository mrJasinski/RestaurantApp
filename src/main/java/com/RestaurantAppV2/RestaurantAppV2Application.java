package com.RestaurantAppV2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class RestaurantAppV2Application {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantAppV2Application.class, args);
	}


	@Bean
	Validator validator()
	{
		return new LocalValidatorFactoryBean();
	}

}
