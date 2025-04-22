package com.example.service;

import com.example.dto.CibilDTO;

public interface CibilService {

	String generateCibil(Integer enquiryId);

	String updateCibilDetails(Integer enquiryId, CibilDTO cibilDto);

}
