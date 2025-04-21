package com.example.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.example.dto.EnquiryDTO;
import com.example.entity.Enquiry;

import com.example.enums.EnquiryStatus;


public interface EnquiryService {

	String saveEnquiry(EnquiryDTO dto);

	String saveStatus(Integer enquiryId, String status);

	String deleteEnquriy(Integer enquiryId);

	EnquiryDTO getEnquiry(Integer enquiryID);

	Page<EnquiryDTO> getAllEnquiries(String firstName, String email, int page, int size, String sortBy);

	List<Enquiry> getEnquiriesById(EnquiryStatus status);

}
