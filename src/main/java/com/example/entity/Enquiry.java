package com.example.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.enums.EnquiryStatus;

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
	private Boolean isDeleted;
	@Enumerated(EnumType.STRING)
	private EnquiryStatus enquiryStatus;
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate creationDate;
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDate updationDate;
	@OneToOne
	private Cibil cibilDetails;

}
