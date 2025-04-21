package com.example.service;

import com.example.dto.EnquiryDTO;

public interface EnquiryService {

	String saveEnquiry(EnquiryDTO dto);

	String saveStatus(Integer enquiryId, String status);

}
