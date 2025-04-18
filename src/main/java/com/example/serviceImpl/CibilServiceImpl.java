package com.example.serviceImpl;


import org.springframework.stereotype.Service;

import com.example.repository.CibilRepository;
import com.example.service.CibilService;

@Service
public class CibilServiceImpl implements CibilService{
	private CibilRepository cibilRepository;

	public CibilServiceImpl(CibilRepository cibilRepository) {
		super();
		this.cibilRepository = cibilRepository;
	}
	

}
