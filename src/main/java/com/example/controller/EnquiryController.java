package com.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.EnquiryDTO;
import com.example.service.EnquiryService;
import org.springframework.web.bind.annotation.RequestBody; 



@RestController
@RequestMapping("/api/enquiry")
@Validated
public class EnquiryController {

	@Autowired
	private EnquiryService enquiryService;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveEnquiry(@Valid @RequestBody EnquiryDTO dto){
			
		String result=enquiryService.saveEnquiry(dto);
		   
	     return new ResponseEntity <String>(result,HttpStatus.CREATED);
	}
	
	
	@PatchMapping("/update-enquiry-status/{enquiryId}/{status}")
	public ResponseEntity<String> updateEnquiryStatus(@PathVariable Integer enquiryId, @PathVariable String status ){
		String result = enquiryService.saveStatus(enquiryId,status);
		return new ResponseEntity <String>(result,HttpStatus.CREATED);
		
	}
}
