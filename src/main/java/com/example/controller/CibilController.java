package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CibilDTO;
import com.example.service.CibilService;

@RestController
public class CibilController {

	@Autowired
	private CibilService cibilService;

	@PostMapping(value = "/generateCibil/{enquiryId}")
	public ResponseEntity<String> generateCibil(@PathVariable Integer enquiryId) {
		String savedCibil = cibilService.generateCibil(enquiryId);
		return new ResponseEntity<String>(savedCibil, HttpStatus.CREATED);
	}

	@PatchMapping("/updateEnquiryCibil/{enquiryId}")
	public ResponseEntity<String> updateCibilDetails(@PathVariable Integer enquiryId, @RequestBody CibilDTO cibilDto) {
		String updatedCibilDetails = cibilService.updateCibilDetails(enquiryId, cibilDto);
		return new ResponseEntity<String>(updatedCibilDetails,HttpStatus.OK);
	}

}
