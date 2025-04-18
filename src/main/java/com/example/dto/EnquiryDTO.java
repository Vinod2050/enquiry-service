package com.example.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class EnquiryDTO {

	@NotBlank(message = "First name is required")
	private String firstName;
	@NotBlank(message = "Last name is required")
	private String lastName;

	@NotNull(message = "Age is required")
	@Min(value = 18, message = "Age must be at least 18")
	@Max(value = 100, message = "Age must be less than or equal to 100")
	private Integer age;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;
	
	@NotNull(message = "Mobile number must not be null")
	@Digits(integer = 10, fraction = 0, message = "Mobile number must be 10 digits")
	private Long mobileNo;
	
	@NotBlank(message = "PAN card number is required")
	@Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "Invalid PAN card format")
	private String panCardNumber;

}
