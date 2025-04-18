package com.example.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cibil {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cibilId;
	private Integer cibilScore;
	private LocalDate cibilScoreDateTime;
	private String status;
	private String cibilRemark;
	@CreationTimestamp
	private LocalDate creationDate;
	@UpdateTimestamp
	private LocalDate updationDate;

}
