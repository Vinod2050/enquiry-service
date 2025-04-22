package com.example.controller;

import java.util.List;

import javax.validation.Valid;

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

	@Autowired
	private EnquiryService enquiryService;

	@PostMapping(value = "/saveEnquiry")
	public ResponseEntity<String> saveEnquiry(@Valid @RequestBody EnquiryDTO dto) {
		String savedEnquiry = enquiryService.saveEnquiry(dto);
		return new ResponseEntity<String>(savedEnquiry, HttpStatus.CREATED);
	}

	@GetMapping(value = "/getEnquiry/{enquiryID}")
	public ResponseEntity<EnquiryDTO> getEnquiryById(Integer enquiryID) {
		EnquiryDTO enquiry = enquiryService.getEnquiry(enquiryID);
		if (enquiry != null) {
			return new ResponseEntity<EnquiryDTO>(enquiry, HttpStatus.FOUND);
		}
		return new ResponseEntity<EnquiryDTO>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value = "/getEnquiries")
	public List<EnquiryDTO> getEnquiries(@RequestParam(required = false) String firstName,
			@RequestParam(required = false) String enquiryStatus, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "firstName") String sortBy) {
		List<EnquiryDTO> list = enquiryService.getAllEnquiries(firstName, enquiryStatus, page, size, sortBy).getContent();
		return list;
	}
	
	@GetMapping(value = "/enquiriesByStatus/{enquiryStatus}")
	public List<Enquiry> getEnquiriesByStatus(@PathVariable EnquiryStatus enquiryStatus) {
		return enquiryService.getEnquiriesByStatus(enquiryStatus);
	}

	@DeleteMapping(value = "/deleteEnquiry/{enquiryId}")
	public ResponseEntity<String> deleteById(@PathVariable Integer enquiryId) {
		String msg = enquiryService.deleteEnquriy(enquiryId);
		return new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);
	}

	@PutMapping(value = "/updateEnquiry/{enquiryId}")
	public ResponseEntity<String> putEnquiry(@PathVariable Integer enquiryId, @RequestBody EnquiryDTO enquiryDTO) {
		String updatedEnquiry = enquiryService.putEnquiry(enquiryId, enquiryDTO);
		return new ResponseEntity<String>(updatedEnquiry, HttpStatus.OK);
	}
	
	@PatchMapping(value = "/updateEnquiryStatus/{enquiryId}/{enquiryStatus}")
	public ResponseEntity<String> updateEnquiryStatus(@PathVariable Integer enquiryId, @PathVariable EnquiryStatus enquiryStatus) {
		String result = enquiryService.saveStatus(enquiryId, enquiryStatus);
		return new ResponseEntity<String>(result, HttpStatus.CREATED);
	}
}
