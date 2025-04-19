package com.example.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.EnquiryDTO;
import com.example.entity.Enquiry;
import com.example.repository.EnquiryRepository;
import com.example.service.EnquiryService;

@Service
public class EnquiryServiceImpl implements EnquiryService {
	@Autowired
	private EnquiryRepository enquiryRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public String saveEnquiry(EnquiryDTO dto) {
		try {
			System.out.println(dto);
			Enquiry enquiry = modelMapper.map(dto, Enquiry.class);

			System.out.println(enquiry);
			enquiry.setIsDeleted(false);
			//enquiry.setMobileNo(Long.parseLong(dto.getMobileNo()));
			enquiryRepository.save(enquiry);
			return "Enquiry Submited Successfully";
		} catch (NumberFormatException e) {
			return "Invalid Mobile Number Format";
		}
	}

}
