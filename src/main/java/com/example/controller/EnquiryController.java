package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.PatchMapping;

import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PostMapping("/save")
	public ResponseEntity<String> saveEnquiry(@Valid @RequestBody EnquiryDTO dto) {

		String result = enquiryService.saveEnquiry(dto);

		return new ResponseEntity<String>(result, HttpStatus.CREATED);

	}

	@GetMapping(value = "/get/{enquiryID}")
	public ResponseEntity<EnquiryDTO> getEnquiryById(Integer enquiryID) {
		EnquiryDTO enquiry = enquiryService.getEnquiry(enquiryID);
		if (enquiry != null) {
			return new ResponseEntity<EnquiryDTO>(enquiry, HttpStatus.FOUND);
		}

		return new ResponseEntity<EnquiryDTO>(HttpStatus.NOT_FOUND);

	}

	@DeleteMapping(value = "/delete/{enquiryId}")
	public ResponseEntity<String> deleteById(@PathVariable Integer enquiryId) {
		String msg = enquiryService.deleteEnquriy(enquiryId);
		return new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);
  }
	
	@PatchMapping("/update-enquiry-status/{enquiryId}/{status}")
	public ResponseEntity<String> updateEnquiryStatus(@PathVariable Integer enquiryId, @PathVariable String status ){
		String result = enquiryService.saveStatus(enquiryId,status);
		return new ResponseEntity <String>(result,HttpStatus.CREATED);
		
	}
}


	@GetMapping(value = "/enquiries")
	public List<EnquiryDTO> getEnquiries(@RequestParam(required = false) String firstName,
			@RequestParam(required = false) String email, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		List<EnquiryDTO> list = enquiryService.getAllEnquiries(firstName, email, page, size, sortBy).getContent();
		return list;
	}

	@GetMapping(value = "/expose-enquiries-by-status/{status}")
	public  List<Enquiry>getEnquiriesByStatus(@PathVariable EnquiryStatus status)
	{
		return enquiryService.getEnquiriesById(status);
	}

}

