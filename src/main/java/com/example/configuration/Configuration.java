package com.example.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@org.springframework.context.annotation.Configuration
public class Configuration {
	
	@Bean
	public OpenAPI enquiryOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Service API").version("1.0").description("API documentation").contact(
						new Contact().name("HomeLoan").email("vinodmache7@gmail.com").url("https://xyz/linkedin.com")));
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}

}
