package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.EnquiryDTO;
import com.example.entity.Enquiry;
import com.example.enums.EnquiryStatus;
import com.example.service.EnquiryService;

@RestController
@RequestMapping("/api/enquiry")
@Validated
public class EnquiryController {
	
	private static final Logger logger=LoggerFactory.getLogger(EnquiryController.class);

	@Autowired
	private EnquiryService enquiryService;

	@PostMapping("/enquires")
	public ResponseEntity<String> saveEnquiry(@Valid @RequestBody EnquiryDTO dto) {
	    logger.info("Received request to save enquiry: ");

	    String savedEnquiry = enquiryService.saveEnquiry(dto);
	    logger.info("Enquiry saved successfully.");

	    return ResponseEntity.status(HttpStatus.CREATED).body(savedEnquiry);
	}


	@GetMapping(value = "/enquires/{enquiryID}")
	public ResponseEntity<EnquiryDTO> getEnquiryById(Integer enquiryID) {
		logger.info("fetching Enquiry with Id : "+enquiryID);
		EnquiryDTO enquiry = enquiryService.getEnquiry(enquiryID);
		if (enquiry != null) {
			logger.info("Enquiry Found");
			return new ResponseEntity<EnquiryDTO>(enquiry, HttpStatus.FOUND);
		}
		logger.warn("Enquiry with given ID not Found"+enquiryID);
		return new ResponseEntity<EnquiryDTO>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/enquiries")
	public ResponseEntity<List<EnquiryDTO>> getEnquiries(
	        @RequestParam(required = false) String firstName,
	        @RequestParam(required = false) EnquiryStatus enquiryStatus,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int size,
	        @RequestParam(defaultValue = "firstName,enquiryStatus") String sortBy) {

		logger.info("Fetching enquiries with filters");
	    List<EnquiryDTO> enquiryList = enquiryService
	            .getAllEnquiries(firstName, enquiryStatus, page, size, sortBy)
	            .getContent();
          
	    return ResponseEntity.ok(enquiryList);
	}
	
	
	@GetMapping(value = "/enquiries/{enquiryStatus}")
	public List<Enquiry> getEnquiriesByStatus(@PathVariable EnquiryStatus enquiryStatus) {
		return enquiryService.getEnquiriesByStatus(enquiryStatus);
	}

	@DeleteMapping(value = "/enquiries/{enquiryId}")
	public ResponseEntity<String> deleteById(@PathVariable Integer enquiryId) {
		logger.warn("Deleting  enquiry with Id : "+enquiryId);
		
		String msg = enquiryService.deleteEnquriy(enquiryId);
		return new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);
	}

	@PutMapping(value = "/enquiries{enquiryId}")
	public ResponseEntity<String> putEnquiry(@PathVariable Integer enquiryId, @RequestBody EnquiryDTO enquiryDTO) {
		logger.info("upadate request Received");
		String updatedEnquiry = enquiryService.putEnquiry(enquiryId, enquiryDTO);
		return new ResponseEntity<String>(updatedEnquiry, HttpStatus.OK);
	}
	
	@PatchMapping(value = "/enquiries/{enquiryId}/{enquiryStatus}")
	public ResponseEntity<String> updateEnquiryStatus(@PathVariable Integer enquiryId, @PathVariable EnquiryStatus enquiryStatus) {
		logger.info("request Received for Enquiry Status");
		String result = enquiryService.saveStatus(enquiryId, enquiryStatus);
		return new ResponseEntity<String>(result, HttpStatus.CREATED);
	}
}
