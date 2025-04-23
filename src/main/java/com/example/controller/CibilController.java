package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;


import com.example.service.CibilService;

@RestController
public class CibilController {

	private static final Logger logger=LoggerFactory.getLogger(EnquiryController.class);
	@Autowired
	private CibilService cibilService;
	

	@PostMapping(value = "/generateCibil/{enquiryId}")
	public ResponseEntity<String> generateCibil(@PathVariable Integer enquiryId) {
		logger.info("cibil request Received");
		String savedCibil = cibilService.generateCibil(enquiryId);
		return new ResponseEntity<String>(savedCibil, HttpStatus.CREATED);
	}

	

}
