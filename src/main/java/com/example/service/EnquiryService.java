package com.example.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.dto.EnquiryDTO;
import com.example.entity.Enquiry;
import com.example.enums.EnquiryStatus;

public interface EnquiryService {

	String saveEnquiry(EnquiryDTO dto);

	String saveStatus(Integer enquiryId, EnquiryStatus enquiryStatus);

	String deleteEnquriy(Integer enquiryId);

	EnquiryDTO getEnquiry(Integer enquiryID);

	Page<EnquiryDTO> getAllEnquiries( String firstName,String enquiryStatus,int page, int size, String sortAttribute);

	List<Enquiry> getEnquiriesByStatus(EnquiryStatus enquiryStatus);

	String putEnquiry(Integer enquiryId, EnquiryDTO enquiryDTO);

	

}
