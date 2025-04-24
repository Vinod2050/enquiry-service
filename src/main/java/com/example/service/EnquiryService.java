package com.example.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.dto.EnquiryDTO;
import com.example.entity.Enquiry;
import com.example.enums.EnquiryStatus;

public interface EnquiryService {

	String saveEnquiry(EnquiryDTO dto);

    String deleteEnquriy(Integer enquiryId);

    EnquiryDTO getEnquiry(Integer enquiryId);

    Page<EnquiryDTO> getAllEnquiries(String firstName, EnquiryStatus enquiryStatus, int page, int size, String sortAttribute);

    List<Enquiry> getEnquiriesByStatus(EnquiryStatus enquiryStatus);

    String putEnquiry(Integer enquiryId, EnquiryDTO enquiryDTO);

	String updateStatus(Integer enquiryId, EnquiryStatus enquiryStatus);
	

}
