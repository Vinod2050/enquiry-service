package com.example.service;

import com.example.dto.EnquiryDTO;
import com.example.entity.Enquiry;

public interface EnquiryService {

	String saveEnquiry(EnquiryDTO dto);

<<<<<<< Updated upstream
	String saveStatus(Integer enquiryId, String status);
=======
	String deleteEnquriy(Integer enquiryId);

	EnquiryDTO getEnquiry(Integer enquiryID);
>>>>>>> Stashed changes

}
