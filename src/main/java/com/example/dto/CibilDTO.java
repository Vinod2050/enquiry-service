package com.example.dto;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
public class CibilDTO {
	private Integer cibilScore;
	@CreationTimestamp
	private LocalDate cibilScoreDateTime;
	private String status;
	private String cibilRemark;
}
