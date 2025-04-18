package com.example.entity;

import java.time.LocalDate;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enquiry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer enquiryId;
	private String firstName;
	private String lastName;
	private Integer age;
	private String email;
	private Long mobileNo;
	private String panCardNumber;
	//private Integer otp;
	@CreationTimestamp
	private LocalDate creationDate;
	@UpdateTimestamp
	private LocalDate updationDate;
	@OneToOne
	private Cibil cibilScore;
}
